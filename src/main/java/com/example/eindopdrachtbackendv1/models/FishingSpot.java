package com.example.eindopdrachtbackendv1.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "fishingspots")
public class FishingSpot {
    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "fishingspot_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1011"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
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
