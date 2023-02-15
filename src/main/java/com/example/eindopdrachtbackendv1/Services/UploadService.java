package com.example.eindopdrachtbackendv1.Services;


import com.example.eindopdrachtbackendv1.DTOS.Input.UploadInputDto;
import com.example.eindopdrachtbackendv1.DTOS.Output.UploadOutputDto;
import com.example.eindopdrachtbackendv1.Exceptions.RecordNotFoundException;
import com.example.eindopdrachtbackendv1.Repositories.UploadRepository;
import com.example.eindopdrachtbackendv1.models.Rating;
import com.example.eindopdrachtbackendv1.models.Upload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.eindopdrachtbackendv1.models.Rating.*;
import static com.example.eindopdrachtbackendv1.models.Rating.ZEROSTARS;

@Service
public class UploadService {

    private final UploadRepository uploadRepository;

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    public UploadService(UploadRepository uploadRepository) {
        this.uploadRepository = uploadRepository;
    }

    public List<UploadOutputDto> getUploads() {
        List<UploadOutputDto> collection = new ArrayList<>();
        List<Upload> list = uploadRepository.findAll();
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
        List<Upload> list = uploadRepository.findBySpeciesfish(speciesfish);
        for (Upload upload : list) {
            collection.add(uploadToUploadOutputDto(upload));
        }

        return collection;
}

    public UploadOutputDto getUpload(Long id) {
        UploadOutputDto dto;
        Optional<Upload> uploadOptional = uploadRepository.findById(id);
        if (uploadOptional.isPresent()) {
            dto = uploadToUploadOutputDto(uploadOptional.get());
        } else {
            throw new RecordNotFoundException(id.toString());
        }
        return dto;
    }

    public Long createUpload(UploadInputDto uploadDTO) {
        Upload newUpload = uploadRepository.save(uploadInputDtoToUpload(uploadDTO));
        return newUpload.getId();
    }

    private UploadOutputDto uploadToUploadOutputDto(Upload upload) {

        UploadOutputDto uploadOutputDto = new UploadOutputDto();

        uploadOutputDto.setId(upload.getId());
        uploadOutputDto.setWeightFish(upload.getWeightFish());
        uploadOutputDto.setLengthFish(upload.getLengthFish());
        uploadOutputDto.setCharsFish(upload.getCharsFish());
        uploadOutputDto.setSpeciesFish(upload.getSpeciesfish());
        uploadOutputDto.setPhotoFish(upload.getPhotoFish());

        uploadOutputDto.setRating(upload.getRating());

        return uploadOutputDto;
    }

    private Upload uploadInputDtoToUpload(UploadInputDto uploadInputDto) {


        Upload upload = new Upload();

        upload.setId(uploadInputDto.getId());
        upload.setWeightFish(uploadInputDto.getWeightFish());
        upload.setLengthFish(uploadInputDto.getLengthFish());
        upload.setCharsFish(uploadInputDto.getCharsFish());
        upload.setSpeciesfish(uploadInputDto.getSpeciesFish());
        upload.setPhotoFish(uploadInputDto.getPhotoFish());

        upload.setRating(Rating.ZEROSTARS);
        return upload;
    }

    public UploadOutputDto updateUpload(UploadInputDto uploadInput) {

        Long inputId = uploadInput.getId();

        Upload upload = uploadRepository.findById(inputId).map(x -> uploadInputDtoToUpload(uploadInput))
                .orElseThrow(() -> new RecordNotFoundException(String.format("Upload with id %d not found", inputId)));

        uploadRepository.save(upload);

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
        Upload upload = uploadRepository.findById(uploadId).get();
        upload.setRating(ratingEnum);
        uploadRepository.save(upload);
    }

    public void deleteUpload(Long id) {
        uploadRepository.deleteById(id);
    }


}
