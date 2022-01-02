package com.phonepe.cabbooking.repository;

import com.phonepe.cabbooking.model.entities.Cab;
import com.phonepe.cabbooking.model.entities.City;
import com.phonepe.cabbooking.model.enums.CabState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CabsRepository extends JpaRepository<Cab, Integer> {

    Optional<Cab> findFirstByCityAndCabStateOrderByLastBookedTime(City city, CabState cabState);
}
