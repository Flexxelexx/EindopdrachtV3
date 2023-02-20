package com.example.eindopdrachtbackendv1.services;


import com.example.eindopdrachtbackendv1.dtos.input.UploadInputDto;
import com.example.eindopdrachtbackendv1.dtos.output.UploadOutputDto;
import com.example.eindopdrachtbackendv1.exceptions.RecordNotFoundException;
import com.example.eindopdrachtbackendv1.models.FileUploadResponse;
import com.example.eindopdrachtbackendv1.repositories.FileUploadRepository;
import com.example.eindopdrachtbackendv1.repositories.UploadRepository;
import com.example.eindopdrachtbackendv1.models.Rating;
import com.example.eindopdrachtbackendv1.models.Upload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.eindopdrachtbackendv1.models.Rating.*;
import static com.example.eindopdrachtbackendv1.models.Rating.ZEROSTARS;

@Service
public class UploadService {

    private final UploadRepository repository;

    private final FileUploadRepository uploadRepository;

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    public UploadService(UploadRepository repository, FileUploadRepository uploadRepository) {
        this.repository = repository;
        this.uploadRepository = uploadRepository;
    }

    public List<UploadOutputDto> getUploads() {
        List<UploadOutputDto> collection = new ArrayList<>();
        List<Upload> list = repository.findAll();
        for (Upload upload : list) {
            collection.add(uploadToUploadOutputDto(upload));
        }

        return collection;
    }

    public List<UploadOutputDto> getSpecies(String speciesfish) {
        if (speciesfish == null) {
            throw new IllegalArgumentException("speciesfish cannot be null");
        }
        List<UploadOutputDto> collection = new ArrayList<>();
        List<Upload> list = repository.findBySpeciesfish(speciesfish);
        for (Upload upload : list) {
            collection.add(uploadToUploadOutputDto(upload));
        }

        return collection;
    }

    public UploadOutputDto getUpload(Long id) {
        UploadOutputDto dto;
        Optional<Upload> uploadOptional = repository.findById(id);
        if (uploadOptional.isPresent()) {
            dto = uploadToUploadOutputDto(uploadOptional.get());
        } else {
            throw new RecordNotFoundException(id.toString());
        }
        return dto;
    }

    public Long createUpload(UploadInputDto uploadDTO) throws IOException {
        Upload newUpload = repository.save(uploadInputDtoToUpload(uploadDTO));
        return newUpload.getId();
    }

    private UploadOutputDto uploadToUploadOutputDto(Upload upload) {

        UploadOutputDto uploadOutputDto = new UploadOutputDto();

        uploadOutputDto.setId(upload.getId());
        uploadOutputDto.setWeightFish(upload.getWeightFish());
        uploadOutputDto.setLengthFish(upload.getLengthFish());
        uploadOutputDto.setCharsFish(upload.getCharsFish());
        uploadOutputDto.setSpeciesFish(upload.getSpeciesfish());
        uploadOutputDto.setLocationCaught(upload.getLocationCaught());
        uploadOutputDto.setCityCaught(upload.getCityCaught());

        uploadOutputDto.setRating(upload.getRating());

        return uploadOutputDto;
    }

    private Upload uploadInputDtoToUpload(UploadInputDto uploadInputDto) throws IOException {


        Upload upload = new Upload();

        upload.setId(uploadInputDto.getId());
        upload.setWeightFish(uploadInputDto.getWeightFish());
        upload.setLengthFish(uploadInputDto.getLengthFish());
        upload.setCharsFish(uploadInputDto.getCharsFish());
        upload.setSpeciesfish(uploadInputDto.getSpeciesFish());
        upload.setLocationCaught(uploadInputDto.getLocationCaught());
        upload.setCityCaught(uploadInputDto.getCityCaught());

        upload.setRating(Rating.ZEROSTARS);
        return upload;
    }

    public UploadOutputDto updateUpload(UploadInputDto uploadInput) {

        Long inputId = uploadInput.getId();

        Upload upload = repository.findById(inputId).map(x -> {
                    try {
                        return uploadInputDtoToUpload(uploadInput);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .orElseThrow(() -> new RecordNotFoundException(String.format("Upload with id %d not found", inputId)));

        repository.save(upload);

        return uploadToUploadOutputDto(upload);
    }


    public void addRating(Integer uploadRating, Long uploadId) {
        Rating ratingEnum;
        switch (uploadRating) {
            case 1:
                ratingEnum = ONESTAR;
                break;
            case 2:
                ratingEnum = TWOSTARS;
                break;
            case 3:
                ratingEnum = THREESTARS;
                break;
            case 4:
                ratingEnum = FOURSTARS;
                break;
            case 5:
                ratingEnum = FIVESTARS;
                break;
            default:
                ratingEnum = ZEROSTARS;
        }
        Upload upload = repository.findById(uploadId).get();
        upload.setRating(ratingEnum);
        repository.save(upload);
    }

    public void deleteUpload(Long id) {
        repository.deleteById(id);
    }


    public void assignPhotoToStudent(String name, Long id) {

        Optional<Upload> optionalUpload = repository.findById(id);

        Optional<FileUploadResponse> fileUploadResponse = uploadRepository.findByFileName(name);

        if (optionalUpload.isPresent() && fileUploadResponse.isPresent()) {

            FileUploadResponse photo = fileUploadResponse.get();

            Upload student = optionalUpload.get();

            student.setFile(photo);

            repository.save(student);

        }

    }

}
