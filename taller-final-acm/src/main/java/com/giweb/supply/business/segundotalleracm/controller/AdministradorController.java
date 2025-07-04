package com.giweb.supply.business.segundotalleracm.controller;

import com.giweb.supply.business.segundotalleracm.model.dto.response.AdministradorDTO;
import com.giweb.supply.business.segundotalleracm.model.dto.request.AdministradorRequestDTO;
import com.giweb.supply.business.segundotalleracm.service.impl.AdministradorServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/administradores")
@RequiredArgsConstructor
public class AdministradorController {

    private final AdministradorServiceImpl administradorServiceImpl;

    @GetMapping
    @PreAuthorize("hasRole('ADMINISTRADOR_GENERAL')")
    public ResponseEntity<List<AdministradorDTO>> getAllAdministradores() {
        return new ResponseEntity<>(administradorServiceImpl.getAllAdministradores(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'ADMINISTRADOR_GENERAL')")
    public ResponseEntity<AdministradorDTO> getAdministradorById(@PathVariable Integer id) {
        return new ResponseEntity<>(administradorServiceImpl.getAdministradorById(id), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMINISTRADOR_GENERAL')")
    public ResponseEntity<AdministradorDTO> createAdministrador(@Valid @RequestBody AdministradorRequestDTO administradorDTO) {
        return new ResponseEntity<>(administradorServiceImpl.saveAdministrador(administradorDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR_GENERAL')")
    public ResponseEntity<AdministradorDTO> updateAdministrador(@PathVariable Integer id, @Valid @RequestBody AdministradorRequestDTO administradorDetails) {
        return new ResponseEntity<>(administradorServiceImpl.updateAdministrador(id, administradorDetails), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR_GENERAL')")
    public ResponseEntity<Void> deleteAdministrador(@PathVariable Integer id) {
        administradorServiceImpl.deleteAdministrador(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
