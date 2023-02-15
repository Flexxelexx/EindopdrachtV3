package com.example.eindopdrachtbackendv1.DTOS.Input;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
public class UploadInputDto {


    public Long id;

    public Double weightFish;

    public Double lengthFish;

    public String charsFish;

    @NotNull
    public String speciesFish;

    public byte[] photoFish;
}