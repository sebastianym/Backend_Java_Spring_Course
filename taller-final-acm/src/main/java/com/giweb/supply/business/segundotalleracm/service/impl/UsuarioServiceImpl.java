package com.giweb.supply.business.segundotalleracm.service.impl;

import com.giweb.supply.business.segundotalleracm.exception.NotFoundException;
import com.giweb.supply.business.segundotalleracm.model.*;
import com.giweb.supply.business.segundotalleracm.model.dto.response.UsuarioDTO;
import com.giweb.supply.business.segundotalleracm.model.dto.request.UsuarioRequestDTO;
import com.giweb.supply.business.segundotalleracm.repository.*;
import com.giweb.supply.business.segundotalleracm.service.IUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements IUsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final ClienteRepository clienteRepository;
    private final EmpleadoRepository empleadoRepository;
    private final AdministradorGeneralRepository administradorGeneralRepository;
    private final AdministradorRepository administradorRepository;

    @Override
    public List<UsuarioDTO> getAllUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioDTO getUsuarioById(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("El Usuario con id " + id + " no fue encontrado."));
        return convertToDto(usuario);
    }

    @Override
    public UsuarioDTO saveUsuario(UsuarioRequestDTO usuarioDTO) {
        Usuario usuario = convertToEntity(usuarioDTO);
        return convertToDto(usuarioRepository.save(usuario));
    }

    @Override
    public UsuarioDTO updateUsuario(Integer id, UsuarioRequestDTO usuarioDetails) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("El Usuario con id " + id + " no fue encontrado."));

        usuario.setNombreUsuario(usuarioDetails.getNombreUsuario());
        usuario.setContrasena(usuarioDetails.getContrasena());
        usuario.setRol(usuarioDetails.getRol());

        // Clear existing associations
        usuario.setCliente(null);
        usuario.setEmpleado(null);
        usuario.setAdministradorGeneral(null);
        usuario.setAdministrador(null);

        // Set new associations based on DTO
        if (usuarioDetails.getIdCliente() != null) {
            Cliente cliente = clienteRepository.findById(usuarioDetails.getIdCliente())
                    .orElseThrow(() -> new NotFoundException("El Cliente con id " + usuarioDetails.getIdCliente() + " no fue encontrado."));
            usuario.setCliente(cliente);
        }
        if (usuarioDetails.getIdEmpleado() != null) {
            Empleado empleado = empleadoRepository.findById(usuarioDetails.getIdEmpleado())
                    .orElseThrow(() -> new NotFoundException("El Empleado con id " + usuarioDetails.getIdEmpleado() + " no fue encontrado."));
            usuario.setEmpleado(empleado);
        }
        if (usuarioDetails.getIdAdministradorGeneral() != null) {
            AdministradorGeneral adminGeneral = administradorGeneralRepository.findById(usuarioDetails.getIdAdministradorGeneral())
                    .orElseThrow(() -> new NotFoundException("El Administrador General con id " + usuarioDetails.getIdAdministradorGeneral() + " no fue encontrado."));
            usuario.setAdministradorGeneral(adminGeneral);
        }
        if (usuarioDetails.getIdAdministrador() != null) {
            Administrador admin = administradorRepository.findById(usuarioDetails.getIdAdministrador())
                    .orElseThrow(() -> new NotFoundException("El Administrador con id " + usuarioDetails.getIdAdministrador() + " no fue encontrado."));
            usuario.setAdministrador(admin);
        }

        return convertToDto(usuarioRepository.save(usuario));
    }

    @Override
    public void deleteUsuario(Integer id) {
        if (!usuarioRepository.existsById(id)) {
            throw new NotFoundException("El Usuario con id " + id + " no fue encontrado.");
        }
        usuarioRepository.deleteById(id);
    }

    private UsuarioDTO convertToDto(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setIdUsuario(usuario.getIdUsuario());
        dto.setNombreUsuario(usuario.getNombreUsuario());
        dto.setContrasena(usuario.getContrasena());
        dto.setRol(usuario.getRol());
        if (usuario.getCliente() != null) {
            dto.setIdCliente(usuario.getCliente().getIdCliente());
        }
        if (usuario.getEmpleado() != null) {
            dto.setIdEmpleado(usuario.getEmpleado().getIdEmpleado());
        }
        if (usuario.getAdministradorGeneral() != null) {
            dto.setIdAdministradorGeneral(usuario.getAdministradorGeneral().getIdAdministradorGeneral());
        }
        if (usuario.getAdministrador() != null) {
            dto.setIdAdministrador(usuario.getAdministrador().getIdAdministrador());
        }
        return dto;
    }

    private Usuario convertToEntity(UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(dto.getNombreUsuario());
        usuario.setContrasena(dto.getContrasena());
        usuario.setRol(dto.getRol());

        if (dto.getIdCliente() != null) {
            Cliente cliente = clienteRepository.findById(dto.getIdCliente())
                    .orElseThrow(() -> new NotFoundException("El Cliente con id " + dto.getIdCliente() + " no fue encontrado."));
            usuario.setCliente(cliente);
        }
        if (dto.getIdEmpleado() != null) {
            Empleado empleado = empleadoRepository.findById(dto.getIdEmpleado())
                    .orElseThrow(() -> new NotFoundException("El Empleado con id " + dto.getIdEmpleado() + " no fue encontrado."));
            usuario.setEmpleado(empleado);
        }
        if (dto.getIdAdministradorGeneral() != null) {
            AdministradorGeneral adminGeneral = administradorGeneralRepository.findById(dto.getIdAdministradorGeneral())
                    .orElseThrow(() -> new NotFoundException("El Administrador General con id " + dto.getIdAdministradorGeneral() + " no fue encontrado."));
            usuario.setAdministradorGeneral(adminGeneral);
        }
        if (dto.getIdAdministrador() != null) {
            Administrador admin = administradorRepository.findById(dto.getIdAdministrador())
                    .orElseThrow(() -> new NotFoundException("El Administrador con id " + dto.getIdAdministrador() + " no fue encontrado."));
            usuario.setAdministrador(admin);
        }

        return usuario;
    }
}
