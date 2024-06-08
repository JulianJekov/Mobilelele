package org.softuni.mobilelele.service;

import org.softuni.mobilelele.events.UserRegisterEvent;

public interface UserActivationService {
    void userRegister(UserRegisterEvent userRegisterEvent);
    void cleanUpObsoleteActivationLinks();
    String createActivationCode(String userEmail);
}
