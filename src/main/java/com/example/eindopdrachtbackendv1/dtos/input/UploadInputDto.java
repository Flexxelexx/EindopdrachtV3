package com.example.eindopdrachtbackendv1.dtos.input;

import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class UploadInputDto {


    public Long id;

    public Double weightFish;

    public Double lengthFish;

    public String charsFish;

    public String speciesFish;

    public byte[] photoFish;

    public String locationCaught;
}