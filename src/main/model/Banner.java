package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//Class that will have a predetermined set of characters where each characters will be chosen randomly
// by using the random class provided by Java
public class Banner extends BannerCreation implements Statistic {
    private static final int UR_RATE = 5;
    private static final int SR_RATE = 25;
    private static final int R_RATE = 70;
    private static final int MULTI_SUMMON_CARDS = 10;
    private List<Character> pool;


    //constructs a list with predetermined cards
    //EFFECTS: constructs a list of pre-determined characters
    public Banner() {
        pool = returnPool();
    }

    //Getter
    public List<Character> getPool() {
        return this.pool;
    }

    //Getter
    public int numberOfCard() {
        return pool.size();
    }


    //EFFECTS: Checks if the id is present in pool. If the id is valid,
    // then return the stats of the first occurring card in box.
    public String displayStatsAsString(int id) {
        String convertStats = "ID is not found";
        for (Character c : pool) {
            if (c.getId() == id) {
                convertStats = "Name: " + c.getName() + "\nATK: " +   c.getATK()
                        + "\nDEF: " + c.getDEF() + "\nHP: " + c.getHP()
                        + "\nLevel: " + c.getLevel() + "\nRarity: "
                        + c.getRarity() + "\nDescription: " + c.getDescription();
            }
        }
        return convertStats;
    }

    //Grab a list of cards that are in the rarity of SSR
    //EFFECTS: returns a list of SSR cards from Pool
    public List<Character> getUltraRareList() {
        List<Character> ssrList = new ArrayList<>();
        for (Character c : pool) {
            if (c.getRarity().equals("UR")) {
                ssrList.add(c);
            }
        }
        return ssrList;
    }

    //Grab a list of cards that are in the rarity of SR
    //EFFECTS: returns a list of SR cards from Pool
    public List<Character> getSuperRareList() {
        List<Character> srList = new ArrayList<>();
        for (Character c : pool) {
            if (c.getRarity().equals("SR")) {
                srList.add(c);
            }
        }
        return srList;
    }

    //Grab a list of cards that are in the rarity of R
    //EFFECTS: returns a list of R cards from Pool
    public List<Character> getRareList() {
        List<Character> rareList = new ArrayList<>();
        for (Character c : pool) {
            if (c.getRarity().equals("R")) {
                rareList.add(c);
            }
        }
        return rareList;
    }

    //Determines which rarity will be revealed first.
    //Used Class Random with function list from https://docs.oracle.com/javase/8/docs/api/java/util/Random.html
    //EFFECTS: creates a list of String that
    private String determineRarity() {
        List<String> probability = new ArrayList<>();
        Random rand = new Random();
        String givenRarity;
        for (int i = 0; i < UR_RATE; i++) {
            probability.add("UR");
        }
        for (int y = 0; y < SR_RATE; y++) {
            probability.add("SR");
        }
        for (int z = 0; z < R_RATE; z++) {
            probability.add("R");
        }
        int randIndex = rand.nextInt(probability.size());
        givenRarity = probability.get(randIndex);
        return givenRarity;
    }


    //Used Class Random with function list from https://docs.oracle.com/javase/8/docs/api/java/util/Random.html
    //EFFECTS: Chooses a random rarity, then calls a list of characters with that rarity and
    // then returns a random character from the list.
    public Character singleSummon() {
        List<Character> currentList;
        String rarity = determineRarity();
        Random random = new Random();
        Character pulledUnit = new Character();
        int index;
        if (rarity.equals("UR")) {
            currentList = getUltraRareList();
            index = random.nextInt(currentList.size());
            pulledUnit = currentList.get(index);
        }
        if (rarity.equals("SR")) {
            currentList = getSuperRareList();
            index = random.nextInt(currentList.size());
            pulledUnit = currentList.get(index);
        }
        if (rarity.equals("R")) {
            currentList = getRareList();
            index = random.nextInt(currentList.size());
            pulledUnit = currentList.get(index);
        }
        return pulledUnit;
    }

    //EFFECTS: Chooses a 10 times random rarity, then calls a list of characters with that rarity and
    // then adds a random character to a list. Returns the list of characters.
    public List<Character> multiSummon() {
        List<Character> currentList;
        Random random = new Random();
        List<Character> pulledUnits = new ArrayList<>();

        for (int i = 0; i < MULTI_SUMMON_CARDS; i++) {
            String rarity = determineRarity();
            if (rarity.equals("UR")) {
                currentList = getUltraRareList();
                int index = random.nextInt(currentList.size());
                pulledUnits.add(currentList.get(index));
            }
            if (rarity.equals("SR")) {
                currentList = getSuperRareList();
                int index = random.nextInt(currentList.size());
                pulledUnits.add(currentList.get(index));
            }
            if (rarity.equals("R")) {
                currentList = getRareList();
                int index = random.nextInt(currentList.size());
                pulledUnits.add(currentList.get(index));
            }
        }
        return pulledUnits;
    }

    //EFFECTS: returns true if id is in the list, otherwise false.
    public boolean checkIDInList(int id) {
        for (Character c : pool) {
            if (c.getId() == id) {
                return true;
            }
        }
        return false;
    }
}

