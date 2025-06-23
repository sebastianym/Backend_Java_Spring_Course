package com.giweb.supply.business.segundotalleracm.model.dto.response;

import lombok.Data;

@Data
public class HotelDTO {
    private Integer idHotel;
    private String nombre;
    private String ciudad;
    private String telefono;
    private String correo;
    private String direccion;
}
