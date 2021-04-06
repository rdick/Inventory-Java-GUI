package model;

import exceptions.ExcepNegNum;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Test write Json Inventory from File
public class JsonWriterTest extends JsonTest {

    // EFFECT: Test write to invalid file
    @Test
    void testWriterInvalidFile() {
        try {
            Inventory inv = new Inventory();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    // EFFECT: Test write to empty inventory
    @Test
    void testWriterEmptyInventory() {
        try {
            Inventory inv = new Inventory();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyInventory.json");
            writer.open();
            writer.write(inv);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyInventory.json");
            inv = reader.read();
            assertEquals(0, inv.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    // EFFECT: Test write to general inventory and then load it
    @Test
    void testWriterGeneralInventory() {
        try {
            Inventory inv = new Inventory();
            try {
                inv.addItem("item1",10,10,"high quality");
            } catch (ExcepNegNum excepNegNum) {
                excepNegNum.printStackTrace();
            }
            try {
                inv.addItem("item2",20,20,"high quality");
            } catch (ExcepNegNum excepNegNum) {
                excepNegNum.printStackTrace();
            }
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralInventory.json");
            writer.open();
            writer.write(inv);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralInventory.json");
            inv = reader.read();
            List<Item> thingies = inv.getAllItems();
            assertEquals(2, thingies.size());
            checkItem("item1",10,10,"high quality", thingies.get(0));
            checkItem("item2",20,20,"high quality", thingies.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
