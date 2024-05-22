package org.softuni.mobilelele.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.softuni.mobilelele.validation.PasswordMatch;
import org.softuni.mobilelele.validation.UniqueUserEmail;

@PasswordMatch(first = "password", second = "confirmPassword", message = "Passwords should match")
public class UserRegisterDTO {

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty(message = "Email is required.")
    @Email
    @UniqueUserEmail
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty(message = "Confirm Password is required")
    private String confirmPassword;

    public UserRegisterDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public UserRegisterDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRegisterDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegisterDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterDTO setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
