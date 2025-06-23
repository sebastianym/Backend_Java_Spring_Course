package com.giweb.supply.business.segundotalleracm.controller;

import com.giweb.supply.business.segundotalleracm.model.dto.response.TipoHabitacionDTO;
import com.giweb.supply.business.segundotalleracm.model.dto.request.TipoHabitacionRequestDTO;
import com.giweb.supply.business.segundotalleracm.service.impl.TipoHabitacionServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-habitacion")
@RequiredArgsConstructor
public class TipoHabitacionController {

    private final TipoHabitacionServiceImpl tipoHabitacionServiceImpl;

    @GetMapping
    public ResponseEntity<List<TipoHabitacionDTO>> getAllTiposHabitacion() {
        return new ResponseEntity<>(tipoHabitacionServiceImpl.getAllTiposHabitacion(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoHabitacionDTO> getTipoHabitacionById(@PathVariable Integer id) {
        return new ResponseEntity<>(tipoHabitacionServiceImpl.getTipoHabitacionById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TipoHabitacionDTO> createTipoHabitacion(@Valid @RequestBody TipoHabitacionRequestDTO tipoHabitacionDTO) {
        return new ResponseEntity<>(tipoHabitacionServiceImpl.saveTipoHabitacion(tipoHabitacionDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoHabitacionDTO> updateTipoHabitacion(@PathVariable Integer id, @Valid @RequestBody TipoHabitacionRequestDTO tipoHabitacionDetails) {
        return new ResponseEntity<>(tipoHabitacionServiceImpl.updateTipoHabitacion(id, tipoHabitacionDetails), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTipoHabitacion(@PathVariable Integer id) {
        tipoHabitacionServiceImpl.deleteTipoHabitacion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
