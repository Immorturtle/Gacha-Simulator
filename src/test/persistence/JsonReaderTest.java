package persistence;

import model.Player;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Player player = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyPlayer() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyPlayer.json");
        try {
            Player player = reader.read();
            assertEquals(123456,player.getUserID());
            assertEquals(0,player.getBox().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReader() {
        JsonReader reader = new JsonReader("./data/testReader.json");
        try {
            Player player = reader.read();
            assertEquals(123456,player.getUserID());
            assertEquals(1,player.getBox().size());
            assertEquals(5,player.getSpent());
            assertEquals(5,player.getCred());
        } catch(IOException e) {
            fail("Should not catch IOException");
        }
    }
}
