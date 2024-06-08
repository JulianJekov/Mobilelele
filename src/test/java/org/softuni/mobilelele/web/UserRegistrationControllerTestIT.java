package org.softuni.mobilelele.web;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.softuni.mobilelele.model.dto.UserRegisterDTO;
import org.softuni.mobilelele.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserRegistrationControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private UserRegisterDTO validUserRegisterDTO;

    @Value("${mail.port}")
    private int port;

    @Value("${mail.host}")
    private String host;

    @Value("${mail.username}")
    private String username;

    @Value("${mail.password}")
    private String password;

    private GreenMail greenMail;

    @BeforeEach
    void setUp() {
        validUserRegisterDTO = new UserRegisterDTO();
        validUserRegisterDTO.setFirstName("Test");
        validUserRegisterDTO.setLastName("Test");
        validUserRegisterDTO.setEmail("Test@test.com");
        validUserRegisterDTO.setPassword("Test");
        validUserRegisterDTO.setConfirmPassword("Test");

        greenMail = new GreenMail(new ServerSetup(port, host, "smtp"));
        greenMail.start();
        greenMail.setUser(username, password);
    }

    @AfterEach
    void tearDown() {
        greenMail.stop();
    }

    @Test
    void registerGetMapping() throws Exception {
        mockMvc.perform(get("/users/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("auth-register"))
                .andExpect(model().attributeExists("userRegisterDTO"))
                .andExpect(model().attribute("userRegisterDTO", instanceOf(UserRegisterDTO.class)));

    }

    @Test
    void testRegistrationWithValidInput() throws Exception {
        mockMvc.perform(post("/users/register").with(csrf())
                        .flashAttr("userRegisterDTO", validUserRegisterDTO))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/login"));

        verify(userService).registerUser(validUserRegisterDTO);

        greenMail.waitForIncomingEmail(1);
        MimeMessage[] receivedMessages = greenMail.getReceivedMessages();

        Assertions.assertEquals(1, receivedMessages.length);

        MimeMessage registrationMessage = receivedMessages[0];

        Assertions.assertEquals(1, registrationMessage.getAllRecipients().length);
    }


    @Test
    void testRegistrationWithInvalidInput() throws Exception {
        UserRegisterDTO invalidUserRegisterDTO = new UserRegisterDTO();
        invalidUserRegisterDTO.setFirstName("");
        mockMvc.perform(post("/users/register").with(csrf())
                        .flashAttr("userRegisterDTO", invalidUserRegisterDTO))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/register"))
                .andExpect(flash().attributeExists("userRegisterDTO"))
                .andExpect(flash().attributeExists("org.springframework.validation.BindingResult.userRegisterDTO"));
    }
}

//MockMvcRequestBuilders.post("/users/register")
//                        .param("firstName", "John")
//                        .param("lastName", "Doe")
//                        .param("email", "john.doe@gmail.com")
//                        .param("password", "password")
//                        .param("confirmPassword", "password")
//                        .with(csrf())
//
//        ).andExpect(status().is3xxRedirection());