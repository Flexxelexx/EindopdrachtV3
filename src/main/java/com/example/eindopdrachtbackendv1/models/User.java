package com.example.eindopdrachtbackendv1.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.time.LocalDate;
import java.util.Collection;


@Entity
@Table(name = "users")
public class User {


    @Id
    @Column
    private String username;
    @Column
    @NotBlank
    @Size(min = 6)
    private String password;
    @Column
    @Email
    private String email;
    @Column
    private LocalDate dob;

    // relations
    @OneToOne
    private Portfolio portfolios;
    @ManyToMany
    private Collection<Role> roles;
    @OneToMany
    private Collection<FishingSpot> fishingSpots;
    @OneToMany
    private Collection<Upload> uploads;

// eager??

    public User() {

    }

    public User(String username, String password, String email, LocalDate dob, Collection<Role> roles, Collection<FishingSpot> fishingSpots, Portfolio portfolios, Collection<Upload> uploads) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.dob = dob;
        this.roles = roles;
        this.fishingSpots = fishingSpots;
        this.portfolios = portfolios;
        this.uploads = uploads;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public Collection<FishingSpot> getFishingSpots() {
        return fishingSpots;
    }

    public void setFishingSpots(Collection<FishingSpot> fishingSpots) {
        this.fishingSpots = fishingSpots;
    }
// add fishingspot

    public Portfolio getPortfolios() {
        return portfolios;
    }

    public void setPortfolios(Portfolio portfolios) {
        this.portfolios = portfolios;
    }

    public Collection<Upload> getUploads() {
        return uploads;
    }

    public void setUploads(Collection<Upload> uploads) {
        this.uploads = uploads;
    }
}
