package com.example.eindopdrachtbackendv1.dtos.output;

import com.example.eindopdrachtbackendv1.models.FileUploadResponse;
import com.example.eindopdrachtbackendv1.models.Rating;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Data
public class UploadOutputDto {

    public Long id;

    public Double weightFish;

    public Double lengthFish;

    public String charsFish;

    public String speciesFish;

    public String locationCaught;

    public String cityCaught;

    public Rating rating;

    public FileUploadResponse file;
}
