package com.giweb.supply.business.segundotalleracm.controller;

import com.giweb.supply.business.segundotalleracm.dto.SeedResponseDTO;
import com.giweb.supply.business.segundotalleracm.service.ISeedService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/unsecured/seed")
@Slf4j
public class UnsecuredSeedController {

    private final ISeedService seedService;

    public UnsecuredSeedController(ISeedService seedService) {
        this.seedService = seedService;
    }

    @PostMapping
    public ResponseEntity<SeedResponseDTO> seedDatabase() {
        log.info("Inicializando base de datos con datos de prueba");
        SeedResponseDTO response = seedService.initializeDatabase();
        log.info("Base de datos inicializada correctamente");
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<SeedResponseDTO> seedDatabaseGet() {
        log.info("Inicializando base de datos con datos de prueba (GET)");
        SeedResponseDTO response = seedService.initializeDatabase();
        log.info("Base de datos inicializada correctamente");
        return ResponseEntity.ok(response);
    }
}
