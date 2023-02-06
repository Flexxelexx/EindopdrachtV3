package com.example.eindopdrachtbackendv1.models;

import javax.persistence.*;
import java.util.Collection;


@Entity
public class Gear {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String rodLength;

    @Column
    private String kindOfReel;

    @Column
    private String lure;

    @Column
    private double line;

    @ManyToOne
    private User user;


    public Gear() {
    }

    public Gear(Long id, String rodLength, String kindOfReel, String lure, double line, User users) {
        this.id = id;
        this.rodLength = rodLength;
        this.kindOfReel = kindOfReel;
        this.lure = lure;
        this.line = line;
        this.user = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRodLength() {
        return rodLength;
    }

    public void setRodLength(String rodLength) {
        this.rodLength = rodLength;
    }

    public String getKindOfReel() {
        return kindOfReel;
    }

    public void setKindOfReel(String kindOfReel) {
        this.kindOfReel = kindOfReel;
    }

    public String getLure() {
        return lure;
    }

    public void setLure(String lure) {
        this.lure = lure;
    }

    public double getLine() {
        return line;
    }

    public void setLine(double line) {
        this.line = line;
    }

    public User getUsers() {
        return user;
    }

    public void setUsers(User users) {
        this.user = users;
    }
}
