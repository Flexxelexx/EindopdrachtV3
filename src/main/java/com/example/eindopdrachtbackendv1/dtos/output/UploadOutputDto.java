package com.example.eindopdrachtbackendv1.dtos.output;

import com.example.eindopdrachtbackendv1.models.Rating;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UploadOutputDto {

    public Long id;

    public Double weightFish;

    public Double lengthFish;

    public String charsFish;

    @NotNull
    public String speciesFish;

    public byte[] photoFish;

    public Rating rating;
}
