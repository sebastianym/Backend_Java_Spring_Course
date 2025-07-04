package com.giweb.supply.business.segundotalleracm.security.dto;

import com.giweb.supply.business.segundotalleracm.security.enums.RolEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO {

    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Size(min = 3, max = 20, message = "El nombre de usuario debe tener entre 3 y 20 caracteres")
    private String username;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, max = 40, message = "La contraseña debe tener entre 6 y 40 caracteres")
    private String password;

    private Set<RolEnum> roles;

    private Integer idCliente;
    private Integer idEmpleado;
    private Integer idAdministrador;
    private Integer idAdministradorGeneral;
}
