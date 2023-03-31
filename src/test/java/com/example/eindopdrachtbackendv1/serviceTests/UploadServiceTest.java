package com.example.eindopdrachtbackendv1.serviceTests;

import com.example.eindopdrachtbackendv1.dtos.FileUploadResponse;
import com.example.eindopdrachtbackendv1.dtos.input.UploadInputDto;
import com.example.eindopdrachtbackendv1.dtos.output.UploadGearOutputDto;
import com.example.eindopdrachtbackendv1.exceptions.RecordNotFoundException;
import com.example.eindopdrachtbackendv1.models.*;
import com.example.eindopdrachtbackendv1.repositories.DocFileRepository;
import com.example.eindopdrachtbackendv1.repositories.GearRepository;
import com.example.eindopdrachtbackendv1.repositories.UploadRepository;
import com.example.eindopdrachtbackendv1.services.UploadService;
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
class UploadServiceTest {

    @Mock
    private UploadRepository mockRepository;
    @Mock
    private DocFileRepository mockDocFileRepository;
    @Mock
    private GearRepository mockGearRepository;

    private UploadService uploadServiceUnderTest;

    @BeforeEach
    void setUp() {
        uploadServiceUnderTest = new UploadService(mockRepository, mockDocFileRepository, mockGearRepository);
    }

