package com.example.eindopdrachtbackendv1.controllers;

import com.example.eindopdrachtbackendv1.dtos.RoleDTO;
import com.example.eindopdrachtbackendv1.repositories.RoleRepository;
import com.example.eindopdrachtbackendv1.models.Role;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

    private final RoleRepository repos;

    public RoleController(RoleRepository repos) {
        this.repos = repos;
    }
    @PostMapping("/roles")
    public String createRole(@RequestBody RoleDTO role) {
        Role newRole = new Role();
        newRole.setRolename(role.rolename);
        repos.save(newRole);

        return "Done";
    }
}
