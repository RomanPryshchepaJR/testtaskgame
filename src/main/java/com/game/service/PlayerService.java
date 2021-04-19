package com.game.service;

import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    public List<Player> getAll(String name, String title, Race race, Profession profession, Long after, Long before,
                               Boolean banned, Integer minExperience, Integer maxExperience, Integer minLevel, Integer maxLevel,
                               String order, int pageNumber, int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(order));
        Date afterDate = isNull(after) ? null : new Date(after);
        Date beforeDate = isNull(before) ? null : new Date(before);
        return playerRepository.getAll(name, title, race, profession, afterDate, beforeDate, banned,
                minExperience, maxExperience, minLevel, maxLevel, pageable);
    }

    public Integer getAllCount(String name, String title, Race race, Profession profession, Long after, Long before, Boolean banned,
                               Integer minExperience, Integer maxExperience, Integer minLevel, Integer maxLevel) {
        Date afterDate = isNull(after) ? null : new Date(after);
        Date beforeDate = isNull(before) ? null : new Date(before);

        return playerRepository.getAllCount(name, title, race, profession, afterDate, beforeDate, banned,
                minExperience, maxExperience, minLevel, maxLevel);
    }

    public Player createPlayer(String name, String title, Race race, Profession profession, long birthday, boolean banned, int experience) {
        int level = getLevel(experience);
        int untilNextLevel = getUntilNextLevel(experience, level);

        Player player = new Player();
        player.setName(name);
        player.setTitle(title);
        player.setRace(race);
        player.setProfession(profession);
        player.setBirthday(new Date(birthday));
        player.setBanned(banned);
        player.setExperience(experience);
        player.setLevel(level);
        player.setUntilNextLevel(untilNextLevel);

        return playerRepository.save(player);
    }

    public Player getPlayer(long id) {
        return playerRepository.findById(id).orElse(null);
    }

    public Player updatePlayer(long id, String name, String title, Race race, Profession profession, Long birthday, Boolean banned, Integer experience) {

        Player player = playerRepository.findById(id).orElse(null);
        if (isNull(player)) return null;

        boolean needUpdate = false;

        if (!StringUtils.isEmpty(name) && name.length() <= 12) {
            player.setName(name);
            needUpdate = true;
        }
        if (!StringUtils.isEmpty(title) && title.length() <= 30) {
            player.setTitle(title);
            needUpdate = true;
        }
        if (nonNull(race)) {
            player.setRace(race);
            needUpdate = true;
        }
        if (nonNull(profession)) {
            player.setProfession(profession);
            needUpdate = true;
        }
        if (nonNull(birthday)) {
            player.setBirthday(new Date(birthday));
            needUpdate = true;
        }
        if (nonNull(banned)) {
            player.setBanned(banned);
            needUpdate = true;
        }
        if (nonNull(experience)) {
            player.setExperience(experience);
            needUpdate = true;
        }

        if (needUpdate) {
            player.setLevel(getLevel(player.getExperience()));
            player.setUntilNextLevel(getUntilNextLevel(player.getExperience(), player.getLevel()));
            playerRepository.save(player);
        }

        return player;
    }

    private int getLevel(int experience) {
        return (int) (Math.sqrt(2500 + 200 * experience) - 50) / 100;
    }

    private int getUntilNextLevel(int experience, int level) {
        return 50 * (level + 1) * (level + 2) - experience;
    }

    public Player delete(long id) {
        Player player = playerRepository.findById(id).orElse(null);
        if (isNull(player)) return null;

        playerRepository.delete(player);
        return player;
    }
}