package com.example.eindopdrachtbackendv1.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "users")
public class User {


    @Id
    @GeneratedValue
    private Long id;
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

    @ManyToMany
    private Set<Role> roles;
    @ManyToMany
    private List<FishingSpot> fishingSpots;
    @OneToMany
    private List<Upload> uploads;

    @OneToMany
    private List<Gear> gears;

    public User() {

    }

    public User(Long id, String username, String password, String email, LocalDate dob, Set<Role> roles, List<FishingSpot> fishingSpots, List<Upload> uploads, List<Gear> gears) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.dob = dob;
        this.roles = roles;
        this.fishingSpots = fishingSpots;
        this.uploads = uploads;
        this.gears = gears;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<FishingSpot> getFishingSpots() {
        return fishingSpots;
    }

    public void setFishingSpots(List<FishingSpot> fishingSpots) {
        this.fishingSpots = fishingSpots;
    }

    public List<Upload> getUploads() {
        return uploads;
    }

    public void setUploads(List<Upload> uploads) {
        this.uploads = uploads;
    }

    public List<Gear> getGears() {
        return gears;
    }

    public void setGears(List<Gear> gears) {
        this.gears = gears;
    }

    public void addFishingSpot(FishingSpot fishingSpot) {
        fishingSpots.add(fishingSpot);
    }

    public void addUpload(Upload upload) {
        uploads.add(upload);
    }

    public void addGear(Gear gear) {
        gears.add(gear);
    }

}
