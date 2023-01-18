package com.example.eindopdrachtbackendv1.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;


import java.util.Collection;
import java.util.Date;

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
    private String photoFish;
    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy@mm:ss")
    private Date timeCaughtFish;

    @OneToMany (fetch = FetchType.EAGER)
    private Collection <User> users;

    @OneToMany (fetch = FetchType.EAGER)
    private Collection <Rating> ratings;

    @ManyToOne (fetch = FetchType.EAGER)
    private Portfolio portfolios;


    public Upload() {

    }

    public Upload(Long id, double weightFish, double lengthFish, String charsFish, String speciesFish, String photoFish, Date timeCaughtFish, Collection<User> users, Collection<Rating> ratings, Portfolio portfolios) {
        this.id = id;
        this.weightFish = weightFish;
        this.lengthFish = lengthFish;
        this.charsFish = charsFish;
        this.speciesFish = speciesFish;
        this.photoFish = photoFish;
        this.timeCaughtFish = timeCaughtFish;
        this.users = users;
        this.ratings = ratings;
        this.portfolios = portfolios;
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

    public String getPhotoFish() {
        return photoFish;
    }

    public void setPhotoFish(String photoFish) {
        this.photoFish = photoFish;
    }

    public Date getTimeCaughtFish() {
        return new Date();
    }

    public void setTimeCaughtFish(Date timeCaughtFish) {
        this.timeCaughtFish = timeCaughtFish;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public Collection<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(Collection<Rating> ratings) {
        this.ratings = ratings;
    }

    public Portfolio getPortfolios() {
        return portfolios;
    }

    public void setPortfolios(Portfolio portfolios) {
        this.portfolios = portfolios;
    }
}
