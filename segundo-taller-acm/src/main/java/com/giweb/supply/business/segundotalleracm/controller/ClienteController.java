package com.giweb.supply.business.segundotalleracm.controller;

import com.giweb.supply.business.segundotalleracm.model.dto.response.ClienteDTO;
import com.giweb.supply.business.segundotalleracm.model.dto.request.ClienteRequestDTO;
import com.giweb.supply.business.segundotalleracm.service.impl.ClienteServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteServiceImpl clienteServiceImpl;

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> getAllClientes() {
        return new ResponseEntity<>(clienteServiceImpl.getAllClientes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> getClienteById(@PathVariable Integer id) {
        return new ResponseEntity<>(clienteServiceImpl.getClienteById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> createCliente(@Valid @RequestBody ClienteRequestDTO clienteDTO) {
        return new ResponseEntity<>(clienteServiceImpl.saveCliente(clienteDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> updateCliente(@PathVariable Integer id, @Valid @RequestBody ClienteRequestDTO clienteDetails) {
        return new ResponseEntity<>(clienteServiceImpl.updateCliente(id, clienteDetails), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Integer id) {
        clienteServiceImpl.deleteCliente(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
