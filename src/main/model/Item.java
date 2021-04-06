package model;

import exceptions.ExceptionIncorrectNumber;
import org.json.JSONObject;
import persistence.Writable;

// Represents an item with id, name, quantity, costPerUnit and details
public class Item implements Writable {
    private static int nextItemId = 1; // tracks id of next account
    private int id;                    // item id
    private String name;               // item name
    private int quantity;              // item quantity
    private int costPerUnit;
    private String details;            // item details

    // REQUIRES: initialQuantity >= 0 and Cost Per Unit >= 0
    // EFFECTS: constructs an item with an name, quantity and details
    public Item(String itemName, int initialQuantity, int initialCostPerUnit, String itemDetails) throws ExceptionIncorrectNumber {
        id = nextItemId++;
        name = itemName;
        details = itemDetails;
        if (initialCostPerUnit >= 0) {
            costPerUnit = initialCostPerUnit;
        } else {
            throw new ExceptionIncorrectNumber();
        }

        if (initialQuantity >= 0) {
            quantity = initialQuantity;
        } else {
            throw new ExceptionIncorrectNumber();
        }
    }

    // EFFECTS: returns item id
    public int getId() {
        return id;
    }

    // EFFECTS: returns item name
    public String getName() {
        return name;
    }

    // EFFECTS: returns a int of quantities about an item
    public int getQuantity() {
        return quantity;
    }

    // EFFECTS: returns an int of cost per unit about an item
    public int getCostPerUnit() {
        return costPerUnit;
    }

    // EFFECTS: returns a string of details about an item
    public String getDetails() {
        return details;
    }

    // EFFECTS: returns a string of id, name, cost/unit and quantity about an item
    public String getIdNameQuantity() {
        String strID = Integer.toString(id);
        String strQ = Integer.toString(quantity);
        String strCost = Integer.toString(costPerUnit);
        String firstHalf = "[id = " + strID + " name: " + name + " quantity: " + strQ + " Cost/Unit: $";
        String secondHalf = strCost + " details: " + details + "] \n";
        return firstHalf + secondHalf;
    }

    // REQUIRES: (quantity - changeQuantity) >= 0
    // MODIFIES: this
    // EFFECTS: used quantity is subtracted from current quantity
    public Boolean changeItemQuantity(int changeQuantity) throws ExceptionIncorrectNumber {
        if (changeQuantity < 0 && quantity < (-1 * changeQuantity)) {
            throw new ExceptionIncorrectNumber();
        } else {
            quantity += changeQuantity;
            return true;
        }
    }


    // 1. Persistence Functions
    // EFFECTS: creates a JSON Object for an item
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("quantity", quantity);
        json.put("costPerUnit", costPerUnit);
        json.put("details", details);
        return json;
    }
}
