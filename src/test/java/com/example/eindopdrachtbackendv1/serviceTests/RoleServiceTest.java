package com.example.eindopdrachtbackendv1.serviceTests;

import com.example.eindopdrachtbackendv1.models.*;
import com.example.eindopdrachtbackendv1.repositories.UserRepository;
import com.example.eindopdrachtbackendv1.services.RoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoleServiceTest {

    @Mock
    private UserRepository mockUserRepos;

    private RoleService roleServiceUnderTest;

    @BeforeEach
    void setUp() {
        roleServiceUnderTest = new RoleService(mockUserRepos);
    }

    @Test
    void testGetRole() {
        // Setup
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
        when(mockUserRepos.findByUsername("username")).thenReturn(userOptional);

        // Run the test
        final Set<Role> result = roleServiceUnderTest.GetRole("username");

        // Verify the results
    }

    @Test
    void testGetRole_UserRepositoryReturnsAbsent() {
        // Setup
        when(mockUserRepos.findByUsername("username")).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> roleServiceUnderTest.GetRole("username")).isInstanceOf(NoSuchElementException.class);
    }
}
