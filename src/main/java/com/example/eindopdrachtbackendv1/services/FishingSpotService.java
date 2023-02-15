package com.example.eindopdrachtbackendv1.services;

import com.example.eindopdrachtbackendv1.dtos.input.FishingspotInputDto;
import com.example.eindopdrachtbackendv1.dtos.output.FishingspotOutputDto;
import com.example.eindopdrachtbackendv1.exceptions.RecordNotFoundException;
import com.example.eindopdrachtbackendv1.repositories.FishingSpotRepository;
import com.example.eindopdrachtbackendv1.models.FishingSpot;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FishingSpotService {

    private final FishingSpotRepository fishingSpotRepository;

    public FishingSpotService(FishingSpotRepository fishingSpotRepository) {
        this.fishingSpotRepository = fishingSpotRepository;
    }

    public List<FishingspotOutputDto> getFishingSpots() {
        List<FishingspotOutputDto> collection = new ArrayList<>();
        List<FishingSpot> list = fishingSpotRepository.findAll();
        for (FishingSpot fishingSpot : list) {
            collection.add(fishingspotToFishingspotOutputDto(fishingSpot));
        }

        return collection;
    }

    public FishingspotOutputDto getFishingSpot(String spotLocation) {
        FishingspotOutputDto dto;
        Optional<FishingSpot> user = fishingSpotRepository.findBySpotLocation(spotLocation);
        if (user.isPresent()) {
            dto = fishingspotToFishingspotOutputDto(user.get());
        } else {
            throw new RecordNotFoundException(spotLocation);
        }
        return dto;
    }

    public String createFishingSpot(FishingspotInputDto fishingSpotDTO) {
        if (!fishingSpotRepository.existsBySpotLocation(fishingSpotDTO.getSpotLocation())) {

            FishingSpot newFishingSpot = fishingSpotRepository.save(fishingSpotInputDtoToFishingspot(fishingSpotDTO));

            return newFishingSpot.getSpotLocation();
        } else {
            throw new RecordNotFoundException(String.format("Fishingspot already exists!"));
        }
    }

    private FishingspotOutputDto fishingspotToFishingspotOutputDto(FishingSpot fishingSpot) {

        FishingspotOutputDto fishingspotOutputDto = new FishingspotOutputDto();

        fishingspotOutputDto.setId(fishingSpot.getId());
        fishingspotOutputDto.setSpotLocation(fishingSpot.getSpotLocation());
        fishingspotOutputDto.setCity(fishingSpot.getCity());
        fishingspotOutputDto.setRegion(fishingSpot.getRegion());

        return fishingspotOutputDto;
    }

    private FishingSpot fishingSpotInputDtoToFishingspot (FishingspotInputDto fishingspotInputDto) {

        FishingSpot fishingSpot = new FishingSpot();

        fishingSpot.setId(fishingspotInputDto.getId());
        fishingSpot.setSpotLocation(fishingspotInputDto.getSpotLocation());
        fishingSpot.setCity(fishingspotInputDto.getCity());
        fishingSpot.setRegion(fishingspotInputDto.getRegion());

        return fishingSpot;
    }


    public FishingspotOutputDto updateFishingspot (FishingspotInputDto userInput) {

        Long inputId = userInput.getId();

        FishingSpot fishingSpot = fishingSpotRepository.findById(inputId).map(x -> fishingSpotInputDtoToFishingspot(userInput))
                .orElseThrow(() -> new RecordNotFoundException(String.format("Fishingspot with id %d not found", inputId)));

        fishingSpotRepository.save(fishingSpot);

        return fishingspotToFishingspotOutputDto(fishingSpot);
    }

    public void deleteFishingSpot(String spotLocation) {
        fishingSpotRepository.deleteBySpotLocation(spotLocation);
    }








}
