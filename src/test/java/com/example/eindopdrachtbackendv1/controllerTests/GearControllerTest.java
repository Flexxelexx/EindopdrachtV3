package com.example.eindopdrachtbackendv1.controllerTests;

import com.example.eindopdrachtbackendv1.controllers.GearController;
import com.example.eindopdrachtbackendv1.dtos.input.GearInputDto;
import com.example.eindopdrachtbackendv1.dtos.output.GearOutputDto;
import com.example.eindopdrachtbackendv1.services.GearService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(GearController.class)
public class GearControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GearService gearService;

    private GearOutputDto gearOutputDto;
    private GearInputDto gearInputDto;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
        gearOutputDto = new GearOutputDto();
        gearOutputDto.setId(1L);
        gearOutputDto.setRodLength(2.5);
        gearOutputDto.setKindOfReel("Spinning");
        gearOutputDto.setKindOfLure("Soft plastic");
        gearOutputDto.setLineLength("8/100");

        gearInputDto = new GearInputDto();
        gearInputDto.setRodLength(2.5);
        gearInputDto.setKindOfReel("Spinning");
        gearInputDto.setKindOfLure("Soft plastic");
        gearInputDto.setLineLength("8/100");
    }

    @Test
    public void testGetGears() throws Exception {
        List<GearOutputDto> gearList = Arrays.asList(gearOutputDto);

        when(gearService.getGears()).thenReturn(gearList);

        mockMvc.perform(get("/gears"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].rodLength", is(2.5)))
                .andExpect(jsonPath("$[0].kindOfReel", is("Spinning")))
                .andExpect(jsonPath("$[0].kindOfLure", is("Soft plastic")))
                .andExpect(jsonPath("$[0].lineLength", is(150.0)));
        verify(gearService, times(1)).getGears();
    }

    @Test
    public void testGetGear() throws Exception {
        when(gearService.getGear(1L)).thenReturn(gearOutputDto);

        mockMvc.perform(get("/gears/{gear}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.rodLength", is(2.5)))
                .andExpect(jsonPath("$.kindOfReel", is("Spinning")))
                .andExpect(jsonPath("$.kindOfLure", is("Soft plastic")))
                .andExpect(jsonPath("$.lineLength", is(150.0)));

        verify(gearService, times(1)).getGear(1L);
    }

    @Test
    public void testCreateGear() throws Exception {
        when(gearService.createGear(gearInputDto)).thenReturn(1L);

        mockMvc.perform(post("/gears")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(gearInputDto)))
                .andExpect(status().isCreated());

        verify(gearService, times(1)).createGear(gearInputDto);
    }

    @Test
    public void testUpdateGear() throws Exception {
        when(gearService.updateGear(gearInputDto)).thenReturn(gearOutputDto);

        mockMvc.perform(put("/gears")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(gearInputDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.rodLength", is(2.5)))
                .andExpect(jsonPath("$.kindOfReel", is("Spinning")))
                .andExpect(jsonPath("$.kindOfLure", is("Soft plastic")))
                .andExpect(jsonPath("$.lineLength", is(150.0)));

        verify(gearService, times(1)).updateGear(gearInputDto);
    }

    @Test
    public void testDeleteGear() throws Exception {
        mockMvc.perform(delete("/gears/{id}", 1))
                .andExpect(status().isNoContent());

        verify(gearService, times(1)).deleteGear(1L);
    }
}
