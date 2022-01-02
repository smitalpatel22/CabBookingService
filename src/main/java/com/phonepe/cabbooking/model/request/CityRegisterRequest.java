package com.phonepe.cabbooking.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CityRegisterRequest {
    private String cityName;
    private String xCoordinates;
    private String yCoordinates;
}
