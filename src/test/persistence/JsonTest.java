package persistence;

import model.Character;
import model.Player;

import static org.junit.jupiter.api.Assertions.*;

public class JsonTest {
    protected void checkCharacter(Character c ,String name, int atk, int def, int hp, String rarity, String description, int id) {
        assertEquals(name, c.getName());
        assertEquals(atk, c.getATK());
        assertEquals(def, c.getDEF());
        assertEquals(hp, c.getHP());
        assertEquals(rarity, c.getRarity());
        assertEquals(description, c.getDescription());
        assertEquals(id, c.getId());

    }
}
