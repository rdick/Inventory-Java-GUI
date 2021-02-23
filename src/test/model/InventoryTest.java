package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

// Tests the Inventory Class of Code
class InventoryTests {
    Inventory newInventory = new Inventory();

    @Test
    public void testAddItemIncorrect(){
        newInventory.addItem("item1",-2,20,"high quality");
        newInventory.addItem("item1",0,-20,"high quality");
    }

    @Test
    public void testAllItemInfo(){
        assertEquals("No items available",newInventory.allItemInfo());

        newInventory.addItem("item1",5,20,"high quality");
        Item item1 = newInventory.getItemByName("item1");
        String item1ID = Integer.toString(item1.getId());

        assertEquals("[id = " + item1ID + " name: item1 quantity: 5 Cost/Unit: " +
                        "$20 details: high quality] \n",
                newInventory.allItemInfo());

        newInventory.addItem("item2",10,5,"low quality");
        Item item2 = newInventory.getItemByName("item2");
        String item2ID = Integer.toString(item2.getId());

        assertEquals("[id = " + item1ID + " name: item1 quantity: 5 Cost/Unit: $20 details: high quality] \n" +
                        "[id = " + item2ID + " name: item2 quantity: 10 Cost/Unit: $5 details: low quality] \n",
                newInventory.allItemInfo());
    }

    @Test
    public void testAddItem() {
        newInventory.addItem("item1",5,20,"high quality");
        assertEquals(1,newInventory.size());
        newInventory.addItem("item2",10,5,"low quality");
        assertEquals(2,newInventory.size());
    }



    @Test
    public void testChangeQuantity() {
        assertFalse(newInventory.changeInventoryQuantity("item1",-10));
        assertFalse(newInventory.changeInventoryQuantity("item1",10));

        newInventory.addItem("item1",5,20,"high quality");
        assertFalse(newInventory.changeInventoryQuantity("item1",-6));
        assertFalse(newInventory.changeInventoryQuantity("item1",-6));
        assertTrue(newInventory.changeInventoryQuantity("item1",-4));
        assertTrue(newInventory.changeInventoryQuantity("item1",0));
        assertTrue(newInventory.changeInventoryQuantity("item1",10));
    }

    @Test
    public void testTotalValue(){
        assertEquals(0,newInventory.totalValue());
        newInventory.addItem("item1",5,20,"high quality");
        assertEquals(100,newInventory.totalValue());
        newInventory.addItem("item2",10,5,"low quality");
        assertEquals(150,newInventory.totalValue());
    }
}