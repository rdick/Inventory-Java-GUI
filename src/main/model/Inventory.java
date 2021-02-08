package model;


import java.util.ArrayList;
import java.util.List;



public class Inventory {
    private List<Item> allItems;

    // EFFECTS: constructs an empty collection of items
    public Inventory() {
        this.allItems = new ArrayList<>();
    }

    // 1.
    // REQUIRES: quantity >= 0, costPerUnit >= 0
    // MODIFIES: this
    // EFFECTS: adds a new item to the list,
    //          if initialQuantity is less than 0 quantity is set to 0
    public void addItem(String name, int quantity, int costPerUnit, String details) {
        Item newItem = new Item(name, quantity,costPerUnit, details);
        this.allItems.add(newItem);
    }

    // 2.
    // EFFECTS: gets the id, name, quantity of each item or No items available message
    public String allItemInfo() {
        if (checkListEmpty(allItems)) {
            return "No items available";
        } else {
            String strAllItems = "";
            for (Item item : allItems) {
                strAllItems = strAllItems + item.getIdNameQuantity();
            }
            return strAllItems;
        }
    }

    // 3.
    //    REQUIRES: (quantity - changeQuantity) >= 0 and itemId that exists
    //    MODIFIES: this
    //    EFFECTS: add or subtract quantity from from current quantity
    //             return false if item id does not exist OR cannot subtract because it will create negative quantity
    public boolean changeInventoryQuantity(String itemName, int changeQuantity) {
        Item foundItem = getItemByName(itemName);

        if (foundItem == null) {
            return false;
        } else {
            return foundItem.changeItemQuantity(changeQuantity);
        }
    }

    // 4.
    //    EFFECTS: find total value of all inventory
    public int totalValue() {
        if (checkListEmpty(allItems)) {
            return 0;
        } else {
            int totalValue = 0;
            for (Item item : allItems) {
                totalValue += (item.getQuantity() * item.getCostPerUnit());
            }
            return totalValue;
        }
    }

    // 5. REQUIRES: item name that exists
    //    EFFECTS: get item by name
    public Item getItemByName(String itemName) {
        for (Item item : allItems) {
            if (item.getName() == itemName) {
                return item;
            }
        }
        return null;

    }

//    //6.
//    //    REQUIRES: itemId that exists
//    //    EFFECTS: finds Item by itemId
//    private Item getItemById(List<Item> allItems, int itemId) {
//        for (Item item : allItems) {
//            if (item.getId() == itemId) {
//                return item;
//            }
//        }
//        return null;
//    }

    //1. Helper
    //    EFFECTS: checks if list of all items is empty
    private static Boolean checkListEmpty(List<Item> allItems) {
        if (allItems.size() == 0) {
            return true;
        }
        return false;
    }

    // 2.
    //    EFFECTS: returns number of items in inventory
    public int size() {
        return allItems.size();
    }
}
