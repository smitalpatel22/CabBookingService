package com.phonepe.cabbooking.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CabRegisterRequest {

    private Integer cabId;
    private Integer cityId;
    private String driverName;
}
