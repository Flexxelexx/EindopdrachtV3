package com.example.eindopdrachtbackendv1.dtos.output;

import com.example.eindopdrachtbackendv1.dtos.FileUploadResponse;
import lombok.Data;

@Data
public class UploadOutputDto {

    public Long id;

    public Double weightFish;

    public Double lengthFish;

    public String charsFish;

    public String speciesFish;

    public String locationCaught;

    public String cityCaught;

    public FileUploadResponse file;


}
