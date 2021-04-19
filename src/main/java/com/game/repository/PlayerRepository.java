package com.game.repository;

import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PlayerRepository extends PagingAndSortingRepository<Player, Long> {

    @Query("select s from Player s where " +
            "(:name is null or s.name like %:name%) and " +
            "(:title is null or s.title like %:title%) and " +
            "(:race is null or s.race = :race) and " +
            "(:profession is null or s.profession = :profession) and " +
            "(:after is null or s.birthday >= :after) and " +
            "(:before is null or s.birthday <= :before) and " +
            "(:banned is null or s.banned = :banned) and " +
            "(:minExperience is null or s.experience >= :minExperience) and " +
            "(:maxExperience is null or s.experience <= :maxExperience) and " +
            "(:minLevel is null or s.level >= :minLevel) and " +
            "(:maxLevel is null or s.level <= :maxLevel) ")
    List<Player> getAll(@Param("name") String name,
                        @Param("title") String title,
                        @Param("race") Race race,
                        @Param("profession") Profession profession,
                        @Param("after") Date after,
                        @Param("before") Date before,
                        @Param("banned") Boolean banned,
                        @Param("minExperience") Integer minExperience,
                        @Param("maxExperience") Integer maxExperience,
                        @Param("minLevel") Integer minLevel,
                        @Param("maxLevel") Integer maxLevel,
                        Pageable pageable);


    @Query("select count(s) from Player s where " +
            "(:name is null or s.name like %:name%) and " +
            "(:title is null or s.title like %:title%) and " +
            "(:race is null or s.race = :race) and " +
            "(:profession is null or s.profession = :profession) and " +
            "(:after is null or s.birthday >= :after) and " +
            "(:before is null or s.birthday <= :before) and " +
            "(:banned is null or s.banned = :banned) and " +
            "(:minExperience is null or s.experience >= :minExperience) and " +
            "(:maxExperience is null or s.experience <= :maxExperience) and " +
            "(:minLevel is null or s.level >= :minLevel) and " +
            "(:maxLevel is null or s.level <= :maxLevel) ")
    Integer getAllCount(@Param("name") String name,
                        @Param("title") String title,
                        @Param("race") Race race,
                        @Param("profession") Profession profession,
                        @Param("after") Date after,
                        @Param("before") Date before,
                        @Param("banned") Boolean banned,
                        @Param("minExperience") Integer minExperience,
                        @Param("maxExperience") Integer maxExperience,
                        @Param("minLevel") Integer minLevel,
                        @Param("maxLevel") Integer maxLevel);
}