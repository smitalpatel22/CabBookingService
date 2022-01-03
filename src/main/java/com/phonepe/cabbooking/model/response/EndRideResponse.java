package com.phonepe.cabbooking.model.response;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class EndRideResponse {

    private Integer tripId;

    private Long tripCost;
}
