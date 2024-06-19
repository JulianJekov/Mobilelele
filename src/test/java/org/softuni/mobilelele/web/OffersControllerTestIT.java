package org.softuni.mobilelele.web;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.softuni.mobilelele.model.entity.Offer;
import org.softuni.mobilelele.model.entity.UserEntity;
import org.softuni.mobilelele.testutils.TestData;
import org.softuni.mobilelele.testutils.UserTestDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OffersControllerTestIT {

    private static final String TEST_USER1_EMAIL = "testuser1@email.com";
    private static final String TEST_USER2_EMAIL = "testuser2@email.com";
    private static final String TEST_ADMIN_EMAIL = "testadmin@email.com";

    @Autowired
    UserTestDataUtil userTestDataUtil;

    @Autowired
    TestData testData;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        testData.cleanUp();
        userTestDataUtil.cleanUp();
    }

    @AfterEach
    void tearDown() {
        testData.cleanUp();
        userTestDataUtil.cleanUp();
    }

    @Test
    void testAnonymousDeletionFails() throws Exception {
        UserEntity testUser = userTestDataUtil.createTestUser(TEST_USER1_EMAIL);
        Offer testOffer = testData.createTestOffer(testUser);

        mockMvc.perform(delete("/offers/{id}", testOffer.getId()).with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/users/login"));
    }

    @Test
    @WithMockUser(username = TEST_USER1_EMAIL)
    void testNonAdminUserOwnOffer() throws Exception {
        UserEntity testUser = userTestDataUtil.createTestUser(TEST_USER1_EMAIL);
        Offer testOffer = testData.createTestOffer(testUser);

        mockMvc.perform(delete("/offers/{id}", testOffer.getId()).with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/offers/all"));
    }

    @WithMockUser(username = TEST_USER2_EMAIL)
    @Test
    void testNonAdminUserNotOwnOffer() throws Exception {
        UserEntity testUser = userTestDataUtil.createTestUser(TEST_USER1_EMAIL);
        userTestDataUtil.createTestUser(TEST_USER2_EMAIL);
        Offer testOffer = testData.createTestOffer(testUser);


        mockMvc.perform(delete("/offers/{id}", testOffer.getId()).with(csrf()))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(username = TEST_ADMIN_EMAIL, roles = {"ADMIN", "USER"})
    @Test
    void testAdminUserNotOwnOffer() throws Exception {
        UserEntity testUser = userTestDataUtil.createTestUser(TEST_USER1_EMAIL);
        Offer testOffer = testData.createTestOffer(testUser);
        userTestDataUtil.createTestAdmin(TEST_ADMIN_EMAIL);
        mockMvc.perform(delete("/offers/{id}", testOffer.getId()).with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/offers/all"));
    }


}