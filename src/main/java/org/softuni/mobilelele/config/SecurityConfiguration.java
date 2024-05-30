package org.softuni.mobilelele.config;

import org.softuni.mobilelele.repository.UserRepository;
import org.softuni.mobilelele.service.impl.MobileleUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(
                // Define which urls are visible by users
                authorizeRequests -> authorizeRequests
                        // All static resources which are situated in js, images, css are available for anyone
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        // Allow anyone to see the home page, the registration page and the login form
                        .requestMatchers("/", "users/login", "users/register").permitAll()
                        // All other requests are authenticated
                        .anyRequest().authenticated()
        ).formLogin(
                formLogin -> {
                    // Redirect here when we access something which is not allowed
                    // Also this is the page where perform login.
                    formLogin
                            .loginPage("users/login")
                            // The names of the input fields (in our case in auth-login.html)
                            .usernameParameter("email")
                            .passwordParameter("password")
                            .defaultSuccessUrl("/")
                            .failureForwardUrl("users/login-error");
                }
        ).logout(
                logout -> {
                    logout
                            // The url where we should POST something in order to perform the logout
                            .logoutUrl("users/logout")
                            // Where to go when logout
                            .logoutSuccessUrl("/")
                            // invalidate the http session
                            .invalidateHttpSession(true);
                }
        );
        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        // This service translates between the mobilele users and roles
        // to representation which spring security understands.
        return new MobileleUserDetailsService(userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder () {
        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }
}
