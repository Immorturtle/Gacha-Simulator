package persistence;

import model.Player;
import model.Character;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

//Test case is inspired by JsonSerializationDemo
public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Player player = new Player(123456);
            JsonWriter writer = new JsonWriter(".data/notarealfile.json");
            writer.open();
            fail("IOException is expected");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    void testWriterPlayerWithOnlyID() {
        Player p = new Player(115456);
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterPlayerWithID.json");
            writer.open();
            writer.write(p);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterPlayerWithID.json");
            p = reader.read();
            assertEquals(115456, p.getUserID());
            assertEquals(0, p.getCred());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


    @Test
    void testWriterPlayer() {
        Player player = new Player(115456);
        player.setCred(50);
        player.setSpent(100);
        Character c = new Character("joe", 100, 200, 300, "SR", "test Description", 3,
                "./data/images/Lucian.jpg" );
        player.store(c);
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterPlayer.json");
            writer.open();
            writer.write(player);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterPlayer.json");
            player = reader.read();
            assertEquals(115456, player.getUserID());
            assertEquals(50, player.getCred());
            assertEquals(100,player.getSpent());
            checkCharacter(c,"joe", 100, 200, 300, "SR", "test Description", 3 );
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
