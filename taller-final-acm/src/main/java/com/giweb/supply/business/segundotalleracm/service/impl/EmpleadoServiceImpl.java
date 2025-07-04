package com.giweb.supply.business.segundotalleracm.service.impl;

import com.giweb.supply.business.segundotalleracm.exception.NotFoundException;
import com.giweb.supply.business.segundotalleracm.model.Empleado;
import com.giweb.supply.business.segundotalleracm.model.dto.response.EmpleadoDTO;
import com.giweb.supply.business.segundotalleracm.model.dto.request.EmpleadoRequestDTO;
import com.giweb.supply.business.segundotalleracm.repository.EmpleadoRepository;
import com.giweb.supply.business.segundotalleracm.service.IEmpleadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmpleadoServiceImpl implements IEmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    @Override
    public List<EmpleadoDTO> getAllEmpleados() {
        return empleadoRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmpleadoDTO getEmpleadoById(Integer id) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("El Empleado con id " + id + " no fue encontrado."));
        return convertToDto(empleado);
    }

    @Override
    public EmpleadoDTO saveEmpleado(EmpleadoRequestDTO empleadoDTO) {
        Empleado empleado = convertToEntity(empleadoDTO);
        return convertToDto(empleadoRepository.save(empleado));
    }

    @Override
    public EmpleadoDTO updateEmpleado(Integer id, EmpleadoRequestDTO empleadoDetails) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("El Empleado con id " + id + " no fue encontrado."));

        empleado.setCorreo(empleadoDetails.getCorreo());
        empleado.setTelefono(empleadoDetails.getTelefono());
        empleado.setPrimerNombre(empleadoDetails.getPrimerNombre());
        empleado.setSegundoNombre(empleadoDetails.getSegundoNombre());
        empleado.setPrimerApellido(empleadoDetails.getPrimerApellido());
        empleado.setSegundoApellido(empleadoDetails.getSegundoApellido());

        return convertToDto(empleadoRepository.save(empleado));
    }

    @Override
    public void deleteEmpleado(Integer id) {
        if (!empleadoRepository.existsById(id)) {
            throw new NotFoundException("El Empleado con id " + id + " no fue encontrado.");
        }
        empleadoRepository.deleteById(id);
    }

    private EmpleadoDTO convertToDto(Empleado empleado) {
        EmpleadoDTO dto = new EmpleadoDTO();
        dto.setIdEmpleado(empleado.getIdEmpleado());
        dto.setCorreo(empleado.getCorreo());
        dto.setTelefono(empleado.getTelefono());
        dto.setPrimerNombre(empleado.getPrimerNombre());
        dto.setSegundoNombre(empleado.getSegundoNombre());
        dto.setPrimerApellido(empleado.getPrimerApellido());
        dto.setSegundoApellido(empleado.getSegundoApellido());
        return dto;
    }

    private Empleado convertToEntity(EmpleadoRequestDTO dto) {
        Empleado empleado = new Empleado();
        empleado.setCorreo(dto.getCorreo());
        empleado.setTelefono(dto.getTelefono());
        empleado.setPrimerNombre(dto.getPrimerNombre());
        empleado.setSegundoNombre(dto.getSegundoNombre());
        empleado.setPrimerApellido(dto.getPrimerApellido());
        empleado.setSegundoApellido(dto.getSegundoApellido());
        return empleado;
    }
}
