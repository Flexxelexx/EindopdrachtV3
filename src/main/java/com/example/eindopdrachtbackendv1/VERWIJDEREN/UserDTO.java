//package com.example.eindopdrachtbackendv1.DTOS;
//
//import com.example.eindopdrachtbackendv1.models.*;
//
//import javax.validation.constraints.*;
//import java.time.LocalDate;
//import java.util.Collection;
//
//
//public class UserDTO {
//
//    @NotBlank
//    public String username;
//    @NotBlank
//    @Size(min = 6)
//    public String password;
//    @Email
//    @NotBlank
//    public String email;
//    @Past
//    public LocalDate dob;
//
//    public String[] roles;
//
//    public Collection<FishingSpot> fishingSpots;
//    public PortfolioDTO portfolios;
//    public Collection<Upload> uploads;
//
//
//    public static UserDTO fromUser(User user) {
//
//        UserDTO dto = new UserDTO();
//
//        dto.setUsername(user.getUsername());
//        dto.setPassword(user.getPassword());
//        dto.setEmail(user.getEmail());
//        dto.setDob(user.getDob());
//        dto.setFishingSpots(user.getFishingSpots());
//        dto.setUploads(user.getUploads());
//        if (user.getPortfolios() != null ) {
//            dto.setPortfolios(PortfolioDTO.fromPortfolio(user.getPortfolios()));
//        }
//
//
//        return dto;
//    }
//
//    public static User toUser(UserDTO userDTO) {
//
//        User user = new User();
//
//        user.setUsername(userDTO.username);
//        user.setPassword(userDTO.password);
//        user.setEmail(userDTO.email);
//        user.setDob(userDTO.dob);
//        user.setFishingSpots(userDTO.fishingSpots);
//        user.setUploads(userDTO.uploads);
//        if (userDTO.portfolios != null) {
//            user.setPortfolios(PortfolioDTO.toPortfolio(userDTO.portfolios));
//        }
//
//        return user;
//    }
//
//
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public LocalDate getDob() {
//        return dob;
//    }
//
//    public void setDob(LocalDate dob) {
//        this.dob = dob;
//    }
//
//    public String[] getRoles() {
//        return roles;
//    }
//
//    public void setRoles(String[] roles) {
//        this.roles = roles;
//    }
//
//    public Collection<FishingSpot> getFishingSpots() {
//        return fishingSpots;
//    }
//
//    public void setFishingSpots(Collection<FishingSpot> fishingSpots) {
//        this.fishingSpots = fishingSpots;
//    }
//
//    public PortfolioDTO getPortfolios() {
//        return portfolios;
//    }
//
//    public void setPortfolios(PortfolioDTO portfolios) {
//        this.portfolios = portfolios;
//    }
//
//    public Collection<Upload> getUploads() {
//        return uploads;
//    }
//
//    public void setUploads(Collection<Upload> uploads) {
//        this.uploads = uploads;
//    }
//}
