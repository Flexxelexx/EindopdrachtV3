package com.example.eindopdrachtbackendv1.repositories;

import com.example.eindopdrachtbackendv1.dtos.input.UploadInputDto;
import com.example.eindopdrachtbackendv1.models.Upload;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface UploadRepository extends JpaRepository <Upload, Long> {
        Optional<Object> findById(UploadInputDto uploadInput);

        List<Upload> findByUsersUsername(String username);

        List<Upload> findBySpeciesfish (String speciesfish);


}
