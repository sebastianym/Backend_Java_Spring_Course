package com.giweb.supply.business.segundotalleracm.service.impl;

import com.giweb.supply.business.segundotalleracm.exception.NotFoundException;
import com.giweb.supply.business.segundotalleracm.model.Administrador;
import com.giweb.supply.business.segundotalleracm.model.dto.response.AdministradorDTO;
import com.giweb.supply.business.segundotalleracm.model.dto.request.AdministradorRequestDTO;
import com.giweb.supply.business.segundotalleracm.repository.AdministradorRepository;
import com.giweb.supply.business.segundotalleracm.service.IAdministradorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdministradorServiceImpl implements IAdministradorService {

    private final AdministradorRepository administradorRepository;

    @Override
    public List<AdministradorDTO> getAllAdministradores() {
        return administradorRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AdministradorDTO getAdministradorById(Integer id) {
        Administrador administrador = administradorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("El Administrador con id " + id + " no fue encontrado."));
        return convertToDto(administrador);
    }

    @Override
    public AdministradorDTO saveAdministrador(AdministradorRequestDTO administradorDTO) {
        Administrador administrador = convertToEntity(administradorDTO);
        return convertToDto(administradorRepository.save(administrador));
    }

    @Override
    public AdministradorDTO updateAdministrador(Integer id, AdministradorRequestDTO administradorDetails) {
        Administrador administrador = administradorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("El Administrador con id " + id + " no fue encontrado."));

        administrador.setPrimerNombre(administradorDetails.getPrimerNombre());
        administrador.setSegundoNombre(administradorDetails.getSegundoNombre());
        administrador.setPrimerApellido(administradorDetails.getPrimerApellido());
        administrador.setSegundoApellido(administradorDetails.getSegundoApellido());
        administrador.setCorreo(administradorDetails.getCorreo());
        administrador.setTelefono(administradorDetails.getTelefono());

        return convertToDto(administradorRepository.save(administrador));
    }

    @Override
    public void deleteAdministrador(Integer id) {
        if (!administradorRepository.existsById(id)) {
            throw new NotFoundException("El Administrador con id " + id + " no fue encontrado.");
        }
        administradorRepository.deleteById(id);
    }

    private AdministradorDTO convertToDto(Administrador administrador) {
        AdministradorDTO dto = new AdministradorDTO();
        dto.setIdAdministrador(administrador.getIdAdministrador());
        dto.setPrimerNombre(administrador.getPrimerNombre());
        dto.setSegundoNombre(administrador.getSegundoNombre());
        dto.setPrimerApellido(administrador.getPrimerApellido());
        dto.setSegundoApellido(administrador.getSegundoApellido());
        dto.setCorreo(administrador.getCorreo());
        dto.setTelefono(administrador.getTelefono());
        return dto;
    }

    private Administrador convertToEntity(AdministradorRequestDTO dto) {
        Administrador administrador = new Administrador();
        administrador.setPrimerNombre(dto.getPrimerNombre());
        administrador.setSegundoNombre(dto.getSegundoNombre());
        administrador.setPrimerApellido(dto.getPrimerApellido());
        administrador.setSegundoApellido(dto.getSegundoApellido());
        administrador.setCorreo(dto.getCorreo());
        administrador.setTelefono(dto.getTelefono());
        return administrador;
    }
}
