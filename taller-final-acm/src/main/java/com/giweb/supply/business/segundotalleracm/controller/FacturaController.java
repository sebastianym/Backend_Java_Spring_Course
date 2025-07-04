package com.giweb.supply.business.segundotalleracm.controller;

import com.giweb.supply.business.segundotalleracm.model.dto.response.FacturaDTO;
import com.giweb.supply.business.segundotalleracm.model.dto.request.FacturaRequestDTO;
import com.giweb.supply.business.segundotalleracm.service.impl.FacturaServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facturas")
@RequiredArgsConstructor
public class FacturaController {

    private final FacturaServiceImpl facturaServiceImpl;

    @GetMapping
    @PreAuthorize("hasAnyRole('EMPLEADO', 'ADMINISTRADOR', 'ADMINISTRADOR_GENERAL')")
    public ResponseEntity<List<FacturaDTO>> getAllFacturas() {
        return new ResponseEntity<>(facturaServiceImpl.getAllFacturas(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('CLIENTE', 'EMPLEADO', 'ADMINISTRADOR', 'ADMINISTRADOR_GENERAL')")
    public ResponseEntity<FacturaDTO> getFacturaById(@PathVariable Integer id) {
        return new ResponseEntity<>(facturaServiceImpl.getFacturaById(id), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('EMPLEADO', 'ADMINISTRADOR', 'ADMINISTRADOR_GENERAL')")
    public ResponseEntity<FacturaDTO> createFactura(@Valid @RequestBody FacturaRequestDTO facturaDTO) {
        return new ResponseEntity<>(facturaServiceImpl.saveFactura(facturaDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('EMPLEADO', 'ADMINISTRADOR', 'ADMINISTRADOR_GENERAL')")
    public ResponseEntity<FacturaDTO> updateFactura(@PathVariable Integer id, @Valid @RequestBody FacturaRequestDTO facturaDetails) {
        return new ResponseEntity<>(facturaServiceImpl.updateFactura(id, facturaDetails), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'ADMINISTRADOR_GENERAL')")
    public ResponseEntity<Void> deleteFactura(@PathVariable Integer id) {
        facturaServiceImpl.deleteFactura(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
