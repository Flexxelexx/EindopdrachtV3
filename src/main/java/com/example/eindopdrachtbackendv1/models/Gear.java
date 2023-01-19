package com.example.eindopdrachtbackendv1.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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


    public Gear() {
    }

    public Gear(Long id, String rodLength, String kindOfReel, String lure, double line) {
        this.id = id;
        this.rodLength = rodLength;
        this.kindOfReel = kindOfReel;
        this.lure = lure;
        this.line = line;
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

}
