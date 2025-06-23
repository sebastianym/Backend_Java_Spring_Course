package com.giweb.supply.business.segundotalleracm.service;

import com.giweb.supply.business.segundotalleracm.model.dto.request.HabitacionRequestDTO;
import com.giweb.supply.business.segundotalleracm.model.dto.response.HabitacionDTO;

import java.util.List;

public interface IHabitacionService {

    List<HabitacionDTO> getAllHabitaciones();

    HabitacionDTO getHabitacionById(Integer id);

    HabitacionDTO saveHabitacion(HabitacionRequestDTO habitacionDTO);

    HabitacionDTO updateHabitacion(Integer id, HabitacionRequestDTO habitacionDetails);

    void deleteHabitacion(Integer id);
}
