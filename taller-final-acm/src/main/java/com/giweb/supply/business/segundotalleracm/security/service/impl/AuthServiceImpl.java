package com.giweb.supply.business.segundotalleracm.security.service.impl;

import com.giweb.supply.business.segundotalleracm.exception.BadRequestException;
import com.giweb.supply.business.segundotalleracm.model.Usuario;
import com.giweb.supply.business.segundotalleracm.repository.AdministradorGeneralRepository;
import com.giweb.supply.business.segundotalleracm.repository.AdministradorRepository;
import com.giweb.supply.business.segundotalleracm.repository.ClienteRepository;
import com.giweb.supply.business.segundotalleracm.repository.EmpleadoRepository;
import com.giweb.supply.business.segundotalleracm.repository.UsuarioRepository;
import com.giweb.supply.business.segundotalleracm.security.dto.JwtResponseDTO;
import com.giweb.supply.business.segundotalleracm.security.dto.LoginRequestDTO;
import com.giweb.supply.business.segundotalleracm.security.dto.RegisterRequestDTO;
import com.giweb.supply.business.segundotalleracm.security.enums.RolEnum;
import com.giweb.supply.business.segundotalleracm.security.jwt.JwtUtils;
import com.giweb.supply.business.segundotalleracm.security.service.IAuthService;
import com.giweb.supply.business.segundotalleracm.security.service.UserDetailsImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements IAuthService {

    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final ClienteRepository clienteRepository;
    private final EmpleadoRepository empleadoRepository;
    private final AdministradorRepository administradorRepository;
    private final AdministradorGeneralRepository administradorGeneralRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;

    public AuthServiceImpl(AuthenticationManager authenticationManager,
                          UsuarioRepository usuarioRepository,
                          ClienteRepository clienteRepository,
                          EmpleadoRepository empleadoRepository,
                          AdministradorRepository administradorRepository,
                          AdministradorGeneralRepository administradorGeneralRepository,
                          PasswordEncoder encoder,
                          JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.usuarioRepository = usuarioRepository;
        this.clienteRepository = clienteRepository;
        this.empleadoRepository = empleadoRepository;
        this.administradorRepository = administradorRepository;
        this.administradorGeneralRepository = administradorGeneralRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public JwtResponseDTO authenticateUser(LoginRequestDTO loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return JwtResponseDTO.builder()
                .token(jwt)
                .id(userDetails.getId())
                .username(userDetails.getUsername())
                .roles(roles)
                .build();
    }

    @Override
    public String registerUser(RegisterRequestDTO registerRequest) {
        if (usuarioRepository.existsByNombreUsuario(registerRequest.getUsername())) {
            throw new BadRequestException("Error: El nombre de usuario ya está en uso");
        }

        for (RolEnum rol : registerRequest.getRoles()) {
            switch (rol) {
                case CLIENTE:
                    if (registerRequest.getIdCliente() == null) {
                        throw new BadRequestException("Error: Para el rol CLIENTE se requiere un ID de cliente");
                    }
                    if (!clienteRepository.existsById(registerRequest.getIdCliente())) {
                        throw new BadRequestException("Error: El cliente con ID " + registerRequest.getIdCliente() + " no existe");
                    }
                    break;
                case EMPLEADO:
                    if (registerRequest.getIdEmpleado() == null) {
                        throw new BadRequestException("Error: Para el rol EMPLEADO se requiere un ID de empleado");
                    }
                    if (!empleadoRepository.existsById(registerRequest.getIdEmpleado())) {
                        throw new BadRequestException("Error: El empleado con ID " + registerRequest.getIdEmpleado() + " no existe");
                    }
                    break;
                case ADMINISTRADOR:
                    if (registerRequest.getIdAdministrador() == null) {
                        throw new BadRequestException("Error: Para el rol ADMINISTRADOR se requiere un ID de administrador");
                    }
                    if (!administradorRepository.existsById(registerRequest.getIdAdministrador())) {
                        throw new BadRequestException("Error: El administrador con ID " + registerRequest.getIdAdministrador() + " no existe");
                    }
                    break;
                case ADMINISTRADOR_GENERAL:
                    if (registerRequest.getIdAdministradorGeneral() == null) {
                        throw new BadRequestException("Error: Para el rol ADMINISTRADOR_GENERAL se requiere un ID de administrador general");
                    }
                    if (!administradorGeneralRepository.existsById(registerRequest.getIdAdministradorGeneral())) {
                        throw new BadRequestException("Error: El administrador general con ID " + registerRequest.getIdAdministradorGeneral() + " no existe");
                    }
                    break;
            }
        }

        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(registerRequest.getUsername());
        usuario.setContrasena(encoder.encode(registerRequest.getPassword()));

        RolEnum rol = registerRequest.getRoles().iterator().next();
        usuario.setRol(rol.name());

        switch (rol) {
            case CLIENTE:
                usuario.setCliente(clienteRepository.findById(registerRequest.getIdCliente()).orElse(null));
                break;
            case EMPLEADO:
                usuario.setEmpleado(empleadoRepository.findById(registerRequest.getIdEmpleado()).orElse(null));
                break;
            case ADMINISTRADOR:
                usuario.setAdministrador(administradorRepository.findById(registerRequest.getIdAdministrador()).orElse(null));
                break;
            case ADMINISTRADOR_GENERAL:
                usuario.setAdministradorGeneral(administradorGeneralRepository.findById(registerRequest.getIdAdministradorGeneral()).orElse(null));
                break;
        }

        usuarioRepository.save(usuario);

        return "Usuario registrado exitosamente";
    }
}
