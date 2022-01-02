package com.phonepe.cabbooking.database;

import com.phonepe.cabbooking.exceptions.NoCabsAvailableException;
import com.phonepe.cabbooking.exceptions.TripNotFoundException;
import com.phonepe.cabbooking.model.entities.Cab;
import com.phonepe.cabbooking.model.entities.City;
import com.phonepe.cabbooking.model.entities.Trip;
import com.phonepe.cabbooking.model.enums.CabState;
import com.phonepe.cabbooking.model.enums.TripStatus;
import com.phonepe.cabbooking.model.request.BookRideRequest;
import com.phonepe.cabbooking.model.response.BookRideResponse;
import com.phonepe.cabbooking.model.response.EndRideResponse;
import com.phonepe.cabbooking.repository.TripRepository;
import com.phonepe.cabbooking.strategies.CabMatchingStrategy;

import java.util.*;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RidesManagerService {

    //for now I am autowiring hardcoded strategy to match cabs
    @Autowired
    CabMatchingStrategy cabMatchingStrategy;

    @Autowired
    CabsManagerService cabsManagerService;

    @Autowired
    CityManagerService cityService;

    @Autowired
    TripRepository tripRepository;

    public BookRideResponse bookRide(BookRideRequest request) {
        Optional<Cab> optionalAvailableCab = cabMatchingStrategy.matchCabToRider(request.getOriginCity());
        if (optionalAvailableCab.isPresent()) {
            Cab cab = optionalAvailableCab.get();
            cabsManagerService.updateCabAvailability(cab, CabState.ON_TRIP);
            Trip trip = addTrip(cab, request.getOriginCity());
            return BookRideResponse.builder()
                    .cabId(cab.getCabId()).driverName(cab.getDriverName()).tripId(trip.getTripId()).build();
        } else {
            throw new NoCabsAvailableException("Cabs are busy in current city. Please try again after some time.");
        }
    }

    public Trip addTrip(Cab cab, Integer cityId) {
        City city = cityService.getCityById(cityId);

        Trip trip = Trip.builder().bookingDate(cab.getLastBookedTime()).city(city).cab(cab).status(TripStatus.IN_PROGRESS).build();
        tripRepository.save(trip);
        return trip;
    }

    public Page<Trip> cabTripHistory(@NonNull final Integer cabId, Pageable pageable) {
        Cab cab = cabsManagerService.getCab(cabId);
        return tripRepository.findByCabOrderByBookingDate(cab, pageable);
    }

    public Trip getTrip(@NonNull final Integer tripid) {
        return tripRepository.findById(tripid)
                .orElseThrow(() -> new TripNotFoundException("Invalid Trip Id."));
    }

    public EndRideResponse endTrip(@NonNull Integer tripId) {
        Trip trip = getTrip(tripId);
        if (trip.getStatus().equals(TripStatus.IN_PROGRESS)) {
            trip.endTrip();
            tripRepository.save(trip);
        } else {
            throw new TripNotFoundException("Trip already ended.");
        }

        Cab cab = trip.getCab();
        cabsManagerService.makeCabIdle(cab);
        // hard coding the cost for now, otherwise we can introduce Pricing strategy to calculate costs of trip.
        return EndRideResponse.builder().tripId(tripId).tripCost(400l).build();
    }
}
