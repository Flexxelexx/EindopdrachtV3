package com.example.eindopdrachtbackendv1.dtos.input;

import com.example.eindopdrachtbackendv1.dtos.FileUploadResponse;
import lombok.Data;

@Data
public class UploadGearInputDto {

    public Double weightFish;

    public Double lengthFish;

    public String charsFish;

    public String speciesFish;

    public String locationCaught;

    public String cityCaught;

    public FileUploadResponse file;

    private Double rodLength;

    private String kindOfReel;

    private String kindOfLure;

    private String lineLength;
}
