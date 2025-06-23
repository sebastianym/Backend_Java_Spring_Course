package com.giweb.supply.business.segundotalleracm.repository;

import com.giweb.supply.business.segundotalleracm.model.TipoHabitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoHabitacionRepository extends JpaRepository<TipoHabitacion, Integer> {
}
