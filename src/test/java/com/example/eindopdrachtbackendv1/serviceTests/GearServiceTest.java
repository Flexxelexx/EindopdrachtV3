package com.example.eindopdrachtbackendv1.serviceTests;

import com.example.eindopdrachtbackendv1.dtos.input.GearInputDto;
import com.example.eindopdrachtbackendv1.dtos.output.GearOutputDto;
import com.example.eindopdrachtbackendv1.exceptions.RecordNotFoundException;
import com.example.eindopdrachtbackendv1.models.*;
import com.example.eindopdrachtbackendv1.repositories.GearRepository;
import com.example.eindopdrachtbackendv1.services.GearService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GearServiceTest {

    @Mock
    private GearRepository mockGearRepository;

    private GearService gearServiceUnderTest;

    @BeforeEach
    void setUp() {
        gearServiceUnderTest = new GearService(mockGearRepository);
    }

    @Test
    void testGetGears() {

        final GearOutputDto gearOutputDto = new GearOutputDto();
        gearOutputDto.setId(0L);
        gearOutputDto.setRodLength(0.0);
        gearOutputDto.setKindOfReel("kindOfReel");
        gearOutputDto.setKindOfLure("kindOfLure");
        gearOutputDto.setLineLength("line");
        final List<GearOutputDto> expectedResult = List.of(gearOutputDto);

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
        final FileDocument fileDocument1 = new FileDocument();
        fileDocument1.setFileName("fileName");
        fileDocument1.setDocFile("content".getBytes());
        fileDocument1.setId(0L);
        final Upload upload1 = new Upload();
        upload1.setId(0L);
        upload1.setWeightFish(0.0);
        upload1.setLengthFish(0.0);
        upload1.setCharsFish("charsFish");
        upload1.setSpeciesfish("speciesfish");
        final User users1 = new User();
        users1.setId(0L);
        users1.setFirstname("firstname");
        users1.setUsername("username");
        users1.setPassword("password");
        users1.setEmail("email");
        upload1.setUsers(users1);
        fileDocument1.setUpload(upload1);
        final List<Gear> gears = List.of(new Gear(0L, 0.0, "kindOfReel", "kindOfLure", "line",
                new User(0L, "firstname", "username", "password", "email", LocalDate.of(2020, 1, 1),
                        Set.of(new Role("rolename")), List.of(new FishingSpot(0L, "spot1", "city1", "region1")),
                        List.of(new Upload(0L, 0.0, 0.0, "charsFish", "speciesfish", null, "locationCaught",
                                "cityCaught", null)), fileDocument),
                new Upload(0L, 0.0, 0.0, "charsFish", "speciesfish",
                        new User(0L, "firstname", "username", "password", "email", LocalDate.of(2020, 1, 1),
                                Set.of(new Role("rolename")), List.of(new FishingSpot(0L, "spot1", "city1", "region1")),
                                List.of(), fileDocument1), "locationCaught", "cityCaught", null)));
        when(mockGearRepository.findAll()).thenReturn(gears);

        final List<GearOutputDto> result = gearServiceUnderTest.getGears();

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetGears_GearRepositoryReturnsNoItems() {

        when(mockGearRepository.findAll()).thenReturn(Collections.emptyList());

        final List<GearOutputDto> result = gearServiceUnderTest.getGears();

        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetGear() {

        final GearOutputDto expectedResult = new GearOutputDto();
        expectedResult.setId(0L);
        expectedResult.setRodLength(0.0);
        expectedResult.setKindOfReel("kindOfReel");
        expectedResult.setKindOfLure("kindOfLure");
        expectedResult.setLineLength("line");

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
        final FileDocument fileDocument1 = new FileDocument();
        fileDocument1.setFileName("fileName");
        fileDocument1.setDocFile("content".getBytes());
        fileDocument1.setId(0L);
        final Upload upload1 = new Upload();
        upload1.setId(0L);
        upload1.setWeightFish(0.0);
        upload1.setLengthFish(0.0);
        upload1.setCharsFish("charsFish");
        upload1.setSpeciesfish("speciesfish");
        final User users1 = new User();
        users1.setId(0L);
        users1.setFirstname("firstname");
        users1.setUsername("username");
        users1.setPassword("password");
        users1.setEmail("email");
        upload1.setUsers(users1);
        fileDocument1.setUpload(upload1);
        final Optional<Gear> gearOptional = Optional.of(new Gear(0L, 0.0, "kindOfReel", "kindOfLure", "line",
                new User(0L, "firstname", "username", "password", "email", LocalDate.of(2020, 1, 1),
                        Set.of(new Role("rolename")), List.of(new FishingSpot(0L, "spot1", "city1", "region1")),
                        List.of(new Upload(0L, 0.0, 0.0, "charsFish", "speciesfish", null, "locationCaught",
                                "cityCaught", null)), fileDocument),
                new Upload(0L, 0.0, 0.0, "charsFish", "speciesfish",
                        new User(0L, "firstname", "username", "password", "email", LocalDate.of(2020, 1, 1),
                                Set.of(new Role("rolename")), List.of(new FishingSpot(0L, "spot1", "city1", "region1")),
                                List.of(), fileDocument1), "locationCaught", "cityCaught", null)));
        when(mockGearRepository.findById(0L)).thenReturn(gearOptional);

        final GearOutputDto result = gearServiceUnderTest.getGear(0L);

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetGear_GearRepositoryReturnsAbsent() {

        when(mockGearRepository.findById(0L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> gearServiceUnderTest.getGear(0L)).isInstanceOf(RecordNotFoundException.class);
    }

    @Test
    void testCreateGear() {

        final GearInputDto gearDTO = new GearInputDto(0.0, "kindOfReel", "kindOfLure", "line");

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
        final FileDocument fileDocument1 = new FileDocument();
        fileDocument1.setFileName("fileName");
        fileDocument1.setDocFile("content".getBytes());
        fileDocument1.setId(0L);
        final Upload upload1 = new Upload();
        upload1.setId(0L);
        upload1.setWeightFish(0.0);
        upload1.setLengthFish(0.0);
        upload1.setCharsFish("charsFish");
        upload1.setSpeciesfish("speciesfish");
        final User users1 = new User();
        users1.setId(0L);
        users1.setFirstname("firstname");
        users1.setUsername("username");
        users1.setPassword("password");
        users1.setEmail("email");
        upload1.setUsers(users1);
        fileDocument1.setUpload(upload1);
        final Gear gear = new Gear(0L, 0.0, "kindOfReel", "kindOfLure", "line",
                new User(0L, "firstname", "username", "password", "email", LocalDate.of(2020, 1, 1),
                        Set.of(new Role("rolename")), List.of(new FishingSpot(0L, "spot1", "city1", "region1")),
                        List.of(new Upload(0L, 0.0, 0.0, "charsFish", "speciesfish", null, "locationCaught",
                                "cityCaught", null)), fileDocument),
                new Upload(0L, 0.0, 0.0, "charsFish", "speciesfish",
                        new User(0L, "firstname", "username", "password", "email", LocalDate.of(2020, 1, 1),
                                Set.of(new Role("rolename")), List.of(new FishingSpot(0L, "spot1", "city1", "region1")),
                                List.of(), fileDocument1), "locationCaught", "cityCaught", null));
        when(mockGearRepository.save(any(Gear.class))).thenReturn(gear);

        final Long result = gearServiceUnderTest.createGear(gearDTO);

        assertThat(result).isEqualTo(0L);
    }

    @Test
    void testUpdateGear() {

        final GearInputDto gearInput = new GearInputDto(1L,0.0, "kindOfReel", "kindOfLure", "line");
        final GearOutputDto expectedResult = new GearOutputDto();
        expectedResult.setId(0L);
        expectedResult.setRodLength(0.0);
        expectedResult.setKindOfReel("kindOfReel");
        expectedResult.setKindOfLure("kindOfLure");
        expectedResult.setLineLength("line");

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
        final FileDocument fileDocument1 = new FileDocument();
        fileDocument1.setFileName("fileName");
        fileDocument1.setDocFile("content".getBytes());
        fileDocument1.setId(0L);
        final Upload upload1 = new Upload();
        upload1.setId(0L);
        upload1.setWeightFish(0.0);
        upload1.setLengthFish(0.0);
        upload1.setCharsFish("charsFish");
        upload1.setSpeciesfish("speciesfish");
        final User users1 = new User();
        users1.setId(0L);
        users1.setFirstname("firstname");
        users1.setUsername("username");
        users1.setPassword("password");
        users1.setEmail("email");
        upload1.setUsers(users1);
        fileDocument1.setUpload(upload1);
        final Optional<Gear> gearOptional = Optional.of(new Gear(0L, 0.0, "kindOfReel", "kindOfLure", "line",
                new User(0L, "firstname", "username", "password", "email", LocalDate.of(2020, 1, 1),
                        Set.of(new Role("rolename")), List.of(new FishingSpot(0L, "spot1", "city1", "region1")),
                        List.of(new Upload(0L, 0.0, 0.0, "charsFish", "speciesfish", null, "locationCaught",
                                "cityCaught", null)), fileDocument),
                new Upload(0L, 0.0, 0.0, "charsFish", "speciesfish",
                        new User(0L, "firstname", "username", "password", "email", LocalDate.of(2020, 1, 1),
                                Set.of(new Role("rolename")), List.of(new FishingSpot(0L, "spot1", "city1", "region1")),
                                List.of(), fileDocument1), "locationCaught", "cityCaught", null)));
        when(mockGearRepository.findById(any(Long.class))).thenReturn(gearOptional);

        final GearOutputDto result = gearServiceUnderTest.updateGear(gearInput);

        assertThat(result).isEqualTo(expectedResult);
        verify(mockGearRepository).save(any(Gear.class));
    }

    @Test
    void testUpdateGear_GearRepositoryFindByIdReturnsAbsent() {

        final GearInputDto gearInput = new GearInputDto(1L,0.0, "kindOfReel", "kindOfLure", "line");
        when(mockGearRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> gearServiceUnderTest.updateGear(gearInput))
                .isInstanceOf(RecordNotFoundException.class);
    }

    @Test
    void testDeleteGear() {

        gearServiceUnderTest.deleteGear(0L);


        verify(mockGearRepository).deleteById(0L);
    }
}
