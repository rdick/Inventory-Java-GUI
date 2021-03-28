package ui;

import model.Inventory;
import persistence.JsonReader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// Card5 Represents -> Load Data Tab
public class Card5 implements ActionListener {
    private JButton loadDataButton;
    private static final String JSON_STORE = "./data/inventory.json";
    private JsonReader jsonReader = new JsonReader(JSON_STORE);

    private Inventory newInventory;

    // EFFECTS: Create Card5 with inventory obj
    public Card5(Inventory newInventory) {
        this.newInventory = newInventory;
    }

    // EFFECTS: Creates Cards5 to Load Data
    public JPanel addCard5() {
        JPanel card5 = new JPanel();

        loadDataButton = new JButton("Load Data");
        loadDataButton.addActionListener(this);
        card5.add(loadDataButton);

        return card5;
    }

    // EFFECTS: Event Listener to Load Data
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            newInventory = jsonReader.read();
            //Create and set up the window.
            JFrame frame = new JFrame("Inventory Tracking");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            //Create and set up the content pane.
            InventoryAppWithUi demo = new InventoryAppWithUi(newInventory);
            demo.addComponentToPane(frame.getContentPane());

            //Display the window.
            frame.pack();
            frame.setVisible(true);


        } catch (IOException exception) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
