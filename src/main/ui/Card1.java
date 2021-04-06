package ui;

import exceptions.ExcepNegNum;
import model.Inventory;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

// Card1 Represents -> New Item Tab
public class Card1 implements ActionListener {

    private static final String SOUND_EFFECT = "./data/tada2.wav";

    private JPanel card1Name = new JPanel();
    private JPanel card1Quantity = new JPanel();
    private JPanel card1Cost = new JPanel();
    private JPanel card1Details = new JPanel();
    private JPanel card1Button = new JPanel();

    private JLabel nameLabel = new JLabel("Name");
    private JLabel quantityLabel = new JLabel("Quantity");
    private JLabel costLabel = new JLabel("Cost");
    private JLabel detailsLabel = new JLabel("Details");

    private JTextField nameTextField;
    private JTextField quantityTextField;
    private JTextField costTextField;
    private JTextField detailsTextField;

    private JButton submitButton;

    private Inventory newInventory;

    // EFFECTS: Create Card1 with inventory obj
    public Card1(Inventory newInventory) {
        this.newInventory = newInventory;
    }

    // EFFECTS: Create First Card and Set Dimensions for All Cards
    public JPanel addCard1() {
        JPanel card1 = new JPanel() {
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.width = 500;
                size.height = 300;
                return size;
            }
        };

        // Add Fields to Card one
        card1.add(createCard1Name());
        card1.add(createCard1Cost());
        card1.add(createCard1Quantity());
        card1.add(createCard1Details());
        card1.add(createCard1SubmitButton());

        card1.setLayout(new BoxLayout(card1, BoxLayout.Y_AXIS));

        return card1;
    }

    // EFFECTS: Create JPanel for Name Fields
    public JPanel createCard1Name() {
        card1Name.add(nameLabel);
        nameTextField = new JTextField("",20);
        card1Name.add(nameTextField);
        return card1Name;
    }

    // EFFECTS: Create JPanel for Quantity Fields
    public JPanel createCard1Quantity() {
        card1Quantity.add(quantityLabel);
        quantityTextField = new JTextField("",20);
        card1Quantity.add(quantityTextField);
        return card1Quantity;
    }

    // EFFECTS: Create JPanel for Cost Fields
    public JPanel createCard1Cost() {
        card1Cost.add(costLabel);
        costTextField = new JTextField("",20);
        card1Cost.add(costTextField);
        return card1Cost;
    }

    // EFFECTS: Create JPanel for Detail Fields
    public JPanel createCard1Details() {
        card1Details.add(detailsLabel);
        detailsTextField = new JTextField("",20);
        card1Details.add(detailsTextField);
        return card1Details;
    }

    // EFFECTS: Create JPanel for Submit Button
    public JPanel createCard1SubmitButton() {
        submitButton =  new JButton("Submit");
        submitButton.addActionListener(this);
        card1Button.add(submitButton);
        return card1Button;
    }

    // EFFECTS: Event Listener -> submit button -> creating new inventory item and play sound
    @Override
    public void actionPerformed(ActionEvent e) {

        String name = nameTextField.getText();
        int quantity = Integer.parseInt(quantityTextField.getText());
        int cost = Integer.parseInt(costTextField.getText());
        String details = detailsTextField.getText();

        try {
            newInventory.addItem(name,quantity,cost,details);
        } catch (ExcepNegNum excepNegNum) {
            excepNegNum.printStackTrace();
        }
        System.out.println(newInventory.size());
        System.out.println(newInventory.allItemInfo());

        File sound = new File("./data/tada2.wav");
        try {
            Clip c = AudioSystem.getClip();
            c.open(AudioSystem.getAudioInputStream(sound));
            c.start();
        } catch (Exception error) {
            System.out.println("fails");
        }
    }
}
