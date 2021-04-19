package com.game.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.entity.Profession;
import com.game.controller.utils.PlayerInfoTest;
import com.game.controller.utils.TestsHelper;
import com.game.entity.Race;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UpdatePlayerTest extends AbstractTest {

    private final TestsHelper testsHelper = new TestsHelper();
    private final ObjectMapper mapper = new ObjectMapper();

    //test1
    @Test
    public void updatePlayerIdZeroTest() throws Exception {
        mockMvc.perform(post("/rest/players/0")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestsHelper.NORMAL_JSON))
                .andExpect(status().isBadRequest());
    }

    //test2
    @Test
    public void updatePlayerNotExistTest() throws Exception {
        mockMvc.perform(post("/rest/players/415")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestsHelper.NORMAL_JSON))
                .andExpect(status().isNotFound());
    }

    //test3
    @Test
    public void updatePlayerInvalidNameTest() throws Exception {
        PlayerInfoTest playerInfoTest = testsHelper.getPlayerInfosById(1);

        mockMvc.perform(MockMvcRequestBuilders.post("/rest/players/" + playerInfoTest.id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestsHelper.EMPTY_NAME_JSON))
                .andExpect(status().isBadRequest());
    }

    //test4
    @Test
    public void updatePlayerInvalidBirthdayTest() throws Exception {
        PlayerInfoTest playerInfoTest = testsHelper.getPlayerInfosById(1);

        mockMvc.perform(MockMvcRequestBuilders.post("/rest/players/" + playerInfoTest.id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestsHelper.NEGATIVE_BIRTHDAY_JSON))
                .andExpect(status().isBadRequest());
    }

    //test5
    @Test
    public void updatePlayerInvalidExperienceTest() throws Exception {
        PlayerInfoTest playerInfoTest = testsHelper.getPlayerInfosById(1);

        mockMvc.perform(MockMvcRequestBuilders.post("/rest/players/" + playerInfoTest.id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestsHelper.TOO_BIG_EXPERIENCE_JSON))
                .andExpect(status().isBadRequest());
    }

    //test6
    @Test
    public void updatePlayerInvalidExperienceTest2() throws Exception {
        PlayerInfoTest playerInfoTest = testsHelper.getPlayerInfosById(1);

        mockMvc.perform(MockMvcRequestBuilders.post("/rest/players/" + playerInfoTest.id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestsHelper.NEGATIVE_EXPERIENCE_JSON))
                .andExpect(status().isBadRequest());
    }

    //test7
    @Test
    public void updatePlayerWithIdTest() throws Exception {
        PlayerInfoTest expected = mapper.readValue(String.format(TestsHelper.NORMAL_JSON_WITH_ID, 5), PlayerInfoTest.class);
        expected.level = 35;
        expected.untilNextLevel = 2614;

        ResultActions resultActions = mockMvc.perform(post("/rest/players/5")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.format(TestsHelper.NORMAL_JSON_WITH_ID, 8L)))
                .andExpect(status().isOk());

        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
        PlayerInfoTest actual = mapper.readValue(contentAsString, PlayerInfoTest.class);

        assertNotEquals("При запросе POST /rest/players/{id} поле id не должно обновляться.", 8, actual.id);
        assertEquals("При запросе POST /rest/players/{id} с id в теле запроса, должны быть обновлены поля, кроме поля id", expected, actual);
    }

    //test8
    @Test
    public void updatePlayerEmptyBodyTest() throws Exception {
        PlayerInfoTest expected = testsHelper.getPlayerInfosById(17);

        ResultActions resultActions = mockMvc.perform(post("/rest/players/17")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk());

        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
        PlayerInfoTest actual = mapper.readValue(contentAsString, PlayerInfoTest.class);

        assertEquals("При запросе POST /rest/players/{id} с пустым телом запроса, корабль не должен изменяться", expected, actual);
    }

    //test9
    @Test
    public void updatePlayerLevelTest() throws Exception {
        PlayerInfoTest expected = mapper.readValue(String.format(TestsHelper.NORMAL_JSON_WITH_ID, 23), PlayerInfoTest.class);
        expected.level = 35;
        expected.untilNextLevel = 2614;

        ResultActions resultActions = mockMvc.perform(post("/rest/players/23")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.format(TestsHelper.NORMAL_JSON_WITH_LEVEL, "9")))
                .andExpect(status().isOk());

        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
        PlayerInfoTest actual = mapper.readValue(contentAsString, PlayerInfoTest.class);

        assertEquals("При запросе POST /rest/players/{id} с level в теле запроса, должны быть обновлены поля, кроме поля level", expected, actual);
    }

    //test10
    @Test
    public void updatePlayerWithDataTest1() throws Exception {
        PlayerInfoTest playerInfoTest = testsHelper.getPlayerInfosById(14);

        String newName = "TestName";
        boolean newBanned = false;
        int newExperience = 2500;

        PlayerInfoTest expected = new PlayerInfoTest(playerInfoTest.id, newName, playerInfoTest.title, playerInfoTest.race, playerInfoTest.profession,
                playerInfoTest.birthday, newBanned, newExperience, 6, 300);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/rest/players/" + playerInfoTest.id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.format(TestsHelper.JSON_SKELETON, newName, newBanned, newExperience)))
                .andExpect(status().isOk());

        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
        PlayerInfoTest actual = mapper.readValue(contentAsString, PlayerInfoTest.class);

        assertEquals("При запросе POST /rest/players/{id} игрок должен обновляться, а рейтинг и остаток до следующего уровня пересчитываться", expected, actual);
    }

    //test11
    @Test
    public void updatePlayerWithDataTest2() throws Exception {
        PlayerInfoTest playerInfoTest = testsHelper.getPlayerInfosById(32);

        String newTitle = "TestName";
        Race newRace = Race.DWARF;
        Profession newProfession = Profession.ROGUE;
        long newBirthday = 1178571600000L;

        PlayerInfoTest expected = new PlayerInfoTest(playerInfoTest.id, playerInfoTest.name, newTitle, newRace, newProfession, newBirthday,
                playerInfoTest.banned, playerInfoTest.experience, playerInfoTest.level, playerInfoTest.untilNextLevel);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/rest/players/" + playerInfoTest.id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.format(TestsHelper.JSON_SKELETON_2, newTitle, newRace, newProfession, newBirthday)))
                .andExpect(status().isOk());

        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
        PlayerInfoTest actual = mapper.readValue(contentAsString, PlayerInfoTest.class);

        assertEquals("При запросе POST /rest/players/{id} корабль должен обновляться и рейтинг пересчитываться", expected, actual);
    }
}