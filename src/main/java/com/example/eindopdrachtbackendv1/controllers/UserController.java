package com.example.eindopdrachtbackendv1.controllers;

import com.example.eindopdrachtbackendv1.dtos.input.UserInputDto;
import com.example.eindopdrachtbackendv1.dtos.output.UserOutputDto;
import com.example.eindopdrachtbackendv1.services.UserService;
import com.example.eindopdrachtbackendv1.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserOutputDto>> getUsers() {

        List<UserOutputDto> userDtos = userService.getUsers();

        return ResponseEntity.ok().body(userDtos);
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity<UserOutputDto> getUser(@PathVariable("username") String username) {

        UserOutputDto optionalUser = userService.getUser(username);

        return ResponseEntity.ok().body(optionalUser);
    }


    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody UserInputDto userDTO) {

        User user = userService.createUser(userDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(user.getUsername()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping(value = "/createadmin")
    public ResponseEntity<User> createAdmin(@Valid @RequestBody UserInputDto userDTO){
        User admin = userService.createAdmin(userDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(admin.getUsername()).toUri();
        return ResponseEntity.created(location).build();
    }


    @PutMapping(value = "/{username}")
    public ResponseEntity<UserOutputDto> updateUser(@Valid @RequestBody UserInputDto userInput) {

        UserOutputDto user = userService.updateUser(userInput);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(user).toUri();

        return ResponseEntity.created(uri).body(user);
    }


    @PostMapping(value = "/{id}/upload/{uploadid}")
    public ResponseEntity<Object> addUpload(@PathVariable("id") Long id, @PathVariable("uploadid") Long uploadID) {

        userService.addUpload(uploadID, id);

        return ResponseEntity.noContent().build();
    }


    @PostMapping(value = "/{id}/addfishingspot/{fishingspotid}")
    public ResponseEntity<Object> addFishingspot(@PathVariable("id") Long id, @PathVariable("fishingspotid") String fishingspotid) {

        userService.addFishingspot(fishingspotid, id);

        return ResponseEntity.noContent().build();
    }


    @DeleteMapping(value = "/{id}/deleteuser")
    public ResponseEntity<Object> deleteUser(@PathVariable("id") Long username) {
        userService.deleteUser(username);
        return ResponseEntity.ok().build();
    }


}
