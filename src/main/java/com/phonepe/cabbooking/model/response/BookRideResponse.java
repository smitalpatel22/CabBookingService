package com.phonepe.cabbooking.model.response;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class BookRideResponse {

    private String driverName;
    private Integer cabId;
    private Integer tripId;
}
