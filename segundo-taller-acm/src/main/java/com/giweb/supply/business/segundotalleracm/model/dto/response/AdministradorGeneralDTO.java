package com.giweb.supply.business.segundotalleracm.model.dto.response;

import lombok.Data;

@Data
public class AdministradorGeneralDTO {
    private Integer idAdministradorGeneral;
    private String correo;
    private String telefono;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
}
