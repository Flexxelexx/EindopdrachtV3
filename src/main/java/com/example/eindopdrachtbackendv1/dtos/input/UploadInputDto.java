package com.example.eindopdrachtbackendv1.dtos.input;

import com.example.eindopdrachtbackendv1.dtos.FileUploadResponse;
import lombok.Data;


@Data
public class UploadInputDto {


    public Long id;

    public Double weightFish;

    public Double lengthFish;

    public String charsFish;

    public String speciesFish;

    public String locationCaught;

    public String cityCaught;

    public FileUploadResponse file;

    public UploadInputDto(Double weightFish, Double lengthFish, String charsFish, String speciesFish, String locationCaught, String cityCaught, FileUploadResponse file) {
        this.weightFish = weightFish;
        this.lengthFish = lengthFish;
        this.charsFish = charsFish;
        this.speciesFish = speciesFish;
        this.locationCaught = locationCaught;
        this.cityCaught = cityCaught;
        this.file = file;
    }

    public UploadInputDto() {

    }

    public UploadInputDto(Long id, Double weightFish, Double lengthFish, String charsFish, String speciesFish, String locationCaught, String cityCaught, FileUploadResponse file) {
        this.id = id;
        this.weightFish = weightFish;
        this.lengthFish = lengthFish;
        this.charsFish = charsFish;
        this.speciesFish = speciesFish;
        this.locationCaught = locationCaught;
        this.cityCaught = cityCaught;
        this.file = file;
    }
}