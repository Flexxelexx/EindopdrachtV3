package com.example.eindopdrachtbackendv1.DTOS.Output;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Data
public class UserOutputDto {

    private Long id;

    private String username;

    @NotBlank
    @Size(min = 6)
    private String password;

    @Email
    private String email;

    private LocalDate dob;

    private Boolean isVerified;

    private List<Long> gearIds;
}
