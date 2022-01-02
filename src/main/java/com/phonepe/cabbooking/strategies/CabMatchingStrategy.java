package com.phonepe.cabbooking.strategies;

import com.phonepe.cabbooking.model.entities.Cab;
import com.phonepe.cabbooking.model.enums.StrategyName;

import java.util.Optional;

public interface CabMatchingStrategy {

  Optional<Cab> matchCabToRider(Integer originCityId);

  StrategyName getStrategyName();
}
