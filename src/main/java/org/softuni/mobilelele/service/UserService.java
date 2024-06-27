package org.softuni.mobilelele.service;

import org.softuni.mobilelele.model.dto.UserRegisterDTO;
import org.springframework.security.core.Authentication;

public interface UserService {
    void registerUser(UserRegisterDTO userRegisterDTO);

//    boolean loginUser(UserLoginDTO userLoginDTO);
//
//    void logoutUser();
    void createUserIfNotExist(String email, String names);

    Authentication login(String email);

    boolean isUserExist(String email);
}
