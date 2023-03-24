package com.example.eindopdrachtbackendv1.controllers;

import com.example.eindopdrachtbackendv1.dtos.RoleDTO;
import com.example.eindopdrachtbackendv1.repositories.RoleRepository;
import com.example.eindopdrachtbackendv1.models.Role;
import com.example.eindopdrachtbackendv1.services.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping(value = "/roles")
public class RoleController {

    private final RoleRepository repos;

    private final RoleService roleService;

    public RoleController(RoleRepository repos, RoleService roleService) {
        this.repos = repos;
        this.roleService = roleService;
    }

    @PostMapping("/roles")
    public String createRole(@RequestBody RoleDTO role) {
        Role newRole = new Role();
        newRole.setRolename(role.rolename);
        repos.save(newRole);

        return "Done";
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity<Collection<Role>> getRole(@PathVariable("username") String username){
        Collection<Role> role = roleService.GetRole(username);

        return ResponseEntity.ok().body(role);
    }

}
