package com.giweb.supply.business.segundotalleracm.model.dto.response;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class PagoDTO {
    private Integer idPago;
    private Timestamp fecha;
    private Integer idReserva;
    private String metodoPago;
    private Integer pagoTotal;
}
