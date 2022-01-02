package com.phonepe.cabbooking.repository;

import com.phonepe.cabbooking.model.entities.Cab;
import com.phonepe.cabbooking.model.entities.Trip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {

    Page<Trip> findByCabOrderByBookingDate(Cab cab, Pageable pageable);
}
