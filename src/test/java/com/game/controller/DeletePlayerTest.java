package com.game.controller;

import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DeletePlayerTest extends AbstractTest {

    //test1
    @Test
    public void deletePlayerByIdZeroTest() throws Exception {
        mockMvc.perform(delete("/rest/players/0"))
                .andExpect(status().isBadRequest());
    }

    //test2
    @Test
    public void deletePlayerByIdNotNumberTest() throws Exception {
        mockMvc.perform(delete("/rest/players/test"))
                .andExpect(status().isBadRequest());
    }

    //test3
    @Test
    public void deletePlayerByIdNotExistTest() throws Exception {
        mockMvc.perform(delete("/rest/players/426"))
                .andExpect(status().isNotFound());
    }

    //test4
    @Test
    public void deletePlayerByIdTest() throws Exception {
        mockMvc.perform(delete("/rest/players/1"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/rest/players/1"))
                .andExpect(status().isNotFound());
    }
}