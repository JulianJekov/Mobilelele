package org.softuni.mobilelele.service.impl;

import org.softuni.mobilelele.events.UserRegisterEvent;
import org.softuni.mobilelele.model.dto.UserRegisterDTO;
import org.softuni.mobilelele.model.entity.UserEntity;
import org.softuni.mobilelele.repository.UserRepository;
import org.softuni.mobilelele.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, ApplicationEventPublisher applicationEventPublisher) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void registerUser(UserRegisterDTO userRegisterDTO) {
        this.userRepository.save(map(userRegisterDTO));

       this.applicationEventPublisher
               .publishEvent(new UserRegisterEvent("UserService",
                       userRegisterDTO.getEmail(), userRegisterDTO.getFullName()));
    }


    private UserEntity map(UserRegisterDTO userRegisterDTO) {
        return new UserEntity()
                .setIsActive(false)
                .setFirstName(userRegisterDTO.getFirstName())
                .setLastName(userRegisterDTO.getLastName())
                .setEmail(userRegisterDTO.getEmail())
                .setPassword(this.passwordEncoder.encode(userRegisterDTO.getPassword()));
    }
}
