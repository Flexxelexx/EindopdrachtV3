package com.example.eindopdrachtbackendv1.controllers;

import com.example.eindopdrachtbackendv1.dtos.input.FishingspotInputDto;
import com.example.eindopdrachtbackendv1.dtos.output.FishingspotOutputDto;
import com.example.eindopdrachtbackendv1.services.FishingSpotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/fishingspots")
public class FishingSpotController {

    private FishingSpotService fishingSpotService;

    public FishingSpotController(FishingSpotService fishingSpotService) {
        this.fishingSpotService = fishingSpotService;
    }

    @GetMapping
    public ResponseEntity<List<FishingspotOutputDto>> getFisingSpots() {

       List<FishingspotOutputDto> fishingSpotDTOS = fishingSpotService.getFishingSpots();

       return ResponseEntity.ok().body(fishingSpotDTOS);
   }

   @GetMapping(value = "/{fishingspot}")
    public ResponseEntity<FishingspotOutputDto> getFishingSpot(@PathVariable("fishingspot") String spotLocation) {

       FishingspotOutputDto optionalFishingSpot = fishingSpotService.getFishingSpot(spotLocation);

       return ResponseEntity.ok().body(optionalFishingSpot);
   }

   @PostMapping(value = "/create")
    public ResponseEntity<FishingspotInputDto> createFishingSpot(@RequestBody FishingspotInputDto id) {

       String newFishingspot = fishingSpotService.createFishingSpot(id);

       URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{fishingspot}")
               .buildAndExpand(newFishingspot).toUri();

       return ResponseEntity.created(location).build();
   }

   @PostMapping
    public ResponseEntity<FishingspotOutputDto> updateFishingspot (@Valid @RequestBody FishingspotInputDto userInput) {

       FishingspotOutputDto fishingspot = fishingSpotService.updateFishingspot(userInput);

       URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
               .buildAndExpand(fishingspot).toUri();

       return ResponseEntity.created(uri).body(fishingspot);

   }

   @DeleteMapping(value = "/{id}/deletespot")
    public ResponseEntity<Object> deleteLocation (@PathVariable String spotLocation) {

       fishingSpotService.deleteFishingSpot(spotLocation);

       return ResponseEntity.noContent().build();
   }

   @GetMapping(value = "/fishingspot/{city}")
    public ResponseEntity<List<FishingspotOutputDto>> getCity(@PathVariable("city") String city) {

        List<FishingspotOutputDto> fishingspotsDTOS = fishingSpotService.getCity(city);

        return ResponseEntity.ok().body(fishingspotsDTOS);
   }

}