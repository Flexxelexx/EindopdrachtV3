package com.example.eindopdrachtbackendv1.controllerTests;


import com.example.eindopdrachtbackendv1.controllers.UserController;
import com.example.eindopdrachtbackendv1.dtos.input.UserInputDto;
import com.example.eindopdrachtbackendv1.dtos.output.UploadGearOutputDto;
import com.example.eindopdrachtbackendv1.dtos.output.UserOutputDto;
import com.example.eindopdrachtbackendv1.models.*;

import com.example.eindopdrachtbackendv1.security.JwtService;
import com.example.eindopdrachtbackendv1.services.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
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
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService mockUserService;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private AuthenticationManager authenticationManager;


    @Test
    void testGetUsers() throws Exception {
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

        final MockHttpServletResponse response = mockMvc.perform(get("/users")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        String expectedResponse = objectMapper.writeValueAsString(userOutputDtos);

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(expectedResponse);
    }

    @Test
    void testGetUsers_UserServiceReturnsNoItems() throws Exception {

        when(mockUserService.getUsers()).thenReturn(Collections.emptyList());

        final MockHttpServletResponse response = mockMvc.perform(get("/users")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }

    @Test
    void testGetUser() throws Exception {

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


        final MockHttpServletResponse response = mockMvc.perform(get("/users/{username}", "username")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        String expectedResponse = objectMapper.writeValueAsString(userOutputDto);

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(expectedResponse);
    }

    @Test
    void testCreateUser() throws Exception {

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


        final MockHttpServletResponse response = mockMvc.perform(post("/users")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();


        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(response.getContentAsString()).isEqualTo("");
    }

    @Test
    void testUpdateUser() throws Exception {
        final UserInputDto userInputDto = new UserInputDto();
        userInputDto.setFirstname("firstname");
        userInputDto.setUsername("username");
        userInputDto.setPassword("password");
        userInputDto.setEmail("email");
        userInputDto.setDob(LocalDate.of(2020, 1, 1));


        final UserOutputDto userOutputDto = new UserOutputDto();
        userOutputDto.setId(0L);
        userOutputDto.setFirstname("firstname");
        userOutputDto.setUsername("username");
        userOutputDto.setPassword("password");
        userOutputDto.setEmail("email");
        userOutputDto.setDob(LocalDate.of(2020, 1, 1));
        userOutputDto.setUploadIds(List.of(0L));
        when(mockUserService.updateUser(userInputDto)).thenReturn(userOutputDto);

        String username = "username";

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        String inputJson = objectMapper.writeValueAsString(userInputDto);

        final MockHttpServletResponse response = mockMvc.perform(put("/users/{username}", username)
                        .content(inputJson).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();


        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(response.getContentAsString()).isEqualTo("");
    }


    @Test
    void testAddFishingspot() throws Exception {

        final MockHttpServletResponse response = mockMvc.perform(
                        post("/users/{id}/addfishingspot/{fishingspotid}", 0, "fishingspotid")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();


        assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
        assertThat(response.getContentAsString()).isEmpty();
        verify(mockUserService).addFishingspot("fishingspotid", 0L);
    }

    @Test
    void testDeleteUser() throws Exception {

        final MockHttpServletResponse response = mockMvc.perform(delete("/users/{id}/deleteuser", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();


        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("");
        verify(mockUserService).deleteUser(0L);
    }
}
