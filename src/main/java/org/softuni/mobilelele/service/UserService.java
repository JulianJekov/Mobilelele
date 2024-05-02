package org.softuni.mobilelele.service;

import org.softuni.mobilelele.model.dto.UserLoginDTO;
import org.softuni.mobilelele.model.dto.UserRegisterDTO;

public interface UserService {
    void registerUser(UserRegisterDTO userRegisterDTO);

    boolean loginUser(UserLoginDTO userLoginDTO);

    void logoutUser();

}
