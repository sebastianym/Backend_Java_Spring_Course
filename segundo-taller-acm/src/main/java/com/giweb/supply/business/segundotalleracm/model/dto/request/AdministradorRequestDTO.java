package com.giweb.supply.business.segundotalleracm.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AdministradorRequestDTO {
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

    @NotBlank(message = "El correo no puede estar vacío")
    @Email(message = "El formato del correo no es válido")
    @Size(max = 100, message = "El correo no puede tener más de 100 caracteres")
    private String correo;

    @NotBlank(message = "El teléfono no puede estar vacío")
    @Pattern(regexp = "^\\d{10}$", message = "El teléfono debe tener 10 dígitos")
    private String telefono;
}
