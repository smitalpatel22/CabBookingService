package com.phonepe.cabbooking.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.phonepe.cabbooking.model.enums.CabState;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Cab {
    @Id
    private Integer cabId;

    public Cab(Integer cabId, CabState cabState, String driverName, City city) {
        this.cabId = cabId;
        this.cabState = cabState;
        this.driverName = driverName;
        this.city = city;
    }

    @Enumerated(EnumType.STRING)
    private CabState cabState = CabState.IDLE;

    private String driverName;

    private Date lastBookedTime = new Date();

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="city_id", nullable=false)
    private City city;

    @JsonManagedReference
    @OneToMany(mappedBy = "cab")
    private Set<Trip> trips;

    public Cab(Integer cabId, CabState cabState) {
        this.cabId = cabId;
        this.cabState = cabState;
    }

    public Cab(CabState cabState, Date lastBookedTime, City city) {
        this.cabState = cabState;
        this.lastBookedTime = lastBookedTime;
        this.city = city;
    }

    public void makeCabOnTrip() {
        this.cabState = CabState.ON_TRIP;
    }

    public void makeCabIdle() {
        this.cabState = CabState.IDLE;
    }

    @Override
    public String toString() {
        return "Cab{" +
                "cabId=" + cabId +
                ", driverName='" + driverName + '\'' +
                '}';
    }
}
