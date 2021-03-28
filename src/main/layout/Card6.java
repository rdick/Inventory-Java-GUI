package layout;

import model.Inventory;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

// Card6 Represents -> Save Data Tab
public class Card6 implements ActionListener {

    private static final String JSON_STORE = "./data/inventory.json";
    private JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private JButton saveDataButton;

    private Inventory newInventory;

    // EFFECTS: Create Card6 with inventory obj
    public Card6(Inventory newInventory) {
        this.newInventory = newInventory;
    }

    // EFFECTS: Creates Cards6 and Saves Data
    public JPanel addCard6() {
        JPanel card6 = new JPanel();

        saveDataButton = new JButton("Save Data");
        saveDataButton.addActionListener(this);
        card6.add(saveDataButton);

        return card6;
    }

    // EFFECTS: Event Listener to Save Data
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            jsonWriter.open();
            jsonWriter.write(newInventory);
            jsonWriter.close();
            System.out.println("Saved Inventory to" + JSON_STORE);
        } catch (FileNotFoundException exception) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }
}
