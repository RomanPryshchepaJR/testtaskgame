package com.game.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.controller.utils.PlayerInfoTest;
import com.game.controller.utils.TestsHelper;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CreatePlayerTest extends AbstractTest {

    //test1
    @Test
    public void createPlayerEmptyBodyTest() throws Exception {
        mockMvc.perform(post("/rest/players/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isBadRequest());
    }

    //test2
    @Test
    public void createPlayerEmptyNameTest() throws Exception {
        mockMvc.perform(post("/rest/players/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestsHelper.EMPTY_NAME_JSON))
                .andExpect(status().isBadRequest());
    }

    //test3
    @Test
    public void createPlayerBirthdayNegativeTest() throws Exception {
        mockMvc.perform(post("/rest/players/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestsHelper.NEGATIVE_BIRTHDAY_JSON))
                .andExpect(status().isBadRequest());
    }

    //test4
    @Test
    public void createPlayerExperienceTooBigTest() throws Exception {
        mockMvc.perform(post("/rest/players/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestsHelper.TOO_BIG_EXPERIENCE_JSON))
                .andExpect(status().isBadRequest());
    }

    //test5
    @Test
    public void createPlayerTitleLengthTooBigTest() throws Exception {
        mockMvc.perform(post("/rest/players/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestsHelper.TOO_BIG_TITLE_LENGTH_JSON))
                .andExpect(status().isBadRequest());
    }

    //test6
    @Test
    public void createPlayerTest() throws Exception {
        ResultActions resultActions = mockMvc.perform(post("/rest/players/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestsHelper.BANNED_TRUE_JSON))
                .andExpect(status().isOk());

        PlayerInfoTest expected = new PlayerInfoTest(41L, "Амарылис", "Прозелит", Race.DWARF, Profession.CLERIC, 988059600000L, true, 63986, 35, 2614);
        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
        PlayerInfoTest actual = new ObjectMapper().readValue(contentAsString, PlayerInfoTest.class);
        assertEquals("Возвращается не правильный результат при запросе создания игрока.", expected, actual);
    }
}