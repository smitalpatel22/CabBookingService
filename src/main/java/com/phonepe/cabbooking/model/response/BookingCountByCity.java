package com.phonepe.cabbooking.model.response;

import com.phonepe.cabbooking.model.entities.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
public class BookingCountByCity {
    private City city;
    private Long total;

    public BookingCountByCity(City city, Long total) {
        this.city = city;
        this.total = total;
    }
}
