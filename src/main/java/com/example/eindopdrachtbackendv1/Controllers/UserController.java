package com.example.eindopdrachtbackendv1.Controllers;

import com.example.eindopdrachtbackendv1.DTOS.Input.UserInputDto;
import com.example.eindopdrachtbackendv1.DTOS.Output.UserOutputDto;
import com.example.eindopdrachtbackendv1.Repositories.RoleRepository;
import com.example.eindopdrachtbackendv1.Repositories.UserRepository;
import com.example.eindopdrachtbackendv1.Services.UserService;
import com.example.eindopdrachtbackendv1.models.Rating;
import com.example.eindopdrachtbackendv1.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    public UserController(UserService userService, RoleRepository roleRepository, PasswordEncoder encoder, UserRepository userRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.userRepository = userRepository;
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

    @PutMapping(value = "")
    public ResponseEntity<UserOutputDto> updateUser(@Valid @RequestBody UserInputDto userInput) {

        UserOutputDto user = userService.updateUser(userInput);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(user).toUri();

        return ResponseEntity.created(uri).body(user);
    }

    @PostMapping(value = "/{id}/upload/{uploadid}")
    public ResponseEntity<Object> addUpload(@PathVariable("id") Long id, @PathVariable("uploadid") Long uploadid) {

        userService.addUpload(uploadid, id);

        return ResponseEntity.noContent().build();
    }


    @PostMapping(value = "/{id}/addfishingspot/{fishingspotid}")
    public ResponseEntity<Object> addFishingspot(@PathVariable("id") Long id, @PathVariable("fishingspotid") String fishingspotid) {

        userService.addFishingspot(fishingspotid, id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/{id}/addgear/{gearid}")
    public ResponseEntity<Object> addGear(@PathVariable("id") Long id, @PathVariable("gearid") Long gearid) {

        userService.addGear(gearid, id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/{id}/addlocation/{locationid}")
    public ResponseEntity<Object> addLocation(@PathVariable("id") Long id, @PathVariable("locationid") Long locationid) {

        userService.addLocation(locationid, id);

        return ResponseEntity.noContent().build();
    }


    @DeleteMapping(value = "/{id}/deleteuser")
    public ResponseEntity<Object> deleteUser(@PathVariable("id") Long username) {
        userService.deleteUser(username);
        return ResponseEntity.noContent().build();
    }


}
