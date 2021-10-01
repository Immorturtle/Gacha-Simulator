package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BannerTest {
    Banner pool;

    @BeforeEach
    void createPool() {
        pool = new Banner();
    }

    @Test
    void poolCreation() {
        List<Character> testMethod;
        assertEquals(20, pool.numberOfCard());
        testMethod = pool.getPool();
        assertEquals(20, testMethod.size());
    }

    @Test
    void testGetRarityList() {
        List<Character> rarityList;
        rarityList = pool.getRareList();
        assertEquals(8, rarityList.size());
        rarityList = pool.getSuperRareList();
        assertEquals(6, rarityList.size());
        rarityList = pool.getUltraRareList();
        assertEquals(6,rarityList.size());
    }

    @Test
    void testDisplayStats() {
        String stats = "Name: " + "Seagull" + "\nATK: " +   "700"
                + "\nDEF: " + "1000" + "\nHP: " + "1650"
                + "\nLevel: " + "1" + "\nRarity: "
                + "R" + "\nDescription: " + "No special abilities";
        assertEquals(stats,pool.displayStatsAsString(19));
        stats = "ID is not found";
        assertEquals(stats, pool.displayStatsAsString(21));
    }

    @Test
    void testSingleSummon() {
        Player user = new Player(123456789);
        for (int i = 0; i < 1000; i++) { //Used this so I could get 100% code coverage
            user.store(pool.singleSummon());
        }
        assertEquals(1000, user.getBox().size());
    }

    @Test
    void testMultiSummon() {
        Player user = new Player(123456789);
        for (int i = 0; i < 3000; i++) { //used this so I could get 100% code coverage
            user.store(pool.multiSummon());
        }
        assertEquals(30000, user.getBox().size());
    }
    @Test
    void testCheckIdInList() {
        assertTrue(pool.checkIDInList(20));
        assertFalse(pool.checkIDInList(21));
    }

}
