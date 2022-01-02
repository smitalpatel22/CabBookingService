package com.phonepe.cabbooking.database;

import com.phonepe.cabbooking.exceptions.InvalidCabException;
import com.phonepe.cabbooking.model.entities.Cab;
import com.phonepe.cabbooking.model.enums.CabState;
import com.phonepe.cabbooking.model.entities.City;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.phonepe.cabbooking.model.request.CabRegisterRequest;
import com.phonepe.cabbooking.model.request.CabUpdateRequest;
import com.phonepe.cabbooking.repository.CabsRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CabsManagerService {

    @Autowired
    private CabsRepository cabsRepository;

    @Autowired
    private CityManagerService cityService;

    public void registerCabs(List<CabRegisterRequest> requests) {
        List<Cab> cabsToBeRegisterd = new ArrayList<>();
        for(CabRegisterRequest request : requests) {
            City city = cityService.getCityById(request.getCityId());
            Cab cab = new Cab(request.getCabId(), CabState.IDLE, request.getDriverName(), city);
            cabsToBeRegisterd.add(cab);
        }
        cabsRepository.saveAll(cabsToBeRegisterd);
    }

    public Cab getCab(@NonNull final Integer cabId) {
        return cabsRepository.findById(cabId)
                .orElseThrow(() -> new InvalidCabException("Invalid Cab Id"));
    }

    public void updateCabLocation(@NonNull final Integer cabId, CabUpdateRequest request) {
        Cab cab = getCab(cabId);
        City city = cityService.getCityById(request.getCityId());
        cab.setCity(city);
        cabsRepository.save(cab);
    }

    public void updateCabAvailability(@NonNull Cab cab, CabState cabState) {
        cab.setCabState(cabState);
        cab.setLastBookedTime(new Date());
        cabsRepository.save(cab);
    }

    public Optional<Cab> getAvailableCabForLocation(@NonNull final City originCity) {
        return cabsRepository.findFirstByCityAndCabStateOrderByLastBookedTime(originCity, CabState.IDLE);
    }

    public void makeCabIdle(Cab cab) {
        if(cab.getCabState().equals(CabState.ON_TRIP)) {
            cab.setLastBookedTime(new Date());  //Keeping booking & ending time field same for now
            cab.makeCabIdle();
            cabsRepository.save(cab);
        } else {
            throw new InvalidCabException("Cab is already Idle");
        }
    }
}
