package com.example.eindopdrachtbackendv1.configs;

import com.example.eindopdrachtbackendv1.models.Upload;

@Mapper(componentModel = "spring")
public interface UploadMapper {

    UploadDTO toDto(Upload upload);

}
