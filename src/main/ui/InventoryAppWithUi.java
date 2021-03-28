package ui;

import model.Inventory;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;


// Full Inventory App with Ui for Project
public class InventoryAppWithUi {
    final String tabName1 = "New Item";
    final String tabName2 = "+ / - Quantity";
    final String tabName3 = "All Items";
    final String tabName4 = "Total Cost";
    final String tabName5 = "Load Data";
    final String tabName6 = "Save Data";

    private static final String JSON_STORE = "./data/inventory.json";
    private JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private JsonReader jsonReader = new JsonReader(JSON_STORE);
    private Inventory newInventory;

    // Create App without existing inventory
    public InventoryAppWithUi() {
        this.newInventory = new Inventory();
    }

    // LOAD DATA - Create App with existing inventory
    public InventoryAppWithUi(Inventory newInventory) {
        this.newInventory = newInventory;
    }

    // LOAD DATA - setup application
    public void addComponentToPane(Container pane) {

        JTabbedPane tabbedPane = new JTabbedPane();

        //Create the "cards".
        JPanel card1 = new Card1(newInventory).addCard1();
        JPanel card2 = new Card2(newInventory).addCard2();
        JPanel card3 = new Card3(newInventory).addCard3();
        JPanel card4 = new Card4(newInventory).addCard4();
        JPanel card5 = new Card5(newInventory).addCard5();
        JPanel card6 = new Card6(newInventory).addCard6();

        //Create the Tabs with cards in them
        tabbedPane.addTab(tabName1, card1);
        tabbedPane.addTab(tabName2, card2);
        tabbedPane.addTab(tabName3, card3);
        tabbedPane.addTab(tabName4, card4);
        tabbedPane.addTab(tabName5, card5);
        tabbedPane.addTab(tabName6, card6);

        pane.add(tabbedPane, BorderLayout.CENTER);
    }
}