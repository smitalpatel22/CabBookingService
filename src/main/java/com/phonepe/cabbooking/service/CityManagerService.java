package com.phonepe.cabbooking.service;

import com.phonepe.cabbooking.exceptions.InvalidCityException;
import com.phonepe.cabbooking.model.entities.City;
import com.phonepe.cabbooking.model.request.CityRegisterRequest;
import com.phonepe.cabbooking.model.response.BookingCountByCity;
import com.phonepe.cabbooking.repository.CityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CityManagerService {

    @Autowired
    private CityRepository cityRepository;

    public void registerCities(List<CityRegisterRequest> requests) {
        List<City> citiesToBeRegistered = new ArrayList<>();

        for(CityRegisterRequest request : requests) {
            City city = new City(request.getCityName(), request.getXCoordinates(), request.getYCoordinates());
            citiesToBeRegistered.add(city);
        }
        cityRepository.saveAll(citiesToBeRegistered);
    }

    public City getCityById(Integer cityId) {
        return cityRepository.findById(cityId)
                .orElseThrow(() -> new InvalidCityException("Invalid City id"));
    }

    public void cityStats() {
        List<BookingCountByCity> bookingCountByCities = cityRepository.countBookingsPerCity();
        log.info("Booking Count By City : {}", bookingCountByCities);
    }
}
