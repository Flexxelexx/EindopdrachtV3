package com.example.eindopdrachtbackendv1.Controllers;

import com.example.eindopdrachtbackendv1.DTOS.Input.UploadInputDto;
import com.example.eindopdrachtbackendv1.DTOS.Output.UploadOutputDto;
import com.example.eindopdrachtbackendv1.Services.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/uploads")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @GetMapping(value = "")
    public ResponseEntity<List<UploadOutputDto>> getUploads() {

        List<UploadOutputDto> uploadDTOS = uploadService.getUploads();

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

    @PutMapping
    public ResponseEntity<UploadOutputDto> updateUpload(@Valid @RequestBody UploadInputDto uploadInput) {

        UploadOutputDto upload = uploadService.updateUpload(uploadInput);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(upload).toUri();

        return ResponseEntity.created(uri).body(upload);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteUpload (@PathVariable Long id) {
        uploadService.deleteUpload(id);
        return ResponseEntity.noContent().build();
    }




}
