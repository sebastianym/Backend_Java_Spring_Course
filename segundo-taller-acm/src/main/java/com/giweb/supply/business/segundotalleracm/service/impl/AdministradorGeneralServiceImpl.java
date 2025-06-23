package com.giweb.supply.business.segundotalleracm.service.impl;

import com.giweb.supply.business.segundotalleracm.exception.NotFoundException;
import com.giweb.supply.business.segundotalleracm.model.AdministradorGeneral;
import com.giweb.supply.business.segundotalleracm.model.dto.response.AdministradorGeneralDTO;
import com.giweb.supply.business.segundotalleracm.model.dto.request.AdministradorGeneralRequestDTO;
import com.giweb.supply.business.segundotalleracm.repository.AdministradorGeneralRepository;
import com.giweb.supply.business.segundotalleracm.service.IAdministradorGeneralService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdministradorGeneralServiceImpl implements IAdministradorGeneralService {

    private final AdministradorGeneralRepository administradorGeneralRepository;

    @Override
    public List<AdministradorGeneralDTO> getAllAdministradoresGenerales() {
        return administradorGeneralRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AdministradorGeneralDTO getAdministradorGeneralById(Integer id) {
        AdministradorGeneral administradorGeneral = administradorGeneralRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("El Administrador General con id " + id + " no fue encontrado."));
        return convertToDto(administradorGeneral);
    }

    @Override
    public AdministradorGeneralDTO saveAdministradorGeneral(AdministradorGeneralRequestDTO administradorGeneralDTO) {
        AdministradorGeneral administradorGeneral = convertToEntity(administradorGeneralDTO);
        return convertToDto(administradorGeneralRepository.save(administradorGeneral));
    }

    @Override
    public AdministradorGeneralDTO updateAdministradorGeneral(Integer id, AdministradorGeneralRequestDTO administradorGeneralDetails) {
        AdministradorGeneral administradorGeneral = administradorGeneralRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("El Administrador General con id " + id + " no fue encontrado."));

        administradorGeneral.setCorreo(administradorGeneralDetails.getCorreo());
        administradorGeneral.setTelefono(administradorGeneralDetails.getTelefono());
        administradorGeneral.setPrimerNombre(administradorGeneralDetails.getPrimerNombre());
        administradorGeneral.setSegundoNombre(administradorGeneralDetails.getSegundoNombre());
        administradorGeneral.setPrimerApellido(administradorGeneralDetails.getPrimerApellido());
        administradorGeneral.setSegundoApellido(administradorGeneralDetails.getSegundoApellido());

        return convertToDto(administradorGeneralRepository.save(administradorGeneral));
    }

    @Override
    public void deleteAdministradorGeneral(Integer id) {
        if (!administradorGeneralRepository.existsById(id)) {
            throw new NotFoundException("El Administrador General con id " + id + " no fue encontrado.");
        }
        administradorGeneralRepository.deleteById(id);
    }

    private AdministradorGeneralDTO convertToDto(AdministradorGeneral administradorGeneral) {
        AdministradorGeneralDTO dto = new AdministradorGeneralDTO();
        dto.setIdAdministradorGeneral(administradorGeneral.getIdAdministradorGeneral());
        dto.setCorreo(administradorGeneral.getCorreo());
        dto.setTelefono(administradorGeneral.getTelefono());
        dto.setPrimerNombre(administradorGeneral.getPrimerNombre());
        dto.setSegundoNombre(administradorGeneral.getSegundoNombre());
        dto.setPrimerApellido(administradorGeneral.getPrimerApellido());
        dto.setSegundoApellido(administradorGeneral.getSegundoApellido());
        return dto;
    }

    private AdministradorGeneral convertToEntity(AdministradorGeneralRequestDTO dto) {
        AdministradorGeneral administradorGeneral = new AdministradorGeneral();
        administradorGeneral.setCorreo(dto.getCorreo());
        administradorGeneral.setTelefono(dto.getTelefono());
        administradorGeneral.setPrimerNombre(dto.getPrimerNombre());
        administradorGeneral.setSegundoNombre(dto.getSegundoNombre());
        administradorGeneral.setPrimerApellido(dto.getPrimerApellido());
        administradorGeneral.setSegundoApellido(dto.getSegundoApellido());
        return administradorGeneral;
    }
}
