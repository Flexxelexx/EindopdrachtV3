package com.example.eindopdrachtbackendv1.dtos.input;

import lombok.Data;


@Data
public class GearInputDto {


    private Long id;

    private Double rodLength;

    private String kindOfReel;

    private String kindOfLure;

    private String lineLength;

    public GearInputDto(Long id, Double rodLength, String kindOfReel, String kindOfLure, String lineLength) {
        this.id = id;
        this.rodLength = rodLength;
        this.kindOfReel = kindOfReel;
        this.kindOfLure = kindOfLure;
        this.lineLength = lineLength;
    }

    public GearInputDto() {
    }

    public GearInputDto(Double rodLength, String kindOfReel, String kindOfLure, String lineLength) {
        this.rodLength = rodLength;
        this.kindOfReel = kindOfReel;
        this.kindOfLure = kindOfLure;
        this.lineLength = lineLength;
    }
}
