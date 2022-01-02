package com.phonepe.cabbooking.model.request;

import com.phonepe.cabbooking.model.enums.CabState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CabUpdateRequest {

    private Integer cityId;

    private CabState cabState;
}
