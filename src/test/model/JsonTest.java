package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Check Item to see if it is equal to values passed
public class JsonTest {
    protected void checkItem(String name, int quantity, int costPerUnit, String details, Item item) {
        assertEquals(name, item.getName());
        assertEquals(quantity, item.getQuantity());
        assertEquals(costPerUnit, item.getCostPerUnit());
        assertEquals(details, item.getDetails());
    }
}
