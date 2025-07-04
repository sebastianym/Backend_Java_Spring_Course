package com.giweb.supply.business.segundotalleracm.model.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class FacturaRequestDTO {
    @NotNull(message = "El id de la reserva no puede ser nulo")
    private Integer idReserva;

    private Integer idPago;

    @NotNull(message = "La fecha de emisión no puede ser nula")
    private Integer fechaEmision;

    @NotNull(message = "El valor total no puede ser nulo")
    @Positive(message = "El valor total debe ser un número positivo")
    private Integer valorTotal;
}
