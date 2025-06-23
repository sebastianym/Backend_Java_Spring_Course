package com.giweb.supply.business.segundotalleracm.controller;

import com.giweb.supply.business.segundotalleracm.model.dto.response.HabitacionDTO;
import com.giweb.supply.business.segundotalleracm.model.dto.request.HabitacionRequestDTO;
import com.giweb.supply.business.segundotalleracm.service.impl.HabitacionServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habitaciones")
@RequiredArgsConstructor
public class HabitacionController {

    private final HabitacionServiceImpl habitacionServiceImpl;

    @GetMapping
    public ResponseEntity<List<HabitacionDTO>> getAllHabitaciones() {
        return new ResponseEntity<>(habitacionServiceImpl.getAllHabitaciones(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HabitacionDTO> getHabitacionById(@PathVariable Integer id) {
        return new ResponseEntity<>(habitacionServiceImpl.getHabitacionById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HabitacionDTO> createHabitacion(@Valid @RequestBody HabitacionRequestDTO habitacionDTO) {
        return new ResponseEntity<>(habitacionServiceImpl.saveHabitacion(habitacionDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HabitacionDTO> updateHabitacion(@PathVariable Integer id, @Valid @RequestBody HabitacionRequestDTO habitacionDetails) {
        return new ResponseEntity<>(habitacionServiceImpl.updateHabitacion(id, habitacionDetails), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHabitacion(@PathVariable Integer id) {
        habitacionServiceImpl.deleteHabitacion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
