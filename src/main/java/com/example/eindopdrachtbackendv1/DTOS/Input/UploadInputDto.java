package com.example.eindopdrachtbackendv1.DTOS.Input;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


@Data
public class UploadInputDto {


    private Long id;

    @Min(value = 0)
    @Max(value = 250)
    private double weightFish;

    @Min(value = 0)
    @Max(value = 100)
    private double lengthFish;

    private String charsFish;

    @NotBlank
    private String speciesFish;

    private byte[] photoFish;
}