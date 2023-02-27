package com.example.eindopdrachtbackendv1.models;

import javax.persistence.*;


@Entity
public class Gear {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Double rodLength;

    @Column
    private String kindOfReel;

    @Column
    private String kindOfLure;

    @Column
    private String lineLength;

    @OneToOne(mappedBy = "gear")
    Upload upload;


    public Gear() {
    }

    public Gear(Long id, Double rodLength, String kindOfReel, String kindOfLure, String line, User users, Upload upload) {
        this.id = id;
        this.rodLength = rodLength;
        this.kindOfReel = kindOfReel;
        this.kindOfLure = kindOfLure;
        this.lineLength = line;
        this.upload = upload;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getRodLength() {
        return rodLength;
    }

    public void setRodLength(Double rodLength) {
        this.rodLength = rodLength;
    }

    public String getKindOfReel() {
        return kindOfReel;
    }

    public void setKindOfReel(String kindOfReel) {
        this.kindOfReel = kindOfReel;
    }

    public String getKindOfLure() {
        return kindOfLure;
    }

    public void setKindOfLure(String lure) {
        this.kindOfLure = lure;
    }

    public String getLineLength() {
        return lineLength;
    }

    public void setLineLength(String line) {
        this.lineLength = line;
    }

    public Upload getUpload() {
        return upload;
    }

    public void setUpload(Upload upload) {
        this.upload = upload;
    }

}
