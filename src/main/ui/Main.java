package ui;

import java.io.FileNotFoundException;

// Inventory App Main Starter Function
public class Main {
    public static void main(String[] args) {
        try {
            new InventoryApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
