package org.softuni.mobilelele.events;

import org.springframework.context.ApplicationEvent;

public class UserRegisterEvent extends ApplicationEvent {

    private final String userEmail;
    private final String userNames;

    public UserRegisterEvent(Object source, String userEmail, String userNames) {
        super(source);
        this.userEmail = userEmail;
        this.userNames = userNames;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserNames() {
        return userNames;
    }
}
