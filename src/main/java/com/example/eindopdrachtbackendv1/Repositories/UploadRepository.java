package com.example.eindopdrachtbackendv1.Repositories;

import com.example.eindopdrachtbackendv1.DTOS.Input.UploadInputDto;
import com.example.eindopdrachtbackendv1.models.Upload;
import com.example.eindopdrachtbackendv1.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UploadRepository extends JpaRepository <Upload, Long> {
        Optional<Object> findById(UploadInputDto uploadInput);
}
