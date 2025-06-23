package com.giweb.supply.business.segundotalleracm.model.dto.response;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ReservaDTO {
    private Integer idReserva;
    private Timestamp fechaInicio;
    private Timestamp fechaFinal;
    private Integer cantidadDias;
    private Boolean estado;
    private Timestamp fechaReserva;
    private Integer idHabitacion;
    private Integer idCliente;
}
