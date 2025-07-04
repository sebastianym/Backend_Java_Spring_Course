package com.giweb.supply.business.segundotalleracm.controller;

import com.giweb.supply.business.segundotalleracm.model.dto.response.HotelDTO;
import com.giweb.supply.business.segundotalleracm.model.dto.request.HotelRequestDTO;
import com.giweb.supply.business.segundotalleracm.service.impl.HotelServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hoteles")
@RequiredArgsConstructor
public class HotelController {

    private final HotelServiceImpl hotelServiceImpl;

    @GetMapping
    public ResponseEntity<List<HotelDTO>> getAllHoteles() {
        return new ResponseEntity<>(hotelServiceImpl.getAllHoteles(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelDTO> getHotelById(@PathVariable Integer id) {
        return new ResponseEntity<>(hotelServiceImpl.getHotelById(id), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMINISTRADOR_GENERAL')")
    public ResponseEntity<HotelDTO> createHotel(@Valid @RequestBody HotelRequestDTO hotelDTO) {
        return new ResponseEntity<>(hotelServiceImpl.saveHotel(hotelDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR_GENERAL')")
    public ResponseEntity<HotelDTO> updateHotel(@PathVariable Integer id, @Valid @RequestBody HotelRequestDTO hotelDetails) {
        return new ResponseEntity<>(hotelServiceImpl.updateHotel(id, hotelDetails), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR_GENERAL')")
    public ResponseEntity<Void> deleteHotel(@PathVariable Integer id) {
        hotelServiceImpl.deleteHotel(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
