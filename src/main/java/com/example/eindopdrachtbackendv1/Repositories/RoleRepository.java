package com.example.eindopdrachtbackendv1.Repositories;

import com.example.eindopdrachtbackendv1.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, String> {

    Role findByRolename(String user);
}
