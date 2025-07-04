package com.giweb.supply.business.segundotalleracm.model.dto.response;

import lombok.Data;

@Data
public class ClienteDTO {
    private Integer idCliente;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private Integer cedula;
    private String direccion;
}
