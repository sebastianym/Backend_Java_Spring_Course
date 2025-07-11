package com.giweb.supply.business.segundotalleracm.repository;

import com.giweb.supply.business.segundotalleracm.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
