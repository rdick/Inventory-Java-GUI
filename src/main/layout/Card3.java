package layout;

import model.Inventory;
import model.Item;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Card3 Represents -> All Items Tab
public class Card3 implements ActionListener {

    private Inventory newInventory;
    private JTable table;
    private DefaultTableModel model;
    private JPanel card3;
    private JButton refreshTableButton;
    private Object[] columnNames;

    // EFFECTS: Create Card3 with inventory obj
    public Card3(Inventory newInventory) {
        this.newInventory = newInventory;
    }

    // EFFECTS: Creates card 3 with table
    public JPanel addCard3() {
        card3 = new JPanel();

        refreshTableButton = new JButton("Refresh Table");
        refreshTableButton.addActionListener(this);
        card3.add(refreshTableButton);


        columnNames = new Object[]{"Name", "Quantity", "Cost / Unit", "Details"};
        Object[] blankRow = new Object[]{"", "", "", ""};

        table = new JTable();
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        model.addRow(blankRow);
        TableForInventory tableForInventory = new TableForInventory(model);
        card3.add(tableForInventory);

        return card3;
    }

    // EFFECTS: Adds item to table
    @Override
    public void actionPerformed(ActionEvent e) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.fireTableDataChanged();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        getInventoryTableData();
        model.fireTableDataChanged();
        table.repaint();
    }

    // EFFECTS: Puts Inventory Data in Rows
    public void getInventoryTableData() {
        int i = 0;
        for (Item item : newInventory.getAllItems()) {
            Object[] row = new Object[4];
            row[0] = item.getName();
            row[1] = String.valueOf(item.getQuantity());
            row[2] = String.valueOf(item.getCostPerUnit());
            row[3] = item.getDetails();
            i = i + 1;
            model.addRow(row);
        }
    }
}
