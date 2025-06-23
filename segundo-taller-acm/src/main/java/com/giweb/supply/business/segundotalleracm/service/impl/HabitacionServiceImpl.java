package com.giweb.supply.business.segundotalleracm.service.impl;

import com.giweb.supply.business.segundotalleracm.exception.NotFoundException;
import com.giweb.supply.business.segundotalleracm.model.Habitacion;
import com.giweb.supply.business.segundotalleracm.model.Hotel;
import com.giweb.supply.business.segundotalleracm.model.TipoHabitacion;
import com.giweb.supply.business.segundotalleracm.model.dto.response.HabitacionDTO;
import com.giweb.supply.business.segundotalleracm.model.dto.request.HabitacionRequestDTO;
import com.giweb.supply.business.segundotalleracm.repository.HabitacionRepository;
import com.giweb.supply.business.segundotalleracm.repository.HotelRepository;
import com.giweb.supply.business.segundotalleracm.repository.TipoHabitacionRepository;
import com.giweb.supply.business.segundotalleracm.service.IHabitacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HabitacionServiceImpl implements IHabitacionService {

    private final HabitacionRepository habitacionRepository;
    private final TipoHabitacionRepository tipoHabitacionRepository;
    private final HotelRepository hotelRepository;

    @Override
    public List<HabitacionDTO> getAllHabitaciones() {
        return habitacionRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public HabitacionDTO getHabitacionById(Integer id) {
        Habitacion habitacion = habitacionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("La Habitación con id " + id + " no fue encontrada."));
        return convertToDto(habitacion);
    }

    @Override
    public HabitacionDTO saveHabitacion(HabitacionRequestDTO habitacionDTO) {
        Habitacion habitacion = convertToEntity(habitacionDTO);
        return convertToDto(habitacionRepository.save(habitacion));
    }

    @Override
    public HabitacionDTO updateHabitacion(Integer id, HabitacionRequestDTO habitacionDetails) {
        Habitacion habitacion = habitacionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("La Habitación con id " + id + " no fue encontrada."));

        habitacion.setNumeroHabitacion(habitacionDetails.getNumeroHabitacion());
        habitacion.setPrecioDia(habitacionDetails.getPrecioDia());
        habitacion.setDisponible(habitacionDetails.getDisponible());

        if (habitacionDetails.getIdTipoHabitacion() != null) {
            TipoHabitacion tipoHabitacion = tipoHabitacionRepository.findById(habitacionDetails.getIdTipoHabitacion())
                    .orElseThrow(() -> new NotFoundException("El Tipo de Habitación con id " + habitacionDetails.getIdTipoHabitacion() + " no fue encontrado."));
            habitacion.setTipoHabitacion(tipoHabitacion);
        }

        if (habitacionDetails.getIdHotel() != null) {
            Hotel hotel = hotelRepository.findById(habitacionDetails.getIdHotel())
                    .orElseThrow(() -> new NotFoundException("El Hotel con id " + habitacionDetails.getIdHotel() + " no fue encontrado."));
            habitacion.setHotel(hotel);
        }

        return convertToDto(habitacionRepository.save(habitacion));
    }

    @Override
    public void deleteHabitacion(Integer id) {
        if (!habitacionRepository.existsById(id)) {
            throw new NotFoundException("La Habitación con id " + id + " no fue encontrada.");
        }
        habitacionRepository.deleteById(id);
    }

    private HabitacionDTO convertToDto(Habitacion habitacion) {
        HabitacionDTO dto = new HabitacionDTO();
        dto.setIdHabitacion(habitacion.getIdHabitacion());
        dto.setNumeroHabitacion(habitacion.getNumeroHabitacion());
        if (habitacion.getTipoHabitacion() != null) {
            dto.setIdTipoHabitacion(habitacion.getTipoHabitacion().getIdTipoHabitacion());
        }
        dto.setPrecioDia(habitacion.getPrecioDia());
        dto.setDisponible(habitacion.getDisponible());
        if (habitacion.getHotel() != null) {
            dto.setIdHotel(habitacion.getHotel().getIdHotel());
        }
        return dto;
    }

    private Habitacion convertToEntity(HabitacionRequestDTO dto) {
        Habitacion habitacion = new Habitacion();
        habitacion.setNumeroHabitacion(dto.getNumeroHabitacion());
        habitacion.setPrecioDia(dto.getPrecioDia());
        habitacion.setDisponible(dto.getDisponible());

        if (dto.getIdTipoHabitacion() != null) {
            TipoHabitacion tipoHabitacion = tipoHabitacionRepository.findById(dto.getIdTipoHabitacion())
                    .orElseThrow(() -> new NotFoundException("El Tipo de Habitación con id " + dto.getIdTipoHabitacion() + " no fue encontrado."));
            habitacion.setTipoHabitacion(tipoHabitacion);
        }

        if (dto.getIdHotel() != null) {
            Hotel hotel = hotelRepository.findById(dto.getIdHotel())
                    .orElseThrow(() -> new NotFoundException("El Hotel con id " + dto.getIdHotel() + " no fue encontrado."));
            habitacion.setHotel(hotel);
        }

        return habitacion;
    }
}
