package com.giweb.supply.business.segundotalleracm.service.impl;

import com.giweb.supply.business.segundotalleracm.exception.NotFoundException;
import com.giweb.supply.business.segundotalleracm.model.Hotel;
import com.giweb.supply.business.segundotalleracm.model.dto.response.HotelDTO;
import com.giweb.supply.business.segundotalleracm.model.dto.request.HotelRequestDTO;
import com.giweb.supply.business.segundotalleracm.repository.HotelRepository;
import com.giweb.supply.business.segundotalleracm.service.IHotelService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements IHotelService {

    private final HotelRepository hotelRepository;

    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

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
        HotelDTO hotelDTO = new HotelDTO();
        hotelDTO.setIdHotel(hotel.getIdHotel());
        hotelDTO.setNombre(hotel.getNombre());
        hotelDTO.setCiudad(hotel.getCiudad());
        hotelDTO.setTelefono(hotel.getTelefono());
        hotelDTO.setCorreo(hotel.getCorreo());
        hotelDTO.setDireccion(hotel.getDireccion());
        return hotelDTO;
    }

    private Hotel convertToEntity(HotelRequestDTO hotelDTO) {
        Hotel hotel = new Hotel();
        hotel.setNombre(hotelDTO.getNombre());
        hotel.setCiudad(hotelDTO.getCiudad());
        hotel.setTelefono(hotelDTO.getTelefono());
        hotel.setCorreo(hotelDTO.getCorreo());
        hotel.setDireccion(hotelDTO.getDireccion());
        return hotel;
    }
}
