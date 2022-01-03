package com.phonepe.cabbooking.model.request;

import com.phonepe.cabbooking.model.enums.CabState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class CabIdleCheckRequest {

    private Date startDate;
    private Date endDate;
}
