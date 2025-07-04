package com.giweb.supply.business.segundotalleracm.service.impl;

import com.giweb.supply.business.segundotalleracm.exception.NotFoundException;
import com.giweb.supply.business.segundotalleracm.model.Pago;
import com.giweb.supply.business.segundotalleracm.model.Reserva;
import com.giweb.supply.business.segundotalleracm.model.dto.response.PagoDTO;
import com.giweb.supply.business.segundotalleracm.model.dto.request.PagoRequestDTO;
import com.giweb.supply.business.segundotalleracm.repository.PagoRepository;
import com.giweb.supply.business.segundotalleracm.repository.ReservaRepository;
import com.giweb.supply.business.segundotalleracm.service.IPagoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PagoServiceImpl implements IPagoService {

    private final PagoRepository pagoRepository;
    private final ReservaRepository reservaRepository;

    @Override
    public List<PagoDTO> getAllPagos() {
        return pagoRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public PagoDTO getPagoById(Integer id) {
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("El Pago con id " + id + " no fue encontrado."));
        return convertToDto(pago);
    }

    @Override
    public PagoDTO savePago(PagoRequestDTO pagoDTO) {
        Pago pago = convertToEntity(pagoDTO);
        return convertToDto(pagoRepository.save(pago));
    }

    @Override
    public PagoDTO updatePago(Integer id, PagoRequestDTO pagoDetails) {
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("El Pago con id " + id + " no fue encontrado."));

        pago.setFecha(pagoDetails.getFecha());
        pago.setMetodoPago(pagoDetails.getMetodoPago());
        pago.setPagoTotal(pagoDetails.getPagoTotal());

        if (pagoDetails.getIdReserva() != null) {
            Reserva reserva = reservaRepository.findById(pagoDetails.getIdReserva())
                    .orElseThrow(() -> new NotFoundException("La Reserva con id " + pagoDetails.getIdReserva() + " no fue encontrada."));
            pago.setReserva(reserva);
        }

        return convertToDto(pagoRepository.save(pago));
    }

    @Override
    public void deletePago(Integer id) {
        if (!pagoRepository.existsById(id)) {
            throw new NotFoundException("El Pago con id " + id + " no fue encontrado.");
        }
        pagoRepository.deleteById(id);
    }

    private PagoDTO convertToDto(Pago pago) {
        PagoDTO dto = new PagoDTO();
        dto.setIdPago(pago.getIdPago());
        dto.setFecha(pago.getFecha());
        if (pago.getReserva() != null) {
            dto.setIdReserva(pago.getReserva().getIdReserva());
        }
        dto.setMetodoPago(pago.getMetodoPago());
        dto.setPagoTotal(pago.getPagoTotal());
        return dto;
    }

    private Pago convertToEntity(PagoRequestDTO dto) {
        Pago pago = new Pago();
        pago.setFecha(dto.getFecha());
        pago.setMetodoPago(dto.getMetodoPago());
        pago.setPagoTotal(dto.getPagoTotal());

        if (dto.getIdReserva() != null) {
            Reserva reserva = reservaRepository.findById(dto.getIdReserva())
                    .orElseThrow(() -> new NotFoundException("La Reserva con id " + dto.getIdReserva() + " no fue encontrada."));
            pago.setReserva(reserva);
        }

        return pago;
    }
}
