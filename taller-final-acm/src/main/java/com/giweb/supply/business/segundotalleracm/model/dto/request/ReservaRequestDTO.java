package com.giweb.supply.business.segundotalleracm.model.dto.request;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ReservaRequestDTO {
    @NotNull(message = "La fecha de inicio no puede ser nula")
    @FutureOrPresent(message = "La fecha de inicio no puede ser en el pasado")
    private Timestamp fechaInicio;

    @NotNull(message = "La fecha final no puede ser nula")
    private Timestamp fechaFinal;

    @NotNull(message = "La cantidad de días no puede ser nula")
    @Positive(message = "La cantidad de días debe ser un número positivo")
    private Integer cantidadDias;

    @NotNull(message = "El estado no puede ser nulo")
    private Boolean estado;

    @NotNull(message = "El id de la habitación no puede ser nulo")
    private Integer idHabitacion;

    @NotNull(message = "El id del cliente no puede ser nulo")
    private Integer idCliente;
}
