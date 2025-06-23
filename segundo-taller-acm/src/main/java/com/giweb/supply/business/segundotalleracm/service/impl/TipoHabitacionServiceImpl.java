package com.giweb.supply.business.segundotalleracm.service.impl;

import com.giweb.supply.business.segundotalleracm.exception.NotFoundException;
import com.giweb.supply.business.segundotalleracm.model.Hotel;
import com.giweb.supply.business.segundotalleracm.model.TipoHabitacion;
import com.giweb.supply.business.segundotalleracm.model.dto.response.TipoHabitacionDTO;
import com.giweb.supply.business.segundotalleracm.model.dto.request.TipoHabitacionRequestDTO;
import com.giweb.supply.business.segundotalleracm.repository.HotelRepository;
import com.giweb.supply.business.segundotalleracm.repository.TipoHabitacionRepository;
import com.giweb.supply.business.segundotalleracm.service.ITipoHabitacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TipoHabitacionServiceImpl implements ITipoHabitacionService {

    private final TipoHabitacionRepository tipoHabitacionRepository;
    private final HotelRepository hotelRepository;

    @Override
    public List<TipoHabitacionDTO> getAllTiposHabitacion() {
        return tipoHabitacionRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public TipoHabitacionDTO getTipoHabitacionById(Integer id) {
        TipoHabitacion tipoHabitacion = tipoHabitacionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("El Tipo de Habitación con id " + id + " no fue encontrado."));
        return convertToDto(tipoHabitacion);
    }

    @Override
    public TipoHabitacionDTO saveTipoHabitacion(TipoHabitacionRequestDTO tipoHabitacionDTO) {
        TipoHabitacion tipoHabitacion = convertToEntity(tipoHabitacionDTO);
        return convertToDto(tipoHabitacionRepository.save(tipoHabitacion));
    }

    @Override
    public TipoHabitacionDTO updateTipoHabitacion(Integer id, TipoHabitacionRequestDTO tipoHabitacionDetails) {
        TipoHabitacion tipoHabitacion = tipoHabitacionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("El Tipo de Habitación con id " + id + " no fue encontrado."));

        if (tipoHabitacionDetails.getIdHotel() != null) {
            Hotel hotel = hotelRepository.findById(tipoHabitacionDetails.getIdHotel())
                    .orElseThrow(() -> new NotFoundException("El Hotel con id " + tipoHabitacionDetails.getIdHotel() + " no fue encontrado."));
            tipoHabitacion.setHotel(hotel);
        }

        tipoHabitacion.setNombre(tipoHabitacionDetails.getNombre());
        tipoHabitacion.setCantidad(tipoHabitacionDetails.getCantidad());

        return convertToDto(tipoHabitacionRepository.save(tipoHabitacion));
    }

    @Override
    public void deleteTipoHabitacion(Integer id) {
        if (!tipoHabitacionRepository.existsById(id)) {
            throw new NotFoundException("El Tipo de Habitación con id " + id + " no fue encontrado.");
        }
        tipoHabitacionRepository.deleteById(id);
    }

    private TipoHabitacionDTO convertToDto(TipoHabitacion tipoHabitacion) {
        TipoHabitacionDTO dto = new TipoHabitacionDTO();
        dto.setIdTipoHabitacion(tipoHabitacion.getIdTipoHabitacion());
        if (tipoHabitacion.getHotel() != null) {
            dto.setIdHotel(tipoHabitacion.getHotel().getIdHotel());
        }
        dto.setNombre(tipoHabitacion.getNombre());
        dto.setCantidad(tipoHabitacion.getCantidad());
        return dto;
    }

    private TipoHabitacion convertToEntity(TipoHabitacionRequestDTO dto) {
        TipoHabitacion tipoHabitacion = new TipoHabitacion();

        if (dto.getIdHotel() != null) {
            Hotel hotel = hotelRepository.findById(dto.getIdHotel())
                    .orElseThrow(() -> new NotFoundException("El Hotel con id " + dto.getIdHotel() + " no fue encontrado."));
            tipoHabitacion.setHotel(hotel);
        }

        tipoHabitacion.setNombre(dto.getNombre());
        tipoHabitacion.setCantidad(dto.getCantidad());
        return tipoHabitacion;
    }
}
