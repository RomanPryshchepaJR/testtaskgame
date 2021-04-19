package com.game.controller;

import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.controller.utils.TestsHelper;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.Assert.assertSame;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GetCountTest extends AbstractTest {

    private final TestsHelper testsHelper = new TestsHelper();

    //test1
    @Test
    public void getCountWithoutFiltersReturnAllPlayers() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(get("/rest/players/count")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        int actual = Integer.parseInt(contentAsString);
        int expected = testsHelper.getAllPlayers().size();

        assertSame("Возвращается не правильный результат при запросе GET /rest/players/count.", expected, actual);
    }

    //test2
    @Test
    public void getCountWithFiltersMinLevelMinExperience() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(get("/rest/players/count?minLevel=42&minExperience=94000")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        int actual = Integer.parseInt(contentAsString);
        int expected = testsHelper.getPlayerInfosByMinLevel(42,
                testsHelper.getPlayerInfosByMinExperience(94000,
                        testsHelper.getAllPlayers())).size();

        assertSame("Возвращается не правильный результат при запросе GET /rest/players/count с параметрами minLevel и minExperience.", expected, actual);
    }

    //test3
    @Test
    public void getCountWithFiltersNameAfterMaxLevel() throws Exception {
        //after 00:00 01.01.2005
        ResultActions resultActions = this.mockMvc.perform(get("/rest/players/count?name=ри&after=1104530400000&maxLevel=40")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        int actual = Integer.parseInt(contentAsString);
        int expected = testsHelper.getPlayerInfosByMaxLevel(40,
                testsHelper.getPlayerInfosByAfter(1104530400000L,
                        testsHelper.getPlayerInfosByName("ри",
                                testsHelper.getAllPlayers()))).size();

        assertSame("Возвращается не правильный результат при запросе GET /rest/players/count с параметрами name, after и maxLevel.", expected, actual);
    }

    //test4
    @Test
    public void getCountWithFiltersRaceProfessionBanned() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(get("/rest/players/count?race=DWARF&profession=CLERIC&banned=true")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        int actual = Integer.parseInt(contentAsString);
        int expected = testsHelper.getPlayerInfosByBaned(true,
                testsHelper.getPlayerInfosByRace(Race.DWARF,
                        testsHelper.getPlayerInfosByProfession(Profession.CLERIC,
                                testsHelper.getAllPlayers()))).size();

        assertSame("Возвращается не правильный результат при запросе GET rest/players/count с параметрами race, profession и banned.", expected, actual);
    }

    //test5
    @Test
    public void getCountWithFiltersRaceProfessionMaxExperience() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(get("/rest/players/count?race=TROLL&profession=WARRIOR&maxExperience=120000")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        int actual = Integer.parseInt(contentAsString);
        int expected = testsHelper.getPlayerInfosByMaxExperience(120000,
                testsHelper.getPlayerInfosByRace(Race.TROLL,
                        testsHelper.getPlayerInfosByProfession(Profession.WARRIOR,
                                testsHelper.getAllPlayers()))).size();

        assertSame("Возвращается не правильный результат при запросе GET /rest/players/count с параметрами race, profession и maxExperience.", expected, actual);
    }

    //test6
    @Test
    public void getCountWithFiltersTitle() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(get("/rest/players/count?title=ий")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        int actual = Integer.parseInt(contentAsString);
        int expected = testsHelper.getPlayerInfosByTitle("ий", testsHelper.getAllPlayers()).size();

        assertSame("Возвращается не правильный результат при запросе GET /rest/players/count с параметром title.", expected, actual);
    }

    //test7
    @Test
    public void getCountWithFiltersRaceProfessionBefore() throws Exception {
        //before 00:00 01.01.2008
        ResultActions resultActions = this.mockMvc.perform(get("/rest/players/count?race=GIANT&profession=WARRIOR&before=1199138400000")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        int actual = Integer.parseInt(contentAsString);
        int expected = testsHelper.getPlayerInfosByBefore(1199138400000L,
                testsHelper.getPlayerInfosByRace(Race.GIANT,
                        testsHelper.getPlayerInfosByProfession(Profession.WARRIOR,
                        testsHelper.getAllPlayers()))).size();

        assertSame("Возвращается не правильный результат при запросе GET /rest/players/count с параметрами race, profession и before.", expected, actual);
    }

    //test8
    @Test
    public void getCountWithFiltersBanned() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(get("/rest/players/count?banned=false")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        int actual = Integer.parseInt(contentAsString);
        int expected = testsHelper.getPlayerInfosByBaned(false, testsHelper.getAllPlayers()).size();

        assertSame("Во звращается не правильный результат при запросе GET /rest/players/count с параметром banned.", expected, actual);
    }
}