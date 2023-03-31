package com.example.eindopdrachtbackendv1.controllerTests;

import com.example.eindopdrachtbackendv1.controllers.UploadController;
import com.example.eindopdrachtbackendv1.dtos.FileUploadResponse;
import com.example.eindopdrachtbackendv1.dtos.input.GearInputDto;
import com.example.eindopdrachtbackendv1.dtos.input.UploadGearInputDto;
import com.example.eindopdrachtbackendv1.dtos.input.UploadInputDto;
import com.example.eindopdrachtbackendv1.dtos.output.UploadGearOutputDto;
import com.example.eindopdrachtbackendv1.security.JwtService;
import com.example.eindopdrachtbackendv1.services.GearService;
import com.example.eindopdrachtbackendv1.services.UploadService;
import com.fasterxml.jackson.core.JsonProcessingException;
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

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(UploadController.class)
class UploadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UploadService mockUploadService;
    @MockBean
    private GearService mockGearService;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private AuthenticationManager authenticationManager;

    @Test
    void testGetUploads() throws Exception {

        final UploadGearOutputDto uploadGearOutputDto = new UploadGearOutputDto();
        uploadGearOutputDto.setId(0L);
        uploadGearOutputDto.setWeightFish(0.0);
        uploadGearOutputDto.setLengthFish(0.0);
        uploadGearOutputDto.setCharsFish("charsFish");
        uploadGearOutputDto.setSpeciesFish("speciesFish");
        uploadGearOutputDto.setLocationCaught("locationCaught");
        uploadGearOutputDto.setCityCaught("cityCaught");
        uploadGearOutputDto.setRodLength(0.0);
        uploadGearOutputDto.setKindOfReel("kindOfReel");
        uploadGearOutputDto.setKindOfLure("kindOfLure");
        uploadGearOutputDto.setLineLength("lineLength");
        uploadGearOutputDto.setUsername("username");
        final FileUploadResponse file = new FileUploadResponse();
        file.setFileName("fileName");
        file.setContentType("contentType");
        file.setUrl(null);
        uploadGearOutputDto.setFile(file);
        final List<UploadGearOutputDto> uploadGearOutputDtos = List.of(uploadGearOutputDto);
        when(mockUploadService.getUploads()).thenReturn(uploadGearOutputDtos);

        final MockHttpServletResponse response = mockMvc.perform(get("/uploads")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testGetUploads_UploadServiceReturnsNoItems() throws Exception {

        when(mockUploadService.getUploads()).thenReturn(Collections.emptyList());

        final MockHttpServletResponse response = mockMvc.perform(get("/uploads")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }

    @Test
    void testGetUpload() throws Exception {

        final UploadGearOutputDto uploadGearOutputDto = new UploadGearOutputDto();
        uploadGearOutputDto.setId(0L);
        uploadGearOutputDto.setWeightFish(0.0);
        uploadGearOutputDto.setLengthFish(0.0);
        uploadGearOutputDto.setCharsFish("charsFish");
        uploadGearOutputDto.setSpeciesFish("speciesFish");
        uploadGearOutputDto.setLocationCaught("locationCaught");
        uploadGearOutputDto.setCityCaught("cityCaught");
        uploadGearOutputDto.setRodLength(0.0);
        uploadGearOutputDto.setKindOfReel("kindOfReel");
        uploadGearOutputDto.setKindOfLure("kindOfLure");
        uploadGearOutputDto.setLineLength("lineLength");
        uploadGearOutputDto.setUsername("username");
        final FileUploadResponse file = new FileUploadResponse();
        file.setFileName("fileName");
        file.setContentType("contentType");
        uploadGearOutputDto.setFile(file);
        when(mockUploadService.getUpload(0L)).thenReturn(uploadGearOutputDto);

        final MockHttpServletResponse response = mockMvc.perform(get("/uploads/{upload}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String expectedResponse = objectMapper.writeValueAsString(uploadGearOutputDto);

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(expectedResponse);
    }

    @Test
    void testGetAllUploadsByUser() throws Exception {

        final UploadGearOutputDto uploadGearOutputDto = new UploadGearOutputDto();
        uploadGearOutputDto.setId(0L);
        uploadGearOutputDto.setWeightFish(0.0);
        uploadGearOutputDto.setLengthFish(0.0);
        uploadGearOutputDto.setCharsFish("charsFish");
        uploadGearOutputDto.setSpeciesFish("speciesFish");
        uploadGearOutputDto.setLocationCaught("locationCaught");
        uploadGearOutputDto.setCityCaught("cityCaught");
        uploadGearOutputDto.setRodLength(0.0);
        uploadGearOutputDto.setKindOfReel("kindOfReel");
        uploadGearOutputDto.setKindOfLure("kindOfLure");
        uploadGearOutputDto.setLineLength("lineLength");
        uploadGearOutputDto.setUsername("username");
        final FileUploadResponse file = new FileUploadResponse();
        file.setFileName("fileName");
        file.setContentType("contentType");
        uploadGearOutputDto.setFile(file);
        final List<UploadGearOutputDto> uploadGearOutputDtos = List.of(uploadGearOutputDto);
        when(mockUploadService.getAllUploadsByUser("username")).thenReturn(uploadGearOutputDtos);

        final MockHttpServletResponse response = mockMvc.perform(get("/uploads/user/{username}", "username")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        String expectedResponse = objectMapper.writeValueAsString(uploadGearOutputDtos);

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(expectedResponse);
    }

    @Test
    void testGetAllUploadsByUser_UploadServiceReturnsNoItems() throws Exception {

        when(mockUploadService.getAllUploadsByUser("username")).thenReturn(Collections.emptyList());

        final MockHttpServletResponse response = mockMvc.perform(get("/uploads/user/{username}", "username")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }

    @Test
    void testCreateUpload() throws Exception {

        when(mockGearService.createGear(new GearInputDto(0.0, "kindOfReel", "kindOfLure", "lineLength")))
                .thenReturn(0L);
        when(mockUploadService.createUpload(
                new UploadInputDto(0.0, 0.0, "charsFish", "speciesFish", "locationCaught", "cityCaught",
                        new FileUploadResponse("fileName", "contentType", "url")), 0L)).thenReturn(0L);

        final MockHttpServletResponse response = mockMvc.perform(post("/uploads")
                        .content(asJsonString(new UploadGearInputDto())).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String expectedResponse = objectMapper.writeValueAsString(null);

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo("0");
    }

    @Test
    void testUpdateUpload() throws Exception {

        final UploadGearOutputDto uploadGearOutputDto = new UploadGearOutputDto();
        uploadGearOutputDto.setId(0L);
        uploadGearOutputDto.setWeightFish(0.0);
        uploadGearOutputDto.setLengthFish(0.0);
        uploadGearOutputDto.setCharsFish("charsFish");
        uploadGearOutputDto.setSpeciesFish("speciesFish");
        uploadGearOutputDto.setLocationCaught("locationCaught");
        uploadGearOutputDto.setCityCaught("cityCaught");
        uploadGearOutputDto.setRodLength(0.0);
        uploadGearOutputDto.setKindOfReel("kindOfReel");
        uploadGearOutputDto.setKindOfLure("kindOfLure");
        uploadGearOutputDto.setLineLength("lineLength");
        uploadGearOutputDto.setUsername("username");
        final FileUploadResponse file = new FileUploadResponse();
        file.setFileName("fileName");
        file.setContentType("contentType");
        uploadGearOutputDto.setFile(file);
        when(mockUploadService.updateUpload(
                new UploadInputDto(0.0, 0.0, "charsFish", "speciesFish", "locationCaught", "cityCaught",
                        new FileUploadResponse("fileName", "contentType", "url")))).thenReturn(uploadGearOutputDto);

        final MockHttpServletResponse response = mockMvc.perform(put("/uploads")
                        .content(asJsonString(new UploadGearInputDto())).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo("");
    }

    @Test
    void testDeleteUpload() throws Exception {

        final MockHttpServletResponse response = mockMvc.perform(delete("/uploads/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
        assertThat(response.getContentAsString()).isEqualTo("");
        verify(mockUploadService).deleteUpload(0L);
    }

    @Test
    void testGetSpecies() throws Exception {

        final UploadGearOutputDto uploadGearOutputDto = new UploadGearOutputDto();
        uploadGearOutputDto.setId(0L);
        uploadGearOutputDto.setWeightFish(0.0);
        uploadGearOutputDto.setLengthFish(0.0);
        uploadGearOutputDto.setCharsFish("charsFish");
        uploadGearOutputDto.setSpeciesFish("speciesFish");
        uploadGearOutputDto.setLocationCaught("locationCaught");
        uploadGearOutputDto.setCityCaught("cityCaught");
        uploadGearOutputDto.setRodLength(0.0);
        uploadGearOutputDto.setKindOfReel("kindOfReel");
        uploadGearOutputDto.setKindOfLure("kindOfLure");
        uploadGearOutputDto.setLineLength("lineLength");
        uploadGearOutputDto.setUsername("username");
        final FileUploadResponse file = new FileUploadResponse();
        file.setFileName("fileName");
        file.setContentType("contentType");
        uploadGearOutputDto.setFile(file);
        final List<UploadGearOutputDto> uploadGearOutputDtos = List.of(uploadGearOutputDto);
        when(mockUploadService.getSpecies("speciesfish")).thenReturn(uploadGearOutputDtos);

        final MockHttpServletResponse response = mockMvc.perform(get("/uploads/species/{speciesfish}", "speciesfish")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        String expectedResponse = objectMapper.writeValueAsString(uploadGearOutputDtos);

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(expectedResponse);
    }

    @Test
    void testGetSpecies_UploadServiceReturnsNoItems() throws Exception {

        when(mockUploadService.getSpecies("speciesfish")).thenReturn(Collections.emptyList());

        final MockHttpServletResponse response = mockMvc.perform(get("/uploads/species/{speciesfish}", "speciesfish")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }

    public static String asJsonString(final UploadGearInputDto uploadGearInputDto) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            return mapper.writeValueAsString(uploadGearInputDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
