package com.example.eindopdrachtbackendv1.DTOS.Input;

import lombok.Data;

@Data
public class FishingspotInputDto {

    private Long id;

    private String spotLocation;

    private String city;

    private String region;
}
