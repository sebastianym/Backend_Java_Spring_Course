package com.giweb.supply.business.segundotalleracm.service;

import com.giweb.supply.business.segundotalleracm.model.dto.request.PagoRequestDTO;
import com.giweb.supply.business.segundotalleracm.model.dto.response.PagoDTO;

import java.util.List;

public interface IPagoService {

    List<PagoDTO> getAllPagos();

    PagoDTO getPagoById(Integer id);

    PagoDTO savePago(PagoRequestDTO pagoDTO);

    PagoDTO updatePago(Integer id, PagoRequestDTO pagoDetails);

    void deletePago(Integer id);
}
