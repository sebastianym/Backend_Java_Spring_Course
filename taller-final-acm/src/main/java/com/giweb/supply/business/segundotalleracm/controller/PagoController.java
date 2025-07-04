package com.giweb.supply.business.segundotalleracm.controller;

import com.giweb.supply.business.segundotalleracm.model.dto.response.PagoDTO;
import com.giweb.supply.business.segundotalleracm.model.dto.request.PagoRequestDTO;
import com.giweb.supply.business.segundotalleracm.service.impl.PagoServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagos")
@RequiredArgsConstructor
public class PagoController {

    private final PagoServiceImpl pagoServiceImpl;

    @GetMapping
    @PreAuthorize("hasAnyRole('EMPLEADO', 'ADMINISTRADOR', 'ADMINISTRADOR_GENERAL')")
    public ResponseEntity<List<PagoDTO>> getAllPagos() {
        return new ResponseEntity<>(pagoServiceImpl.getAllPagos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('CLIENTE', 'EMPLEADO', 'ADMINISTRADOR', 'ADMINISTRADOR_GENERAL')")
    public ResponseEntity<PagoDTO> getPagoById(@PathVariable Integer id) {
        return new ResponseEntity<>(pagoServiceImpl.getPagoById(id), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('EMPLEADO', 'ADMINISTRADOR', 'ADMINISTRADOR_GENERAL')")
    public ResponseEntity<PagoDTO> createPago(@Valid @RequestBody PagoRequestDTO pagoDTO) {
        return new ResponseEntity<>(pagoServiceImpl.savePago(pagoDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('EMPLEADO', 'ADMINISTRADOR', 'ADMINISTRADOR_GENERAL')")
    public ResponseEntity<PagoDTO> updatePago(@PathVariable Integer id, @Valid @RequestBody PagoRequestDTO pagoDetails) {
        return new ResponseEntity<>(pagoServiceImpl.updatePago(id, pagoDetails), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'ADMINISTRADOR_GENERAL')")
    public ResponseEntity<Void> deletePago(@PathVariable Integer id) {
        pagoServiceImpl.deletePago(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
