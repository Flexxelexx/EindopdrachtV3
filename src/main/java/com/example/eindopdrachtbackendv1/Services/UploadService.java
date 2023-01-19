package com.example.eindopdrachtbackendv1.Services;


import com.example.eindopdrachtbackendv1.DTOS.Input.UploadInputDto;
import com.example.eindopdrachtbackendv1.DTOS.Output.UploadOutputDto;
import com.example.eindopdrachtbackendv1.Exceptions.RecordNotFoundException;
import com.example.eindopdrachtbackendv1.Repositories.UploadRepository;
import com.example.eindopdrachtbackendv1.models.Rating;
import com.example.eindopdrachtbackendv1.models.Upload;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UploadService {

    private final UploadRepository uploadRepository;

    public UploadService (UploadRepository uploadRepository) {
        this.uploadRepository = uploadRepository;
    }

    public List<UploadOutputDto> getUploads() {
        List <UploadOutputDto> collection = new ArrayList<>();
        List <Upload> list = uploadRepository.findAll();
        for (Upload upload : list ) {
            collection.add(uploadToUploadOutputDto(upload));
        }

        return collection;
    }

    public UploadOutputDto getUpload (Long id) {
        UploadOutputDto dto;
        Optional<Upload> uploadOptional = uploadRepository.findById(id);
        if (uploadOptional.isPresent()) {
            dto = uploadToUploadOutputDto(uploadOptional.get());
        } else {
            throw new RecordNotFoundException(id.toString());
        }
        return dto;
    }

    public Long createUpload (UploadInputDto uploadDTO) {
        Upload newUpload = uploadRepository.save(uploadInputDtoToUpload(uploadDTO));
        return newUpload.getId();
    }

    private UploadOutputDto uploadToUploadOutputDto (Upload upload) {

        UploadOutputDto uploadOutputDto = new UploadOutputDto();

        uploadOutputDto.setId(upload.getId());
        uploadOutputDto.setWeightFish(upload.getWeightFish());
        uploadOutputDto.setLengthFish(upload.getLengthFish());
        uploadOutputDto.setCharsFish(upload.getCharsFish());
        uploadOutputDto.setSpeciesFish(upload.getSpeciesFish());
        uploadOutputDto.setPhotoFish(upload.getPhotoFish());

        return uploadOutputDto;
    }

    private Upload uploadInputDtoToUpload (UploadInputDto uploadInputDto) {


        Upload upload = new Upload();

        upload.setId(uploadInputDto.getId());
        upload.setWeightFish(uploadInputDto.getWeightFish());
        upload.setLengthFish(uploadInputDto.getLengthFish());
        upload.setCharsFish(uploadInputDto.getCharsFish());
        upload.setSpeciesFish(uploadInputDto.getSpeciesFish());
        upload.setPhotoFish(uploadInputDto.getPhotoFish());

        upload.setRating(Rating.ZEROSTARS);
        return upload;
    }
    public UploadOutputDto updateUpload (UploadInputDto uploadInput) {

        Long inputId = uploadInput.getId();

        Upload upload = uploadRepository.findById(inputId).map(x -> uploadInputDtoToUpload(uploadInput))
                .orElseThrow(() -> new RecordNotFoundException(String.format("Upload with id %d not found", inputId)));

        uploadRepository.save(upload);

        return uploadToUploadOutputDto(upload);
    }


    public void deleteUpload (Long id) {
        uploadRepository.deleteById(id);
    }


}
