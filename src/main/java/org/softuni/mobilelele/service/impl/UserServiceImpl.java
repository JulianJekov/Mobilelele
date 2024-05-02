package org.softuni.mobilelele.service.impl;

import org.softuni.mobilelele.model.dto.UserLoginDTO;
import org.softuni.mobilelele.model.dto.UserRegisterDTO;
import org.softuni.mobilelele.model.entity.User;
import org.softuni.mobilelele.repository.UserRepository;
import org.softuni.mobilelele.service.UserService;
import org.softuni.mobilelele.util.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CurrentUser currentUser;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.currentUser = currentUser;
    }

    @Override
    public void registerUser(UserRegisterDTO userRegisterDTO) {
        this.userRepository.save(map(userRegisterDTO));
    }

    @Override
    public boolean loginUser(UserLoginDTO userLoginDTO) {

        final User user = this.userRepository.findByEmail(userLoginDTO.getEmail()).orElse(null);

        boolean loginSuccess = false;

        if (user != null) {
            final String rawPassword = userLoginDTO.getPassword();
            final String encodedPassword = user.getPassword();

            loginSuccess = encodedPassword != null && this.passwordEncoder.matches(rawPassword, encodedPassword);

            if (loginSuccess) {
                this.currentUser
                        .setLogged(true)
                        .setFirstName(user.getFirstName())
                        .setLastName(user.getLastName());
            } else {
                this.currentUser.logout();
            }
        }

        return loginSuccess;
    }

    @Override
    public void logoutUser() {
        this.currentUser.logout();
    }


    private User map(UserRegisterDTO userRegisterDTO) {
        return new User()
                .setActive(true)
                .setFirstName(userRegisterDTO.getFirstName())
                .setLastName(userRegisterDTO.getLastName())
                .setEmail(userRegisterDTO.getEmail())
                .setPassword(this.passwordEncoder.encode(userRegisterDTO.getPassword()));
    }
}
