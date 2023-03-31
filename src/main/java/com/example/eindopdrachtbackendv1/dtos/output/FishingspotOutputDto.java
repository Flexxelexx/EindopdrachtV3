package com.example.eindopdrachtbackendv1.dtos.output;

import lombok.Data;

@Data
public class FishingspotOutputDto {

    private Long id;

    private String spotLocation;

    private String city;

    private String region;

    public FishingspotOutputDto(String spot1, String city1, String description1) {
    }

    public FishingspotOutputDto() {

    }
}
