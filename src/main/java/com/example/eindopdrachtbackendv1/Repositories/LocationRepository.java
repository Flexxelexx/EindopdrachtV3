package com.example.eindopdrachtbackendv1.Repositories;

import com.example.eindopdrachtbackendv1.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository <Location, Long> {
}
