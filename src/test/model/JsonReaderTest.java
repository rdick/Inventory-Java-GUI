package model;

import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Read Json Inventory from File
public class JsonReaderTest extends JsonTest{

    // EFFECT: Test reader to non existent file
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Inventory inv = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    // EFFECT: Test reader to empty inventory
    @Test
    void testReaderEmptyInventory() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyInventory.json");
        try {
            Inventory inv = reader.read();
//            assertEquals("My work room", inv.getName());
            assertEquals(0, inv.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    // EFFECT: Test reader of already filled inventory
    @Test
    void testReaderGeneralInventory() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralInventory.json");
        try {
            Inventory inv = reader.read();
            List<Item> items = inv.getAllItems();
            checkItem("Jimmy",5, 10,"funny dog", items.get(0));
            checkItem("Johnny", 8,8,"asf;jasdf", items.get(1));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
