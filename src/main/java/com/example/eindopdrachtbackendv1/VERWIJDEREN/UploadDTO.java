//package com.example.eindopdrachtbackendv1.DTOS;
//
//import com.example.eindopdrachtbackendv1.models.Upload;
//import com.fasterxml.jackson.annotation.JsonFormat;
//
//import javax.validation.constraints.Max;
//import javax.validation.constraints.Min;
//import javax.validation.constraints.NotBlank;
//import java.util.Date;
//
//public class UploadDTO {
//
//    public Long id;
//    @Min(value = 0)
//    @Max(value = 250)
//    public double weightFish;
//
//    @Min(value = 0)
//    @Max(value = 100)
//    public double lengthFish;
//
//    public String charsFish;
//    @NotBlank
//    public String speciesFish;
//
//    public byte[] photoFish;
//
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy@HH:mm", locale = "en_GB")
//    public Date timeCaughtFish;
//
//    public static UploadDTO fromUpload(Upload upload) {
//
//        UploadDTO dto = new UploadDTO();
//
//        dto.setId(upload.getId());
//        dto.setWeightFish(upload.getWeightFish());
//        dto.setLengthFish(upload.getLengthFish());
//        dto.setCharsFish(upload.getCharsFish());
//        dto.setSpeciesFish(upload.getSpeciesFish());
//        dto.setPhotoFish(upload.getPhotoFish());
//        dto.setTimeCaughtFish(upload.getTimeCaughtFish());
//
//        return dto;
//    }
//
//    public static Upload toUpload(UploadDTO uploadDTO) {
//
//        Upload upload = new Upload();
//
//        upload.setId(uploadDTO.id);
//        upload.setWeightFish(uploadDTO.weightFish);
//        upload.setLengthFish(uploadDTO.lengthFish);
//        upload.setCharsFish(uploadDTO.charsFish);
//        upload.setSpeciesFish(uploadDTO.speciesFish);
//        upload.setPhotoFish(uploadDTO.photoFish);
//        upload.setTimeCaughtFish(uploadDTO.timeCaughtFish);
//
//        return upload;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public double getWeightFish() {
//        return weightFish;
//    }
//
//    public void setWeightFish(double weightFish) {
//        this.weightFish = weightFish;
//    }
//
//    public double getLengthFish() {
//        return lengthFish;
//    }
//
//    public void setLengthFish(double lengthFish) {
//        this.lengthFish = lengthFish;
//    }
//
//    public String getCharsFish() {
//        return charsFish;
//    }
//
//    public void setCharsFish(String charsFish) {
//        this.charsFish = charsFish;
//    }
//
//    public String getSpeciesFish() {
//        return speciesFish;
//    }
//
//    public void setSpeciesFish(String speciesFish) {
//        this.speciesFish = speciesFish;
//    }
//
//    public byte[] getPhotoFish() {
//        return photoFish;
//    }
//
//    public void setPhotoFish(byte[] photoFish) {
//        this.photoFish = photoFish;
//    }
//
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm @ dd-MM-yyyy", locale = "en_GB")
//    public Date getTimeCaughtFish() {
//        return timeCaughtFish;
//    }
//
//    public void setTimeCaughtFish(Date timeCaughtFish) {
//        this.timeCaughtFish = timeCaughtFish;
//    }
//}
