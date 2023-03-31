package com.example.eindopdrachtbackendv1.controllerTests;

import com.example.eindopdrachtbackendv1.controllers.GearController;
import com.example.eindopdrachtbackendv1.dtos.input.GearInputDto;
import com.example.eindopdrachtbackendv1.dtos.input.UploadGearInputDto;
import com.example.eindopdrachtbackendv1.dtos.output.GearOutputDto;
import com.example.eindopdrachtbackendv1.security.JwtService;
import com.example.eindopdrachtbackendv1.services.GearService;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(GearController.class)
class GearControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GearService mockGearService;

    @MockBean
    private JwtService jwtService;

    @Test
    void testGetGears() throws Exception {

        final GearOutputDto gearOutputDto = new GearOutputDto();
        gearOutputDto.setId(0L);
        gearOutputDto.setRodLength(0.0);
        gearOutputDto.setKindOfReel("kindOfReel");
        gearOutputDto.setKindOfLure("kindOfLure");
        gearOutputDto.setLineLength("lineLength");
        final List<GearOutputDto> gearOutputDtos = List.of(gearOutputDto);
        when(mockGearService.getGears()).thenReturn(gearOutputDtos);

        final MockHttpServletResponse response = mockMvc.perform(get("/gears")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String expectedResponse = objectMapper.writeValueAsString(gearOutputDtos);

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(expectedResponse);
    }

    @Test
    void testGetGears_GearServiceReturnsNoItems() throws Exception {

        when(mockGearService.getGears()).thenReturn(Collections.emptyList());

        final MockHttpServletResponse response = mockMvc.perform(get("/gears")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }

    @Test
    void testGetGear() throws Exception {

        final GearOutputDto gearOutputDto = new GearOutputDto();
        gearOutputDto.setId(0L);
        gearOutputDto.setRodLength(0.0);
        gearOutputDto.setKindOfReel("kindOfReel");
        gearOutputDto.setKindOfLure("kindOfLure");
        gearOutputDto.setLineLength("lineLength");
        when(mockGearService.getGear(0L)).thenReturn(gearOutputDto);

        final MockHttpServletResponse response = mockMvc.perform(get("/gears/{gear}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String expectedResponse = objectMapper.writeValueAsString(gearOutputDto);

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(expectedResponse);
    }

    @Test
    void testCreateGear() throws Exception {

        when(mockGearService.createGear(new GearInputDto(0.0, "kindOfReel", "kindOfLure", "lineLength")))
                .thenReturn(0L);

        final MockHttpServletResponse response = mockMvc.perform(post("/gears")
                        .content(asJsonString(new GearInputDto())).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo("");
    }

    @Test
    void testUpdateGear() throws Exception {

        final GearOutputDto gearOutputDto = new GearOutputDto();
        gearOutputDto.setId(0L);
        gearOutputDto.setRodLength(0.0);
        gearOutputDto.setKindOfReel("kindOfReel");
        gearOutputDto.setKindOfLure("kindOfLure");
        gearOutputDto.setLineLength("lineLength");
        when(mockGearService.updateGear(new GearInputDto(0.0, "kindOfReel", "kindOfLure", "lineLength")))
                .thenReturn(gearOutputDto);

        final MockHttpServletResponse response = mockMvc.perform(put("/gears")
                        .content(asJsonString(new GearInputDto())).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String expectedResponse = objectMapper.writeValueAsString(gearOutputDto);

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo("");
    }

    @Test
    void testDeleteGear() throws Exception {

        final MockHttpServletResponse response = mockMvc.perform(delete("/gears/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();


        assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
        assertThat(response.getContentAsString()).isEqualTo("");
        verify(mockGearService).deleteGear(0L);
    }

    public static String asJsonString(final GearInputDto gearInputDto) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            return mapper.writeValueAsString(gearInputDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
