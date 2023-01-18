package com.example.eindopdrachtbackendv1.models;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "fishingspots")
public class FishingSpot {
    @Id
    @Column
    private String spotLocation;

    private String city;

    private String region;

    private String accessibility;


    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<User> users;


    public FishingSpot() {

    }

    public FishingSpot(String spotLocation, String city, String region, String accessibility, Collection<User> users) {
        this.spotLocation = spotLocation;
        this.city = city;
        this.region = region;
        this.accessibility = accessibility;
        this.users = users;
    }

    public String getSpotLocation() {
        return spotLocation;
    }

    public void setSpotLocation(String spotLocation) {
        this.spotLocation = spotLocation;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAccessibility() {
        return accessibility;
    }

    public void setAccessibility(String accessibility) {
        this.accessibility = accessibility;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }
}
