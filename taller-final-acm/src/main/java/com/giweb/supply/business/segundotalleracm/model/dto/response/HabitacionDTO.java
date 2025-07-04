package com.giweb.supply.business.segundotalleracm.model.dto.response;

import lombok.Data;

@Data
public class HabitacionDTO {
    private Integer idHabitacion;
    private Integer numeroHabitacion;
    private Integer idTipoHabitacion;
    private Integer precioDia;
    private Boolean disponible;
    private Integer idHotel;
}
