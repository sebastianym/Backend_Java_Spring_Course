package com.giweb.supply.business.segundotalleracm.controller;

import com.giweb.supply.business.segundotalleracm.model.dto.response.AdministradorGeneralDTO;
import com.giweb.supply.business.segundotalleracm.model.dto.request.AdministradorGeneralRequestDTO;
import com.giweb.supply.business.segundotalleracm.service.impl.AdministradorGeneralServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/administradores-generales")
@RequiredArgsConstructor
public class AdministradorGeneralController {

    private final AdministradorGeneralServiceImpl administradorGeneralServiceImpl;

    @GetMapping
    @PreAuthorize("hasRole('ADMINISTRADOR_GENERAL')")
    public ResponseEntity<List<AdministradorGeneralDTO>> getAllAdministradoresGenerales() {
        return new ResponseEntity<>(administradorGeneralServiceImpl.getAllAdministradoresGenerales(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR_GENERAL')")
    public ResponseEntity<AdministradorGeneralDTO> getAdministradorGeneralById(@PathVariable Integer id) {
        return new ResponseEntity<>(administradorGeneralServiceImpl.getAdministradorGeneralById(id), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMINISTRADOR_GENERAL')")
    public ResponseEntity<AdministradorGeneralDTO> createAdministradorGeneral(@Valid @RequestBody AdministradorGeneralRequestDTO administradorGeneralDTO) {
        return new ResponseEntity<>(administradorGeneralServiceImpl.saveAdministradorGeneral(administradorGeneralDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR_GENERAL')")
    public ResponseEntity<AdministradorGeneralDTO> updateAdministradorGeneral(@PathVariable Integer id, @Valid @RequestBody AdministradorGeneralRequestDTO administradorGeneralDetails) {
        return new ResponseEntity<>(administradorGeneralServiceImpl.updateAdministradorGeneral(id, administradorGeneralDetails), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR_GENERAL')")
    public ResponseEntity<Void> deleteAdministradorGeneral(@PathVariable Integer id) {
        administradorGeneralServiceImpl.deleteAdministradorGeneral(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
