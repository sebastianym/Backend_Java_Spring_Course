package com.giweb.supply.business.segundotalleracm.service.impl;

import com.giweb.supply.business.segundotalleracm.exception.NotFoundException;
import com.giweb.supply.business.segundotalleracm.model.Cliente;
import com.giweb.supply.business.segundotalleracm.model.Habitacion;
import com.giweb.supply.business.segundotalleracm.model.Reserva;
import com.giweb.supply.business.segundotalleracm.model.dto.response.ReservaDTO;
import com.giweb.supply.business.segundotalleracm.model.dto.request.ReservaRequestDTO;
import com.giweb.supply.business.segundotalleracm.repository.ClienteRepository;
import com.giweb.supply.business.segundotalleracm.repository.HabitacionRepository;
import com.giweb.supply.business.segundotalleracm.repository.ReservaRepository;
import com.giweb.supply.business.segundotalleracm.service.IReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservaServiceImpl implements IReservaService {

    private final ReservaRepository reservaRepository;
    private final HabitacionRepository habitacionRepository;
    private final ClienteRepository clienteRepository;

    @Override
    public List<ReservaDTO> getAllReservas() {
        return reservaRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ReservaDTO getReservaById(Integer id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("La Reserva con id " + id + " no fue encontrada."));
        return convertToDto(reserva);
    }

    @Override
    public ReservaDTO saveReserva(ReservaRequestDTO reservaDTO) {
        Reserva reserva = convertToEntity(reservaDTO);
        reserva.setFechaReserva(new Timestamp(System.currentTimeMillis()));
        return convertToDto(reservaRepository.save(reserva));
    }

    @Override
    public ReservaDTO updateReserva(Integer id, ReservaRequestDTO reservaDetails) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("La Reserva con id " + id + " no fue encontrada."));

        reserva.setFechaInicio(reservaDetails.getFechaInicio());
        reserva.setFechaFinal(reservaDetails.getFechaFinal());
        reserva.setCantidadDias(reservaDetails.getCantidadDias());
        reserva.setEstado(reservaDetails.getEstado());

        if (reservaDetails.getIdHabitacion() != null) {
            Habitacion habitacion = habitacionRepository.findById(reservaDetails.getIdHabitacion())
                    .orElseThrow(() -> new NotFoundException("La Habitación con id " + reservaDetails.getIdHabitacion() + " no fue encontrada."));
            reserva.setHabitacion(habitacion);
        }

        if (reservaDetails.getIdCliente() != null) {
            Cliente cliente = clienteRepository.findById(reservaDetails.getIdCliente())
                    .orElseThrow(() -> new NotFoundException("El Cliente con id " + reservaDetails.getIdCliente() + " no fue encontrado."));
            reserva.setCliente(cliente);
        }

        return convertToDto(reservaRepository.save(reserva));
    }

    @Override
    public void deleteReserva(Integer id) {
        if (!reservaRepository.existsById(id)) {
            throw new NotFoundException("La Reserva con id " + id + " no fue encontrada.");
        }
        reservaRepository.deleteById(id);
    }

    private ReservaDTO convertToDto(Reserva reserva) {
        ReservaDTO dto = new ReservaDTO();
        dto.setIdReserva(reserva.getIdReserva());
        dto.setFechaInicio(reserva.getFechaInicio());
        dto.setFechaFinal(reserva.getFechaFinal());
        dto.setCantidadDias(reserva.getCantidadDias());
        dto.setEstado(reserva.getEstado());
        dto.setFechaReserva(reserva.getFechaReserva());
        if (reserva.getHabitacion() != null) {
            dto.setIdHabitacion(reserva.getHabitacion().getIdHabitacion());
        }
        if (reserva.getCliente() != null) {
            dto.setIdCliente(reserva.getCliente().getIdCliente());
        }
        return dto;
    }

    private Reserva convertToEntity(ReservaRequestDTO dto) {
        Reserva reserva = new Reserva();

        if (dto.getIdHabitacion() != null) {
            Habitacion habitacion = habitacionRepository.findById(dto.getIdHabitacion())
                    .orElseThrow(() -> new NotFoundException("La Habitación con id " + dto.getIdHabitacion() + " no fue encontrada."));
            reserva.setHabitacion(habitacion);
        }

        if (dto.getIdCliente() != null) {
            Cliente cliente = clienteRepository.findById(dto.getIdCliente())
                    .orElseThrow(() -> new NotFoundException("El Cliente con id " + dto.getIdCliente() + " no fue encontrado."));
            reserva.setCliente(cliente);
        }

        reserva.setFechaInicio(dto.getFechaInicio());
        reserva.setFechaFinal(dto.getFechaFinal());
        reserva.setCantidadDias(dto.getCantidadDias());
        reserva.setEstado(dto.getEstado());

        return reserva;
    }
}
