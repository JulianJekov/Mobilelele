package org.softuni.mobilelele.config;

import org.softuni.mobilelele.model.enums.UserRoleEnum;
import org.softuni.mobilelele.repository.UserRepository;
import org.softuni.mobilelele.service.impl.MobileleUserDetailsService;
import org.softuni.mobilelele.service.oauth.OAuthSuccessHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {

    private final String rememberMeKey;

    public SecurityConfiguration(@Value("${mobilele.remember.me.key}") String rememberMeKey) {
        this.rememberMeKey = rememberMeKey;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity,
                                           OAuthSuccessHandler oAuthSuccessHandler) throws Exception {
      return  httpSecurity.authorizeHttpRequests(
                        // Define which urls are visible by users
                        authorizeRequests -> authorizeRequests
                                // All static resources which are situated in js, images, css are available for anyone
                                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                                // Allow anyone to see the home page, the registration page and the login form
                                .requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()
                                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                                .requestMatchers("/", "/users/login", "/users/register", "/users/login-error").permitAll()
                                .requestMatchers("/offers/all").permitAll()
                                .requestMatchers("/api/currency/convert").permitAll()
                                .requestMatchers(HttpMethod.GET, "/offers/**").permitAll()
                                .requestMatchers("/error").permitAll()
                                .requestMatchers("/brands/all").hasRole(UserRoleEnum.ADMIN.name())
                                // All other requests are authenticated
                                .anyRequest().authenticated()
                ).formLogin(
                        formLogin -> {
                            // Redirect here when we access something which is not allowed
                            // Also this is the page where perform login.
                            formLogin
                                    .loginPage("/users/login")
                                    // The names of the input fields (in our case in auth-login.html)
                                    .usernameParameter("email")
                                    .passwordParameter("password")
                                    .defaultSuccessUrl("/")
                                    .failureForwardUrl("/users/login-error");
                        }
                ).logout(
                        logout -> {
                            logout
                                    // The url where we should POST something in order to perform the logout
                                    .logoutUrl("/users/logout")
                                    // Where to go when logout
                                    .logoutSuccessUrl("/users/login")
                                    // invalidate the http session
                                    .invalidateHttpSession(true);
                        }
                ).rememberMe(
                        rememberMe ->
                                rememberMe
                                        .key(rememberMeKey)
                                        .rememberMeParameter("rememberme")
                                        .rememberMeCookieName("rememberme")

                ).oauth2Login(
                        oauth -> {
                            oauth.successHandler(oAuthSuccessHandler);
                        }
                ).build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        // This service translates between the mobilele users and roles
        // to representation which spring security understands.
        return new MobileleUserDetailsService(userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }
}
