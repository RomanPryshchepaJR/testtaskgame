package com.game.controller.utils;

import com.game.entity.Profession;
import com.game.entity.Race;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Objects;

public class PlayerInfoTest {
    public Long id;
    public String name;
    public String title;
    public Race race;
    public Profession profession;
    public Long birthday;
    public Boolean banned;
    public Integer experience;
    public Integer level;
    public Integer untilNextLevel;

    public PlayerInfoTest() {
    }

    public PlayerInfoTest(Long id, String name, String title, Race race, Profession profession, Long birthday,
                          Boolean banned, Integer experience, Integer level, Integer untilNextLevel) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.race = race;
        this.profession = profession;
        this.birthday = birthday;
        this.banned = banned;
        this.experience = experience;
        this.level = level;
        this.untilNextLevel = untilNextLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerInfoTest that = (PlayerInfoTest) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(title, that.title) &&
                race == that.race &&
                profession == that.profession &&
                Objects.equals(birthday, that.birthday) &&
                Objects.equals(banned, that.banned) &&
                Objects.equals(experience, that.experience) &&
                Objects.equals(level, that.level) &&
                Objects.equals(untilNextLevel, that.untilNextLevel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, title, race, profession, birthday, banned, experience, level, untilNextLevel);
    }

    @Override
    public String toString() {
        LocalDate birthday = Instant.ofEpochMilli(this.birthday).atZone(ZoneId.systemDefault()).toLocalDate();
        return "PlayerInfoTest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}