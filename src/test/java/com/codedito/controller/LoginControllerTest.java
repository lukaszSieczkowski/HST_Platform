package com.codedito.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class LoginControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldLoginSuccessfulToUserPage() throws Exception {
        mockMvc.perform(get("/protected/user/userpage")
                .with(user("user").password("pass").roles("USER")))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldLoginSuccessfulToAdminPage() throws Exception {
        mockMvc.perform(get("/protected/admin/adminpage")
                .with(user("admin").password("pass").roles("ADMIN")))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldNotLoginToUserPageWithWrongRole() throws Exception {
        mockMvc.perform(get("/protected/user/userpage")
                .with(user("user").password("pass").roles("ADMIN")))
                .andExpect(status().isForbidden());
    }

    @Test
    public void shouldNotLoginToAdminPageWithWrongRole() throws Exception {
        final ResultActions resultActions = mockMvc.perform(get("/protected/admin/adminpage")
                .with(user("admin").password("pass").roles("USER")))
                .andExpect(status().isForbidden());
    }
}