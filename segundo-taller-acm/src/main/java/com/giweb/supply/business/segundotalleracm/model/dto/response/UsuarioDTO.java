package com.giweb.supply.business.segundotalleracm.model.dto.response;

import lombok.Data;

@Data
public class UsuarioDTO {
    private Integer idUsuario;
    private String nombreUsuario;
    private String contrasena;
    private String rol;
    private Integer idCliente;
    private Integer idEmpleado;
    private Integer idAdministradorGeneral;
    private Integer idAdministrador;
}
