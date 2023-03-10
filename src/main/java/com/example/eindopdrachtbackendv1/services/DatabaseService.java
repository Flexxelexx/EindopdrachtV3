package com.example.eindopdrachtbackendv1.services;

import com.example.eindopdrachtbackendv1.exceptions.RecordNotFoundException;
import com.example.eindopdrachtbackendv1.repositories.DocFileRepository;
import com.example.eindopdrachtbackendv1.dtos.FileUploadResponse;
import com.example.eindopdrachtbackendv1.models.FileDocument;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class DatabaseService {
    private final DocFileRepository doc;

    public DatabaseService(DocFileRepository doc) {
        this.doc = doc;
    }

    public Collection<FileDocument> getALlFromDB() {
        return doc.findAll();
    }

    public FileDocument uploadFileDocument(String fileName, MultipartFile file) throws IOException {
        FileDocument fileDocument = new FileDocument();
        fileDocument.setFileName(fileName);
        fileDocument.setDocFile(file.getBytes());

        doc.save(fileDocument);

        return fileDocument;
    }

    public ResponseEntity<byte[]> singleFileDownload(String fileName, HttpServletRequest request) {

        Optional<FileDocument> optionalFileDocument = doc.findByFileName(fileName);
        if (optionalFileDocument.isEmpty()) {
            throw new RecordNotFoundException("file not found");
        } else {
            FileDocument document = optionalFileDocument.get();

            String mimeType = request.getServletContext().getMimeType(document.getFileName());

            return ResponseEntity.ok().contentType(MediaType.parseMediaType(mimeType)).header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + document.getFileName()).body(document.getDocFile());
        }
    }

    public List<FileUploadResponse> createMultipleUpload(MultipartFile[] files) {
        List<FileUploadResponse> uploadResponseList = new ArrayList<>();
        Arrays.stream(files).forEach(file -> {

            String name = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            FileDocument fileDocument = new FileDocument();
            fileDocument.setFileName(name);
            try {
                fileDocument.setDocFile(file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }

            doc.save(fileDocument);

            String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFromDB/").path(name).toUriString();

            String contentType = file.getContentType();

            String uniqueEndpoint = ServletUriComponentsBuilder.fromCurrentContextPath().path("/photos/").path(fileDocument.getId().toString()).toUriString();

            FileUploadResponse response = new FileUploadResponse(name, contentType, url);

            uploadResponseList.add(response);
        });
        return uploadResponseList;
    }

    public void getZipDownload(String[] files, HttpServletResponse response) throws IOException {
        try (ZipOutputStream zos = new ZipOutputStream(response.getOutputStream())) {
            Arrays.stream(files).forEach(file -> {
                try {
                    createZipEntry(file, zos);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            zos.finish();

            response.setStatus(200);
            response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;fileName=zipfile");
        }
    }

    public Resource downLoadFileDatabase(String fileName) {

        String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFromDB/").path(fileName).toUriString();

        Resource resource;

        try {
            resource = new UrlResource(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Issue in reading the file", e);
        }

        if (resource.exists() && resource.isReadable()) {
            return resource;
        } else {
            throw new RuntimeException("the file doesn't exist or not readable");
        }
    }

    public void createZipEntry(String file, ZipOutputStream zos) throws IOException {

        Resource resource = downLoadFileDatabase(file);
        ZipEntry zipEntry = new ZipEntry(Objects.requireNonNull(resource.getFilename()));
        try {
            zipEntry.setSize(resource.contentLength());
            zos.putNextEntry(zipEntry);

            StreamUtils.copy(resource.getInputStream(), zos);

            zos.closeEntry();
        } catch (IOException e) {
            System.out.println("some exception while zipping");
        }

    }
}
