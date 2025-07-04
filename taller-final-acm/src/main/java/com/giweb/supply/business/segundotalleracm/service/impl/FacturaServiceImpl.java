package com.giweb.supply.business.segundotalleracm.service.impl;

import com.giweb.supply.business.segundotalleracm.exception.NotFoundException;
import com.giweb.supply.business.segundotalleracm.model.Factura;
import com.giweb.supply.business.segundotalleracm.model.Pago;
import com.giweb.supply.business.segundotalleracm.model.Reserva;
import com.giweb.supply.business.segundotalleracm.model.dto.response.FacturaDTO;
import com.giweb.supply.business.segundotalleracm.model.dto.request.FacturaRequestDTO;
import com.giweb.supply.business.segundotalleracm.repository.FacturaRepository;
import com.giweb.supply.business.segundotalleracm.repository.PagoRepository;
import com.giweb.supply.business.segundotalleracm.repository.ReservaRepository;
import com.giweb.supply.business.segundotalleracm.service.IFacturaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FacturaServiceImpl implements IFacturaService {

    private final FacturaRepository facturaRepository;
    private final ReservaRepository reservaRepository;
    private final PagoRepository pagoRepository;

    @Override
    public List<FacturaDTO> getAllFacturas() {
        return facturaRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public FacturaDTO getFacturaById(Integer id) {
        Factura factura = facturaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("La Factura con id " + id + " no fue encontrada."));
        return convertToDto(factura);
    }

    @Override
    public FacturaDTO saveFactura(FacturaRequestDTO facturaDTO) {
        Factura factura = convertToEntity(facturaDTO);
        return convertToDto(facturaRepository.save(factura));
    }

    @Override
    public FacturaDTO updateFactura(Integer id, FacturaRequestDTO facturaDetails) {
        Factura factura = facturaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("La Factura con id " + id + " no fue encontrada."));

        factura.setFechaEmision(facturaDetails.getFechaEmision());
        factura.setValorTotal(facturaDetails.getValorTotal());

        if (facturaDetails.getIdReserva() != null) {
            Reserva reserva = reservaRepository.findById(facturaDetails.getIdReserva())
                    .orElseThrow(() -> new NotFoundException("La Reserva con id " + facturaDetails.getIdReserva() + " no fue encontrada."));
            factura.setReserva(reserva);
        }

        if (facturaDetails.getIdPago() != null) {
            Pago pago = pagoRepository.findById(facturaDetails.getIdPago())
                    .orElseThrow(() -> new NotFoundException("El Pago con id " + facturaDetails.getIdPago() + " no fue encontrado."));
            factura.setPago(pago);
        }

        return convertToDto(facturaRepository.save(factura));
    }

    @Override
    public void deleteFactura(Integer id) {
        if (!facturaRepository.existsById(id)) {
            throw new NotFoundException("La Factura con id " + id + " no fue encontrada.");
        }
        facturaRepository.deleteById(id);
    }

    private FacturaDTO convertToDto(Factura factura) {
        FacturaDTO dto = new FacturaDTO();
        dto.setIdFactura(factura.getIdFactura());
        if (factura.getReserva() != null) {
            dto.setIdReserva(factura.getReserva().getIdReserva());
        }
        if (factura.getPago() != null) {
            dto.setIdPago(factura.getPago().getIdPago());
        }
        dto.setFechaEmision(factura.getFechaEmision());
        dto.setValorTotal(factura.getValorTotal());
        return dto;
    }

    private Factura convertToEntity(FacturaRequestDTO dto) {
        Factura factura = new Factura();

        if (dto.getIdReserva() != null) {
            Reserva reserva = reservaRepository.findById(dto.getIdReserva())
                    .orElseThrow(() -> new NotFoundException("La Reserva con id " + dto.getIdReserva() + " no fue encontrada."));
            factura.setReserva(reserva);
        }

        if (dto.getIdPago() != null) {
            Pago pago = pagoRepository.findById(dto.getIdPago())
                    .orElseThrow(() -> new NotFoundException("El Pago con id " + dto.getIdPago() + " no fue encontrado."));
            factura.setPago(pago);
        }

        factura.setFechaEmision(dto.getFechaEmision());
        factura.setValorTotal(dto.getValorTotal());
        return factura;
    }
}
