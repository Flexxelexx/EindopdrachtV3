package com.example.eindopdrachtbackendv1.DTOS;


import com.example.eindopdrachtbackendv1.models.FishingSpot;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

public class FishingSpotDTO {

    @Id
    private Long id;
    @NotBlank
    private String spotLocation;
    @NotBlank
    private String city;
    private String region;
    private String accesibility;


public static FishingSpotDTO fromFishingSpot (FishingSpot fishingSpot) {

    FishingSpotDTO dto = new FishingSpotDTO();

    dto.setId(fishingSpot.getId());
    dto.setSpotLocation(fishingSpot.getSpotLocation());
    dto.setCity(fishingSpot.getCity());
    dto.setRegion(fishingSpot.getRegion());
    dto.setAccesibility(fishingSpot.getAccessibility());

    return dto;
}

public static FishingSpot toFishingspot (FishingSpotDTO fishingSpotDTO) {

    FishingSpot fishingSpot = new FishingSpot();

    fishingSpot.setId(fishingSpotDTO.getId());
    fishingSpot.setSpotLocation(fishingSpotDTO.getSpotLocation());
    fishingSpot.setCity(fishingSpotDTO.getCity());
    fishingSpot.setRegion(fishingSpotDTO.getRegion());
    fishingSpot.setAccessibility(fishingSpotDTO.getAccesibility());

    return fishingSpot;
}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpotLocation() {
        return spotLocation;
    }

    public void setSpotLocation(String spotLocation) {
        this.spotLocation = spotLocation;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAccesibility() {
        return accesibility;
    }

    public void setAccesibility(String accesibility) {
        this.accesibility = accesibility;
    }
}
