package com.giweb.supply.business.segundotalleracm.controller;

import com.giweb.supply.business.segundotalleracm.model.dto.response.ReservaDTO;
import com.giweb.supply.business.segundotalleracm.model.dto.request.ReservaRequestDTO;
import com.giweb.supply.business.segundotalleracm.service.impl.ReservaServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaServiceImpl reservaServiceImpl;

    @GetMapping
    public ResponseEntity<List<ReservaDTO>> getAllReservas() {
        return new ResponseEntity<>(reservaServiceImpl.getAllReservas(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaDTO> getReservaById(@PathVariable Integer id) {
        return new ResponseEntity<>(reservaServiceImpl.getReservaById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ReservaDTO> createReserva(@Valid @RequestBody ReservaRequestDTO reservaDTO) {
        return new ResponseEntity<>(reservaServiceImpl.saveReserva(reservaDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservaDTO> updateReserva(@PathVariable Integer id, @Valid @RequestBody ReservaRequestDTO reservaDetails) {
        return new ResponseEntity<>(reservaServiceImpl.updateReserva(id, reservaDetails), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Integer id) {
        reservaServiceImpl.deleteReserva(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
