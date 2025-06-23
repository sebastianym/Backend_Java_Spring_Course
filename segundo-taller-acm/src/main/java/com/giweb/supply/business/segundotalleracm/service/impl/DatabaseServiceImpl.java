package com.giweb.supply.business.segundotalleracm.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DatabaseServiceImpl {

    private final JdbcTemplate jdbcTemplate;

    @Transactional
    public void resetDatabase() {
        String sql = "TRUNCATE TABLE " +
                     "administrador, administrador_general, cliente, empleado, " +
                     "factura, habitacion, hotel, pago, reserva, tipo_habitacion, usuario " +
                     "RESTART IDENTITY CASCADE;";
        jdbcTemplate.execute(sql);
    }
}
