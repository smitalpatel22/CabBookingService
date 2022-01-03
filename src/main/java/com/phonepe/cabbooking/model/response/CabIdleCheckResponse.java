package com.phonepe.cabbooking.model.response;

import com.phonepe.cabbooking.model.enums.CabState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CabIdleCheckResponse {

    private Long idleTime;
}
