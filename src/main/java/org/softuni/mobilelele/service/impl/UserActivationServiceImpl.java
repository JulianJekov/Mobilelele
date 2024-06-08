package org.softuni.mobilelele.service.impl;

import org.softuni.mobilelele.events.UserRegisterEvent;
import org.softuni.mobilelele.exception.ObjectNotFoundException;
import org.softuni.mobilelele.model.entity.UserActivationCode;
import org.softuni.mobilelele.repository.UserActivationRepository;
import org.softuni.mobilelele.repository.UserRepository;
import org.softuni.mobilelele.service.EmailService;
import org.softuni.mobilelele.service.UserActivationService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.Random;

@Service
public class UserActivationServiceImpl implements UserActivationService {


    private static final String ACTIVATION_CODE_SYMBOLS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int ACTIVATION_CODE_LENGTH = 20;
    private final EmailService emailService;
    private final UserRepository userRepository;
    private final UserActivationRepository userActivationRepository;


    public UserActivationServiceImpl(EmailService emailService, UserRepository userRepository, UserActivationRepository userActivationRepository) {
        this.emailService = emailService;
        this.userRepository = userRepository;
        this.userActivationRepository = userActivationRepository;
    }

    @EventListener(UserRegisterEvent.class)
    @Override
    public void userRegister(UserRegisterEvent userRegisterEvent) {
        String activationCode = createActivationCode(userRegisterEvent.getUserEmail());
        System.out.println("User with email " + userRegisterEvent.getUserEmail());
        emailService.sendRegistrationEmail(userRegisterEvent.getUserEmail(), userRegisterEvent.getUserNames(), activationCode);
    }

    @Override
    public void cleanUpObsoleteActivationLinks() {

    }

    @Override
    public String createActivationCode(String userEmail) {
        String activationCode = generateActivationCode();
        UserActivationCode userActivationCode = new UserActivationCode();
        userActivationCode.setActivationCode(activationCode);
        userActivationCode.setCreated(Instant.now());
        userActivationCode.setUser(this.userRepository.findByEmail(userEmail)
                        .orElseThrow(() -> new ObjectNotFoundException("User with email " + userEmail + " not found")));
        this.userActivationRepository.save(userActivationCode);
        return activationCode;
    }

    private static String generateActivationCode() {
        Random random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < ACTIVATION_CODE_LENGTH; i++) {
            int randInx = random.nextInt(ACTIVATION_CODE_SYMBOLS.length());
            sb.append(ACTIVATION_CODE_SYMBOLS.charAt(randInx));
        }
        return sb.toString();
    }
}
