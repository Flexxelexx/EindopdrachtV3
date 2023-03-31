package com.example.eindopdrachtbackendv1.repositories;

import com.example.eindopdrachtbackendv1.models.FishingSpot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface FishingSpotRepository extends JpaRepository <FishingSpot, Long> {
    void deleteBySpotLocation(String spotLocation);
    Optional <FishingSpot> findBySpotLocation (String spotLocation);
    boolean existsBySpotLocation (String spotLocation);

    List<FishingSpot> findByCity (String city);

}
