//package com.example.eindopdrachtbackendv1.Services;
//
//import com.example.eindopdrachtbackendv1.DTOS.Input.FishInputDto;
//import com.example.eindopdrachtbackendv1.DTOS.Output.FishOutputDto;
//import com.example.eindopdrachtbackendv1.Exceptions.RecordNotFoundException;
//import com.example.eindopdrachtbackendv1.Repositories.FishRepository;
//
////import com.example.eindopdrachtbackendv1.models.Fish;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class FishService {
//
//    private final FishRepository fishRepository;
//
//    public FishService(FishRepository fishRepository) {
//        this.fishRepository = fishRepository;
//    }
//
//    public List<FishOutputDto> getAllFish() {
//        List<FishOutputDto> collection = new ArrayList<>();
//        List<Fish> list = fishRepository.findAll();
//        for (Fish fish : list) {
//            collection.add(fishToFishOutputDto(fish));
//        }
//
//        return collection;
//    }
//
//    public FishOutputDto getFish(String fishname) {
//        FishOutputDto dto;
//        Optional<Fish> fish = fishRepository.findBySpeciesFish(fishname);
//        if (fish.isPresent()) {
//            dto = fishToFishOutputDto(fish.get());
//        } else {
//            throw new RecordNotFoundException(fishname);
//        }
//        return dto;
//    }
//
//    public Fish createFish(FishInputDto fishDto) {
//        Fish newFish = new Fish();
//        newFish.setCharsFish(fishDto.getCharsFish());
//        newFish.setSpeciesFish(fishDto.getSpeciesFish());
//
//        fishRepository.save(newFish);
//
//        return newFish;
//    }
//
//    private FishOutputDto fishToFishOutputDto (Fish fish) {
//
//        FishOutputDto fishOutputDto = new FishOutputDto();
//
//        fishOutputDto.setId(fish.getId());
//        fishOutputDto.setCharsFish(fish.getCharsFish());
//        fishOutputDto.setSpeciesFish(fish.getSpeciesFish());
//
//        return fishOutputDto;
//    }
//
//    private Fish fishInputDtoToFish (FishInputDto fishInputDto) {
//
//        Fish fish = new Fish();
//
//        fish.setId(fishInputDto.getId());
//        fish.setCharsFish(fishInputDto.getCharsFish());
//        fish.setSpeciesFish(fishInputDto.getSpeciesFish());
//
//        return fish;
//    }
//
//    public FishOutputDto updateFish (FishInputDto userInput) {
//
//        Long inputId = userInput.getId();
//
//        Fish fish = fishRepository.findById(inputId).map(x -> fishInputDtoToFish(userInput))
//                .orElseThrow(() -> new RecordNotFoundException(String.format("Fish with id %d not found", inputId)));
//
//        fishRepository.save(fish);
//
//        return fishToFishOutputDto(fish);
//    }
//}