    @Test
    void testGetUploads() {

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
        uploadGearOutputDto.setLineLength("line");
        uploadGearOutputDto.setUsername("username");
        final FileUploadResponse file = new FileUploadResponse();
        file.setFileName("fileName");
        file.setContentType("contentType");
        file.setUrl("url");
        uploadGearOutputDto.setFile(null);
        final List<UploadGearOutputDto> expectedResult = List.of(uploadGearOutputDto);

        final FileDocument fileDocument = new FileDocument();
        fileDocument.setFileName("fileName");
        fileDocument.setDocFile("content".getBytes());
        fileDocument.setId(0L);
        final Upload upload = new Upload();
        upload.setId(0L);
        upload.setWeightFish(0.0);
        upload.setLengthFish(0.0);
        upload.setCharsFish("charsFish");
        upload.setSpeciesfish("speciesFish");
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
        upload1.setSpeciesfish("speciesFish");
        final User users1 = new User();
        users1.setId(0L);
        users1.setFirstname("firstname");
        users1.setUsername("username");
        users1.setPassword("password");
        users1.setEmail("email");
        upload1.setUsers(users1);
        fileDocument1.setUpload(upload1);
        final List<Upload> uploads = List.of(new Upload(0L, 0.0, 0.0, "charsFish", "speciesFish",
                new User(0L, "firstname", "username", "password", "email", LocalDate.of(2020, 1, 1),
                        Set.of(new Role("rolename")), List.of(new FishingSpot(0L, "spot1", "city1", "region1")),
                        List.of(), fileDocument), "locationCaught", "cityCaught",
                new Gear(0L, 0.0, "kindOfReel", "kindOfLure", "line",
                        new User(0L, "firstname", "username", "password", "email", LocalDate.of(2020, 1, 1),
                                Set.of(new Role("rolename")), List.of(new FishingSpot(0L, "spot1", "city1", "region1")),
                                List.of(), fileDocument1), null)));
        when(mockRepository.findAll()).thenReturn(uploads);

        final List<UploadGearOutputDto> result = uploadServiceUnderTest.getUploads();

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetUploads_UploadRepositoryReturnsNoItems() {
        when(mockRepository.findAll()).thenReturn(Collections.emptyList());

        final List<UploadGearOutputDto> result = uploadServiceUnderTest.getUploads();

        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetSpecies() {

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
        uploadGearOutputDto.setLineLength("line");
        uploadGearOutputDto.setUsername("username");
        final FileUploadResponse file = new FileUploadResponse();
        file.setFileName("fileName");
        file.setContentType("contentType");
        file.setUrl("url");
        uploadGearOutputDto.setFile(null);
        final List<UploadGearOutputDto> expectedResult = List.of(uploadGearOutputDto);

        final FileDocument fileDocument = new FileDocument();
        fileDocument.setFileName("fileName");
        fileDocument.setDocFile("content".getBytes());
        fileDocument.setId(0L);
        final Upload upload = new Upload();
        upload.setId(0L);
        upload.setWeightFish(0.0);
        upload.setLengthFish(0.0);
        upload.setCharsFish("charsFish");
        upload.setSpeciesfish("speciesFish");
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
        upload1.setSpeciesfish("speciesFish");
        final User users1 = new User();
        users1.setId(0L);
        users1.setFirstname("firstname");
        users1.setUsername("username");
        users1.setPassword("password");
        users1.setEmail("email");
        upload1.setUsers(users1);
        fileDocument1.setUpload(upload1);
        final List<Upload> uploads = List.of(new Upload(0L, 0.0, 0.0, "charsFish", "speciesFish",
                new User(0L, "firstname", "username", "password", "email", LocalDate.of(2020, 1, 1),
                        Set.of(new Role("rolename")), List.of(new FishingSpot(0L, "spot1", "city1", "region1")),
                        List.of(), fileDocument), "locationCaught", "cityCaught",

                new Gear(0L, 0.0, "kindOfReel", "kindOfLure", "line",
                        new User(0L, "firstname", "username", "password", "email", LocalDate.of(2020, 1, 1),
                                Set.of(new Role("rolename")), List.of(new FishingSpot(0L, "spot1", "city1", "region1")),
                                List.of(), fileDocument1), null)));

        when(mockRepository.findBySpeciesfish("speciesFish")).thenReturn(uploads);

        final List<UploadGearOutputDto> result = uploadServiceUnderTest.getSpecies("speciesFish");

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetSpecies_UploadRepositoryReturnsNoItems() {

        when(mockRepository.findBySpeciesfish("speciesfish")).thenReturn(Collections.emptyList());

        final List<UploadGearOutputDto> result = uploadServiceUnderTest.getSpecies("speciesfish");

        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetUpload() {
        final UploadGearOutputDto expectedResult = new UploadGearOutputDto();
        expectedResult.setId(0L);
        expectedResult.setWeightFish(0.0);
        expectedResult.setLengthFish(0.0);
        expectedResult.setCharsFish("charsFish");
        expectedResult.setSpeciesFish("speciesFish");
        expectedResult.setLocationCaught("locationCaught");
        expectedResult.setCityCaught("cityCaught");
        expectedResult.setRodLength(0.0);
        expectedResult.setKindOfReel("kindOfReel");
        expectedResult.setKindOfLure("kindOfLure");
        expectedResult.setLineLength("line");
        expectedResult.setUsername("username");
        final FileUploadResponse file = new FileUploadResponse();
        file.setFileName("fileName");
        file.setContentType("contentType");
        file.setUrl("url");
        expectedResult.setFile(null);

        final FileDocument fileDocument = new FileDocument();
        fileDocument.setFileName("fileName");
        fileDocument.setDocFile("content".getBytes());
        fileDocument.setId(0L);
        final Upload upload1 = new Upload();
        upload1.setId(0L);
        upload1.setWeightFish(0.0);
        upload1.setLengthFish(0.0);
        upload1.setCharsFish("charsFish");
        upload1.setSpeciesfish("speciesFish");
        final User users = new User();
        users.setId(0L);
        users.setFirstname("firstname");
        users.setUsername("username");
        users.setPassword("password");
        users.setEmail("email");
        upload1.setUsers(users);
        fileDocument.setUpload(upload1);
        final FileDocument fileDocument1 = new FileDocument();
        fileDocument1.setFileName("fileName");
        fileDocument1.setDocFile("content".getBytes());
        fileDocument1.setId(0L);
        final Upload upload2 = new Upload();
        upload2.setId(0L);
        upload2.setWeightFish(0.0);
        upload2.setLengthFish(0.0);
        upload2.setCharsFish("charsFish");
        upload2.setSpeciesfish("speciesFish");
        final User users1 = new User();
        users1.setId(0L);
        users1.setFirstname("firstname");
        users1.setUsername("username");
        users1.setPassword("password");
        users1.setEmail("email");
        upload2.setUsers(users1);
        fileDocument1.setUpload(upload2);
        final Optional<Upload> upload = Optional.of(new Upload(0L, 0.0, 0.0, "charsFish", "speciesFish",
                new User(0L, "firstname", "username", "password", "email", LocalDate.of(2020, 1, 1),
                        Set.of(new Role("rolename")), List.of(new FishingSpot(0L, "spot1", "city1", "region1")),
                        List.of(), fileDocument), "locationCaught", "cityCaught",
                new Gear(0L, 0.0, "kindOfReel", "kindOfLure", "line",
                        new User(0L, "firstname", "username", "password", "email", LocalDate.of(2020, 1, 1),
                                Set.of(new Role("rolename")), List.of(new FishingSpot(0L, "spot1", "city1", "region1")),
                                List.of(), fileDocument1), null)));
        when(mockRepository.findById(0L)).thenReturn(upload);

        final UploadGearOutputDto result = uploadServiceUnderTest.getUpload(0L);

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetUpload_UploadRepositoryReturnsAbsent() {

        when(mockRepository.findById(0L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> uploadServiceUnderTest.getUpload(0L)).isInstanceOf(RecordNotFoundException.class);
    }

    @Test
    void testGetAllUploadsByUser() {

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
        uploadGearOutputDto.setLineLength("line");
        uploadGearOutputDto.setUsername("username");
        final FileUploadResponse file = new FileUploadResponse();
        file.setFileName("fileName");
        file.setContentType("contentType");
        file.setUrl("url");
        uploadGearOutputDto.setFile(null);
        final List<UploadGearOutputDto> expectedResult = List.of(uploadGearOutputDto);

        final FileDocument fileDocument = new FileDocument();
        fileDocument.setFileName("fileName");
        fileDocument.setDocFile("content".getBytes());
        fileDocument.setId(0L);
        final Upload upload = new Upload();
        upload.setId(0L);
        upload.setWeightFish(0.0);
        upload.setLengthFish(0.0);
        upload.setCharsFish("charsFish");
        upload.setSpeciesfish("speciesFish");
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
        upload1.setSpeciesfish("speciesFish");
        final User users1 = new User();
        users1.setId(0L);
        users1.setFirstname("firstname");
        users1.setUsername("username");
        users1.setPassword("password");
        users1.setEmail("email");
        upload1.setUsers(users1);
        fileDocument1.setUpload(upload1);
        final List<Upload> uploads = List.of(new Upload(0L, 0.0, 0.0, "charsFish", "speciesFish",
                new User(0L, "firstname", "username", "password", "email", LocalDate.of(2020, 1, 1),
                        Set.of(new Role("rolename")), List.of(new FishingSpot(0L, "spot1", "city1", "region1")),
                        List.of(), fileDocument), "locationCaught", "cityCaught",
                new Gear(0L, 0.0, "kindOfReel", "kindOfLure", "line",
                        new User(0L, "firstname", "username", "password", "email", LocalDate.of(2020, 1, 1),
                                Set.of(new Role("rolename")), List.of(new FishingSpot(0L, "spot1", "city1", "region1")),
                                List.of(), fileDocument1), null)));
        when(mockRepository.findByUsersUsername("username")).thenReturn(uploads);

        final List<UploadGearOutputDto> result = uploadServiceUnderTest.getAllUploadsByUser("username");

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetAllUploadsByUser_UploadRepositoryReturnsNoItems() {
        when(mockRepository.findByUsersUsername("username")).thenReturn(Collections.emptyList());

        final List<UploadGearOutputDto> result = uploadServiceUnderTest.getAllUploadsByUser("username");

        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testCreateUpload() throws Exception {

        final UploadInputDto uploadDTO = new UploadInputDto(0.0, 0.0, "charsFish", "speciesFish", "locationCaught",
                "cityCaught", new FileUploadResponse("fileName", "contentType", "url"));

        final FileDocument fileDocument = new FileDocument();
        fileDocument.setFileName("fileName");
        fileDocument.setDocFile("content".getBytes());
        fileDocument.setId(0L);
        final Upload upload = new Upload();
        upload.setId(0L);
        upload.setWeightFish(0.0);
        upload.setLengthFish(0.0);
        upload.setCharsFish("charsFish");
        upload.setSpeciesfish("speciesFish");
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
        upload1.setSpeciesfish("speciesFish");
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
                        List.of(new Upload(0L, 0.0, 0.0, "charsFish", "speciesFish", null, "locationCaught",
                                "cityCaught", null)), fileDocument),
                new Upload(0L, 0.0, 0.0, "charsFish", "speciesFish",
                        new User(0L, "firstname", "username", "password", "email", LocalDate.of(2020, 1, 1),
                                Set.of(new Role("rolename")), List.of(new FishingSpot(0L, "spot1", "city1", "region1")),
                                List.of(), fileDocument1), "locationCaught", "cityCaught", null)));
        when(mockGearRepository.findById(0L)).thenReturn(gearOptional);

        final FileDocument fileDocument2 = new FileDocument();
        fileDocument2.setFileName("fileName");
        fileDocument2.setDocFile("content".getBytes());
        fileDocument2.setId(0L);
        final Upload upload2 = new Upload();
        upload2.setId(0L);
        upload2.setWeightFish(0.0);
        upload2.setLengthFish(0.0);
        upload2.setCharsFish("charsFish");
        upload2.setSpeciesfish("speciesFish");
        final User users2 = new User();
        users2.setId(0L);
        users2.setFirstname("firstname");
        users2.setUsername("username");
        users2.setPassword("password");
        users2.setEmail("email");
        upload2.setUsers(users2);
        fileDocument2.setUpload(upload2);
        final Optional<FileDocument> optionalFileDocument = Optional.of(fileDocument2);
        when(mockDocFileRepository.findByFileName("fileName")).thenReturn(optionalFileDocument);

        final FileDocument fileDocument3 = new FileDocument();
        fileDocument3.setFileName("fileName");
        fileDocument3.setDocFile("content".getBytes());
        fileDocument3.setId(0L);
        final Upload upload4 = new Upload();
        upload4.setId(0L);
        upload4.setWeightFish(0.0);
        upload4.setLengthFish(0.0);
        upload4.setCharsFish("charsFish");
        upload4.setSpeciesfish("speciesFish");
        final User users3 = new User();
        users3.setId(0L);
        users3.setFirstname("firstname");
        users3.setUsername("username");
        users3.setPassword("password");
        users3.setEmail("email");
        upload4.setUsers(users3);
        fileDocument3.setUpload(upload4);
        final FileDocument fileDocument4 = new FileDocument();
        fileDocument4.setFileName("fileName");
        fileDocument4.setDocFile("content".getBytes());
        fileDocument4.setId(0L);
        final Upload upload5 = new Upload();
        upload5.setId(0L);
        upload5.setWeightFish(0.0);
        upload5.setLengthFish(0.0);
        upload5.setCharsFish("charsFish");
        upload5.setSpeciesfish("speciesFish");
        final User users4 = new User();
        users4.setId(0L);
        users4.setFirstname("firstname");
        users4.setUsername("username");
        users4.setPassword("password");
        users4.setEmail("email");
        upload5.setUsers(users4);
        fileDocument4.setUpload(upload5);
        final Upload upload3 = new Upload(0L, 0.0, 0.0, "charsFish", "speciesFish",
                new User(0L, "firstname", "username", "password", "email", LocalDate.of(2020, 1, 1),
                        Set.of(new Role("rolename")), List.of(new FishingSpot(0L, "spot1", "city1", "region1")),
                        List.of(), fileDocument3), "locationCaught", "cityCaught",
                new Gear(0L, 0.0, "kindOfReel", "kindOfLure", "line",
                        new User(0L, "firstname", "username", "password", "email", LocalDate.of(2020, 1, 1),
                                Set.of(new Role("rolename")), List.of(new FishingSpot(0L, "spot1", "city1", "region1")),
                                List.of(), fileDocument4), null));
        when(mockRepository.save(any(Upload.class))).thenReturn(upload3);

        final Long result = uploadServiceUnderTest.createUpload(uploadDTO, 0L);

        assertThat(result).isEqualTo(0L);
    }

    @Test
    void testCreateUpload_GearRepositoryReturnsAbsent() throws Exception {

        final UploadInputDto uploadDTO = new UploadInputDto(0.0, 0.0, "charsFish", "speciesFish", "locationCaught",
                "cityCaught", new FileUploadResponse("fileName", "contentType", "url"));
        when(mockGearRepository.findById(0L)).thenReturn(Optional.empty());

        final FileDocument fileDocument = new FileDocument();
        fileDocument.setFileName("fileName");
        fileDocument.setDocFile("content".getBytes());
        fileDocument.setId(0L);
        final Upload upload = new Upload();
        upload.setId(0L);
        upload.setWeightFish(0.0);
        upload.setLengthFish(0.0);
        upload.setCharsFish("charsFish");
        upload.setSpeciesfish("speciesFish");
        final User users = new User();
        users.setId(0L);
        users.setFirstname("firstname");
        users.setUsername("username");
        users.setPassword("password");
        users.setEmail("email");
        upload.setUsers(users);
        fileDocument.setUpload(upload);
        final Optional<FileDocument> optionalFileDocument = Optional.of(fileDocument);
        when(mockDocFileRepository.findByFileName("fileName")).thenReturn(optionalFileDocument);

        final FileDocument fileDocument1 = new FileDocument();
        fileDocument1.setFileName("fileName");
        fileDocument1.setDocFile("content".getBytes());
        fileDocument1.setId(0L);
        final Upload upload2 = new Upload();
        upload2.setId(0L);
        upload2.setWeightFish(0.0);
        upload2.setLengthFish(0.0);
        upload2.setCharsFish("charsFish");
        upload2.setSpeciesfish("speciesFish");
        final User users1 = new User();
        users1.setId(0L);
        users1.setFirstname("firstname");
        users1.setUsername("username");
        users1.setPassword("password");
        users1.setEmail("email");
        upload2.setUsers(users1);
        fileDocument1.setUpload(upload2);
        final FileDocument fileDocument2 = new FileDocument();
        fileDocument2.setFileName("fileName");
        fileDocument2.setDocFile("content".getBytes());
        fileDocument2.setId(0L);
        final Upload upload3 = new Upload();
        upload3.setId(0L);
        upload3.setWeightFish(0.0);
        upload3.setLengthFish(0.0);
        upload3.setCharsFish("charsFish");
        upload3.setSpeciesfish("speciesFish");
        final User users2 = new User();
        users2.setId(0L);
        users2.setFirstname("firstname");
        users2.setUsername("username");
        users2.setPassword("password");
        users2.setEmail("email");
        upload3.setUsers(users2);
        fileDocument2.setUpload(upload3);
        final Upload upload1 = new Upload(0L, 0.0, 0.0, "charsFish", "speciesFish",
                new User(0L, "firstname", "username", "password", "email", LocalDate.of(2020, 1, 1),
                        Set.of(new Role("rolename")), List.of(new FishingSpot(0L, "spot1", "city1", "region1")),
                        List.of(), fileDocument1), "locationCaught", "cityCaught",
                new Gear(0L, 0.0, "kindOfReel", "kindOfLure", "line",
                        new User(0L, "firstname", "username", "password", "email", LocalDate.of(2020, 1, 1),
                                Set.of(new Role("rolename")), List.of(new FishingSpot(0L, "spot1", "city1", "region1")),
                                List.of(), fileDocument2), null));
        when(mockRepository.save(any(Upload.class))).thenReturn(upload1);

        final Long result = uploadServiceUnderTest.createUpload(uploadDTO, 0L);

        assertThat(result).isEqualTo(0L);
    }

    @Test
    void testCreateUpload_DocFileRepositoryReturnsAbsent() throws Exception {

        final UploadInputDto uploadDTO = new UploadInputDto(0.0, 0.0, "charsFish", "speciesFish", "locationCaught",
                "cityCaught", new FileUploadResponse("fileName", "contentType", "url"));

        final FileDocument fileDocument = new FileDocument();
        fileDocument.setFileName("fileName");
        fileDocument.setDocFile("content".getBytes());
        fileDocument.setId(0L);
        final Upload upload = new Upload();
        upload.setId(0L);
        upload.setWeightFish(0.0);
        upload.setLengthFish(0.0);
        upload.setCharsFish("charsFish");
        upload.setSpeciesfish("speciesFish");
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
        upload1.setSpeciesfish("speciesFish");
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
                        List.of(new Upload(0L, 0.0, 0.0, "charsFish", "speciesFish", null, "locationCaught",
                                "cityCaught", null)), fileDocument),
                new Upload(0L, 0.0, 0.0, "charsFish", "speciesFish",
                        new User(0L, "firstname", "username", "password", "email", LocalDate.of(2020, 1, 1),
                                Set.of(new Role("rolename")), List.of(new FishingSpot(0L, "spot1", "city1", "region1")),
                                List.of(), fileDocument1), "locationCaught", "cityCaught", null)));
        when(mockGearRepository.findById(0L)).thenReturn(gearOptional);

        when(mockDocFileRepository.findByFileName("fileName")).thenReturn(Optional.empty());

        final FileDocument fileDocument2 = new FileDocument();
        fileDocument2.setFileName("fileName");
        fileDocument2.setDocFile("content".getBytes());
        fileDocument2.setId(0L);
        final Upload upload3 = new Upload();
        upload3.setId(0L);
        upload3.setWeightFish(0.0);
        upload3.setLengthFish(0.0);
        upload3.setCharsFish("charsFish");
        upload3.setSpeciesfish("speciesFish");
        final User users2 = new User();
        users2.setId(0L);
        users2.setFirstname("firstname");
        users2.setUsername("username");
        users2.setPassword("password");
        users2.setEmail("email");
        upload3.setUsers(users2);
        fileDocument2.setUpload(upload3);
        final FileDocument fileDocument3 = new FileDocument();
        fileDocument3.setFileName("fileName");
        fileDocument3.setDocFile("content".getBytes());
        fileDocument3.setId(0L);
        final Upload upload4 = new Upload();
        upload4.setId(0L);
        upload4.setWeightFish(0.0);
        upload4.setLengthFish(0.0);
        upload4.setCharsFish("charsFish");
        upload4.setSpeciesfish("speciesFish");
        final User users3 = new User();
        users3.setId(0L);
        users3.setFirstname("firstname");
        users3.setUsername("username");
        users3.setPassword("password");
        users3.setEmail("email");
        upload4.setUsers(users3);
        fileDocument3.setUpload(upload4);
        final Upload upload2 = new Upload(0L, 0.0, 0.0, "charsFish", "speciesFish",
                new User(0L, "firstname", "username", "password", "email", LocalDate.of(2020, 1, 1),
                        Set.of(new Role("rolename")), List.of(new FishingSpot(0L, "spot1", "city1", "region1")),
                        List.of(), fileDocument2), "locationCaught", "cityCaught",
                new Gear(0L, 0.0, "kindOfReel", "kindOfLure", "line",
                        new User(0L, "firstname", "username", "password", "email", LocalDate.of(2020, 1, 1),
                                Set.of(new Role("rolename")), List.of(new FishingSpot(0L, "spot1", "city1", "region1")),
                                List.of(), fileDocument3), null));
        when(mockRepository.save(any(Upload.class))).thenReturn(upload2);

        final Long result = uploadServiceUnderTest.createUpload(uploadDTO, 0L);

        assertThat(result).isEqualTo(0L);
    }


    @Test
    void testUpdateUpload_UploadRepositoryFindByIdReturnsAbsent() {

        final UploadInputDto uploadInput = new UploadInputDto(1L,0.0, 0.0, "charsFish", "speciesFish", "locationCaught",
                "cityCaught", new FileUploadResponse("fileName", "contentType", "url"));
        when(mockRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> uploadServiceUnderTest.updateUpload(uploadInput))
                .isInstanceOf(RecordNotFoundException.class);
    }

    @Test
    void testDeleteUpload() {

        uploadServiceUnderTest.deleteUpload(0L);


        verify(mockRepository).deleteById(0L);
    }
}
