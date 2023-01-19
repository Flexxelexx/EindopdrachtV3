package com.example.eindopdrachtbackendv1.models;

import javax.persistence.*;
import java.util.Collection;


@Entity
public class Gear {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String rodLength;

    @Column
    private String kindOfReel;

    @Column
    private String lure;

    @Column
    private double line;

    @OneToMany
    private Collection<User> users;


    public Gear() {
    }

    public Gear(Long id, String rodLength, String kindOfReel, String lure, double line, Collection<User> users) {
        this.id = id;
        this.rodLength = rodLength;
        this.kindOfReel = kindOfReel;
        this.lure = lure;
        this.line = line;
        this.users = users;
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

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }
}
