package com.example.eindopdrachtbackendv1.DTOS.Output;

import lombok.Data;

import javax.persistence.Id;

@Data
public class RoleOutputDto {

    @Id
    private String rolename;

}
