import java.util.Scanner;

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
        System.out.println("----- Pizza Order -----");
        System.out.printf("Pizza Type: %s ($%.2f)\n", pizzaType, basePrice);
        System.out.printf("Toppings: $%.2f\n", toppingPrice);
        System.out.printf("Total Cost (Pizza + Toppings): $%.2f\n", calculateTotal());
        System.out.println("-----------------------");
    }
}

public class PizzaParlourclient {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Pizza types and their base prices
        String[] pizzaTypes = {"Margherita", "Pepperoni", "Vegetarian", "Supreme"};
        double[] basePrices = {8.99, 10.99, 9.49, 11.49};

        // Toppings and their prices
        String[] toppings = {"Mushrooms", "Olives", "Bell Peppers", "Extra Cheese"};
        double[] toppingPrices = {1.50, 1.25, 1.75, 2.00};

        // Display pizza types
        System.out.println("Welcome to the Pizza Parlour!");
        System.out.println("Choose a pizza type:");

        for (int i = 0; i < pizzaTypes.length; i++) {
            System.out.printf("%d. %s\n", i + 1, pizzaTypes[i]);
        }

        System.out.print("Enter the number of your choice: ");
        int pizzaChoice = scanner.nextInt() - 1;

        if (pizzaChoice < 0 || pizzaChoice >= pizzaTypes.length) {
            System.out.println("Invalid choice. Exiting...");
            return;
        }

        PizzaParlour order = new PizzaParlour(pizzaTypes[pizzaChoice], basePrices[pizzaChoice]);

        // Display toppings
        System.out.println("Select toppings (Enter 0 to finish):");

        for (int i = 0; i < toppings.length; i++) {
            System.out.printf("%d. %s\n", i + 1, toppings[i]);
        }

        boolean addingToppings = true;
        while (addingToppings) {
            System.out.print("Enter the number of your topping choice: ");
            int toppingChoice = scanner.nextInt() - 1;

            if (toppingChoice == -1) {
                addingToppings = false;
            } else if (toppingChoice < 0 || toppingChoice >= toppings.length) {
                System.out.println("Invalid choice. Please try again.");
            } else {
                order.addTopping(toppingPrices[toppingChoice]);
                System.out.println("Added: " + toppings[toppingChoice]);
            }
        }

        // Print receipt
        order.printReceipt();
        System.out.println("Thank you for ordering! Enjoy your pizza!");

        scanner.close();
    }
}
