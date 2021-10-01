package model;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

//Class that creates all the pre-determined characters.
abstract class DataBase {

    private List<Character> units;

    private static List<Character> addUR() {
        List<Character> listOfUltraRare = new ArrayList<>();
        listOfUltraRare.add(new Character("Lucian", 2350, 1705, 2455, "UR",
                " Fires a laser in the direction of the target enemy, dealing physical damage", 1,
                "./data/images/Lucian.jpg"));
        listOfUltraRare.add(new Character("Joker", 2225, 1560, 2510, "UR", "Summons Arsene", 2,
                "./data/images/Joker.png"));
        listOfUltraRare.add(new Character("Hero", 2500, 1510, 2350, "UR", "Has a 12% chance to One-Shot", 3,
                "./data/images/Hero.png"));
        listOfUltraRare.add(new Character("Kevin", 2349, 1650, 2221, "UR", "Human Bullet Tank no Jutsu", 4,
                "./data/images/Kevin.jpg"));
        listOfUltraRare.add(new Character("Goku", 2459, 1459, 2059, "UR",
                "Transform into a super saiyan when conditions are met", 5, "./data/images/Goku.png"));
        listOfUltraRare.add(new Character("Omar", 2000, 1400, 1600, "UR", "I'm sorry you pulled him", 6,
                "./data/images/Omar.png"));
        return listOfUltraRare;
    }

    private static List<Character> addSR() {
        List<Character> listOfSuperRare = new ArrayList<>();
        listOfSuperRare.add(new Character("Sett", 2101, 1410, 1989, "SR",
                "Punches enemy with enormous strength", 7, "./data/images/Sett.jpg"));
        listOfSuperRare.add(new Character("Zilean", 1589, 1545, 1954, "SR",
                "Ability to revive allies when endangered", 8,
                "./data/images/Zilean.jpg"));
        listOfSuperRare.add(new Character("Sage", 1300, 1400, 1984, "SR",
                "Heals allies every two turns", 9, "./data/images/Sage.jpg"));
        listOfSuperRare.add(new Character("Steve", 1650, 1200, 2100, "SR",
                "Can build using resources in the battlefield", 10, "./data/images/Steve.png"));
        listOfSuperRare.add(new Character("Arceus", 1799, 1333, 1877, "SR",
                "Convert physical damage to magical damage", 11,
                "./data/images/Arceus.png"));
        listOfSuperRare.add(new Character("Shyvana", 1600, 1343, 1871, "SR",
                "Transform into dragon when HP is 30% or less", 12, "./data/images/Shyvana.jpg"));
        return listOfSuperRare;
    }

    private static List<Character> addR() {
        List<Character> listOfRare = new ArrayList<>();
        listOfRare.add(new Character("Impostor", 999, 1100, 1410, "R", "Can disguise as another character", 13,
                "./data/images/Impostor.png"));
        listOfRare.add(new Character("Sakura", 250, 800, 1000, "R", "Thinks she is equal to Naruto and Sasuke", 14,
                "./data/images/Sakura.png"));
        listOfRare.add(new Character("Tahm Kench", 750, 1250, 2200, "R", "Reduces damage and gains extra HP", 15,
                "./data/images/Tahm Kench.jpg"));
        listOfRare.add(new Character("Barbara", 750, 900, 1500, "R", "Creates a aura around allies healing them", 16,
                "./data/images/Barbara.png"));
        listOfRare.add(new Character("Tachanka", 800, 1300, 1900, "R", "LMG MOUNTED AND LOADED", 17,
                "./data/images/Tachanka.jpg"));
        listOfRare.add(new Character("Luden", 700, 1200, 1950, "R", "A spirit that hates the Lion King", 18,
                "./data/images/Luden.jpg"));
        listOfRare.add(new Character("Seagull", 700, 1000, 1650, "R", "No special abilities", 19,
                "./data/images/Seagull.png"));
        listOfRare.add(new Character("Miller", 932, 1200, 1320, "R", "Extremely intelligent", 20,
                "./data/images/Miller.jpg"));
        return listOfRare;
    }

    //EFFECT: constructs pre-determined cards and adds them into the list.
    public DataBase() {
        units = new ArrayList<>();
        units.addAll(addUR());
        units.addAll(addSR());
        units.addAll(addR());
    }


    //Getter
    public List<Character> characterLists() {
        return this.units;
    }
}
