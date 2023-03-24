package com.example.eindopdrachtbackendv1.dtos.output;

import com.example.eindopdrachtbackendv1.models.Role;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
public class UserOutputDto {

    private Long id;

    private String firstname;

    private String username;

    @NotBlank
    @Size(min = 6)
    private String password;

    @Email
    private String email;

    private LocalDate dob;

    private List <Long> uploadIds;

    private Set<String> role;

}
