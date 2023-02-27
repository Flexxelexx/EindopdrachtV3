package com.example.eindopdrachtbackendv1.dtos.input;

import lombok.Data;


@Data
public class GearInputDto {


    private Long id;

    private Double rodLength;

    private String kindOfReel;

    private String lure;

    private String line;

    public GearInputDto(Double rodLength, String kindOfReel, String lure, String line) {
        this.rodLength = rodLength;
        this.kindOfReel = kindOfReel;
        this.lure = lure;
        this.line = line;
    }
}
