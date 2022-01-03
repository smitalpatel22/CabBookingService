package com.phonepe.cabbooking.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.phonepe.cabbooking.model.enums.TripStatus;
import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tripId;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="city_id", nullable=false)
    private City city;

    private Date bookingDate;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="cab_id", nullable=false)
    private Cab cab;

    @Enumerated(EnumType.STRING)
    private TripStatus status = TripStatus.IN_PROGRESS;

    public void endTrip() {
        this.status = TripStatus.FINISHED;
    }
}


