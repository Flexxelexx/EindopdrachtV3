package com.example.eindopdrachtbackendv1.services;

import com.example.eindopdrachtbackendv1.models.Role;
import com.example.eindopdrachtbackendv1.models.User;
import com.example.eindopdrachtbackendv1.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class RoleService {
    private final UserRepository userRepos;

    public RoleService(UserRepository userRepos) {
        this.userRepos = userRepos;
    }

    public Set<Role> GetRole(String username){

        Optional<User> optionalUser = userRepos.findByUsername(username);
        Set<Role> userRole = optionalUser.get().getRoles();

        return userRole;
    }

}
