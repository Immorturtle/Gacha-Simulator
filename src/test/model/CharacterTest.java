package model;


import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {

    @Test
    void testCharacterWithStats() {
        Character unit = new Character("test", 1, 1, 1, "UR","test",3,
                "./data/images/Lucian.jpg");
        assertEquals("test",unit.getName());
        assertEquals(1,unit.getATK());
        assertEquals(1,unit.getDEF());
        assertEquals(1,unit.getHP());
        assertEquals("UR",unit.getRarity());
        assertEquals("test", unit.getDescription());
        assertEquals(3,unit.getId());
        assertEquals(1, unit.getLevel());
        assertEquals("./data/images/Lucian.jpg",unit.getImage());
    }
    @Test
    void testCharacterWithNoStats() {
        Character unit = new Character();
        assertEquals(" ",unit.getName());
        assertEquals(0,unit.getATK());
        assertEquals(0,unit.getDEF());
        assertEquals(0,unit.getHP());
        assertEquals(" ",unit.getRarity());
        assertEquals(" ", unit.getDescription());
        assertEquals(0,unit.getId());
        assertEquals(0, unit.getLevel());
    }

}
