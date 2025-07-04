package com.giweb.supply.business.segundotalleracm.service;

import com.giweb.supply.business.segundotalleracm.model.dto.request.FacturaRequestDTO;
import com.giweb.supply.business.segundotalleracm.model.dto.response.FacturaDTO;

import java.util.List;

public interface IFacturaService {

    List<FacturaDTO> getAllFacturas();

    FacturaDTO getFacturaById(Integer id);

    FacturaDTO saveFactura(FacturaRequestDTO facturaDTO);

    FacturaDTO updateFactura(Integer id, FacturaRequestDTO facturaDetails);

    void deleteFactura(Integer id);
}
