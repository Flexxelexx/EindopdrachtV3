package com.example.eindopdrachtbackendv1.services;

import com.example.eindopdrachtbackendv1.repositories.LocationRepository;
import com.example.eindopdrachtbackendv1.models.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public void saveLocation(Location location) {
        locationRepository.save(location);
    }
}
