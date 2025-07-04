package com.giweb.supply.business.segundotalleracm.service;

import com.giweb.supply.business.segundotalleracm.model.dto.request.ReservaRequestDTO;
import com.giweb.supply.business.segundotalleracm.model.dto.response.ReservaDTO;

import java.util.List;

public interface IReservaService {

    List<ReservaDTO> getAllReservas();

    ReservaDTO getReservaById(Integer id);

    ReservaDTO saveReserva(ReservaRequestDTO reservaDTO);

    ReservaDTO updateReserva(Integer id, ReservaRequestDTO reservaDetails);

    void deleteReserva(Integer id);
}
