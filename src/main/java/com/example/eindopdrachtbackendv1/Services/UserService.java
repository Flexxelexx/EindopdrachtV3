package com.example.eindopdrachtbackendv1.Services;

import com.example.eindopdrachtbackendv1.DTOS.Input.UserInputDto;
import com.example.eindopdrachtbackendv1.DTOS.Output.UserOutputDto;
import com.example.eindopdrachtbackendv1.Exceptions.RecordNotFoundException;
import com.example.eindopdrachtbackendv1.Repositories.*;
import com.example.eindopdrachtbackendv1.models.*;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.*;


@Service
public class UserService {
    private final UserRepository userRepository;

    private UploadRepository uploadRepository;

    private FishingSpotRepository fishingSpotRepository;

    private GearRepository gearRepository;

    private PasswordEncoder encoder;

    private RoleRepository roleRepository;

    public UserService(UserRepository userRepository, UploadRepository uploadRepository, FishingSpotRepository fishingSpotRepository, GearRepository gearRepository, PasswordEncoder encoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.uploadRepository = uploadRepository;
        this.fishingSpotRepository = fishingSpotRepository;
        this.gearRepository = gearRepository;
        this.encoder = encoder;
        this.roleRepository = roleRepository;
    }

    public List<UserOutputDto> getUsers() {
        List<UserOutputDto> collection = new ArrayList<>();
        List<User> list = userRepository.findAll();
        for (User user : list) {
            collection.add(userToUserOutputDto(user));
        }

        return collection;
    }

    public UserOutputDto getUser(String username) {
        UserOutputDto dto;
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            dto = userToUserOutputDto(user.get());
        } else {
            throw new UsernameNotFoundException(username);
        }
        return dto;
    }

    public boolean userExists(Long id) {
        return userRepository.existsById(id);
    }

    public User createUser(UserInputDto userDTO) {
        User newUser = new User();
        newUser.setUsername(userDTO.getUsername());
        newUser.setPassword(encoder.encode(userDTO.getPassword()));
        newUser.setEmail(userDTO.getEmail());
        newUser.setDob(userDTO.getDob());

        Set<Role> userRoles = new HashSet<>();
        userRoles.add(new Role("USER"));
        newUser.setRoles(userRoles);


        userRepository.save(newUser);

        return newUser;
    }

    private UserOutputDto userToUserOutputDto(User user) {

        UserOutputDto userOutputDto = new UserOutputDto();

        userOutputDto.setId(user.getId());
        userOutputDto.setUsername(user.getUsername());
        userOutputDto.setPassword(encoder.encode(user.getPassword()));
        userOutputDto.setEmail(user.getEmail());
        userOutputDto.setDob(user.getDob());

        return userOutputDto;
    }

    private User userInputDtoToUser(UserInputDto userInputDto) {

        User user = new User();

        user.setId(userInputDto.getId());
        user.setUsername(userInputDto.getUsername());
        user.setPassword(encoder.encode(userInputDto.getPassword()));
        user.setEmail(userInputDto.getEmail());
        user.setDob(userInputDto.getDob());

        return user;
    }


    public UserOutputDto updateUser(UserInputDto userInput) {

        Long inputId = userInput.getId();

        User user = userRepository.findById(inputId)
                .orElseThrow(() -> new RecordNotFoundException(String.format("User with id %d not found", inputId)));

        user.setId(userInput.getId());

        userRepository.save(user);

        return userToUserOutputDto(user);
    }

    public void addFishingspot(String fishingspotID, Long userID) {
        Optional<User> userOptional = userRepository.findById(userID);
        Optional<FishingSpot> fishingSpotOptional = fishingSpotRepository.findBySpotLocation(fishingspotID);
        if (!userOptional.isEmpty() && !fishingSpotOptional.isEmpty()) {
            User user = userOptional.get();
            FishingSpot fishingSpot = fishingSpotOptional.get();
            user.addFishingSpot(fishingSpot);
            userRepository.save(user);
        }
    }

    public void addUpload(Long uploadID, Long userID) {
        Optional<User> userOptional = userRepository.findById(userID);
        Optional<Upload> uploadOptional = uploadRepository.findById(uploadID);
        if (!userOptional.isEmpty() && !uploadOptional.isEmpty()) {
            User user = userOptional.get();
            Upload upload = uploadOptional.get();
            user.addUpload(upload);
            userRepository.save(user);
        }
    }

    public void addGear(Long gearID, Long userID) {
        Optional<User> userOptional = userRepository.findById(userID);
        Optional<Gear> gearOptional = gearRepository.findById(gearID);
        if (!userOptional.isEmpty() && !gearOptional.isEmpty()) {
            User user = userOptional.get();
            Gear gear = gearOptional.get();
            user.addGear(gear);
            userRepository.save(user);
        }
    }


    public void deleteUser(Long username) {
        userRepository.deleteById(username);
    }
}
