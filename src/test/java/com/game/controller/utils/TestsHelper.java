package com.game.controller.utils;

import com.game.controller.PlayerOrder;
import com.game.entity.Profession;
import com.game.entity.Race;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TestsHelper {
    public final static String NORMAL_JSON =
            "{" +
                    "\"name\": \"Амарылис\"," +
                    "\"title\":\"Прозелит\"," +
                    "\"race\": \"HUMAN\"," +
                    "\"profession\": \"CLERIC\"," +
                    "\"birthday\" : 988059600000," +
                    "\"banned\":false," +
                    "\"experience\": 63986" +
                    "}";

    public final static String NORMAL_JSON_WITH_ID =
            "{" +
                    "\"id\": %s," +
                    "\"name\": \"Амарылис\"," +
                    "\"title\":\"Прозелит\"," +
                    "\"race\": \"HUMAN\"," +
                    "\"profession\": \"CLERIC\"," +
                    "\"birthday\" : 988059600000," +
                    "\"banned\":false," +
                    "\"experience\": 63986" +
                    "}";

    public final static String NORMAL_JSON_WITH_LEVEL =
            "{" +
                    "\"name\": \"Амарылис\"," +
                    "\"title\":\"Прозелит\"," +
                    "\"race\": \"HUMAN\"," +
                    "\"profession\": \"CLERIC\"," +
                    "\"birthday\" : 988059600000," +
                    "\"banned\":false," +
                    "\"experience\": 63986," +
                    "\"level\": %s" +
                    "}";

    public static final String EMPTY_NAME_JSON =
            "{" +
                    "\"name\": \"\"," +
                    "\"title\":\"Прозелит\"," +
                    "\"race\": \"HUMAN\"," +
                    "\"profession\": \"HUMAN\"," +
                    "\"profession\": \"CLERIC\"," +
                    "\"birthday\" : 988059600000," +
                    "\"banned\":false," +
                    "\"experience\": 63986" +
                    "}";

    public static final String NEGATIVE_BIRTHDAY_JSON =
            "{" +
                    "\"name\": \"Амарылис\"," +
                    "\"title\":\"Прозелит\"," +
                    "\"race\": \"HUMAN\"," +
                    "\"birthday\" : -988059600000," +
                    "\"banned\":false," +
                    "\"experience\": 63986" +
                    "}";

    public static final String TOO_BIG_EXPERIENCE_JSON =
            "{" +
                    "\"name\": \"Амарылис\"," +
                    "\"title\":\"Прозелит\"," +
                    "\"race\": \"HUMAN\"," +
                    "\"profession\": \"CLERIC\"," +
                    "\"birthday\" : 988059600000," +
                    "\"banned\":false," +
                    "\"experience\": 10000001" +
                    "}";

    public static final String NEGATIVE_EXPERIENCE_JSON =
            "{" +
                    "\"name\": \"Амарылис\"," +
                    "\"title\":\"Прозелит\"," +
                    "\"race\": \"HUMAN\"," +
                    "\"profession\": \"CLERIC\"," +
                    "\"birthday\" : 988059600000," +
                    "\"Banned\":false," +
                    "\"experience\": -2" +
                    "}";

    public static final String TOO_BIG_TITLE_LENGTH_JSON =
            "{" +
                    "\"name\": \"Амарылис\"," +
                    "\"title\":\"ПрозелитПрозелитПрозелитПрозелит\"," +
                    "\"race\": \"HUMAN\"," +
                    "\"profession\": \"CLERIC\"," +
                    "\"birthday\" : 988059600000," +
                    "\"banned\":false," +
                    "\"experience\": 63986" +
                    "}";

    public static final String BANNED_TRUE_JSON =
            "{" +
                    "\"name\": \"Амарылис\"," +
                    "\"title\":\"Прозелит\"," +
                    "\"race\": \"DWARF\"," +
                    "\"profession\": \"CLERIC\"," +
                    "\"birthday\" : 988059600000," +
                    "\"banned\":true," +
                    "\"experience\": 63986" +
                    "}";

    public static final String JSON_SKELETON =
            "{" +
                    "\"name\": \"%s\"," +
                    "\"Banned\":%s," +
                    "\"experience\": %s" +
                    "}";

    public static final String JSON_SKELETON_2 =
            "{" +
                    "\"title\": \"%s\"," +
                    "\"race\": \"%s\"," +
                    "\"profession\": \"%s\"," +
                    "\"birthday\": %s" +
                    "}";

    private final List<PlayerInfoTest> allPlayers = new ArrayList<>();

    public TestsHelper() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            allPlayers.add(new PlayerInfoTest(1L, "Ниус", "Приходящий Без Шума", Race.HOBBIT, Profession.ROGUE, sdf.parse("2010-10-12").getTime(), false, 58347, 33, 1153));
            allPlayers.add(new PlayerInfoTest(2L, "Никрашш", "НайтВульф", Race.ORC, Profession.WARLOCK, sdf.parse("2010-02-14").getTime(), false, 174403, 58, 2597));
            allPlayers.add(new PlayerInfoTest(3L, "Эззэссэль", "шипящая", Race.DWARF, Profession.CLERIC, sdf.parse("2006-02-28").getTime(), true, 804, 3, 196));
            allPlayers.add(new PlayerInfoTest(4L, "Бэлан", "Тсе Раа", Race.DWARF, Profession.ROGUE, sdf.parse("2008-02-25").getTime(), true, 44553, 29, 1947));
            allPlayers.add(new PlayerInfoTest(5L, "Элеонора", "Бабушка", Race.HUMAN, Profession.SORCERER, sdf.parse("2006-01-07").getTime(), true, 63986, 35, 2614));
            allPlayers.add(new PlayerInfoTest(6L, "Эман", "Ухастый Летун", Race.ELF, Profession.SORCERER, sdf.parse("2004-06-21").getTime(), false, 163743, 56, 1557));
            allPlayers.add(new PlayerInfoTest(7L, "Талан", "Рожденный в Бронксе", Race.GIANT, Profession.ROGUE, sdf.parse("2005-05-15").getTime(), false, 68950, 36, 1350));
            allPlayers.add(new PlayerInfoTest(8L, "Арилан", "Благотворитель", Race.ELF, Profession.SORCERER, sdf.parse("2006-08-10").getTime(), false, 61023, 34, 1977));
            allPlayers.add(new PlayerInfoTest(9L, "Деракт", "Эльфёнок Красное Ухо", Race.ELF, Profession.ROGUE, sdf.parse("2010-06-22").getTime(), false, 156630, 55, 2970));
            allPlayers.add(new PlayerInfoTest(10L, "Архилл", "Смертоносный", Race.GIANT, Profession.PALADIN, sdf.parse("2005-01-12").getTime(), false, 76010, 38, 1990));
            allPlayers.add(new PlayerInfoTest(11L, "Эндарион", "Маленький эльфенок", Race.ELF, Profession.DRUID, sdf.parse("2001-04-24").getTime(), false, 103734, 45, 4366));
            allPlayers.add(new PlayerInfoTest(12L, "Фаэрвин", "Темный Идеолог", Race.HUMAN, Profession.NAZGUL, sdf.parse("2010-09-06").getTime(), false, 7903, 12, 1197));
            allPlayers.add(new PlayerInfoTest(13L, "Харидин", "Бедуин", Race.TROLL, Profession.WARRIOR, sdf.parse("2009-09-08").getTime(), false, 114088, 47, 3512));
            allPlayers.add(new PlayerInfoTest(14L, "Джур", "БоРец с жАжДой", Race.ORC, Profession.DRUID, sdf.parse("2009-07-14").getTime(), false, 29573, 23, 427));
            allPlayers.add(new PlayerInfoTest(15L, "Грон", "Воин обреченный на бой", Race.GIANT, Profession.PALADIN, sdf.parse("2005-04-28").getTime(), false, 174414, 58, 2586));
            allPlayers.add(new PlayerInfoTest(16L, "Морвиел", "Копье Калимы", Race.ELF, Profession.CLERIC, sdf.parse("2010-03-15").getTime(), false, 49872, 31, 2928));
            allPlayers.add(new PlayerInfoTest(17L, "Ннуфис", "ДиамантоваЯ", Race.HUMAN, Profession.ROGUE, sdf.parse("2001-09-03").getTime(), false, 162477, 56, 2823));
            allPlayers.add(new PlayerInfoTest(18L, "Ырх", "Троль гнет ель", Race.TROLL, Profession.WARRIOR, sdf.parse("2001-04-08").getTime(), true, 136860, 51, 940));
            allPlayers.add(new PlayerInfoTest(19L, "Блэйк", "Серый Воин", Race.HUMAN, Profession.ROGUE, sdf.parse("2005-05-23").getTime(), false, 151039, 54, 2961));
            allPlayers.add(new PlayerInfoTest(20L, "Нэсс", "Бусинка", Race.TROLL, Profession.WARRIOR, sdf.parse("2008-02-09").getTime(), true, 64945, 35, 1655));
            allPlayers.add(new PlayerInfoTest(21L, "Ферин", "Воитель", Race.TROLL, Profession.WARRIOR, sdf.parse("2003-07-08").getTime(), false, 120006, 48, 2494));
            allPlayers.add(new PlayerInfoTest(22L, "Солках", "Ученик Магии", Race.ELF, Profession.SORCERER, sdf.parse("2001-11-07").getTime(), false, 152996, 54, 1004));
            allPlayers.add(new PlayerInfoTest(23L, "Сцинк", "Титан Войны", Race.GIANT, Profession.WARRIOR, sdf.parse("2008-01-04").getTime(), true, 86585, 41, 3715));
            allPlayers.add(new PlayerInfoTest(24L, "Айша", "Искусительница", Race.HUMAN, Profession.CLERIC, sdf.parse("2010-01-25").getTime(), false, 106181, 45, 1919));
            allPlayers.add(new PlayerInfoTest(25L, "Тант", "Черт закAтай вату", Race.DWARF, Profession.PALADIN, sdf.parse("2010-10-03").getTime(), false, 33889, 25, 1211));
            allPlayers.add(new PlayerInfoTest(26L, "Трениган", "Великий Волшебник", Race.ELF, Profession.SORCERER, sdf.parse("2004-05-17").getTime(), false, 91676, 42, 2924));
            allPlayers.add(new PlayerInfoTest(27L, "Вуджер", "Печальный", Race.TROLL, Profession.NAZGUL, sdf.parse("2010-10-04").getTime(), false, 93079, 42, 1521));
            allPlayers.add(new PlayerInfoTest(28L, "Камираж", "БAнкир", Race.DWARF, Profession.CLERIC, sdf.parse("2005-08-05").getTime(), true, 79884, 39, 2116));
            allPlayers.add(new PlayerInfoTest(29L, "Ларкин", "СвЯтой", Race.HOBBIT, Profession.CLERIC, sdf.parse("2003-07-10").getTime(), false, 111868, 46, 932));
            allPlayers.add(new PlayerInfoTest(30L, "Зандир", "Темновидец", Race.ELF, Profession.WARLOCK, sdf.parse("2003-05-24").getTime(), false, 29654, 23, 346));
            allPlayers.add(new PlayerInfoTest(31L, "Балгор", "пещерный Урук", Race.ORC, Profession.NAZGUL, sdf.parse("2005-02-23").getTime(), false, 18869, 18, 131));
            allPlayers.add(new PlayerInfoTest(32L, "Регарн", "Любитель ОЛивье", Race.GIANT, Profession.WARRIOR, sdf.parse("2006-12-23").getTime(), false, 144878, 53, 3622));
            allPlayers.add(new PlayerInfoTest(33L, "Анжелли", "Молодой Боец", Race.DWARF, Profession.WARRIOR, sdf.parse("2010-04-08").getTime(), false, 59281, 33, 219));
            allPlayers.add(new PlayerInfoTest(34L, "Джерис", "Имперский Воин", Race.ORC, Profession.WARRIOR, sdf.parse("2001-05-12").getTime(), false, 173807, 58, 3193));
            allPlayers.add(new PlayerInfoTest(35L, "Жэкс", "Ярочкино Солнышко", Race.GIANT, Profession.WARRIOR, sdf.parse("2008-01-04").getTime(), false, 848, 3, 152));
            allPlayers.add(new PlayerInfoTest(36L, "Филуэль", "Химик и Карпускулярник.", Race.ELF, Profession.WARLOCK, sdf.parse("2008-08-03").getTime(), false, 48496, 30, 1104));
            allPlayers.add(new PlayerInfoTest(37L, "Яра", "Прельстивая", Race.HUMAN, Profession.CLERIC, sdf.parse("2004-06-12").getTime(), false, 138306, 52, 4794));
            allPlayers.add(new PlayerInfoTest(38L, "Иллинас", "Иероглиф", Race.HOBBIT, Profession.WARRIOR, sdf.parse("2007-06-03").getTime(), false, 115546, 47, 2054));
            allPlayers.add(new PlayerInfoTest(39L, "Ардонг", "Вспышк A", Race.HUMAN, Profession.WARLOCK, sdf.parse("2009-09-16").getTime(), false, 24984, 21, 316));
            allPlayers.add(new PlayerInfoTest(40L, "Аттирис", "и.о.Карвандоса", Race.ELF, Profession.SORCERER, sdf.parse("2010-04-15").getTime(), true, 60520, 34, 2480));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public List<PlayerInfoTest> getAllPlayers() {
        return allPlayers;
    }


    public List<PlayerInfoTest> getPlayerInfosByName(String name, List<PlayerInfoTest> players) {
        List<PlayerInfoTest> result = new ArrayList<>();
        for (PlayerInfoTest player : players) {
            if (player.name.contains(name)) {
                result.add(player);
            }
        }
        return result;
    }

    public List<PlayerInfoTest> getPlayerInfosByTitle(String title, List<PlayerInfoTest> players) {
        List<PlayerInfoTest> result = new ArrayList<>();
        for (PlayerInfoTest player : players) {
            if (player.title.contains(title)) {
                result.add(player);
            }
        }
        return result;
    }

    public List<PlayerInfoTest> getPlayerInfosByRace(Race race, List<PlayerInfoTest> players) {
        List<PlayerInfoTest> result = new ArrayList<>();
        for (PlayerInfoTest player : players) {
            if (player.race == race) {
                result.add(player);
            }
        }
        return result;
    }

    public List<PlayerInfoTest> getPlayerInfosByProfession(Profession profession, List<PlayerInfoTest> players) {
        List<PlayerInfoTest> result = new ArrayList<>();
        for (PlayerInfoTest player : players) {
            if (player.profession == profession) {
                result.add(player);
            }
        }
        return result;
    }

    public List<PlayerInfoTest> getPlayerInfosByAfter(Long after, List<PlayerInfoTest> players) {
        List<PlayerInfoTest> result = new ArrayList<>();
        for (PlayerInfoTest player : players) {
            if (player.birthday >= after) {
                result.add(player);
            }
        }
        return result;
    }

    public List<PlayerInfoTest> getPlayerInfosByBefore(Long before, List<PlayerInfoTest> players) {
        List<PlayerInfoTest> result = new ArrayList<>();
        for (PlayerInfoTest player : players) {
            if (player.birthday <= before) {
                result.add(player);
            }
        }
        return result;
    }

    public List<PlayerInfoTest> getPlayerInfosByBaned(Boolean Banned, List<PlayerInfoTest> players) {
        List<PlayerInfoTest> result = new ArrayList<>();
        for (PlayerInfoTest player : players) {
            if (player.banned == Banned) {
                result.add(player);
            }
        }
        return result;
    }

    public List<PlayerInfoTest> getPlayerInfosByMinExperience(Integer minExperience, List<PlayerInfoTest> players) {
        List<PlayerInfoTest> result = new ArrayList<>();
        for (PlayerInfoTest player : players) {
            if (player.experience >= minExperience) {
                result.add(player);
            }
        }
        return result;
    }

    public List<PlayerInfoTest> getPlayerInfosByMaxExperience(Integer maxExperience, List<PlayerInfoTest> players) {
        List<PlayerInfoTest> result = new ArrayList<>();
        for (PlayerInfoTest player : players) {
            if (player.experience <= maxExperience) {
                result.add(player);
            }
        }
        return result;
    }

    public List<PlayerInfoTest> getPlayerInfosByMinLevel(Integer minLevel, List<PlayerInfoTest> players) {
        List<PlayerInfoTest> result = new ArrayList<>();
        for (PlayerInfoTest player : players) {
            if (player.level >= minLevel) {
                result.add(player);
            }
        }
        return result;
    }

    public List<PlayerInfoTest> getPlayerInfosByMaxLevel(Integer maxLevel, List<PlayerInfoTest> players) {
        List<PlayerInfoTest> result = new ArrayList<>();
        for (PlayerInfoTest player : players) {
            if (player.level <= maxLevel) {
                result.add(player);
            }
        }
        return result;
    }

    public List<PlayerInfoTest> getPlayerInfosByPage(Integer pageNumber, Integer pageSize, List<PlayerInfoTest> players) {
        int skip = pageNumber * pageSize;
        List<PlayerInfoTest> result = new ArrayList<>();
        for (int i = skip; i < Math.min(skip + pageSize, players.size()); i++) {
            result.add(players.get(i));
        }
        return result;
    }

    public PlayerInfoTest getPlayerInfosById(long id) {
        return allPlayers.stream().filter(s -> s.id == id).findFirst().orElse(null);
    }
}