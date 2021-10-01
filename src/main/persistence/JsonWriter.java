package persistence;

import model.Player;
import org.json.JSONObject;

import java.io.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import static java.awt.Event.TAB;

//Reads Json file and loads the file.
//All the portion of the code is inspired by JsonSerializationDemo
public class JsonWriter {

    private PrintWriter writer;
    private String destination;

    //This source code is referenced by JsonSerializationDemo.
    //EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    //This source code is referenced by JsonSerializationDemo.
    //MODIFIES: this
    //EFFECTS: opens writer; throws FilesNotFoundException if destination file cannot
    //be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    //This source code is referenced by JsonSerializationDemo.
    //MODIFIES: this
    //EFFECTS: writes JSON representation of player to file
    public void write(Player player) {
        JSONObject json = player.toJson();
        saveToFile(json.toString(TAB));
    }

    //This source code is referenced by JsonSerializationDemo.
    //EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    //This source code is referenced by JsonSerializationDemo.
    //MODIFIES: this
    //EFFECTS: writes string to file
    public void saveToFile(String json) {
        writer.print(json);
    }

}
