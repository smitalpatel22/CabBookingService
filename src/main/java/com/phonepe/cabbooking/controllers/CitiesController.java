package com.phonepe.cabbooking.controllers;

import com.phonepe.cabbooking.service.CityManagerService;
import com.phonepe.cabbooking.model.request.CityRegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CitiesController {

    @Autowired
    private CityManagerService cityService;

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity registerCity(@RequestBody List<CityRegisterRequest> request) {
        cityService.registerCities(request);
        return ResponseEntity.ok("Cities registered Successfully.");
    }

    @GetMapping(value = "/cityStats", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity cityStats() {
        cityService.cityStats();
        return ResponseEntity.ok("Cities registered Successfully.");
    }
}
