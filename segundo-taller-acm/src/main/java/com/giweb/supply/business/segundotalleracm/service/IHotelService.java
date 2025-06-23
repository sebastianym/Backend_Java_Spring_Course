package com.giweb.supply.business.segundotalleracm.service;

import com.giweb.supply.business.segundotalleracm.model.dto.request.HotelRequestDTO;
import com.giweb.supply.business.segundotalleracm.model.dto.response.HotelDTO;

import java.util.List;

public interface IHotelService {

    List<HotelDTO> getAllHoteles();

    HotelDTO getHotelById(Integer id);

    HotelDTO saveHotel(HotelRequestDTO hotelDTO);

    HotelDTO updateHotel(Integer id, HotelRequestDTO hotelDetails);

    void deleteHotel(Integer id);
}
