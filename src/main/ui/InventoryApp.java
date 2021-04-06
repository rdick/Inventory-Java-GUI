package ui;

import exceptions.ExceptionIncorrectNumber;
import model.Inventory;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Inventory App main body of function
public class InventoryApp {
    private static final String JSON_STORE = "./data/inventory.json";
    private Scanner input1;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private Inventory newInventory;
    private Boolean keepPlaying;

    // EFFECTS: constructs Inventory and runs application
    public InventoryApp() throws FileNotFoundException {
        input1 = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runInventory();
    }

    //    EFFECT: Main method of Inventory. Just follow along in console and you will be able to edit your inventory
    private void runInventory() {
        keepPlaying = true;
        newInventory = new Inventory();
        while (keepPlaying) {
            int choice1 = mainInitialQuestion();
            if (choice1 == 1) {
                mainAddNewItem(newInventory);
            } else if (choice1 == 2) {
                try {
                    mainReduceInventory(newInventory);
                } catch (ExceptionIncorrectNumber exceptionIncorrectNumber) {
                    exceptionIncorrectNumber.printStackTrace();
                }
            } else if (choice1 == 3) {
                try {
                    mainAddInventory(newInventory);
                } catch (ExceptionIncorrectNumber exceptionIncorrectNumber) {
                    exceptionIncorrectNumber.printStackTrace();
                }
            } else if (choice1 == 4) {
                System.out.println(newInventory.allItemInfo() + "\n");
            } else if (choice1 == 5) {
                System.out.println("Total Value of is:$" + Integer.toString(newInventory.totalValue()) + "\n");
            } else if (choice1 == 6) {
                saveInventory();
            } else if (choice1 == 7) {
                loadInventory();
            } else if (choice1 == 8) {
                System.out.println("Thank you for Reviewing for Inventory! ");
                keepPlaying = false;
            }
        }
    }

    //    EFFECT: initial question requires user to make a decision on what action to take
    private static int mainInitialQuestion() {

        Scanner sc1 = new Scanner(System.in);
        System.out.println("Choose from the following options");
        System.out.println("Press '1' Add New Item");
        System.out.println("Press '2' Reduce Quantity of Item");
        System.out.println("Press '3' Add Quantity to a Item");
        System.out.println("Press '4' Review All Items in Inventory");
        System.out.println("Press '5' Review Total Cost of Inventory");
        System.out.println("Press '6' Save Inventory Data");
        System.out.println("Press '7' Load Inventory Data");
        System.out.println("Press '8' Exit");

        return sc1.nextInt();

    }

    //  EFFECT Asks user question about new inventory item user wants to add then adds the item to the inventory
    private static void mainAddNewItem(Inventory newInventory) {
        Scanner sc2 = new Scanner(System.in);
        Scanner sc3 = new Scanner(System.in);
        Scanner sc4 = new Scanner(System.in);
        Scanner sc5 = new Scanner(System.in);

        System.out.println("What is the name of the item?");
        String name1 = sc2.nextLine();

        System.out.println("What is the quantity of the item?");
        int quantity1 = sc3.nextInt();

        System.out.println("What is the cost/unit of the (rounded to the nearest dollar)?");
        int costPerUnit1 = sc4.nextInt();

        System.out.println("What are the specific details of the item?");
        String details1 = sc5.nextLine();

        try {
            newInventory.addItem(name1, quantity1, costPerUnit1, details1);
        } catch (ExceptionIncorrectNumber exceptionIncorrectNumber) {
            exceptionIncorrectNumber.printStackTrace();
        }
        System.out.println("New Item Added, Inventory Size: " + Integer.toString(newInventory.size()));
    }

    //    REQUIRES: name has to be in inventory AND quantity of item cannot be negative
    //    EFFECT: Asks the user quantity they want to add and adds quantity based on their answer
    private static void mainReduceInventory(Inventory newInventory) throws ExceptionIncorrectNumber {

        Scanner sc6 = new Scanner(System.in);
        Scanner sc7 = new Scanner(System.in);

        System.out.println("What is the name of the item?");
        String name2 = sc6.nextLine();

        System.out.println("How many of the item have you used?");
        int quantity2 = (-1 * sc7.nextInt());

        if (newInventory.changeInventoryQuantity(name2, quantity2)) {
            System.out.println("Quantity changed on your item \n");
        } else {
            System.out.println("ERROR: Name of Item or Quantity Invalid Option \n");
        }
    }

    //    REQUIRES: name has to be in inventory AND quantity of item cannot be negative
    //    EFFECT: Asks the user quantity they want to subtract and minuses quantity based on their answer
    private static void mainAddInventory(Inventory newInventory) throws ExceptionIncorrectNumber {
        Scanner sc8 = new Scanner(System.in);
        Scanner sc9 = new Scanner(System.in);

        System.out.println("What is the name of the item?");
        String name3 = sc8.nextLine();

        System.out.println("How many of the item have you received?");
        int quantity3 = sc9.nextInt();

        if (newInventory.changeInventoryQuantity(name3, quantity3)) {
            System.out.println("Quantity changed on your item \n");
        } else {
            System.out.println("ERROR: Name of Item or Quantity Invalid Option \n");
        }
    }

    // EFFECTS: saves the inventory to a file
    private void saveInventory() {
        try {
            jsonWriter.open();
            jsonWriter.write(newInventory);
            jsonWriter.close();
            System.out.println("Saved Inventory to" + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads inventory from a file
    private void loadInventory() {
        try {
            newInventory = jsonReader.read();
            System.out.println("Loaded Inventory from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
