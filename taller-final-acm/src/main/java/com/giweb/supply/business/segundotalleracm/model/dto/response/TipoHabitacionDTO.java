package com.giweb.supply.business.segundotalleracm.model.dto.response;

import lombok.Data;

@Data
public class TipoHabitacionDTO {
    private Integer idTipoHabitacion;

    private Integer idHotel;

    private String nombre;

    private Integer cantidad;
}
