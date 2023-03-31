package com.example.eindopdrachtbackendv1.repositories;

import com.example.eindopdrachtbackendv1.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    Role findByRolename(String user);
}
