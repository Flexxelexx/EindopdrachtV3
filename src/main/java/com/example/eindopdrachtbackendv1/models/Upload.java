package com.example.eindopdrachtbackendv1.models;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.File;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "uploads")
public class Upload {
    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "upload_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1011"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    private Long id;
    @Column
    private Double weightFish;
    @Column
    private Double lengthFish;
    @Column
    private String charsFish;
    @Column
    private String speciesfish;
    @Column
    private String locationCaught;

    @Column
    private String cityCaught;


    @ManyToOne
    private User users;

    @OneToOne(fetch = FetchType.LAZY)
    FileDocument file;

    @OneToOne
    Gear gear;


    public Upload() {

    }

    public Upload(Long id, Double weightFish, Double lengthFish, String charsFish, String speciesfish, User users, String locationCaught, String cityCaught, Gear gear) {
        this.id = id;
        this.weightFish = weightFish;
        this.lengthFish = lengthFish;
        this.charsFish = charsFish;
        this.speciesfish = speciesfish;
        this.users = users;
        this.locationCaught = locationCaught;
        this.cityCaught = cityCaught;
        this.gear = gear;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getWeightFish() {
        return weightFish;
    }

    public void setWeightFish(Double weightFish) {
        this.weightFish = weightFish;
    }

    public Double getLengthFish() {
        return lengthFish;
    }

    public void setLengthFish(Double lengthFish) {
        this.lengthFish = lengthFish;
    }

    public String getCharsFish() {
        return charsFish;
    }

    public void setCharsFish(String charsFish) {
        this.charsFish = charsFish;
    }

    public String getSpeciesfish() {
        return speciesfish;
    }

    public void setSpeciesfish(String speciesfish) {
        this.speciesfish = speciesfish;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public String getLocationCaught() {
        return locationCaught;
    }

    public void setLocationCaught(String locationCaught) {
        this.locationCaught = locationCaught;
    }

    public String getCityCaught() {
        return cityCaught;
    }

    public void setCityCaught(String cityCaught) {
        this.cityCaught = cityCaught;
    }

    public FileDocument getFile() {
        return file;
    }

    public void setFile(FileDocument file) {
        this.file = file;
    }

    public Gear getGear() {
        return gear;
    }

    public void setGear(Gear gear) {
        this.gear = gear;
    }

}
