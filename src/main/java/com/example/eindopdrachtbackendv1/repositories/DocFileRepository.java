package com.example.eindopdrachtbackendv1.repositories;

import com.example.eindopdrachtbackendv1.models.FileDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public interface DocFileRepository extends JpaRepository<FileDocument, Long> {
    Optional<FileDocument> findByFileName(String fileName);
}
