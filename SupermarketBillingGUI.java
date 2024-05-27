import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SupermarketBillingGUI {

    private JFrame frame;
    private JTextField itemNameField, itemQuantityField, itemPriceField, taxRateField, discountField;
    private JTextArea billTextArea;
    private ArrayList<Item> items = new ArrayList<>();
    private double subTotal = 0;
    private double tax = 0;
    private double discount = 0;
    private double finalTotal = 0;

    public SupermarketBillingGUI() {
        frame = new JFrame("Supermarket Billing System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);

        itemNameField = new JTextField(10);
        itemQuantityField = new JTextField(5);
        itemPriceField = new JTextField(5);
        taxRateField = new JTextField(5);
        discountField = new JTextField(5);
        billTextArea = new JTextArea(20, 30);
        billTextArea.setEditable(false);

        JButton addButton = new JButton("Add Item");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addItem();
            }
        });

        JButton finalizeButton = new JButton("Finalize Bill");
        finalizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalizeBill();
            }
        });

        JPanel panel = new JPanel();
        panel.add(new JLabel("Item Name:"));
        panel.add(itemNameField);
        panel.add(new JLabel("Quantity:"));
        panel.add(itemQuantityField);
        panel.add(new JLabel("Price:"));
        panel.add(itemPriceField);
        panel.add(addButton);
        panel.add(new JLabel("Tax Rate (%):"));
        panel.add(taxRateField);
        panel.add(new JLabel("Discount ($):"));
        panel.add(discountField);
        panel.add(finalizeButton);
        panel.add(new JScrollPane(billTextArea));

        frame.add(panel);
        frame.setVisible(true);
    }

    private void addItem() {
        String itemName = itemNameField.getText();
        int itemQuantity = Integer.parseInt(itemQuantityField.getText());
        double itemPrice = Double.parseDouble(itemPriceField.getText());
        Item item = new Item(itemName, itemQuantity, itemPrice);
        items.add(item);
        subTotal += itemQuantity * itemPrice;
        billTextArea.append(itemName + " x " + itemQuantity + " @ $" + itemPrice + "\n");
        itemNameField.setText("");
        itemQuantityField.setText("");
        itemPriceField.setText("");
    }

    private void finalizeBill() {
        double taxRate = Double.parseDouble(taxRateField.getText());
        tax = (taxRate * subTotal) / 100;
        discount = Double.parseDouble(discountField.getText());
        finalTotal = subTotal + tax - discount;
        billTextArea.append("\nSubtotal: $" + subTotal + "\nTax: $" + tax + "\nDiscount: $" + discount + "\nTotal: $" + finalTotal);
    }

    public static void main(String[] args) {
        new SupermarketBillingGUI();
    }
}

class Item {
    private String name;
    private int quantity;
    private double price;

    public Item(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
}
