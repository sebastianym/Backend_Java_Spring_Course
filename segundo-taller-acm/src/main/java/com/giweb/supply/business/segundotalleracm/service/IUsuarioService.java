package com.giweb.supply.business.segundotalleracm.service;

import com.giweb.supply.business.segundotalleracm.model.dto.request.UsuarioRequestDTO;
import com.giweb.supply.business.segundotalleracm.model.dto.response.UsuarioDTO;

import java.util.List;

public interface IUsuarioService {

    List<UsuarioDTO> getAllUsuarios();

    UsuarioDTO getUsuarioById(Integer id);

    UsuarioDTO saveUsuario(UsuarioRequestDTO usuarioDTO);

    UsuarioDTO updateUsuario(Integer id, UsuarioRequestDTO usuarioDetails);

    void deleteUsuario(Integer id);
}
