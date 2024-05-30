package org.softuni.mobilelele.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserLoginController {

    @GetMapping("/users/login")
    public String login() {
        return "auth-login";
    }

//    @PostMapping("/users/login")
//    public String login(UserLoginDTO userLoginDTO) {
//        final boolean loginSuccessful = this.userService.loginUser(userLoginDTO);
//        return loginSuccessful ? "index" : "auth-login";
//    }

//    @GetMapping("/users/logout")
//    public String logout() {
//        this.userService.logoutUser();
//        return "index";
//    }
}
