package com.example.eindopdrachtbackendv1.DTOS.Output;

import com.example.eindopdrachtbackendv1.models.Rating;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class UploadOutputDto {

    public Long id;

    public Double weightFish;

    public Double lengthFish;

    public String charsFish;

    public String speciesFish;

    public byte[] photoFish;

    public Rating rating;
}
