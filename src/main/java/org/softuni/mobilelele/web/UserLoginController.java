package org.softuni.mobilelele.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserLoginController {

    @GetMapping("/users/login")
    public String login() {
        return "auth-login";
    }

    @PostMapping("/users/login-error")
    public String onFailure(Model model) {
        model.addAttribute("bad_credentials", true);
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
