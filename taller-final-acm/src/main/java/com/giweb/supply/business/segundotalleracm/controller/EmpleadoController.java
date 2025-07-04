package com.giweb.supply.business.segundotalleracm.controller;

import com.giweb.supply.business.segundotalleracm.model.dto.response.EmpleadoDTO;
import com.giweb.supply.business.segundotalleracm.model.dto.request.EmpleadoRequestDTO;
import com.giweb.supply.business.segundotalleracm.service.impl.EmpleadoServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
@RequiredArgsConstructor
public class EmpleadoController {

    private final EmpleadoServiceImpl empleadoServiceImpl;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'ADMINISTRADOR_GENERAL')")
    public ResponseEntity<List<EmpleadoDTO>> getAllEmpleados() {
        return new ResponseEntity<>(empleadoServiceImpl.getAllEmpleados(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('EMPLEADO', 'ADMINISTRADOR', 'ADMINISTRADOR_GENERAL')")
    public ResponseEntity<EmpleadoDTO> getEmpleadoById(@PathVariable Integer id) {
        return new ResponseEntity<>(empleadoServiceImpl.getEmpleadoById(id), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'ADMINISTRADOR_GENERAL')")
    public ResponseEntity<EmpleadoDTO> createEmpleado(@Valid @RequestBody EmpleadoRequestDTO empleadoDTO) {
        return new ResponseEntity<>(empleadoServiceImpl.saveEmpleado(empleadoDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'ADMINISTRADOR_GENERAL')")
    public ResponseEntity<EmpleadoDTO> updateEmpleado(@PathVariable Integer id, @Valid @RequestBody EmpleadoRequestDTO empleadoDetails) {
        return new ResponseEntity<>(empleadoServiceImpl.updateEmpleado(id, empleadoDetails), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR_GENERAL')")
    public ResponseEntity<Void> deleteEmpleado(@PathVariable Integer id) {
        empleadoServiceImpl.deleteEmpleado(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
