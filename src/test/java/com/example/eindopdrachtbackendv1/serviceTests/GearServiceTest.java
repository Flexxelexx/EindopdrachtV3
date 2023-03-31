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
import static org.mockito.ArgumentMatchers.anyLong;
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
        // Setup
        final GearOutputDto gearOutputDto = new GearOutputDto();
        gearOutputDto.setId(0L);
        gearOutputDto.setRodLength(0.0);
        gearOutputDto.setKindOfReel("kindOfReel");
        gearOutputDto.setKindOfLure("kindOfLure");
        gearOutputDto.setLineLength("line");
        final List<GearOutputDto> expectedResult = List.of(gearOutputDto);

        // Configure GearRepository.findAll(...).
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

        // Run the test
        final List<GearOutputDto> result = gearServiceUnderTest.getGears();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetGears_GearRepositoryReturnsNoItems() {
        // Setup
        when(mockGearRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<GearOutputDto> result = gearServiceUnderTest.getGears();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetGear() {
        // Setup
        final GearOutputDto expectedResult = new GearOutputDto();
        expectedResult.setId(0L);
        expectedResult.setRodLength(0.0);
        expectedResult.setKindOfReel("kindOfReel");
        expectedResult.setKindOfLure("kindOfLure");
        expectedResult.setLineLength("line");

        // Configure GearRepository.findById(...).
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

        // Run the test
        final GearOutputDto result = gearServiceUnderTest.getGear(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetGear_GearRepositoryReturnsAbsent() {
        // Setup
        when(mockGearRepository.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> gearServiceUnderTest.getGear(0L)).isInstanceOf(RecordNotFoundException.class);
    }

    @Test
    void testCreateGear() {
        // Setup
        final GearInputDto gearDTO = new GearInputDto(0.0, "kindOfReel", "kindOfLure", "line");

        // Configure GearRepository.save(...).
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

        // Run the test
        final Long result = gearServiceUnderTest.createGear(gearDTO);

        // Verify the results
        assertThat(result).isEqualTo(0L);
    }

    @Test
    void testUpdateGear() {
        // Setup
        final GearInputDto gearInput = new GearInputDto(1L,0.0, "kindOfReel", "kindOfLure", "line");
        final GearOutputDto expectedResult = new GearOutputDto();
        expectedResult.setId(0L);
        expectedResult.setRodLength(0.0);
        expectedResult.setKindOfReel("kindOfReel");
        expectedResult.setKindOfLure("kindOfLure");
        expectedResult.setLineLength("line");

        // Configure GearRepository.findById(...).
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

        final FileDocument fileDocument2 = new FileDocument();
        fileDocument2.setFileName("fileName");
        fileDocument2.setDocFile("content".getBytes());
        fileDocument2.setId(0L);
        final Upload upload2 = new Upload();
        upload2.setId(0L);
        upload2.setWeightFish(0.0);
        upload2.setLengthFish(0.0);
        upload2.setCharsFish("charsFish");
        upload2.setSpeciesfish("speciesfish");
        final User users2 = new User();
        users2.setId(0L);
        users2.setFirstname("firstname");
        users2.setUsername("username");
        users2.setPassword("password");
        users2.setEmail("email");
        upload2.setUsers(users2);
        fileDocument2.setUpload(upload2);
        final FileDocument fileDocument3 = new FileDocument();
        fileDocument3.setFileName("fileName");
        fileDocument3.setDocFile("content".getBytes());
        fileDocument3.setId(0L);
        final Upload upload3 = new Upload();
        upload3.setId(0L);
        upload3.setWeightFish(0.0);
        upload3.setLengthFish(0.0);
        upload3.setCharsFish("charsFish");
        upload3.setSpeciesfish("speciesfish");
        final User users3 = new User();
        users3.setId(0L);
        users3.setFirstname("firstname");
        users3.setUsername("username");
        users3.setPassword("password");
        users3.setEmail("email");
        upload3.setUsers(users3);
        fileDocument3.setUpload(upload3);
        final Gear gear = new Gear(0L, 0.0, "kindOfReel", "kindOfLure", "line",
                new User(0L, "firstname", "username", "password", "email", LocalDate.of(2020, 1, 1),
                        Set.of(new Role("rolename")), List.of(new FishingSpot(0L, "spot1", "city1", "region1")),
                        List.of(new Upload(0L, 0.0, 0.0, "charsFish", "speciesfish", null, "locationCaught",
                                "cityCaught", null)), fileDocument2),
                new Upload(0L, 0.0, 0.0, "charsFish", "speciesfish",
                        new User(0L, "firstname", "username", "password", "email", LocalDate.of(2020, 1, 1),
                                Set.of(new Role("rolename")), List.of(new FishingSpot(0L, "spot1", "city1", "region1")),
                                List.of(), fileDocument3), "locationCaught", "cityCaught", null));
        when(mockGearRepository.save(any(Gear.class))).thenReturn(gear);

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
