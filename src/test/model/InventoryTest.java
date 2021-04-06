package model;

import exceptions.ExcepNegNum;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Tests the Inventory Class of Code
class InventoryTest {
    Inventory newInventory = new Inventory();

    // EFFECT: Test add incorrect item
    @Test
    public void testAddItemIncorrect(){
        try {
            newInventory.addItem("item1",-2,20,"high quality");
            fail("can't add item incorrectly");
        } catch (ExcepNegNum excepNegNum) {
            // expected
        }

        try {
            newInventory.addItem("item1",0,-20,"high quality");
            fail("can't add item incorrectly");
        } catch (ExcepNegNum excepNegNum) {
            // expected
        }
    }

    // EFFECT: Test all item info
    @Test
    public void testAllItemInfo(){
        assertEquals("No items available",newInventory.allItemInfo());

        try {
            newInventory.addItem("item1",5,20,"high quality");
        } catch (ExcepNegNum excepNegNum) {
            fail("Something wrong with program");
        }
        Item item1 = newInventory.getItemByName("item1");
        String item1ID = Integer.toString(item1.getId());

        assertEquals("[id = " + item1ID + " name: item1 quantity: 5 Cost/Unit: " +
                        "$20 details: high quality] \n",
                newInventory.allItemInfo());

        try {
            newInventory.addItem("item2",10,5,"low quality");
        } catch (ExcepNegNum excepNegNum) {
            fail("Something wrong with program");
        }
        Item item2 = newInventory.getItemByName("item2");
        String item2ID = Integer.toString(item2.getId());

        assertEquals("[id = " + item1ID + " name: item1 quantity: 5 Cost/Unit: $20 details: high quality] \n" +
                        "[id = " + item2ID + " name: item2 quantity: 10 Cost/Unit: $5 details: low quality] \n",
                newInventory.allItemInfo());
    }

    // EFFECT: Test add item
    @Test
    public void testAddItem() {
        try {
            newInventory.addItem("item1",5,20,"high quality");
            assertEquals(1,newInventory.size());
        } catch (ExcepNegNum excepNegNum) {
            fail("Something wrong with program");
        }

        try {
            newInventory.addItem("item2",10,5,"low quality");
            assertEquals(2,newInventory.size());
        } catch (ExcepNegNum excepNegNum) {
            fail("Something wrong with program");
        }

    }

    // EFFECT: Test change quantity of item
    @Test
    public void testChangeQuantity() {
        try {
            assertEquals(newInventory.changeInventoryQuantity("item1",10), false);
        } catch (ExcepNegNum excepNegNum) {
            // expected
        }

        try {
            newInventory.addItem("item1",5,20,"high quality");
            newInventory.changeInventoryQuantity("item1",-10);
            fail("Program should not be able to reduce quantity that much");
        } catch (ExcepNegNum excepNegNum) {
            // expected
        }
        try {
            assertTrue(newInventory.changeInventoryQuantity("item1",10));
        } catch (ExcepNegNum excepNegNum) {
            fail("Program should not have error");
        }
    }

    // EFFECT: Test total value of inventory
    @Test
    public void testTotalValue(){
        assertEquals(0,newInventory.totalValue());
        try {
            newInventory.addItem("item1",5,20,"high quality");
            assertEquals(100,newInventory.totalValue());
        } catch (ExcepNegNum excepNegNum) {
            fail("there should be no error");
        }

        try {
            newInventory.addItem("item2",10,5,"low quality");
            assertEquals(150,newInventory.totalValue());

        } catch (ExcepNegNum excepNegNum) {
            fail("there should be no error");
        }
    }

    @Test
    public void robustClass(){

        // Change Quantity INCORRECTLY
        try {
            newInventory.addItem("item1",5,20,"high quality");
            newInventory.changeInventoryQuantity("item1",-10);
            fail("Program should not be able to reduce quantity that much");
        } catch (ExcepNegNum excepNegNum) {
            // expected
        }

        // Change Quantity CORRECTLY
        try {
            assertTrue(newInventory.changeInventoryQuantity("item1",10));
        } catch (ExcepNegNum excepNegNum) {
            fail("Program should not have error");
        }


        // ADD ITEM INCORRECTLY
        try {
            newInventory.addItem("item2",-2,20,"high quality");
            fail("can't add item incorrectly");
        } catch (ExcepNegNum excepNegNum) {
            // expected
        }

        try {
            newInventory.addItem("item3",0,-20,"high quality");
            fail("can't add item incorrectly");
        } catch (ExcepNegNum excepNegNum) {
            // expected
        }



        // ADD ITEM CORRECTLY
        try {
            newInventory.addItem("item4",5,20,"high quality");
            assertEquals(2,newInventory.size());
        } catch (ExcepNegNum excepNegNum) {
            fail("Something wrong with program");
        }

        try {
            newInventory.addItem("item5",10,5,"low quality");
            assertEquals(3,newInventory.size());
        } catch (ExcepNegNum excepNegNum) {
            fail("Something wrong with program");
        }
    }
}