package org.softuni.mobilelele.web;

import org.softuni.mobilelele.model.dto.UserLoginDTO;
import org.softuni.mobilelele.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserLoginController {

    private final UserService userService;

    @Autowired
    public UserLoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/login")
    public String login() {

        return "auth-login";
    }

    @PostMapping("/users/login")
    public String login(UserLoginDTO userLoginDTO) {
        boolean loginSuccessful = this.userService.loginUser(userLoginDTO);

        return loginSuccessful ? "index" : "auth-login";
    }
}
