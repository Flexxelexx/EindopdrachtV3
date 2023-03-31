package com.example.eindopdrachtbackendv1.repositories;

import com.example.eindopdrachtbackendv1.models.Gear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GearRepository extends JpaRepository <Gear, Long> {
}
