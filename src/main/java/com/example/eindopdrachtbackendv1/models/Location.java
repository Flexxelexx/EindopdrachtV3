package com.example.eindopdrachtbackendv1.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String longitude;

    @Column(nullable = false)
    private String latitude;

    @ManyToOne
    private User users;

    public Location() {

    }

    public Location(Long id, String longitude, String latitude, User user) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.users = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public User getUser() {
        return users;
    }

    public void setUser(User user) {
        this.users = user;
    }
}
