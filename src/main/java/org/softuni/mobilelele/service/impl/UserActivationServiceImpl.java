package org.softuni.mobilelele.service.impl;

import org.softuni.mobilelele.events.UserRegisterEvent;
import org.softuni.mobilelele.service.EmailService;
import org.softuni.mobilelele.service.UserActivationService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class UserActivationServiceImpl implements UserActivationService {

    private final EmailService emailService;

    public UserActivationServiceImpl(EmailService emailService) {
        this.emailService = emailService;
    }

    @EventListener(UserRegisterEvent.class)
    @Override
    public void userRegister(UserRegisterEvent userRegisterEvent) {
       // TODO: add activation links
        System.out.println("User with email " + userRegisterEvent.getUserEmail());
        emailService.sendRegistrationEmail(userRegisterEvent.getUserEmail(), userRegisterEvent.getUserNames());
    }
}
