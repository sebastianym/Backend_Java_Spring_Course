package com.giweb.supply.business.segundotalleracm.model.dto.request;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class PagoRequestDTO {
    @NotNull(message = "La fecha no puede ser nula")
    @FutureOrPresent(message = "La fecha no puede ser en el pasado")
    private Timestamp fecha;

    @NotNull(message = "El id de la reserva no puede ser nulo")
    private Integer idReserva;

    @NotBlank(message = "El método de pago no puede estar vacío")
    @Size(max = 20, message = "El método de pago no puede tener más de 20 caracteres")
    private String metodoPago;

    @NotNull(message = "El pago total no puede ser nulo")
    @Positive(message = "El pago total debe ser un número positivo")
    private Integer pagoTotal;
}
