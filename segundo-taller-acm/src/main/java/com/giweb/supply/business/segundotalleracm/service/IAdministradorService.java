package com.giweb.supply.business.segundotalleracm.service;

import com.giweb.supply.business.segundotalleracm.model.dto.request.AdministradorRequestDTO;
import com.giweb.supply.business.segundotalleracm.model.dto.response.AdministradorDTO;

import java.util.List;

public interface IAdministradorService {

    List<AdministradorDTO> getAllAdministradores();

    AdministradorDTO getAdministradorById(Integer id);

    AdministradorDTO saveAdministrador(AdministradorRequestDTO administradorDTO);

    AdministradorDTO updateAdministrador(Integer id, AdministradorRequestDTO administradorDetails);

    void deleteAdministrador(Integer id);
}
