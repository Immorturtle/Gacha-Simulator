package model;


import org.json.JSONObject;
import persistence.Writeable;

import javax.swing.*;

//Creates a character card with different stats.
public class Character implements Writeable {
    private String name;
    private int atk;
    private int def;
    private int hp;
    private int level;
    private String rarity;
    private String description;
    private int id;
    private String image;

    //EFFECTS: Constructs a character with given parameters
    public Character(String name, int atk, int def, int hp, String rarity, String description, int id,
                     String image) {
        this.name = name;
        this.atk = atk;
        this.def = def;
        this.hp = hp;
        this.level = 1;
        this.rarity = rarity;
        this.description = description;
        this.id = id;
        this.image = image;
    }

    //EFFECTS: Constructs an empty character.
    public Character() {
        this.name = " ";
        this.atk = 0;
        this.def = 0;
        this.hp = 0;
        this.level = 0;
        this.rarity = " ";
        this.description = " ";
        this.id = 0;
        this.image = null;
    }

    //Getters
    public String getName() {
        return this.name;
    }

    public int getATK() {
        return this.atk;
    }

    public int getDEF() {
        return this.def;
    }

    public int getHP() {
        return this.hp;
    }

    public int getLevel() {
        return this.level;
    }

    public String getRarity() {
        return this.rarity;
    }

    public String getDescription() {
        return this.description;
    }

    public String getImage() {
        return this.image;
    }

    public int getId() {
        return this.id;
    }

    public void setLevel(int level) {
        this.level = level;
    }






    @Override
    //EFFECTS: writes Character to JSON File
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("ATK", atk);
        json.put("DEF", def);
        json.put("HP", hp);
        json.put("Level", level);
        json.put("Rarity",rarity);
        json.put("Description",description);
        json.put("ID",id);
        json.put("Image",image);
        return json;
    }



}




