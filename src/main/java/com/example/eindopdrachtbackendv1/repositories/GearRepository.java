package com.example.eindopdrachtbackendv1.repositories;

import com.example.eindopdrachtbackendv1.models.Gear;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GearRepository extends JpaRepository <Gear, Long> {
}
