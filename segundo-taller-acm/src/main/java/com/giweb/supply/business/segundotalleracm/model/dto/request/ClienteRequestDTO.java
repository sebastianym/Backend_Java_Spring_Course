package com.giweb.supply.business.segundotalleracm.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ClienteRequestDTO {
    @NotBlank(message = "El primer nombre no puede estar vacío")
    @Size(max = 50, message = "El primer nombre no puede tener más de 50 caracteres")
    private String primerNombre;

    @Size(max = 50, message = "El segundo nombre no puede tener más de 50 caracteres")
    private String segundoNombre;

    @NotBlank(message = "El primer apellido no puede estar vacío")
    @Size(max = 50, message = "El primer apellido no puede tener más de 50 caracteres")
    private String primerApellido;

    @Size(max = 50, message = "El segundo apellido no puede tener más de 50 caracteres")
    private String segundoApellido;

    @NotNull(message = "La cédula no puede ser nula")
    @Positive(message = "La cédula debe ser un número positivo")
    private Integer cedula;

    @NotBlank(message = "La dirección no puede estar vacía")
    @Size(max = 150, message = "La dirección no puede tener más de 150 caracteres")
    private String direccion;
}
