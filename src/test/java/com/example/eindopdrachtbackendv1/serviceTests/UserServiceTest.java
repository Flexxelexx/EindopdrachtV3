package com.example.eindopdrachtbackendv1.serviceTests;

import com.example.eindopdrachtbackendv1.dtos.input.UserInputDto;
import com.example.eindopdrachtbackendv1.dtos.output.UserOutputDto;
import com.example.eindopdrachtbackendv1.exceptions.RecordNotFoundException;
import com.example.eindopdrachtbackendv1.models.*;
import com.example.eindopdrachtbackendv1.repositories.FishingSpotRepository;
import com.example.eindopdrachtbackendv1.repositories.RoleRepository;
import com.example.eindopdrachtbackendv1.repositories.UploadRepository;
import com.example.eindopdrachtbackendv1.repositories.UserRepository;
import com.example.eindopdrachtbackendv1.security.JwtService;
import com.example.eindopdrachtbackendv1.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

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
class UserServiceTest {

    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private UploadRepository mockUploadRepository;
    @Mock
    private FishingSpotRepository mockFishingSpotRepository;
    @Mock
    private PasswordEncoder mockEncoder;
    @Mock
    private RoleRepository mockRoleRepository;

    private UserService userServiceUnderTest;


    @BeforeEach
    void setUp() {
        userServiceUnderTest = new UserService(mockUserRepository, mockUploadRepository, mockFishingSpotRepository,
                mockEncoder, mockRoleRepository);
    }

