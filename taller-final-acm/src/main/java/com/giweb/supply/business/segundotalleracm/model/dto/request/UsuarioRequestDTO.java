package com.giweb.supply.business.segundotalleracm.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsuarioRequestDTO {
    @NotBlank(message = "El nombre de usuario no puede estar vacío")
    @Size(max = 255, message = "El nombre de usuario no puede tener más de 255 caracteres")
    private String nombreUsuario;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(min = 8, max = 255, message = "La contraseña debe tener entre 8 y 255 caracteres")
    private String contrasena;

    @NotBlank(message = "El rol no puede estar vacío")
    @Size(max = 30, message = "El rol no puede tener más de 30 caracteres")
    private String rol;

    private Integer idCliente;
    private Integer idEmpleado;
    private Integer idAdministradorGeneral;
    private Integer idAdministrador;
}
