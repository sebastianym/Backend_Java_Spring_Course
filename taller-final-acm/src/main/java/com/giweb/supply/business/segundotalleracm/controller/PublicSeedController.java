package com.giweb.supply.business.segundotalleracm.controller;

import com.giweb.supply.business.segundotalleracm.dto.SeedResponseDTO;
import com.giweb.supply.business.segundotalleracm.service.ISeedService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/seed")
public class PublicSeedController {

    private final ISeedService seedService;

    public PublicSeedController(ISeedService seedService) {
        this.seedService = seedService;
    }

    @PostMapping
    public ResponseEntity<SeedResponseDTO> seedDatabase() {
        SeedResponseDTO response = seedService.initializeDatabase();
        return ResponseEntity.ok(response);
    }
}
