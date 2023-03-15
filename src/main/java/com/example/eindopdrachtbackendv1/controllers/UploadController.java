package com.example.eindopdrachtbackendv1.controllers;

import com.example.eindopdrachtbackendv1.dtos.input.GearInputDto;
import com.example.eindopdrachtbackendv1.dtos.input.UploadGearInputDto;
import com.example.eindopdrachtbackendv1.dtos.input.UploadInputDto;
import com.example.eindopdrachtbackendv1.dtos.output.UploadGearOutputDto;
import com.example.eindopdrachtbackendv1.dtos.output.UploadOutputDto;
import com.example.eindopdrachtbackendv1.models.User;
import com.example.eindopdrachtbackendv1.repositories.UploadRepository;
import com.example.eindopdrachtbackendv1.services.DatabaseService;
import com.example.eindopdrachtbackendv1.services.GearService;
import com.example.eindopdrachtbackendv1.services.UploadService;
import com.example.eindopdrachtbackendv1.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Locale;


@RestController
@RequestMapping(value = "/uploads")
public class UploadController {

    private final UploadService uploadService;

    private final UploadRepository uploadRepository;

    private final DatabaseService databaseService;

    private final UserService userService;

    private final GearService gearService;


    @Autowired
    public UploadController(UploadService uploadService, UploadRepository uploadRepository, DatabaseService databaseService, UserService userService, GearService gearService) {
        this.uploadService = uploadService;
        this.uploadRepository = uploadRepository;
        this.databaseService = databaseService;
        this.userService = userService;
        this.gearService = gearService;
    }

    @GetMapping(value = "")
    public ResponseEntity<List<UploadGearOutputDto>> getUploads() {


        List<UploadGearOutputDto> uploadDTOS = uploadService.getUploads();

        return ResponseEntity.ok().body(uploadDTOS);
    }

    @GetMapping(value = "/{upload}")
    public ResponseEntity<UploadGearOutputDto> getUpload(@PathVariable("upload") Long id) {

        UploadGearOutputDto optionalUpload = uploadService.getUpload(id);

        return ResponseEntity.ok().body(optionalUpload);
    }

    @PostMapping()
    public ResponseEntity<UploadInputDto> createUpload(@RequestBody UploadGearInputDto id) throws IOException {

        GearInputDto gearDTO = new GearInputDto(id.getRodLength(), id.getKindOfReel(), id.getKindOfLure(), id.getLineLength());

        UploadInputDto uploadDTO = new UploadInputDto(id.getWeightFish(), id.getLengthFish(), id.getCharsFish(), id.getSpeciesFish(), id.getLocationCaught(), id.getCityCaught(), id.getFile());

        Long newGear = gearService.createGear(gearDTO);
        String newUpload = uploadService.createUpload(uploadDTO, newGear).toString();


        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(newUpload).toUri();

        return ResponseEntity.created(location).build();
    }

    @PostMapping("/rating/{uploadId}")
    public ResponseEntity<String> addRating(@PathVariable Long uploadId, @RequestBody Integer rating) {

        uploadService.addRating(rating, uploadId);
        return ResponseEntity.ok("Rating has been given!");

    }

    @PutMapping
    public ResponseEntity<UploadGearOutputDto> updateUpload(@Valid @RequestBody UploadInputDto uploadInput) {

        UploadGearOutputDto upload = uploadService.updateUpload(uploadInput);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(upload).toUri();

        return ResponseEntity.created(uri).body(upload);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteUpload(@PathVariable Long id) {
        uploadService.deleteUpload(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping(value = "/species/{speciesfish}")
    public ResponseEntity<List<UploadGearOutputDto>> getSpecies(@PathVariable("speciesfish") String speciesfish) {

        List<UploadGearOutputDto> uploadDTOS = uploadService.getSpecies(speciesfish.toLowerCase(Locale.ROOT));

        return ResponseEntity.ok().body(uploadDTOS);
    }



}
