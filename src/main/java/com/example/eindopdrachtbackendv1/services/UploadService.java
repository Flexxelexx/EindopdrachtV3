package com.example.eindopdrachtbackendv1.services;


import com.example.eindopdrachtbackendv1.dtos.FileUploadResponse;
import com.example.eindopdrachtbackendv1.dtos.input.UploadInputDto;
import com.example.eindopdrachtbackendv1.dtos.output.UploadGearOutputDto;
import com.example.eindopdrachtbackendv1.exceptions.RecordNotFoundException;
import com.example.eindopdrachtbackendv1.models.*;
import com.example.eindopdrachtbackendv1.repositories.DocFileRepository;
import com.example.eindopdrachtbackendv1.repositories.GearRepository;
import com.example.eindopdrachtbackendv1.repositories.UploadRepository;
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

    private final DocFileRepository docFileRepository;
    private final GearRepository gearRepository;


    public UploadService(UploadRepository repository, DocFileRepository docFileRepository, GearRepository gearRepository) {
        this.repository = repository;
        this.docFileRepository = docFileRepository;
        this.gearRepository = gearRepository;
    }

    public List<UploadGearOutputDto> getUploads() {
        List<UploadGearOutputDto> collection = new ArrayList<>();
        List<Upload> list = repository.findAll();
        for (Upload upload : list) {
            collection.add(uploadToUploadOutputDto(upload));
        }

        return collection;
    }

    public List<UploadGearOutputDto> getSpecies(String speciesfish) {
        if (speciesfish == null) {
            throw new IllegalArgumentException("speciesfish cannot be null");
        }
        List<UploadGearOutputDto> collection = new ArrayList<>();
        List<Upload> list = repository.findBySpeciesfish(speciesfish);
        for (Upload upload : list) {
            collection.add(uploadToUploadOutputDto(upload));
        }

        return collection;
    }

    public UploadGearOutputDto getUpload(Long id) {
        UploadGearOutputDto dto;
        Optional<Upload> uploadOptional = repository.findById(id);
        if (uploadOptional.isPresent()) {
            dto = uploadToUploadOutputDto(uploadOptional.get());
        } else {
            throw new RecordNotFoundException(id.toString());
        }
        return dto;
    }


    public Long createUpload(UploadInputDto uploadDTO, Long gearID) throws IOException {
        Upload upload = uploadInputDtoToUpload(uploadDTO);
        Optional<Gear> optionalGear = gearRepository.findById(gearID);
        Optional<FileDocument> optionalFileDocument = docFileRepository.findByFileName(uploadDTO.getFile().getFileName());
        if (optionalGear.isPresent()) {
            Gear gear = optionalGear.get();
            upload.setGear(gear);
        }
        if (optionalFileDocument.isPresent()) {
            FileDocument fileUpload = optionalFileDocument.get();
            upload.setFile(fileUpload);
        }

        Upload newUpload = repository.save(upload);
        return newUpload.getId();
    }

    private UploadGearOutputDto uploadToUploadOutputDto(Upload upload) {

        UploadGearOutputDto uploadGearOutputDto = new UploadGearOutputDto();

        uploadGearOutputDto.setId(upload.getId());
        uploadGearOutputDto.setWeightFish(upload.getWeightFish());
        uploadGearOutputDto.setLengthFish(upload.getLengthFish());
        uploadGearOutputDto.setCharsFish(upload.getCharsFish());
        uploadGearOutputDto.setSpeciesFish(upload.getSpeciesfish());
        uploadGearOutputDto.setLocationCaught(upload.getLocationCaught());
        uploadGearOutputDto.setCityCaught(upload.getCityCaught());
        if (upload.getFile() != null) {
            FileUploadResponse fileUploadResponse = new FileUploadResponse(
                    upload.getFile().getFileName(),
                    "Image",
                    "http://localhost:8080/downloadFromDB/" + upload.getFile().getFileName()
            );
            uploadGearOutputDto.setFile(fileUploadResponse);
        }

        uploadGearOutputDto.setRodLength(upload.getGear().getRodLength());
        uploadGearOutputDto.setKindOfReel(upload.getGear().getKindOfReel());
        uploadGearOutputDto.setKindOfLure(upload.getGear().getKindOfLure());
        uploadGearOutputDto.setLineLength(upload.getGear().getLineLength());

        if (upload.getUsers() != null) {
            uploadGearOutputDto.setUsername(upload.getUsers().getUsername());
        }


        uploadGearOutputDto.setRating(upload.getRating());

        return uploadGearOutputDto;
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

    public UploadGearOutputDto updateUpload(UploadInputDto uploadInput) {

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


    public void addGearToUpload(Long gearID, Long userID) {
        Optional<Upload> uploadOptional = repository.findById(userID);
        Optional<Gear> gearOptional = gearRepository.findById(gearID);
        if (uploadOptional != null && gearOptional != null) {
            Upload upload = uploadOptional.get();
            Gear gear = gearOptional.get();
            gear.setUpload(upload);
            gearRepository.save(gear);
        }
    }

}
