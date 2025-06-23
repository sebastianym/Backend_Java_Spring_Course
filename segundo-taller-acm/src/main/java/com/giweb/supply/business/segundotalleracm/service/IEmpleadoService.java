package com.giweb.supply.business.segundotalleracm.service;

import com.giweb.supply.business.segundotalleracm.model.dto.request.EmpleadoRequestDTO;
import com.giweb.supply.business.segundotalleracm.model.dto.response.EmpleadoDTO;

import java.util.List;

public interface IEmpleadoService {

    List<EmpleadoDTO> getAllEmpleados();

    EmpleadoDTO getEmpleadoById(Integer id);

    EmpleadoDTO saveEmpleado(EmpleadoRequestDTO empleadoDTO);

    EmpleadoDTO updateEmpleado(Integer id, EmpleadoRequestDTO empleadoDetails);

    void deleteEmpleado(Integer id);
}
