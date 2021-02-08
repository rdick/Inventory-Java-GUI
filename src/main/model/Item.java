package model;

// Represents an Item having an id, name, quantity, and details
public class Item {
    private static int nextItemId = 1; // tracks id of next account
    private int id;                    // item id
    private String name;               // item name
    private int quantity;              // item quantity
    private int costPerUnit;
    private String details;            // item details

    // REQUIRES: initialQuantity >= 0 and Cost Per Unit >= 0
    // EFFECTS: constructs an item with an name, quantity and details
    public Item(String itemName, int initialQuantity, int initialCostPerUnit, String itemDetails) {
        id = nextItemId++;
        name = itemName;
        details = itemDetails;
        if (initialCostPerUnit >= 0) {
            costPerUnit = initialCostPerUnit;
        } else {
            costPerUnit = 0;
        }

        if (initialQuantity >= 0) {
            quantity = initialQuantity;
        } else {
            quantity = 0;
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

    // EFFECTS: returns a string of details about an item
    public int getQuantity() {
        return quantity;
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
    public Boolean changeItemQuantity(int changeQuantity) {
        if (quantity < (changeQuantity * -1)) {
            return false;
        } else {
            quantity += changeQuantity;
            return true;
        }
    }

    // EFFECTS: returns a string of details about an item
    public int getCostPerUnit() {
        return costPerUnit;
    }

}
