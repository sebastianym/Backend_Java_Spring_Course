package com.giweb.supply.business.segundotalleracm.service;

import com.giweb.supply.business.segundotalleracm.model.dto.request.TipoHabitacionRequestDTO;
import com.giweb.supply.business.segundotalleracm.model.dto.response.TipoHabitacionDTO;

import java.util.List;

public interface ITipoHabitacionService {

    List<TipoHabitacionDTO> getAllTiposHabitacion();

    TipoHabitacionDTO getTipoHabitacionById(Integer id);

    TipoHabitacionDTO saveTipoHabitacion(TipoHabitacionRequestDTO tipoHabitacionDTO);

    TipoHabitacionDTO updateTipoHabitacion(Integer id, TipoHabitacionRequestDTO tipoHabitacionDetails);

    void deleteTipoHabitacion(Integer id);
}
