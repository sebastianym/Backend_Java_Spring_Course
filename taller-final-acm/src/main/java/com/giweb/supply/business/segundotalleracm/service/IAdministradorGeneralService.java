package com.giweb.supply.business.segundotalleracm.service;

import com.giweb.supply.business.segundotalleracm.model.dto.request.AdministradorGeneralRequestDTO;
import com.giweb.supply.business.segundotalleracm.model.dto.response.AdministradorGeneralDTO;

import java.util.List;

public interface IAdministradorGeneralService {

    List<AdministradorGeneralDTO> getAllAdministradoresGenerales();

    AdministradorGeneralDTO getAdministradorGeneralById(Integer id);

    AdministradorGeneralDTO saveAdministradorGeneral(AdministradorGeneralRequestDTO administradorGeneralDTO);

    AdministradorGeneralDTO updateAdministradorGeneral(Integer id, AdministradorGeneralRequestDTO administradorGeneralDetails);

    void deleteAdministradorGeneral(Integer id);
}