    @Test
    void testGetUsers() {
        // Setup
        final UserOutputDto userOutputDto = new UserOutputDto();
        userOutputDto.setId(0L);
        userOutputDto.setFirstname("firstname");
        userOutputDto.setUsername("username");
        userOutputDto.setPassword("password");
        userOutputDto.setEmail("email");
        userOutputDto.setDob(LocalDate.of(2020, 1, 1));
        userOutputDto.setUploadIds(List.of(0L));
        userOutputDto.setRole(Set.of("rolename"));
        final List<UserOutputDto> expectedResult = List.of(userOutputDto);

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
        final User users1 = new User();
        users1.setId(0L);
        users1.setFirstname("firstname");
        users1.setUsername("username");
        users1.setPassword("password");
        users1.setEmail("email");
        upload.setUsers(users1);
        fileDocument.setUpload(upload);
        final List<User> users = List.of(
                new User(0L, "firstname", "username", "password", "email", LocalDate.of(2020, 1, 1),
                        Set.of(new Role("rolename")), List.of(new FishingSpot(0L, "spot1", "city1", "region1")),
                        List.of(new Upload(0L, 0.0, 0.0, "charsFish", "speciesfish", null, "locationCaught",
                                "cityCaught", new Gear(0L, 0.0, "kindOfReel", "kindOfLure", "line", null, null))),
                        fileDocument));
        when(mockUserRepository.findAll()).thenReturn(users);

        when(mockEncoder.encode("password")).thenReturn("password");

        final List<UserOutputDto> result = userServiceUnderTest.getUsers();

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetUsers_UserRepositoryReturnsNoItems() {
        when(mockUserRepository.findAll()).thenReturn(Collections.emptyList());

        final List<UserOutputDto> result = userServiceUnderTest.getUsers();

        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetUser() {
        // Setup
        final UserOutputDto expectedResult = new UserOutputDto();
        expectedResult.setId(0L);
        expectedResult.setFirstname("firstname");
        expectedResult.setUsername("username");
        expectedResult.setPassword("password");
        expectedResult.setEmail("email");
        expectedResult.setDob(LocalDate.of(2020, 1, 1));
        expectedResult.setUploadIds(List.of(0L));
        expectedResult.setRole(Set.of("rolename"));

        // Configure UserRepository.findByUsername(...).
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
        final Optional<User> userOptional = Optional.of(
                new User(0L, "firstname", "username", "password", "email", LocalDate.of(2020, 1, 1),
                        Set.of(new Role("rolename")), List.of(new FishingSpot(0L, "spot1", "city1", "region1")),
                        List.of(new Upload(0L, 0.0, 0.0, "charsFish", "speciesfish", null, "locationCaught",
                                "cityCaught", new Gear(0L, 0.0, "kindOfReel", "kindOfLure", "line", null, null))),
                        fileDocument));
        when(mockUserRepository.findByUsername("username")).thenReturn(userOptional);

        when(mockEncoder.encode("password")).thenReturn("password");

        final UserOutputDto result = userServiceUnderTest.getUser("username");

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetUser_UserRepositoryReturnsAbsent() {
        when(mockUserRepository.findByUsername("username")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userServiceUnderTest.getUser("username"))
                .isInstanceOf(UsernameNotFoundException.class);
    }

    @Test
    void testUserExists() {
        when(mockUserRepository.existsById(0L)).thenReturn(false);

        final boolean result = userServiceUnderTest.userExists(0L);

        assertThat(result).isFalse();
    }

    @Test
    void testCreateUser() {

        final UserInputDto userDTO = new UserInputDto();
        userDTO.setId(0L);
        userDTO.setFirstname("firstname");
        userDTO.setUsername("username");
        userDTO.setPassword("password");
        userDTO.setEmail("email");
        userDTO.setDob(LocalDate.of(2020, 1, 1));

        when(mockEncoder.encode("password")).thenReturn("password");
        when(mockRoleRepository.findByRolename("ROLE_USER")).thenReturn(new Role("rolename"));

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
        when(mockUserRepository.save(any(User.class))).thenReturn(user);

        final User result = userServiceUnderTest.createUser(userDTO);

        verify(mockUserRepository).save(any(User.class));
    }

    @Test
    void testCreateAdmin() {

        final UserInputDto userDTO = new UserInputDto();
        userDTO.setId(0L);
        userDTO.setFirstname("firstname");
        userDTO.setUsername("username");
        userDTO.setPassword("password");
        userDTO.setEmail("email");
        userDTO.setDob(LocalDate.of(2020, 1, 1));

        when(mockEncoder.encode("password")).thenReturn("password");
        when(mockRoleRepository.findByRolename("ROLE_ADMIN")).thenReturn(new Role("rolename"));

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
        when(mockUserRepository.save(any(User.class))).thenReturn(user);

        final User result = userServiceUnderTest.createAdmin(userDTO);

        verify(mockUserRepository).save(any(User.class));
    }

    @Test
    void testUpdateUser() {

        final UserInputDto userInput = new UserInputDto();
        userInput.setId(0L);
        userInput.setFirstname("firstname");
        userInput.setUsername("username");
        userInput.setPassword("password");
        userInput.setEmail("email");
        userInput.setDob(LocalDate.of(2020, 1, 1));

        final UserOutputDto expectedResult = new UserOutputDto();
        expectedResult.setId(0L);
        expectedResult.setFirstname("firstname");
        expectedResult.setUsername("username");
        expectedResult.setPassword("password");
        expectedResult.setEmail("email");
        expectedResult.setDob(LocalDate.of(2020, 1, 1));
        expectedResult.setUploadIds(List.of(0L));
        expectedResult.setRole(Set.of("rolename"));

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
        final Optional<User> userOptional = Optional.of(
                new User(0L, "firstname", "username", "password", "email", LocalDate.of(2020, 1, 1),
                        Set.of(new Role("rolename")), List.of(new FishingSpot(0L, "spot1", "city1", "region1")),
                        List.of(new Upload(0L, 0.0, 0.0, "charsFish", "speciesfish", null, "locationCaught",
                                "cityCaught", new Gear(0L, 0.0, "kindOfReel", "kindOfLure", "line", null, null))),
                        fileDocument));
        when(mockUserRepository.findById(0L)).thenReturn(userOptional);

        when(mockEncoder.encode("password")).thenReturn("password");

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
        final User user = new User(0L, "firstname", "username", "password", "email", LocalDate.of(2020, 1, 1),
                Set.of(new Role("rolename")), List.of(new FishingSpot(0L, "spot1", "city1", "region1")),
                List.of(new Upload(0L, 0.0, 0.0, "charsFish", "speciesfish", null, "locationCaught", "cityCaught",
                        new Gear(0L, 0.0, "kindOfReel", "kindOfLure", "line", null, null))), fileDocument1);
        when(mockUserRepository.save(any(User.class))).thenReturn(user);

        final UserOutputDto result = userServiceUnderTest.updateUser(userInput);

        assertThat(result).isEqualTo(expectedResult);
        verify(mockUserRepository).save(any(User.class));
    }

    @Test
    void testUpdateUser_UserRepositoryFindByIdReturnsAbsent() {

        final UserInputDto userInput = new UserInputDto();
        userInput.setId(0L);
        userInput.setFirstname("firstname");
        userInput.setUsername("username");
        userInput.setPassword("password");
        userInput.setEmail("email");
        userInput.setDob(LocalDate.of(2020, 1, 1));

        when(mockUserRepository.findById(0L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userServiceUnderTest.updateUser(userInput))
                .isInstanceOf(RecordNotFoundException.class);
    }



    @Test
    void testAddFishingspot_UserRepositoryFindByIdReturnsAbsent() {
        when(mockUserRepository.findById(0L)).thenReturn(Optional.empty());

        final Optional<FishingSpot> fishingSpotOptional = Optional.of(new FishingSpot(0L, "spot1", "city1", "region1"));
        when(mockFishingSpotRepository.findBySpotLocation("fishingspotID")).thenReturn(fishingSpotOptional);

        userServiceUnderTest.addFishingspot("fishingspotID", 0L);

    }

    @Test
    void testAddFishingspot_FishingSpotRepositoryReturnsAbsent() {

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
        final Optional<User> userOptional = Optional.of(
                new User(0L, "firstname", "username", "password", "email", LocalDate.of(2020, 1, 1),
                        Set.of(new Role("rolename")), List.of(new FishingSpot(0L, "spot1", "city1", "region1")),
                        List.of(new Upload(0L, 0.0, 0.0, "charsFish", "speciesfish", null, "locationCaught",
                                "cityCaught", new Gear(0L, 0.0, "kindOfReel", "kindOfLure", "line", null, null))),
                        fileDocument));
        when(mockUserRepository.findById(0L)).thenReturn(userOptional);

        when(mockFishingSpotRepository.findBySpotLocation("fishingspotID")).thenReturn(Optional.empty());

        userServiceUnderTest.addFishingspot("fishingspotID", 0L);

    }

    @Test
    void testAddUpload() {

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
        final Optional<User> userOptional = Optional.of(
                new User(0L, "firstname", "username", "password", "email", LocalDate.of(2020, 1, 1),
                        Set.of(new Role("rolename")), List.of(new FishingSpot(0L, "spot1", "city1", "region1")),
                        List.of(new Upload(0L, 0.0, 0.0, "charsFish", "speciesfish", null, "locationCaught",
                                "cityCaught", new Gear(0L, 0.0, "kindOfReel", "kindOfLure", "line", null, null))),
                        fileDocument));
        when(mockUserRepository.findById(0L)).thenReturn(userOptional);

        final FileDocument fileDocument1 = new FileDocument();
        fileDocument1.setFileName("fileName");
        fileDocument1.setDocFile("content".getBytes());
        fileDocument1.setId(0L);
        final Upload upload2 = new Upload();
        upload2.setId(0L);
        upload2.setWeightFish(0.0);
        upload2.setLengthFish(0.0);
        upload2.setCharsFish("charsFish");
        upload2.setSpeciesfish("speciesfish");
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
        upload3.setSpeciesfish("speciesfish");
        final User users2 = new User();
        users2.setId(0L);
        users2.setFirstname("firstname");
        users2.setUsername("username");
        users2.setPassword("password");
        users2.setEmail("email");
        upload3.setUsers(users2);
        fileDocument2.setUpload(upload3);
        final Optional<Upload> upload1 = Optional.of(new Upload(0L, 0.0, 0.0, "charsFish", "speciesfish",
                new User(0L, "firstname", "username", "password", "email", LocalDate.of(2020, 1, 1),
                        Set.of(new Role("rolename")), List.of(new FishingSpot(0L, "spot1", "city1", "region1")),
                        List.of(), fileDocument1), "locationCaught", "cityCaught",
                new Gear(0L, 0.0, "kindOfReel", "kindOfLure", "line",
                        new User(0L, "firstname", "username", "password", "email", LocalDate.of(2020, 1, 1),
                                Set.of(new Role("rolename")), List.of(new FishingSpot(0L, "spot1", "city1", "region1")),
                                List.of(), fileDocument2), null)));
        when(mockUploadRepository.findById(0L)).thenReturn(upload1);

        final FileDocument fileDocument3 = new FileDocument();
        fileDocument3.setFileName("fileName");
        fileDocument3.setDocFile("content".getBytes());
        fileDocument3.setId(0L);
        final Upload upload5 = new Upload();
        upload5.setId(0L);
        upload5.setWeightFish(0.0);
        upload5.setLengthFish(0.0);
        upload5.setCharsFish("charsFish");
        upload5.setSpeciesfish("speciesfish");
        final User users3 = new User();
        users3.setId(0L);
        users3.setFirstname("firstname");
        users3.setUsername("username");
        users3.setPassword("password");
        users3.setEmail("email");
        upload5.setUsers(users3);
        fileDocument3.setUpload(upload5);
        final FileDocument fileDocument4 = new FileDocument();
        fileDocument4.setFileName("fileName");
        fileDocument4.setDocFile("content".getBytes());
        fileDocument4.setId(0L);
        final Upload upload6 = new Upload();
        upload6.setId(0L);
        upload6.setWeightFish(0.0);
        upload6.setLengthFish(0.0);
        upload6.setCharsFish("charsFish");
        upload6.setSpeciesfish("speciesfish");
        final User users4 = new User();
        users4.setId(0L);
        users4.setFirstname("firstname");
        users4.setUsername("username");
        users4.setPassword("password");
        users4.setEmail("email");
        upload6.setUsers(users4);
        fileDocument4.setUpload(upload6);
        final Upload upload4 = new Upload(0L, 0.0, 0.0, "charsFish", "speciesfish",
                new User(0L, "firstname", "username", "password", "email", LocalDate.of(2020, 1, 1),
                        Set.of(new Role("rolename")), List.of(new FishingSpot(0L, "spot1", "city1", "region1")),
                        List.of(), fileDocument3), "locationCaught", "cityCaught",
                new Gear(0L, 0.0, "kindOfReel", "kindOfLure", "line",
                        new User(0L, "firstname", "username", "password", "email", LocalDate.of(2020, 1, 1),
                                Set.of(new Role("rolename")), List.of(new FishingSpot(0L, "spot1", "city1", "region1")),
                                List.of(), fileDocument4), null));
        when(mockUploadRepository.save(any(Upload.class))).thenReturn(upload4);

        userServiceUnderTest.addUpload(0L, 0L);

        verify(mockUploadRepository).save(any(Upload.class));
    }

    @Test
    void testAddUpload_UserRepositoryReturnsAbsent() {

        when(mockUserRepository.findById(0L)).thenReturn(Optional.empty());

        final FileDocument fileDocument = new FileDocument();
        fileDocument.setFileName("fileName");
        fileDocument.setDocFile("content".getBytes());
        fileDocument.setId(0L);
        final Upload upload1 = new Upload();
        upload1.setId(0L);
        upload1.setWeightFish(0.0);
        upload1.setLengthFish(0.0);
        upload1.setCharsFish("charsFish");
        upload1.setSpeciesfish("speciesfish");
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
        upload2.setSpeciesfish("speciesfish");
        final User users1 = new User();
        users1.setId(0L);
        users1.setFirstname("firstname");
        users1.setUsername("username");
        users1.setPassword("password");
        users1.setEmail("email");
        upload2.setUsers(users1);
        fileDocument1.setUpload(upload2);
        final Optional<Upload> upload = Optional.of(new Upload(0L, 0.0, 0.0, "charsFish", "speciesfish",
                new User(0L, "firstname", "username", "password", "email", LocalDate.of(2020, 1, 1),
                        Set.of(new Role("rolename")), List.of(new FishingSpot(0L, "spot1", "city1", "region1")),
                        List.of(), fileDocument), "locationCaught", "cityCaught",
                new Gear(0L, 0.0, "kindOfReel", "kindOfLure", "line",
                        new User(0L, "firstname", "username", "password", "email", LocalDate.of(2020, 1, 1),
                                Set.of(new Role("rolename")), List.of(new FishingSpot(0L, "spot1", "city1", "region1")),
                                List.of(), fileDocument1), null)));
        when(mockUploadRepository.findById(0L)).thenReturn(upload);

        userServiceUnderTest.addUpload(0L, 0L);

    }

    @Test
    void testAddUpload_UploadRepositoryFindByIdReturnsAbsent() {

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
        final Optional<User> userOptional = Optional.of(
                new User(0L, "firstname", "username", "password", "email", LocalDate.of(2020, 1, 1),
                        Set.of(new Role("rolename")), List.of(new FishingSpot(0L, "spot1", "city1", "region1")),
                        List.of(new Upload(0L, 0.0, 0.0, "charsFish", "speciesfish", null, "locationCaught",
                                "cityCaught", new Gear(0L, 0.0, "kindOfReel", "kindOfLure", "line", null, null))),
                        fileDocument));
        when(mockUserRepository.findById(0L)).thenReturn(userOptional);

        when(mockUploadRepository.findById(0L)).thenReturn(Optional.empty());

        userServiceUnderTest.addUpload(0L, 0L);

    }

    @Test
    void testDeleteUser() {

        userServiceUnderTest.deleteUser(0L);

        verify(mockUserRepository).deleteById(0L);
    }
}
