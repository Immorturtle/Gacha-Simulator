package model;


import exceptions.InsufficientValueException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writeable;

import java.util.ArrayList;
import java.util.List;

//Player stores data that are specific to the user. As Such, this class will be used to "save" data.
public class Player implements Statistic, Writeable {
    private int userID;
    private List<Character> box;
    private int cred;
    private int spent;


    //EFFECTS: Constructs a userID, empty Box, zero currency, and zero spend
    public Player(int userID) {
        this.userID = userID;
        this.box = new ArrayList<>();
        cred = 0;
        spent = 0;
    }

    //Getters
    public int getUserID() {
        return this.userID;
    }

    public List<Character> getBox() {
        return this.box;
    }

    public int getSpent() {
        return this.spent;
    }

    public int getCred() {
        return this.cred;
    }

    public void setCred(int cred) {
        this.cred = cred;
    }

    public void setSpent(int spent) {
        this.spent = spent;
    }


    //REQUIRES: val > 0
    //MODIFIES: this
    //EFFECTS: adds value to cred
    public void addCred(int val) {
        cred += val;
    }

    //MODIFIES: this
    //EFFECTS: adds the character to box
    public void store(Character character) {
        box.add(character);
    }

    //MODIFIES: this
    //EFFECTS: adds a collection of characters to box
    public void store(List<Character> characters) {
        box.addAll(characters);
    }

    //EFFECTS: returns SSR in box
    public List<Character> getUltraRareList() {
        List<Character> urList = new ArrayList<>();
        for (Character c : box) {
            if (c.getRarity().equals("UR")) {
                urList.add(c);
            }
        }
        return urList;
    }

    //Grab a list of cards that are in the rarity of SR
    //EFFECTS: returns a list of SR cards from Pool
    public List<Character> getSuperRareList() {
        List<Character> srList = new ArrayList<>();
        for (Character c : box) {
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
        for (Character c : box) {
            if (c.getRarity().equals("R")) {
                rareList.add(c);
            }
        }
        return rareList;
    }

    //REQUIRES: pos in index of box
    //Displays the stat of the specified position of the box.
    public String displayStatsAsString(int pos) {
        Character c;
        c = box.get(pos - 1);
        String convertStats = "Name: " + c.getName() + "\nATK: " + c.getATK()
                + "\nDEF: " + c.getDEF() + "\nHP: " + c.getHP()
                + "\nLevel: " + c.getLevel() + "\nRarity: "
                + c.getRarity() + "\nDescription: " + c.getDescription();

        return convertStats;
    }


    //Removes specified
    //MODIFIES: this
    //EFFECTS: If cred is sufficient, deduct cred from val, record cred spent, and return true, otherwise return false.
    public boolean pay(int val) throws InsufficientValueException {
        if (cred >= val) {
            cred -= val;
            spent += val;
            return true;
        } else {
            throw new InsufficientValueException();
        }
    }

    //EFFECTS: return the size of the pool
    public int numberOfCard() {
        return box.size();
    }

    //REQUIRES: pos > 0
    //EFFECTS: If box.size() is >= pos - 1, return true, otherwise return false
    public boolean checkIDInList(int pos) {
        if (box.size() > pos - 1) {
            return true;
        }
        return false;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("userID", userID);
        json.put("box", boxToJson());
        json.put("cred", cred);
        json.put("spent", spent);
        return json;
    }

    private JSONArray boxToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Character c : box) {
            jsonArray.put(c.toJson());
        }
        return jsonArray;
    }
    

}
