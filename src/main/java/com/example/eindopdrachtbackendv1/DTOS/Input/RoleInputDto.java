package com.example.eindopdrachtbackendv1.DTOS.Input;

import lombok.Data;

import javax.persistence.Id;

@Data
public class RoleInputDto {

    @Id
    private String rolename;

}
