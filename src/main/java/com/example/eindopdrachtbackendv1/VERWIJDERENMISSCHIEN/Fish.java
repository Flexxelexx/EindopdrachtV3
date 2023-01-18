//package com.example.eindopdrachtbackendv1.models;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Entity
//@Table(name = "fishes")
//public class Fish {
//
//    @Id
//    @GeneratedValue
//    private Long id;
//
//
//
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "fish")
//    private List<Upload> uploads;
//
//    public Fish() {
//
//    }
//
//    public Fish(Long id, String charsFish, String speciesFish, List<Upload> uploads) {
//        this.id = id;
//        this.charsFish = charsFish;
//        this.speciesFish = speciesFish;
//        this.uploads = uploads;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getCharsFish() {
//        return charsFish;
//    }
//
//    public void setCharsFish(String charsFish) {
//        this.charsFish = charsFish;
//    }
//
//    public String getSpeciesFish() {
//        return speciesFish;
//    }
//
//    public void setSpeciesFish(String speciesFish) {
//        this.speciesFish = speciesFish;
//    }
//
//    public List<Upload> getUploads() {
//        return uploads;
//    }
//
//    public void setUploads(List<Upload> uploads) {
//        this.uploads = uploads;
//    }
//}

