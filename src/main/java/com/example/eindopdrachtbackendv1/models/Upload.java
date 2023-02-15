package com.example.eindopdrachtbackendv1.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

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
    @NotNull
    @Column
    private String speciesfish;
    @Column
    @Lob
    private byte[] photoFish;


    @OneToMany(fetch = FetchType.EAGER)
    private Collection<User> users;

    private Rating rating;


    public Upload() {

    }

    public Upload(Long id, Double weightFish, Double lengthFish, String charsFish, String speciesfish, byte[] photoFish, Collection<User> users, Rating rating) {
        this.id = id;
        this.weightFish = weightFish;
        this.lengthFish = lengthFish;
        this.charsFish = charsFish;
        this.speciesfish = speciesfish;
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

    public Double getWeightFish() {
        return weightFish;
    }

    public void setWeightFish(double weightFish) {
        this.weightFish = weightFish;
    }

    public Double getLengthFish() {
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

    public String getSpeciesfish() {
        return speciesfish;
    }

    public void setSpeciesfish(String speciesfish) {
        this.speciesfish = speciesfish;
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
