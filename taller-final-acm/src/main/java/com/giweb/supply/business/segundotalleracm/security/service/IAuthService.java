package com.giweb.supply.business.segundotalleracm.security.service;

import com.giweb.supply.business.segundotalleracm.security.dto.JwtResponseDTO;
import com.giweb.supply.business.segundotalleracm.security.dto.LoginRequestDTO;
import com.giweb.supply.business.segundotalleracm.security.dto.RegisterRequestDTO;

public interface IAuthService {
    JwtResponseDTO authenticateUser(LoginRequestDTO loginRequest);
    String registerUser(RegisterRequestDTO registerRequest);
}
