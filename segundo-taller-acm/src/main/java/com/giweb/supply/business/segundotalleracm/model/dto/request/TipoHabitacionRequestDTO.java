package com.giweb.supply.business.segundotalleracm.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TipoHabitacionRequestDTO {
    @NotNull(message = "El id del hotel no puede ser nulo")
    private Integer idHotel;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    private String nombre;

    @NotNull(message = "La cantidad no puede ser nula")
    @PositiveOrZero(message = "La cantidad debe ser un número positivo o cero")
    private Integer cantidad;
}
