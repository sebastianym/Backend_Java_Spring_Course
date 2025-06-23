package com.giweb.supply.business.segundotalleracm.repository;

import com.giweb.supply.business.segundotalleracm.model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Integer> {
}
