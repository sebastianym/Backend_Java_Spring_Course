package com.giweb.supply.business.segundotalleracm.service;

import com.giweb.supply.business.segundotalleracm.model.dto.request.ClienteRequestDTO;
import com.giweb.supply.business.segundotalleracm.model.dto.response.ClienteDTO;

import java.util.List;

public interface IClienteService {

    List<ClienteDTO> getAllClientes();

    ClienteDTO getClienteById(Integer id);

    ClienteDTO saveCliente(ClienteRequestDTO clienteDTO);

    ClienteDTO updateCliente(Integer id, ClienteRequestDTO clienteDetails);

    void deleteCliente(Integer id);
}
