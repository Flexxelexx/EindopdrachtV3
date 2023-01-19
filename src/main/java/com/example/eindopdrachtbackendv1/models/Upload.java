package com.example.eindopdrachtbackendv1.models;

import javax.persistence.*;
import java.util.Collection;


@Entity
public class Upload {

    @Id
    @GeneratedValue
    private Long id;
    @Column
    private double weightFish;
    @Column
    private double lengthFish;
    @Column
    private String charsFish;
    @Column
    private String speciesFish;
    @Column
    @Lob
    private byte[] photoFish;


    @OneToMany(fetch = FetchType.EAGER)
    private Collection<User> users;

    private Rating rating;


    public Upload() {

    }

    public Upload(Long id, double weightFish, double lengthFish, String charsFish, String speciesFish, byte[] photoFish, Collection<User> users, Rating rating) {
        this.id = id;
        this.weightFish = weightFish;
        this.lengthFish = lengthFish;
        this.charsFish = charsFish;
        this.speciesFish = speciesFish;
        this.photoFish = photoFish;
        this.users = users;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getWeightFish() {
        return weightFish;
    }

    public void setWeightFish(double weightFish) {
        this.weightFish = weightFish;
    }

    public double getLengthFish() {
        return lengthFish;
    }

    public void setLengthFish(double lengthFish) {
        this.lengthFish = lengthFish;
    }

    public String getCharsFish() {
        return charsFish;
    }

    public void setCharsFish(String charsFish) {
        this.charsFish = charsFish;
    }

    public String getSpeciesFish() {
        return speciesFish;
    }

    public void setSpeciesFish(String speciesFish) {
        this.speciesFish = speciesFish;
    }

    public byte[] getPhotoFish() {
        return photoFish;
    }

    public void setPhotoFish(byte[] photoFish) {
        this.photoFish = photoFish;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }


    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }
}
