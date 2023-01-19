package com.example.eindopdrachtbackendv1.models;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "fishingspots")
public class FishingSpot {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String spotLocation;
    @Column
    private String city;
    @Column
    private String region;


    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<User> users;




    public FishingSpot() {

    }

    public FishingSpot(Long id, String spotLocation, String city, String region, Collection<User> users) {
        this.id = id;
        this.spotLocation = spotLocation;
        this.city = city;
        this.region = region;
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }
}
