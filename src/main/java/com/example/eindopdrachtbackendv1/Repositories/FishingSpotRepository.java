package com.example.eindopdrachtbackendv1.Repositories;

import com.example.eindopdrachtbackendv1.models.FishingSpot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FishingSpotRepository extends JpaRepository <FishingSpot, Long> {
    void deleteBySpotLocation(String spotLocation);
    Optional <FishingSpot> findBySpotLocation (String spotLocation);
    boolean existsBySpotLocation (String spotLocation);

}
