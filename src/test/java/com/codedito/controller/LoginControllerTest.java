package com.codedito.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class LoginControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldLoginSuccessfulToUserPage() throws Exception {
        mockMvc.perform(get("/main")
                .with(user("user").password("pass").roles("USER")))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldLoginSuccessfulToAdminPage() throws Exception {
        mockMvc.perform(get("/main")
                .with(user("admin").password("pass").roles("ADMIN")))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnMainView() throws Exception {
        mockMvc.perform(get("/main")
                .with(user("user").password("pass").roles("USER")))
                .andExpect(view().name("protected/main"));
    }

    @Test
    public void shouldReturnIndexView() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(view().name("index"));
    }

    @Test
    public void shouldReturnLoginView() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(view().name("login_page"));
    }

    @Test
    public void shouldReturnForgotPasswordView() throws Exception {
        mockMvc.perform(get("/forgot_password"))
                .andExpect(view().name("forgot_password"));
    }
}