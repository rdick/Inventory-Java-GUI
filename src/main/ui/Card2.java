package ui;

import model.Inventory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Card2 Represents -> + / - Quantity Tab
public class Card2 implements ActionListener {
    private JPanel card2;
    private JPanel card2Name;
    private JPanel card2Quantity;
    private JPanel card2SubmitButton;

    private JLabel name2Label = new JLabel("Name");
    private JLabel quantity2Label = new JLabel("Quantity");

    private JTextField name2TextField;
    private JTextField quantity2TextField;

    private JButton submitButton2;

    private Inventory newInventory;

    // EFFECTS: Constructor for inventory object
    public Card2(Inventory newInventory) {
        this.newInventory = newInventory;
    }

    // EFFECTS: Create Card Two and Set Dimensions for All Cards
    public JPanel addCard2() {
        card2 = new JPanel();

        card2Name = new JPanel();
        card2Quantity = new JPanel();
        card2SubmitButton = new JPanel();

        card2Name.add(name2Label);
        name2TextField = new JTextField("", 20);
        card2Name.add(name2TextField);

        card2Quantity.add(quantity2Label);
        quantity2TextField = new JTextField("", 20);
        card2Quantity.add(quantity2TextField);

        submitButton2 = new JButton("Submit");
        submitButton2.addActionListener(this);
        card2SubmitButton.add(submitButton2);

        card2.add(card2Name);
        card2.add(card2Quantity);
        card2.add(card2SubmitButton);

        card2.setLayout(new BoxLayout(card2, BoxLayout.Y_AXIS));
        return card2;
    }

    // EFFECTS: Click Handler for Changing Quantity
    @Override
    public void actionPerformed(ActionEvent e) {
        String itemName = name2TextField.getText();
        int itemQuantity = Integer.parseInt(quantity2TextField.getText());

        newInventory.changeInventoryQuantity(itemName,itemQuantity);
        System.out.println(newInventory.allItemInfo());
    }
}