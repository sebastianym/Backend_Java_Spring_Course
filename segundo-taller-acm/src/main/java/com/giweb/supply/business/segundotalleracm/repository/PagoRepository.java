package com.giweb.supply.business.segundotalleracm.repository;

import com.giweb.supply.business.segundotalleracm.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Integer> {
}
