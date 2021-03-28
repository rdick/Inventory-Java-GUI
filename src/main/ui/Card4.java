package ui;

import model.Inventory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Card4 Represents -> Total Cost Tab
public class Card4 implements ActionListener {
    JButton refreshTotalCost;

    private Inventory newInventory;
    private JLabel totalCostLabel;


    // EFFECTS: Create Card4 with inventory obj
    public Card4(Inventory newInventory) {
        this.newInventory = newInventory;
    }

    // EFFECTS: Creates Cards4 and Displays Total Cost
    public JPanel addCard4() {

        JPanel card4 = new JPanel();
        totalCostLabel = new JLabel("Total Value of Inventory $" + newInventory.totalValue(), JLabel.CENTER);
        refreshTotalCost = new JButton("Refresh Total Cost");
        refreshTotalCost.addActionListener(this);

        card4.add(Box.createHorizontalGlue());
        card4.add(totalCostLabel);
        card4.add(refreshTotalCost);
        card4.add(Box.createHorizontalGlue());

        return card4;
    }

    // EFFECTS: Event Listener Refreshes Total Cost of Inventory
    @Override
    public void actionPerformed(ActionEvent e) {
        totalCostLabel.setText("Total Value of Inventory $" + newInventory.totalValue());
    }

}
