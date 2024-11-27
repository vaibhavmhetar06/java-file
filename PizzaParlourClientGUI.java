import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PizzaParlour {
    private String pizzaType;
    private double basePrice;
    private double toppingPrice;

    public PizzaParlour(String pizzaType, double basePrice) {
        this.pizzaType = pizzaType;
        this.basePrice = basePrice;
        this.toppingPrice = 0;
    }

    public void addTopping(double price) {
        this.toppingPrice += price;
    }

    public double calculateTotal() {
        return basePrice + toppingPrice;
    }

    public void printReceipt() {
        StringBuilder receipt = new StringBuilder();
        receipt.append("----- Pizza Order -----\n");
        receipt.append(String.format("Pizza Type: %s ($%.2f)\n", pizzaType, basePrice));
        receipt.append(String.format("Toppings: $%.2f\n", toppingPrice));
        receipt.append(String.format("Total Cost (Pizza + Toppings): $%.2f\n", calculateTotal()));
        receipt.append("-----------------------");
        JOptionPane.showMessageDialog(null, receipt.toString(), "Receipt", JOptionPane.INFORMATION_MESSAGE);
    }
}

public class PizzaParlourClientGUI {
    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("Pizza Parlour");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Pizza types and their base prices
        String[] pizzaTypes = {"Margherita", "Pepperoni", "Vegetarian", "Supreme"};
        double[] basePrices = {8.99, 10.99, 9.49, 11.49};

        // Toppings and their prices
        String[] toppings = {"Mushrooms", "Olives", "Bell Peppers", "Extra Cheese"};
        double[] toppingPrices = {1.50, 1.25, 1.75, 2.00};

        // Panel for pizza selection
        JPanel pizzaPanel = new JPanel();
        pizzaPanel.setLayout(new GridLayout(0, 1));
        pizzaPanel.setBorder(BorderFactory.createTitledBorder("Choose a Pizza"));

        ButtonGroup pizzaGroup = new ButtonGroup();
        JRadioButton[] pizzaButtons = new JRadioButton[pizzaTypes.length];
        for (int i = 0; i < pizzaTypes.length; i++) {
            pizzaButtons[i] = new JRadioButton(pizzaTypes[i] + " ($" + basePrices[i] + ")");
            pizzaGroup.add(pizzaButtons[i]);
            pizzaPanel.add(pizzaButtons[i]);
        }

        // Panel for toppings selection
        JPanel toppingsPanel = new JPanel();
        toppingsPanel.setLayout(new GridLayout(0, 1));
        toppingsPanel.setBorder(BorderFactory.createTitledBorder("Select Toppings"));

        JCheckBox[] toppingCheckBoxes = new JCheckBox[toppings.length];
        for (int i = 0; i < toppings.length; i++) {
            toppingCheckBoxes[i] = new JCheckBox(toppings[i] + " ($" + toppingPrices[i] + ")");
            toppingsPanel.add(toppingCheckBoxes[i]);
        }

        // Order button
        JButton orderButton = new JButton("Place Order");
        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Determine selected pizza
                int pizzaChoice = -1;
                for (int i = 0; i < pizzaButtons.length; i++) {
                    if (pizzaButtons[i].isSelected()) {
                        pizzaChoice = i;
                        break;
                    }
                }

                if (pizzaChoice == -1) {
                    JOptionPane.showMessageDialog(frame, "Please choose a pizza type.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                PizzaParlour order = new PizzaParlour(pizzaTypes[pizzaChoice], basePrices[pizzaChoice]);

                // Add selected toppings
                for (int i = 0; i < toppingCheckBoxes.length; i++) {
                    if (toppingCheckBoxes[i].isSelected()) {
                        order.addTopping(toppingPrices[i]);
                    }
                }

                // Print receipt
                order.printReceipt();
            }
        });

        // Add panels and button to frame
        frame.add(pizzaPanel, BorderLayout.NORTH);
        frame.add(toppingsPanel, BorderLayout.CENTER);
        frame.add(orderButton, BorderLayout.SOUTH);

        // Display the frame
        frame.setVisible(true);
    }
}
