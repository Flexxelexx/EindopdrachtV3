package com.example.eindopdrachtbackendv1.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY,
            generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "user_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1002"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    private Long id;

    @Column
    private String firstname;
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
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dob;

    @Lob
    private byte[] profilepicture;


    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
    @ManyToMany
    private List<FishingSpot> fishingSpots;
    @OneToMany
    private List<Upload> uploads;

    @OneToMany(mappedBy = "user")
    private List<Gear> gears;

    @OneToMany
    private List<Location> locations;

    @OneToOne
    FileUploadResponse file;

    public User() {

    }

    public User(Long id, String firstname, String username, String password, String email, LocalDate dob, Set<Role> roles, List<FishingSpot> fishingSpots, List<Upload> uploads, List<Gear> gears, List<Location> locations, byte[] profilepicture ) {
        this.id = id;
        this.firstname = firstname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.dob = dob;
        this.roles = roles;
        this.fishingSpots = fishingSpots;
        this.uploads = uploads;
        this.gears = gears;
        this.locations = locations;
        this.profilepicture = profilepicture;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
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

    public void addLocation(Location location) {
        locations.add(location);
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }



}
