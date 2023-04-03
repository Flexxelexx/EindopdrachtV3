package com.example.eindopdrachtbackendv1.controllers;

import com.example.eindopdrachtbackendv1.dtos.input.UserInputDto;
import com.example.eindopdrachtbackendv1.dtos.output.UserOutputDto;
import com.example.eindopdrachtbackendv1.models.*;
import com.example.eindopdrachtbackendv1.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService mockUserService;

    @Test
    void testGetUsers() throws Exception {
        // Setup
        // Configure UserService.getUsers(...).
        final UserOutputDto userOutputDto = new UserOutputDto();
        userOutputDto.setId(0L);
        userOutputDto.setFirstname("firstname");
        userOutputDto.setUsername("username");
        userOutputDto.setPassword("password");
        userOutputDto.setEmail("email");
        userOutputDto.setDob(LocalDate.of(2020, 1, 1));
        userOutputDto.setUploadIds(List.of(0L));
        userOutputDto.setRole(Set.of("value"));
        final List<UserOutputDto> userOutputDtos = List.of(userOutputDto);
        when(mockUserService.getUsers()).thenReturn(userOutputDtos);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/users")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetUsers_UserServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockUserService.getUsers()).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/users")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }

    @Test
    void testGetUser() throws Exception {
        // Setup
        // Configure UserService.getUser(...).
        final UserOutputDto userOutputDto = new UserOutputDto();
        userOutputDto.setId(0L);
        userOutputDto.setFirstname("firstname");
        userOutputDto.setUsername("username");
        userOutputDto.setPassword("password");
        userOutputDto.setEmail("email");
        userOutputDto.setDob(LocalDate.of(2020, 1, 1));
        userOutputDto.setUploadIds(List.of(0L));
        userOutputDto.setRole(Set.of("value"));
        when(mockUserService.getUser("username")).thenReturn(userOutputDto);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/users/{username}", "username")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testCreateUser() throws Exception {
        // Setup
        // Configure UserService.createUser(...).
        final FileDocument fileDocument = new FileDocument();
        fileDocument.setFileName("fileName");
        fileDocument.setDocFile("content".getBytes());
        fileDocument.setId(0L);
        final Upload upload = new Upload();
        upload.setId(0L);
        upload.setWeightFish(0.0);
        upload.setLengthFish(0.0);
        upload.setCharsFish("charsFish");
        upload.setSpeciesfish("speciesfish");
        final User users = new User();
        users.setId(0L);
        users.setFirstname("firstname");
        users.setUsername("username");
        users.setPassword("password");
        users.setEmail("email");
        upload.setUsers(users);
        fileDocument.setUpload(upload);
        final User user = new User(0L, "firstname", "username", "password", "email", LocalDate.of(2020, 1, 1),
                Set.of(new Role("rolename")), List.of(new FishingSpot(0L, "spot1", "city1", "region1")),
                List.of(new Upload(0L, 0.0, 0.0, "charsFish", "speciesfish", null, "locationCaught", "cityCaught",
                        new Gear(0L, 0.0, "kindOfReel", "kindOfLure", "line", null, null))), fileDocument);
        when(mockUserService.createUser(new UserInputDto())).thenReturn(user);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/users")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testCreateAdmin() throws Exception {
        // Setup
        // Configure UserService.createAdmin(...).
        final FileDocument fileDocument = new FileDocument();
        fileDocument.setFileName("fileName");
        fileDocument.setDocFile("content".getBytes());
        fileDocument.setId(0L);
        final Upload upload = new Upload();
        upload.setId(0L);
        upload.setWeightFish(0.0);
        upload.setLengthFish(0.0);
        upload.setCharsFish("charsFish");
        upload.setSpeciesfish("speciesfish");
        final User users = new User();
        users.setId(0L);
        users.setFirstname("firstname");
        users.setUsername("username");
        users.setPassword("password");
        users.setEmail("email");
        upload.setUsers(users);
        fileDocument.setUpload(upload);
        final User user = new User(0L, "firstname", "username", "password", "email", LocalDate.of(2020, 1, 1),
                Set.of(new Role("rolename")), List.of(new FishingSpot(0L, "spot1", "city1", "region1")),
                List.of(new Upload(0L, 0.0, 0.0, "charsFish", "speciesfish", null, "locationCaught", "cityCaught",
                        new Gear(0L, 0.0, "kindOfReel", "kindOfLure", "line", null, null))), fileDocument);
        when(mockUserService.createAdmin(new UserInputDto())).thenReturn(user);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/users/createadmin")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testUpdateUser() throws Exception {
        // Setup
        // Configure UserService.updateUser(...).
        final UserOutputDto userOutputDto = new UserOutputDto();
        userOutputDto.setId(0L);
        userOutputDto.setFirstname("firstname");
        userOutputDto.setUsername("username");
        userOutputDto.setPassword("password");
        userOutputDto.setEmail("email");
        userOutputDto.setDob(LocalDate.of(2020, 1, 1));
        userOutputDto.setUploadIds(List.of(0L));
        userOutputDto.setRole(Set.of("value"));
        when(mockUserService.updateUser(new UserInputDto())).thenReturn(userOutputDto);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(put("/users/{username}")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testAddUpload() throws Exception {
        // Setup
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/users/{id}/upload/{uploadid}", 0, 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
        verify(mockUserService).addUpload(0L, 0L);
    }

    @Test
    void testAddFishingspot() throws Exception {
        // Setup
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(
                        post("/users/{id}/addfishingspot/{fishingspotid}", 0, "fishingspotid")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
        verify(mockUserService).addFishingspot("fishingspotid", 0L);
    }

    @Test
    void testDeleteUser() throws Exception {
        // Setup
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/users/{id}/deleteuser", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
        verify(mockUserService).deleteUser(0L);
    }
}
