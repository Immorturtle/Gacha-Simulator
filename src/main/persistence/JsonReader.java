package persistence;

import model.Player;
import model.Character;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

//Class that saves data from model package. This source code is referenced by JsonSerializationDemo.
public class JsonReader {
    private String source;

    //This source code is referenced by JsonSerializationDemo.
    //EFFECTS: constructs a reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    //This source code is referenced by JsonSerializationDemo.
    //EFFECTS: reads player from file and returns it.
    //throws IOException if an error occurs while reading the file
    public Player read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePlayer(jsonObject);
    }

    //This source code is referenced by JsonSerializationDemo.
    //EFFECTS: reads source file as string and returns it as a string
    public String readFile(String source) throws IOException {
        StringBuilder builder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> builder.append(s));
        }
        return builder.toString();
    }

    //This source code is referenced by JsonSerializationDemo.
    //EFFECTS: parses player from JSON object and returns it
    private Player parsePlayer(JSONObject jsonObject) {
        int id = jsonObject.getInt("userID");
        Player player = new Player(id);
        addBox(player, jsonObject);
        addCred(player, jsonObject);
        addSpent(player,jsonObject);
        return player;
    }

    //This source code is referenced by JsonSerializationDemo.
    //MODIFIES: player
    //EFFECTS: parses box from JSON object and adds them to Player
    private void addBox(Player player, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("box");
        for (Object json : jsonArray) {
            JSONObject nextCharacter = (JSONObject) json;
            addCharacter(player, nextCharacter);
        }
    }

    //This source code is referenced by JsonSerializationDemo.
    ///MODIFIES: player
    //EFFECTS: parses character from JSON object and adds it to player
    private void addCharacter(Player player, JSONObject jsonObject) {

        String name = jsonObject.getString("name");
        int atk = jsonObject.getInt("ATK");
        int def = jsonObject.getInt("DEF");
        int hp = jsonObject.getInt("HP");
        int level = jsonObject.getInt("Level");
        String rarity = jsonObject.getString("Rarity");
        String description = jsonObject.getString("Description");
        int id = jsonObject.getInt("ID");
        String image = jsonObject.getString("Image");

        Character character = new Character(name, atk,def, hp, rarity, description, id, image);
        character.setLevel(level);
        player.store(character);
    }

    //This source code is referenced by JsonSerializationDemo.
    //MODIFIES: player
    //EFFECTS: parses cred from JSON object and adds it to player
    private void addCred(Player player, JSONObject jsonObject) {
        int cred = jsonObject.getInt("cred");
        player.setCred(cred);
    }

    //This source code is referenced by JsonSerializationDemo.
    //MODIFIES: player
    //EFFECTS: parses spent from JSON object and adds it to player
    private void addSpent(Player player, JSONObject jsonObject) {
        int spent = jsonObject.getInt("spent");
        player.setSpent(spent);
    }


}
