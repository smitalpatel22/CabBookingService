package com.phonepe.cabbooking.controllers;

import com.phonepe.cabbooking.model.request.CabIdleCheckRequest;
import com.phonepe.cabbooking.model.response.CabIdleCheckResponse;
import com.phonepe.cabbooking.service.CabsManagerService;
import com.phonepe.cabbooking.service.RidesManagerService;
import com.phonepe.cabbooking.model.entities.Trip;
import com.phonepe.cabbooking.model.request.CabRegisterRequest;
import com.phonepe.cabbooking.model.request.CabUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cab")
public class CabsController {

    @Autowired
    private CabsManagerService cabsManagerService;

    @Autowired
    private RidesManagerService ridesManagerService;

    @PostMapping(value = "register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity registerCab(@RequestBody List<CabRegisterRequest> request) {
        cabsManagerService.registerCabs(request);
        return ResponseEntity.ok("Cabs registered Successfully.");
    }

    @PatchMapping(value = "{cabId}/location", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateCabLocation(@PathVariable Integer cabId, @RequestBody CabUpdateRequest cabUpdateRequest) {
        cabsManagerService.updateCabLocation(cabId, cabUpdateRequest);
        return ResponseEntity.ok("Cab City updated Successfully.");
    }

    @GetMapping(value = "/history/{cabId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Trip>> cabTripHistory(@PathVariable Integer cabId, Pageable pageable) {
        Page<Trip> response = ridesManagerService.cabTripHistory(cabId, pageable);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping(value = "/idleStats/{cabId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CabIdleCheckResponse> cabIdleStats(@PathVariable Integer cabId, @RequestBody CabIdleCheckRequest request) {
        CabIdleCheckResponse cabIdleCheckResponse = ridesManagerService.cabIdleStats(cabId, request.getStartDate(), request.getEndDate());
        return new ResponseEntity(cabIdleCheckResponse, HttpStatus.OK);
    }
}
