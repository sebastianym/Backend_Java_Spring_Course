package com.giweb.supply.business.segundotalleracm.controller;

import com.giweb.supply.business.segundotalleracm.service.impl.DatabaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/utils")
@RequiredArgsConstructor
public class DatabaseController {

    private final DatabaseServiceImpl databaseServiceImpl;

    @PostMapping("/reset-database")
    public ResponseEntity<Void> resetDatabase() {
        databaseServiceImpl.resetDatabase();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
