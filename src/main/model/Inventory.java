package model;


import exceptions.ExcepNegNum;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;


// Represents an Inventory that has many Items
public class Inventory implements Writable {
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
    public void addItem(String name, int quantity, int costPerUnit, String details) throws ExcepNegNum {
        Item newItem = null;
        newItem = new Item(name, quantity,costPerUnit, details);
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
    public boolean changeInventoryQuantity(String itemName, int changeQuantity) throws ExcepNegNum {
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

    // 5.
    // REQUIRES: item name that exists
    //    EFFECTS: get item by name
    public Item getItemByName(String itemName) {
        for (Item item : allItems) {
            if (item.getName().equals(itemName)) {
                return item;
            }
        }
        return null;

    }

    //1. Helper
    //    EFFECTS: checks if list of all items is empty
    private static Boolean checkListEmpty(List<Item> allItems) {
        if (allItems.size() == 0) {
            return true;
        }
        return false;
    }

    // 2. Helper
    //    EFFECTS: returns number of items in inventory
    public int size() {
        return allItems.size();
    }

    // 3. Helper
    //    EFFECTS: adds new Item to array
    public void add(Item newItem) {
        this.allItems.add(newItem);
    }

    // 1. Persistence Functions
    //     EFFECTS: returns JSON array of items named "inventory"
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("inventory", inventoryToJson());
        return json;
    }

    // 2. Persistence Functions
    //     EFFECTS: returns Items in this inventory as a JSON array of items
    private JSONArray inventoryToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Item i : allItems) {
            jsonArray.put(i.toJson());
        }

        return jsonArray;
    }

    // 3. Persistence Functions
    //     EFFECTS: returns an unmodifiable list of items in this inventory
    public List<Item> getAllItems() {
        return allItems;
    }
}
