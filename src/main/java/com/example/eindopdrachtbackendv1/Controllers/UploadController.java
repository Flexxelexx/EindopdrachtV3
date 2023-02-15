package com.example.eindopdrachtbackendv1.Controllers;

import com.example.eindopdrachtbackendv1.DTOS.Input.UploadInputDto;
import com.example.eindopdrachtbackendv1.DTOS.Output.UploadOutputDto;
import com.example.eindopdrachtbackendv1.Services.UploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/uploads")
public class UploadController {

    private final UploadService uploadService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @GetMapping(value = "")
    public ResponseEntity<List<UploadOutputDto>> getUploads() {


        List<UploadOutputDto> uploadDTOS = uploadService.getUploads();

        return ResponseEntity.ok().body(uploadDTOS);
    }

    @GetMapping(value = "/species/{speciesfish}")
    public ResponseEntity<List<UploadOutputDto>> getSpecies(@PathVariable("speciesfish") String speciesfish) {

        System.out.println("De opgegeven speciesfish waarde is: " + speciesfish);
        List<UploadOutputDto> uploadDTOS = uploadService.getSpecies(speciesfish);
        System.out.println("Lijst met uploadDTOS: " + uploadDTOS);

        return ResponseEntity.ok().body(uploadDTOS);
    }


    @GetMapping(value = "/{upload}")
    public ResponseEntity<UploadOutputDto> getUpload(@PathVariable("upload") Long id) {

        UploadOutputDto optionalUpload = uploadService.getUpload(id);

        return ResponseEntity.ok().body(optionalUpload);
    }

    @PostMapping()
    public ResponseEntity<UploadInputDto> createUpload(@RequestBody UploadInputDto id) {

        String newUpload = uploadService.createUpload(id).toString();

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
    public ResponseEntity<UploadOutputDto> updateUpload(@Valid @RequestBody UploadInputDto uploadInput) {

        UploadOutputDto upload = uploadService.updateUpload(uploadInput);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(upload).toUri();

        return ResponseEntity.created(uri).body(upload);

    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteUpload(@PathVariable Long id) {
        uploadService.deleteUpload(id);
        return ResponseEntity.noContent().build();
    }


}
