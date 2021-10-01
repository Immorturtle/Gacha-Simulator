package model;

import exceptions.InsufficientValueException;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class PlayerTest {

    @Test
    void testStoreRarityList() {
        Player user = new Player(65498452);
        assertEquals(65498452, user.getUserID());
        Character unit = new Character("Lucian",300,200, 1000,"UR",
                "test",0, "./data/images/Lucian.jpg");
        assertEquals(0,user.getSuperRareList().size());
        Character unit2 = new Character("L",300,200, 1000,"SR",
                "test1,",1, "./data/images/Lucian.jpg");
        Character unit3 = new Character("L",300,200, 1000,"R",
                "test2,",2, "./data/images/Lucian.jpg");;
        assertEquals(0,user.getRareList().size());
        assertEquals(0,user.getSuperRareList().size());
        assertEquals(0,user.getUltraRareList().size());
        user.store(unit);
        user.store(unit2);
        user.store(unit3);
        assertEquals(1,user.getRareList().size());
        assertEquals(1,user.getSuperRareList().size());
        assertEquals(1,user.getUltraRareList().size());

    }

    @Test
    void testStoreMultipleCharacters() {
        Player user = new Player(23475678);
        List<Character> arrayOfCharacters = new ArrayList<>();
        Character unit = new Character("Lucian",300,200, 1000,"UR",
                "Lucian fires a laser in the direction of the target enemy,",0, "./data/images/Lucian.jpg");
        Character unit2 = new Character("L",300,200, 1000,"R",
                "Lucian fires a laser in the direction of the target enemy,",1, "./data/images/Lucian.jpg");
        arrayOfCharacters.add(unit);
        arrayOfCharacters.add(unit2);
        user.store(arrayOfCharacters);
        assertEquals(2, user.getBox().size());
        assertTrue(user.checkIDInList(1));
        assertFalse(user.checkIDInList(3));
    }

    @Test
    void testNumberOfCard() {
        Player user = new Player(123456789);
        Character unit = new Character("test", 1, 1, 1, "UR","test",3, "./data/images/Lucian.jpg");
        for (int i = 0; i < 10; i++) {
            user.store(unit);
        }
        assertEquals(10, user.numberOfCard());
    }

    @Test
    void testDisplayStats() {
        Player user = new Player(123456789);
        Character unit = new Character("test",1, 1, 1, "UR","test",3, "./data/images/Lucian.jpg");
        String stats = "Name: " + "test" + "\nATK: " + "1"
                +"\nDEF: " + "1" + "\nHP: " + "1"
                + "\nLevel: " + "1" + "\nRarity: "
                + "UR" + "\nDescription: " + "test";
        user.store(unit);
        assertEquals(stats, user.displayStatsAsString(1));
    }

    @Test
    void testCurrencySystem() {
        Player user = new Player(123456789);
        user.addCred(50);
        assertEquals(50, user.getCred());
        try {
            user.pay(50);
            //pass
        }  catch (InsufficientValueException e) {
            fail("Exception should not have been throw");
        }
        try {
            user.pay(50);
            fail("Exception should not have been throw");
        }  catch (InsufficientValueException e) {
            //pass
        }
        assertEquals(50, user.getSpent());
    }

}
