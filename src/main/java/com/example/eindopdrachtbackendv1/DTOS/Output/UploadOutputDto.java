package com.example.eindopdrachtbackendv1.DTOS.Output;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class UploadOutputDto {

    public Long id;

    @Min(value = 0)
    @Max(value = 250)
    public double weightFish;

    @Min(value = 0)
    @Max(value = 100)
    public double lengthFish;

    public String charsFish;

    @NotBlank
    public String speciesFish;

    public byte[] photoFish;

}
