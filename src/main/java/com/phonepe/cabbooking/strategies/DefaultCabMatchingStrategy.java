package com.phonepe.cabbooking.strategies;

import com.phonepe.cabbooking.database.CabsManagerService;
import com.phonepe.cabbooking.database.CityManagerService;
import com.phonepe.cabbooking.model.entities.Cab;
import com.phonepe.cabbooking.model.entities.City;

import java.util.Optional;

import com.phonepe.cabbooking.model.enums.StrategyName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultCabMatchingStrategy implements CabMatchingStrategy {

    @Autowired
    CabsManagerService cabsService;

    @Autowired
    CityManagerService cityService;

    @Override
    public Optional<Cab> matchCabToRider(Integer originCityId) {
        City city = cityService.getCityById(originCityId);
        return cabsService.getAvailableCabForLocation(city);
    }

    @Override
    public StrategyName getStrategyName() {
        return StrategyName.DEFAULT;
    }
}
