package com.giweb.supply.business.segundotalleracm.model.dto.response;

import lombok.Data;

@Data
public class AdministradorDTO {
    private Integer idAdministrador;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String correo;
    private String telefono;
}
