package ui;

import model.Inventory;
import java.util.Scanner;

public class Main {

//    EFFECT: Main method of Inventory. Just follow along in console and you will be able to edit your inventory
    public static void main(String[] args) {
        Inventory newInventory = new Inventory();
        Boolean keepPlaying = true;

        while (keepPlaying) {
            int choice1 = mainInitialQuestion();
            if (choice1 == 1) {
                mainAddNewItem(newInventory);
            } else if (choice1 == 2) {
                mainReduceInventory(newInventory);
            } else if (choice1 == 3) {
                mainAddInventory(newInventory);
            } else if (choice1 == 4) {
                System.out.println(newInventory.allItemInfo() + "\n");
            } else if (choice1 == 5) {
                String totalValue = Integer.toString(newInventory.totalValue());
                System.out.println("Total Value of Inventory is: $" + totalValue + "\n");
            } else if (choice1 == 6) {
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
        System.out.println("Press '5' Review Total Cost of Inventory Inventory");
        System.out.println("Press '6' Exit");

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

        newInventory.addItem(name1, quantity1, costPerUnit1, details1);
        System.out.println("New Item Added, Inventory Size: " + Integer.toString(newInventory.size()));
    }

    //    REQUIRES: name has to be in inventory AND quantity of item cannot be negative
    //    EFFECT: Asks the user quantity they want to add and adds quantity based on their answer
    private static void mainReduceInventory(Inventory newInventory) {

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
    private static void mainAddInventory(Inventory newInventory) {
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
}
