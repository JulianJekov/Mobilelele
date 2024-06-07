package org.softuni.mobilelele.service.impl;

import org.softuni.mobilelele.model.dto.UserRegisterDTO;
import org.softuni.mobilelele.model.entity.UserEntity;
import org.softuni.mobilelele.repository.UserRepository;
import org.softuni.mobilelele.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registerUser(UserRegisterDTO userRegisterDTO) {
        this.userRepository.save(map(userRegisterDTO));
    }


    private UserEntity map(UserRegisterDTO userRegisterDTO) {
        return new UserEntity()
                .setIsActive(true)
                .setFirstName(userRegisterDTO.getFirstName())
                .setLastName(userRegisterDTO.getLastName())
                .setEmail(userRegisterDTO.getEmail())
                .setPassword(this.passwordEncoder.encode(userRegisterDTO.getPassword()));
    }
}
