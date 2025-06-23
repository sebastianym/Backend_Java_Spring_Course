package com.giweb.supply.business.segundotalleracm.service.impl;

import com.giweb.supply.business.segundotalleracm.exception.NotFoundException;
import com.giweb.supply.business.segundotalleracm.model.Hotel;
import com.giweb.supply.business.segundotalleracm.model.dto.response.HotelDTO;
import com.giweb.supply.business.segundotalleracm.model.dto.request.HotelRequestDTO;
import com.giweb.supply.business.segundotalleracm.repository.HotelRepository;
import com.giweb.supply.business.segundotalleracm.service.IHotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements IHotelService {

    private final HotelRepository hotelRepository;

    @Override
    public List<HotelDTO> getAllHoteles() {
        return hotelRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public HotelDTO getHotelById(Integer id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("El Hotel con id " + id + " no fue encontrado."));
        return convertToDto(hotel);
    }

    @Override
    public HotelDTO saveHotel(HotelRequestDTO hotelDTO) {
        Hotel hotel = convertToEntity(hotelDTO);
        return convertToDto(hotelRepository.save(hotel));
    }

    @Override
    public HotelDTO updateHotel(Integer id, HotelRequestDTO hotelDetails) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("El Hotel con id " + id + " no fue encontrado."));

        hotel.setNombre(hotelDetails.getNombre());
        hotel.setCiudad(hotelDetails.getCiudad());
        hotel.setTelefono(hotelDetails.getTelefono());
        hotel.setCorreo(hotelDetails.getCorreo());
        hotel.setDireccion(hotelDetails.getDireccion());

        return convertToDto(hotelRepository.save(hotel));
    }

    @Override
    public void deleteHotel(Integer id) {
        if (!hotelRepository.existsById(id)) {
            throw new NotFoundException("El Hotel con id " + id + " no fue encontrado.");
        }
        hotelRepository.deleteById(id);
    }

    private HotelDTO convertToDto(Hotel hotel) {
        HotelDTO dto = new HotelDTO();
        dto.setIdHotel(hotel.getIdHotel());
        dto.setNombre(hotel.getNombre());
        dto.setCiudad(hotel.getCiudad());
        dto.setTelefono(hotel.getTelefono());
        dto.setCorreo(hotel.getCorreo());
        dto.setDireccion(hotel.getDireccion());
        return dto;
    }

    private Hotel convertToEntity(HotelRequestDTO dto) {
        Hotel hotel = new Hotel();
        hotel.setNombre(dto.getNombre());
        hotel.setCiudad(dto.getCiudad());
        hotel.setTelefono(dto.getTelefono());
        hotel.setCorreo(dto.getCorreo());
        hotel.setDireccion(dto.getDireccion());
        return hotel;
    }
}
