package com.sos.facemash.controller.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@WebAppConfiguration
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class UserControllerTest {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void getAllUsersTest() throws Exception {
        ResultActions result = mockMvc.perform(get("/users/{filter}", ""));
        result.andDo(print());
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.users.[0].userName").value("arc90"))
                .andExpect(jsonPath("$.users.[0].mail").value("arc_90@coldmail.com"))

                .andExpect(jsonPath("$.users.[1].userName").value("beblie16"))
                .andExpect(jsonPath("$.users.[1].mail").value("bel.buar@coldmail.com"))

                .andExpect(jsonPath("$.users.[2].userName").value("charlie_magic"))
                .andExpect(jsonPath("$.users.[2].mail").value("charlie_magic@coldmail.com"));
    }

    @Test
    public void getUserButNotFoundTest() throws Exception {
        ResultActions result = mockMvc.perform(get("/user/{userName}", "undefined"));
        result.andDo(print());
        result.andExpect(status().isNotFound());
    }

    @Test
    public void getUserWorksTest() throws Exception {
        ResultActions result = mockMvc.perform(get("/user/{userName}/", "arc90"));
        result.andDo(print());
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value("arc90"))
                .andExpect(jsonPath("$.mail").value("arc_90@coldmail.com"))
                .andExpect(jsonPath("$.phone").value(555297462))
                .andExpect(jsonPath("$.name").value("Arcangel"))
                .andExpect(jsonPath("$.lastName").value("Alberti"));
    }

    @Test
    public void createUserWorksTest() throws Exception {
        ResultActions result = mockMvc.perform(post("/users/")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(
                        "{ \"lastName\": \"Tarancon\", \"mail\": \"alber.tara@coldmail.com\", \"name\": \"Alber\", \"phone\": 240792345, \"userName\": \"albertara\"}"
                ));
        result.andDo(print());
        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.userName").value("albertara"))
                .andExpect(jsonPath("$.mail").value("alber.tara@coldmail.com"))
                .andExpect(jsonPath("$.phone").value(240792345))
                .andExpect(jsonPath("$.name").value("Alber"))
                .andExpect(jsonPath("$.lastName").value("Tarancon"));
    }

    @Test
    public void createUserBadRequestTest()throws Exception {
        ResultActions result = mockMvc.perform(post("/users/")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(
                        "{ \"lastName\": \"asdfasdf\", \"mail\": \"albedasdaa@coldmail.com\", \"name\": \"Albeasdr\", \"phone\": 621238478}"
                ));
        result.andDo(print());
        result.andExpect(status().isBadRequest());
    }

    @Test
    public void createUserButUserExistsTest() {
    }

    @Test
    public void modifyMsg() {
    }

    @Test
    public void deleteMsg() {
    }
}