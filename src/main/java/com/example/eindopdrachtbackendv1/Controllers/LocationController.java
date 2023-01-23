package com.example.eindopdrachtbackendv1.Controllers;

import com.example.eindopdrachtbackendv1.Services.LocationService;
import com.example.eindopdrachtbackendv1.models.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationController {

    @Autowired
    private LocationService locationService;

    @PostMapping("/location")
    public void saveLocation(@RequestBody Location location) {
        locationService.saveLocation(location);
    }
}
