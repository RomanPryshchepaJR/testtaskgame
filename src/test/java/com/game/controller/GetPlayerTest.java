package com.game.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.controller.utils.PlayerInfoTest;
import com.game.controller.utils.TestsHelper;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GetPlayerTest extends AbstractTest {

    //test1
    @Test
    public void getPlayerByIdEqualZeroTest() throws Exception {
        mockMvc.perform(get("/rest/players/0"))
                .andExpect(status().isBadRequest());
    }

    //test2
    @Test
    public void getPlayerByIdNotNumberTest() throws Exception {
        mockMvc.perform(get("/rest/players/test"))
                .andExpect(status().isBadRequest());
    }

    //test3
    @Test
    public void getPlayerByIdNotExistTest() throws Exception {
        mockMvc.perform(get("/rest/players/410"))
                .andExpect(status().isNotFound());
    }

    //test4
    @Test
    public void getPlayerByIdTest() throws Exception {
        PlayerInfoTest expected = new TestsHelper().getPlayerInfosById(14);

        ResultActions resultActions = mockMvc.perform(get("/rest/players/14"))
                .andExpect(status().isOk());

        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        PlayerInfoTest actual = mapper.readValue(contentAsString, PlayerInfoTest.class);
        assertEquals("Вернулся неправильный объект при запросе GET /rest/players/{id}", expected, actual);
    }
}