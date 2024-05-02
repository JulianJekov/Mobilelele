package org.softuni.mobilelele.util;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component(value = "currentUser")
@SessionScope
public class CurrentUser {

    private String firstName;

    private String lastName;

    private Boolean isLogged;


    public String getFirstName() {
        return firstName;
    }

    public CurrentUser setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public CurrentUser setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getFullName() {
        StringBuilder sb = new StringBuilder();
        if (firstName != null) {
            sb.append(firstName);
        }
        if (lastName != null) {
            if (!sb.isEmpty()) {
                sb.append(" ");
            }
            sb.append(lastName);
        }
        return sb.toString();
    }

    public Boolean getLogged() {
        return isLogged;
    }

    public CurrentUser setLogged(Boolean logged) {
        isLogged = logged;
        return this;
    }

    @Override
    public String toString() {
        return "CurrentUser{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", isLogged=" + isLogged +
                '}';
    }

    public void logout() {
        this.setLogged(false);
        this.setFirstName(null);
        this.setLastName(null);
    }
}
