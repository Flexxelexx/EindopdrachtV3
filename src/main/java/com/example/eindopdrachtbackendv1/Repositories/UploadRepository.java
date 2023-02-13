package com.example.eindopdrachtbackendv1.Repositories;

import com.example.eindopdrachtbackendv1.DTOS.Input.UploadInputDto;
import com.example.eindopdrachtbackendv1.models.Upload;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UploadRepository extends JpaRepository <Upload, Long> {
        Optional<Object> findById(UploadInputDto uploadInput);

        List<Upload> findBySpeciesfish (String speciesfish);

}
