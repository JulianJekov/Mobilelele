package org.softuni.mobilelele.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.softuni.mobilelele.model.entity.Role;
import org.softuni.mobilelele.model.entity.UserEntity;
import org.softuni.mobilelele.model.enums.UserRoleEnum;
import org.softuni.mobilelele.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MobileleUserDetailsServiceTest {

    private MobileleUserDetailsService serviceToTest;

    @Mock
    private UserRepository userRepositoryMock;

    @BeforeEach
    void setUp() {
        serviceToTest = new MobileleUserDetailsService(userRepositoryMock);
    }

    @Test
    void testLoadUserByUsernameNotFound() {
        assertThrows(UsernameNotFoundException.class,
                () -> serviceToTest.loadUserByUsername("pesho@softuni.bg"));
    }

    @Test
    void testUserFoundException() {
        //Arrange
        UserEntity testUser = createTestUser();
        when(userRepositoryMock.findByEmail(testUser.getEmail()))
                .thenReturn(Optional.of(testUser));

        //Act
        UserDetails userDetails = serviceToTest.loadUserByUsername(testUser.getEmail());

        //Assert
        assertNotNull(userDetails);

        assertEquals(testUser.getEmail(), userDetails.getUsername(),
                "Username is not mapped to email");

        assertEquals(testUser.getPassword(), userDetails.getPassword());
        assertEquals(2, userDetails.getAuthorities().size());
        assertTrue(containsAuthority(userDetails, "ROLE_" + UserRoleEnum.ADMIN),
                "User is not admin");
        assertTrue(containsAuthority(userDetails, "ROLE_" + UserRoleEnum.USER),
                "User is not user");

    }

    private boolean containsAuthority(UserDetails userDetails, String authority) {
        return userDetails
                .getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals(authority));
    }

    private static UserEntity createTestUser() {
        return
                new UserEntity().setFirstName("firstName")
                        .setLastName("lastName")
                        .setEmail("email")
                        .setIsActive(false)
                        .setPassword("password")
                        .setRoles(List.of(
                                new Role().setRole(UserRoleEnum.ADMIN),
                                new Role().setRole(UserRoleEnum.USER)
                        ));
    }

}
