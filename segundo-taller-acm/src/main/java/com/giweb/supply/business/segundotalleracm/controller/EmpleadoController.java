package com.giweb.supply.business.segundotalleracm.controller;

import com.giweb.supply.business.segundotalleracm.model.dto.response.EmpleadoDTO;
import com.giweb.supply.business.segundotalleracm.model.dto.request.EmpleadoRequestDTO;
import com.giweb.supply.business.segundotalleracm.service.impl.EmpleadoServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
@RequiredArgsConstructor
public class EmpleadoController {

    private final EmpleadoServiceImpl empleadoServiceImpl;

    @GetMapping
    public ResponseEntity<List<EmpleadoDTO>> getAllEmpleados() {
        return new ResponseEntity<>(empleadoServiceImpl.getAllEmpleados(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoDTO> getEmpleadoById(@PathVariable Integer id) {
        return new ResponseEntity<>(empleadoServiceImpl.getEmpleadoById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EmpleadoDTO> createEmpleado(@Valid @RequestBody EmpleadoRequestDTO empleadoDTO) {
        return new ResponseEntity<>(empleadoServiceImpl.saveEmpleado(empleadoDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoDTO> updateEmpleado(@PathVariable Integer id, @Valid @RequestBody EmpleadoRequestDTO empleadoDetails) {
        return new ResponseEntity<>(empleadoServiceImpl.updateEmpleado(id, empleadoDetails), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmpleado(@PathVariable Integer id) {
        empleadoServiceImpl.deleteEmpleado(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
