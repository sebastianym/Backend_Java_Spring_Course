package com.giweb.supply.business.segundotalleracm.controller;

import com.giweb.supply.business.segundotalleracm.model.dto.response.UsuarioDTO;
import com.giweb.supply.business.segundotalleracm.model.dto.request.UsuarioRequestDTO;
import com.giweb.supply.business.segundotalleracm.service.impl.UsuarioServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioServiceImpl usuarioServiceImpl;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'ADMINISTRADOR_GENERAL')")
    public ResponseEntity<List<UsuarioDTO>> getAllUsuarios() {
        return new ResponseEntity<>(usuarioServiceImpl.getAllUsuarios(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'ADMINISTRADOR_GENERAL')")
    public ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable Integer id) {
        return new ResponseEntity<>(usuarioServiceImpl.getUsuarioById(id), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'ADMINISTRADOR_GENERAL')")
    public ResponseEntity<UsuarioDTO> createUsuario(@Valid @RequestBody UsuarioRequestDTO usuarioDTO) {
        return new ResponseEntity<>(usuarioServiceImpl.saveUsuario(usuarioDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'ADMINISTRADOR_GENERAL')")
    public ResponseEntity<UsuarioDTO> updateUsuario(@PathVariable Integer id, @Valid @RequestBody UsuarioRequestDTO usuarioDetails) {
        return new ResponseEntity<>(usuarioServiceImpl.updateUsuario(id, usuarioDetails), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR_GENERAL')")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Integer id) {
        usuarioServiceImpl.deleteUsuario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
