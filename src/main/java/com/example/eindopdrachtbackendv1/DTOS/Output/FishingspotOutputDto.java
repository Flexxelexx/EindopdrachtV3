package com.example.eindopdrachtbackendv1.DTOS.Output;

import lombok.Data;

@Data
public class FishingspotOutputDto {

    private Long id;

    private String spotLocation;

    private String city;

    private String region;
}
