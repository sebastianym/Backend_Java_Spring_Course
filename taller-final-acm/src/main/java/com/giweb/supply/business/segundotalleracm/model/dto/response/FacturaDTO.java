package com.giweb.supply.business.segundotalleracm.model.dto.response;

import lombok.Data;

@Data
public class FacturaDTO {
    private Integer idFactura;
    private Integer idReserva;
    private Integer idPago;
    private Integer fechaEmision;
    private Integer valorTotal;
}
