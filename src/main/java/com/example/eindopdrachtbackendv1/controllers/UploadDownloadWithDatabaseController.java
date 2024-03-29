package com.example.eindopdrachtbackendv1.controllers;

import com.example.eindopdrachtbackendv1.dtos.FileUploadResponse;
import com.example.eindopdrachtbackendv1.services.DatabaseService;
import com.example.eindopdrachtbackendv1.models.FileDocument;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@CrossOrigin
@RestController
public class UploadDownloadWithDatabaseController {

    private final DatabaseService databaseService;

    public UploadDownloadWithDatabaseController(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @PostMapping("/single/uploadDb")
    public FileUploadResponse singleFileUpload(@RequestParam( value = "file") MultipartFile file ) throws IOException {
        String fileName = System.currentTimeMillis() + "-" + new Random().nextInt(1000) + "-" + file.getOriginalFilename();
        FileDocument fileDocument = databaseService.uploadFileDocument(fileName, file);
        String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFromDB/").path(fileName).toUriString();
        String contentType = file.getContentType();
        return new FileUploadResponse(fileDocument.getFileName(), contentType, url);
    }

    @GetMapping("/downloadFromDB/{fileName}")
    ResponseEntity<byte[]> downLoadSingleFile(@PathVariable String fileName, HttpServletRequest request) {

        return databaseService.singleFileDownload(fileName, request);
    }

    @PostMapping("/multiple/upload/db")
    List<FileUploadResponse> multipleUpload(@RequestParam("files") MultipartFile [] files) {

        if(files.length > 7) {
            throw new RuntimeException("to many files selected");
        }

        return databaseService.createMultipleUpload(files);

    }

    @GetMapping("zipDownload/db")
    public void zipDownload(@RequestParam("fileName") String[] files, HttpServletResponse response) throws IOException {

        databaseService.getZipDownload(files, response);

    }

    @GetMapping("/getAll/db")
    public Collection<FileDocument> getAllFromDB(){
        return databaseService.getALlFromDB();
    }
}

