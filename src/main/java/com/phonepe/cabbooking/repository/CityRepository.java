package com.phonepe.cabbooking.repository;

import com.phonepe.cabbooking.model.entities.City;
import com.phonepe.cabbooking.model.response.BookingCountByCity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

    @Query("SELECT new com.phonepe.cabbooking.model.response.BookingCountByCity(t.city, COUNT(*)) FROM Trip AS t GROUP BY t.city ORDER BY COUNT(*) DESC")
    List<BookingCountByCity> countBookingsPerCity();


}
