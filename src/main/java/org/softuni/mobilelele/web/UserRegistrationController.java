package org.softuni.mobilelele.web;

import jakarta.validation.Valid;
import org.softuni.mobilelele.model.dto.ReCaptchaResponseDTO;
import org.softuni.mobilelele.model.dto.UserRegisterDTO;
import org.softuni.mobilelele.service.ReCaptchaService;
import org.softuni.mobilelele.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/users")
@Controller
public class UserRegistrationController {

    private final UserService userService;
    private final ReCaptchaService reCaptchaService;

    @Autowired
    public UserRegistrationController(UserService userService, ReCaptchaService reCaptchaService) {
        this.userService = userService;
        this.reCaptchaService = reCaptchaService;
    }

    @GetMapping("/register")
    public String register(Model model) {

        if (!model.containsAttribute("userRegisterDTO")) {
            model.addAttribute("userRegisterDTO", new UserRegisterDTO());
        }

        return "auth-register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegisterDTO userRegisterDTO, BindingResult bindingResult,
                           RedirectAttributes rAtt, @RequestParam("g-recaptcha-response") String reCaptchaResponse) {

        boolean isBot = !reCaptchaService.verifyReCaptcha(reCaptchaResponse)
                .map(ReCaptchaResponseDTO::isSuccess)
                .orElse(false);

        if (isBot) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            rAtt.addFlashAttribute("userRegisterDTO", userRegisterDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterDTO", bindingResult);
            return "redirect:/users/login";
        }

        this.userService.registerUser(userRegisterDTO);
        return "redirect:/users/login";
    }
}
