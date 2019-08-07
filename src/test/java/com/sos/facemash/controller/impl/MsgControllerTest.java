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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@WebAppConfiguration
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class MsgControllerTest {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void getMessageButMsgNotFoundTest() throws Exception {
        ResultActions result = mockMvc.perform(get("/users/{userName}/message/{msgId}/", "arc90", 0));
        result.andDo(print());
        result.andExpect(status().isNotFound());
    }

    @Test
    public void getMessageButUserNotFoundTest() throws Exception {
        ResultActions result = mockMvc.perform(get("/users/{userName}/message/{msgId}/", "undefined", 1));
        result.andDo(print());
        result.andExpect(status().isNotFound());
    }

    @Test
    public void getMessageWorksTest() throws Exception {
        ResultActions result = mockMvc.perform(get("/users/{userName}/message/{msgId}/", "arc90", 50));
        result.andDo(print());
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("prueba1"))
                .andExpect(jsonPath("$.body").value("Esto es el primer mensaje de prueba"))
                .andExpect(jsonPath("$.date").value("2019-05-16T22:00:00.000+0000"))
                .andExpect(jsonPath("$.owner").value("arc90"))
                .andExpect(jsonPath("$.destination").isEmpty());
    }

    @Test
    public void getAllMessagesWithOutFilterTest() throws Exception {
        ResultActions result = mockMvc
                .perform(get("/users/{userName}/messages/", "arc90")
                        .param("nElements", "10"));
        result.andDo(print());
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.messages.[0].title").value("prueba1"))
                .andExpect(jsonPath("$.messages.[0].owner").value("arc90"))
                .andExpect(jsonPath("$.messages.[0].date").value("2019-05-16T22:00:00.000+0000"))
                .andExpect(jsonPath("$.messages.[0].destination").isEmpty())

                .andExpect(jsonPath("$.messages.[1].title").value("prueba2"))
                .andExpect(jsonPath("$.messages.[1].owner").value("arc90"))
                .andExpect(jsonPath("$.messages.[1].date").value("2019-05-18T22:00:00.000+0000"))
                .andExpect(jsonPath("$.messages.[1].destination").value("beblie16"))

                .andExpect(jsonPath("$.messages.[2].title").value("mensaje prueba3"))
                .andExpect(jsonPath("$.messages.[2].owner").value("arc90"))
                .andExpect(jsonPath("$.messages.[2].date").value("2019-05-22T22:00:00.000+0000"))
                .andExpect(jsonPath("$.messages.[2].destination").value("charlie_magic"))

                .andExpect(jsonPath("$.messages.[3].title").value("mensaje prueba4"))
                .andExpect(jsonPath("$.messages.[3].owner").value("arc90"))
                .andExpect(jsonPath("$.messages.[3].date").value("2019-05-26T22:00:00.000+0000"))
                .andExpect(jsonPath("$.messages.[3].destination").value("beblie16"));
    }

    @Test
    public void getAllMessagesWithFilterTest() throws Exception {
        ResultActions result = mockMvc
                .perform(get("/users/{userName}/messages/", "arc90")
                        .param("filter", "mensaje")
                        .param("nElements", "10"));
        result.andDo(print());
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.messages.[0].title").value("mensaje prueba3"))
                .andExpect(jsonPath("$.messages.[0].owner").value("arc90"))
                .andExpect(jsonPath("$.messages.[0].date").value("2019-05-22T22:00:00.000+0000"))
                .andExpect(jsonPath("$.messages.[0].destination").value("charlie_magic"))

                .andExpect(jsonPath("$.messages.[1].title").value("mensaje prueba4"))
                .andExpect(jsonPath("$.messages.[1].owner").value("arc90"))
                .andExpect(jsonPath("$.messages.[1].date").value("2019-05-26T22:00:00.000+0000"))
                .andExpect(jsonPath("$.messages.[1].destination").value("beblie16"));
    }

    @Test
    public void getAllMessagesButUserNotFoundTest() throws Exception {
        ResultActions result = mockMvc
                .perform(get("/users/{userName}/messages/", "undefined")
                        .param("nElements", "10"));
        result.andDo(print());
        result.andExpect(status().isNotFound());
    }


    @Test
    public void createMsgButUserNotFoundTest() throws Exception {
        ResultActions result = mockMvc
                .perform(post("/users/{userName}/message/", "undefined")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(
                                "{ \"body\": \"string\", \"title\": \"string\"}"
                        ));
        result.andDo(print());
        result.andExpect(status().isNotFound());
    }

    @Test
    public void createMsgButBadInputTest() throws Exception {
        ResultActions result = mockMvc
                .perform(post("/users/{userName}/message/", "arc90")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(
                                "{ \"body\": \"string\"}"
                        ));
        result.andDo(print());
        result.andExpect(status().isBadRequest());
    }

    @Test
    public void createMsgWithOutDestinationWorksTest() throws Exception {
        ResultActions result = mockMvc
                .perform(post("/users/{userName}/message/", "arc90")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(
                                "{ \"body\": \"Esto es un post de un mensaje\", \"title\": \"Probando el Post\"}"
                        ));
        result.andDo(print());
        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Probando el Post"))
                .andExpect(jsonPath("$.body").value("Esto es un post de un mensaje"))
                .andExpect(jsonPath("$.owner").value("arc90"))
                .andExpect(jsonPath("$.destination").isEmpty());
    }
    @Test
    public void createMsgWithDestinationWorksTest() throws Exception {
        ResultActions result = mockMvc
                .perform(post("/users/{userName}/message/", "arc90")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(
                                "{ \"body\": \"mensaje con destinatario\", \"destinationId\": \"beblie16\", \"title\": \"msg with destiny\"}"));
        result.andDo(print());
        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("msg with destiny"))
                .andExpect(jsonPath("$.body").value("mensaje con destinatario"))
                .andExpect(jsonPath("$.owner").value("arc90"))
                .andExpect(jsonPath("$.destination").value("beblie16"));
    }

    @Test
    public void modifyMsgWithDestinationWorksTest() throws Exception{
        ResultActions result = mockMvc
                .perform(put("/users/{userName}/message/{msgId}/", "arc90",53)
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(
                                "{ \"body\": \"El mensaje se ha modificado\", \"title\": \"Mensaje modificado\"}"));
        result.andDo(print());
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Mensaje modificado"))
                .andExpect(jsonPath("$.body").value("El mensaje se ha modificado"))
                .andExpect(jsonPath("$.owner").value("arc90"))
                .andExpect(jsonPath("$.destination").value("beblie16"));
    }

    @Test
    public void modifyMsgWithOutDestinationWorksTest() throws Exception{
        ResultActions result = mockMvc
                .perform(put("/users/{userName}/message/{msgId}/", "arc90",54)
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(
                                "{ \"body\": \"El mensaje se ha modificado\", \"title\": \"Mensaje modificado\"}"));
        result.andDo(print());
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Mensaje modificado"))
                .andExpect(jsonPath("$.body").value("El mensaje se ha modificado"))
                .andExpect(jsonPath("$.owner").value("arc90"))
                .andExpect(jsonPath("$.destination").isEmpty());
    }

    @Test
    public void modifyMsgButUserNotFoundTest() throws Exception{
        ResultActions result = mockMvc
                .perform(put("/users/{userName}/message/{msgId}/", "undefined",53)
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(
                                "{ \"body\": \"El mensaje se ha modificado\", \"title\": \"Mensaje modificado\"}"));
        result.andDo(print());
        result.andExpect(status().isNotFound());
    }

    @Test
    public void modifyMsgButMsgNotFoundTest() throws Exception{
        ResultActions result = mockMvc
                .perform(put("/users/{userName}/message/{msgId}/", "arc90",-1)
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(
                                "{ \"body\": \"El mensaje se ha modificado\", \"title\": \"Mensaje modificado\"}"));
        result.andDo(print());
        result.andExpect(status().isNotFound());
    }

    @Test
    public void deleteMsgWithOutDestinationWorksTest() throws Exception  {
        ResultActions result = mockMvc.perform(delete("/users/{userName}/message/{msgId}/", "arc90", 54));
        result.andDo(print());
        result.andExpect(status().isNoContent());
    }

    @Test
    public void deleteMsgWithDestinationWorksTest() throws Exception  {
        ResultActions result = mockMvc.perform(delete("/users/{userName}/message/{msgId}/", "arc90", 53));
        result.andDo(print());
        result.andExpect(status().isNoContent());
    }

    @Test
    public void deleteMessageButMsgNotFoundTest() throws Exception {
        ResultActions result = mockMvc.perform(delete("/users/{userName}/message/{msgId}/", "arc90", 0));
        result.andDo(print());
        result.andExpect(status().isNotFound());
    }

    @Test
    public void deleteMessageButUserNotFoundTest() throws Exception {
        ResultActions result = mockMvc.perform(delete("/users/{userName}/message/{msgId}/", "undefined", 1));
        result.andDo(print());
        result.andExpect(status().isNotFound());
    }
}