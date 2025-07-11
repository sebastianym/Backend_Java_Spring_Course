package com.giweb.supply.business.segundotalleracm.repository;

import com.giweb.supply.business.segundotalleracm.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {
}
