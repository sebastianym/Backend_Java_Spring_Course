package com.giweb.supply.business.segundotalleracm.controller;

import com.giweb.supply.business.segundotalleracm.dto.SeedResponseDTO;
import com.giweb.supply.business.segundotalleracm.service.ISeedService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/seed")
public class SeedController {

    private final ISeedService seedService;

    public SeedController(ISeedService seedService) {
        this.seedService = seedService;
    }

    @PostMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<SeedResponseDTO> seedDatabase() {
        SeedResponseDTO response = seedService.initializeDatabase();
        return ResponseEntity.ok(response);
    }
}
