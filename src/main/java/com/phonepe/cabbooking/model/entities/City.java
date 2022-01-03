package com.phonepe.cabbooking.model.entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import java.util.Set;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer cityId;

    private String cityName;

    private String xCoordinates;

    private String yCoordinates;

    public City(String cityName, String xcoordinates, String ycoordinates) {
        this.cityName = cityName;
        this.xCoordinates = xcoordinates;
        this.yCoordinates = ycoordinates;
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "city")
    private Set<Cab> cabs;

    @JsonManagedReference
    @OneToMany(mappedBy = "city")
    private Set<Trip> trips;

    @Override
    public String toString() {
        return "City{" +
                "cityId=" + cityId +
                ", cityName='" + cityName + '\'' +
                ", xCoordinates='" + xCoordinates + '\'' +
                ", yCoordinates='" + yCoordinates + '\'' +
                '}';
    }
}
