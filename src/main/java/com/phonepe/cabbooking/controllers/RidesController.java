package com.phonepe.cabbooking.controllers;

import com.phonepe.cabbooking.database.RidesManagerService;

import com.phonepe.cabbooking.model.request.BookRideRequest;
import com.phonepe.cabbooking.model.response.BookRideResponse;
import com.phonepe.cabbooking.model.response.EndRideResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ride")
public class RidesController {

    @Autowired
    private RidesManagerService ridesManagerService;


    @PostMapping(value = "/book", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookRideResponse> bookRide(@RequestBody BookRideRequest request) {
        BookRideResponse response = ridesManagerService.bookRide(request);
        return new ResponseEntity(response, HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/end/{tripId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EndRideResponse> endTrip(@PathVariable Integer tripId) {
        EndRideResponse response = ridesManagerService.endTrip(tripId);
        return new ResponseEntity(response, HttpStatus.ACCEPTED);
    }



}
