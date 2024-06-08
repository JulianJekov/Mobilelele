package org.softuni.mobilelele.service.impl;

import org.softuni.mobilelele.events.UserRegisterEvent;
import org.softuni.mobilelele.model.dto.UserRegisterDTO;
import org.softuni.mobilelele.model.entity.UserEntity;
import org.softuni.mobilelele.model.enums.UserRoleEnum;
import org.softuni.mobilelele.repository.RoleRepository;
import org.softuni.mobilelele.repository.UserRepository;
import org.softuni.mobilelele.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, ApplicationEventPublisher applicationEventPublisher, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.applicationEventPublisher = applicationEventPublisher;
        this.roleRepository = roleRepository;
    }

    @Override
    public void registerUser(UserRegisterDTO userRegisterDTO) {
        UserEntity user = map(userRegisterDTO);
        setRoles(user);
        this.userRepository.save(user);

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

    private void setRoles(UserEntity user) {
      if(this.userRepository.count() == 0) {
          user.setRoles(this.roleRepository.findAll());
      } else {
          user.setRoles(List.of(this.roleRepository.findByRole(UserRoleEnum.USER)));
      }
    }

}
