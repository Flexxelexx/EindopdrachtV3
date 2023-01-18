//package com.example.eindopdrachtbackendv1.Controllers;
//
//import com.example.eindopdrachtbackendv1.DTOS.Input.FishInputDto;
//import com.example.eindopdrachtbackendv1.DTOS.Output.FishOutputDto;
//import com.example.eindopdrachtbackendv1.Services.FishService;
//import com.example.eindopdrachtbackendv1.models.Fish;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//
//
//import javax.validation.Valid;
//import java.net.URI;
//import java.util.List;
//
//@RestController
//@RequestMapping(value = "/fish")
//public class FishController {
//
//    private final FishService fishService;
//
//    public FishController(FishService fishService) {
//        this.fishService = fishService;
//    }
//
//    @GetMapping
//    public ResponseEntity<List<FishOutputDto>> getAllFish() {
//
//        List<FishOutputDto> fishOutputDtos = fishService.getAllFish();
//
//        return ResponseEntity.ok().body(fishOutputDtos);
//    }
//
//    @GetMapping(value = "/{speciesfish}")
//    public ResponseEntity<FishOutputDto> getFish (@PathVariable("speciesfish") String speciesFish) {
//
//        FishOutputDto optionalFish = fishService.getFish(speciesFish);
//
//        return ResponseEntity.ok().body(optionalFish);
//    }
//
//    @PostMapping
//    public ResponseEntity<Fish> createFish(@Valid @RequestBody FishInputDto fishDTO) {
//
//        Fish fish = fishService.createFish(fishDTO);
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{species}")
//                .buildAndExpand(fish.getSpeciesFish()).toUri();
//        return ResponseEntity.created(location).build();
//    }
//
//    @PutMapping
//    public ResponseEntity<FishOutputDto> updateFish (@Valid @RequestBody FishInputDto fishDTO) {
//
//        FishOutputDto fish = fishService.updateFish(fishDTO);
//
//        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
//                .buildAndExpand(fish).toUri();
//
//        return ResponseEntity.created(uri).body(fish);
//    }
//}
