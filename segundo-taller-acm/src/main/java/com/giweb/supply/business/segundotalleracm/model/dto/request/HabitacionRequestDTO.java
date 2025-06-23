package com.giweb.supply.business.segundotalleracm.model.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class HabitacionRequestDTO {
    @NotNull(message = "El número de habitación no puede ser nulo")
    @Positive(message = "El número de habitación debe ser un número positivo")
    private Integer numeroHabitacion;

    @NotNull(message = "El id del tipo de habitación no puede ser nulo")
    private Integer idTipoHabitacion;

    @NotNull(message = "El precio por día no puede ser nulo")
    @Positive(message = "El precio por día debe ser un número positivo")
    private Integer precioDia;

    @NotNull(message = "La disponibilidad no puede ser nula")
    private Boolean disponible;

    @NotNull(message = "El id del hotel no puede ser nulo")
    private Integer idHotel;
}
