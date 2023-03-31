package com.example.eindopdrachtbackendv1.dtos.input;

import lombok.Data;

@Data
public class FishingspotInputDto {

    private Long id;

    private String spotLocation;

    private String city;

    private String region;

    public FishingspotInputDto(Long l, String spot1, String city1, String description1) {
    }
}
