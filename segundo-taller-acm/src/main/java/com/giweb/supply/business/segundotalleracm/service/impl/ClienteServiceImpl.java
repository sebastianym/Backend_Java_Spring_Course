package com.giweb.supply.business.segundotalleracm.service.impl;

import com.giweb.supply.business.segundotalleracm.exception.NotFoundException;
import com.giweb.supply.business.segundotalleracm.model.Cliente;
import com.giweb.supply.business.segundotalleracm.model.dto.response.ClienteDTO;
import com.giweb.supply.business.segundotalleracm.model.dto.request.ClienteRequestDTO;
import com.giweb.supply.business.segundotalleracm.repository.ClienteRepository;
import com.giweb.supply.business.segundotalleracm.service.IClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements IClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    public List<ClienteDTO> getAllClientes() {
        return clienteRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ClienteDTO getClienteById(Integer id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("El Cliente con id " + id + " no fue encontrado."));
        return convertToDto(cliente);
    }

    @Override
    public ClienteDTO saveCliente(ClienteRequestDTO clienteDTO) {
        Cliente cliente = convertToEntity(clienteDTO);
        return convertToDto(clienteRepository.save(cliente));
    }

    @Override
    public ClienteDTO updateCliente(Integer id, ClienteRequestDTO clienteDetails) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("El Cliente con id " + id + " no fue encontrado."));

        cliente.setPrimerNombre(clienteDetails.getPrimerNombre());
        cliente.setSegundoNombre(clienteDetails.getSegundoNombre());
        cliente.setPrimerApellido(clienteDetails.getPrimerApellido());
        cliente.setSegundoApellido(clienteDetails.getSegundoApellido());
        cliente.setCedula(clienteDetails.getCedula());
        cliente.setDireccion(clienteDetails.getDireccion());

        return convertToDto(clienteRepository.save(cliente));
    }

    @Override
    public void deleteCliente(Integer id) {
        if (!clienteRepository.existsById(id)) {
            throw new NotFoundException("El Cliente con id " + id + " no fue encontrado.");
        }
        clienteRepository.deleteById(id);
    }

    private ClienteDTO convertToDto(Cliente cliente) {
        ClienteDTO dto = new ClienteDTO();
        dto.setIdCliente(cliente.getIdCliente());
        dto.setPrimerNombre(cliente.getPrimerNombre());
        dto.setSegundoNombre(cliente.getSegundoNombre());
        dto.setPrimerApellido(cliente.getPrimerApellido());
        dto.setSegundoApellido(cliente.getSegundoApellido());
        dto.setCedula(cliente.getCedula());
        dto.setDireccion(cliente.getDireccion());
        return dto;
    }

    private Cliente convertToEntity(ClienteRequestDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setPrimerNombre(dto.getPrimerNombre());
        cliente.setSegundoNombre(dto.getSegundoNombre());
        cliente.setPrimerApellido(dto.getPrimerApellido());
        cliente.setSegundoApellido(dto.getSegundoApellido());
        cliente.setCedula(dto.getCedula());
        cliente.setDireccion(dto.getDireccion());
        return cliente;
    }
}
