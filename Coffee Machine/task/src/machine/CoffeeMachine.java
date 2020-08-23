package machine;

import java.util.Scanner;

public class CoffeeMachine {
    private static final int ESPRESSO_WATER_PER_CUP = 250;
    private static final int ESPRESSO_MILK_PER_CUP = 0;
    private static final int ESPRESSO_COFFEE_PER_CUP = 16;
    private static final int ESPRESSO_PRICE = 4;

    private static final int LATTE_WATER_PER_CUP = 350;
    private static final int LATTE_MILK_PER_CUP = 75;
    private static final int LATTE_COFFEE_PER_CUP = 20;
    private static final int LATTE_PRICE = 7;

    private static final int CAPPUCCINO_WATER_PER_CUP = 200;
    private static final int CAPPUCCINO_MILK_PER_CUP = 100;
    private static final int CAPPUCCINO_COFFEE_PER_CUP = 12;
    private static final int CAPPUCCINO_PRICE = 6;

    private static final Scanner scanner = new Scanner(System.in);

    private static int money = 550;
    private static int water = 400;
    private static int milk = 540;
    private static int coffee = 120;
    private static int cups = 9;

    public static void main(String[] args) {
        System.out.print("Write action (buy, fill, take, remaining, exit):\n> ");

        while (true) {
            switch (scanner.next()) {
                case "remaining":
                    printState();
                    break;
                case "buy":
                    handleBuyAction();
                    break;
                case "fill":
                    handleFillAction();
                    break;
                case "take":
                    handleTakeAction();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Unexpected action.");
            }
        }
    }

    private static void printState() {
        System.out.printf("The coffee machine has:\n%d of water\n%d of milk\n", water, milk);
        System.out.printf("%d of coffee beans\n%d of disposable cups\n%d of money\n", coffee, cups, money);
    }

    private static void handleBuyAction() {
        System.out.print("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:\n> ");

        switch (scanner.next()) {
            case "back":
                return;
            case "1":
                makeCoffee(ESPRESSO_WATER_PER_CUP, ESPRESSO_MILK_PER_CUP, ESPRESSO_COFFEE_PER_CUP);
                processPayment(ESPRESSO_PRICE);
                break;
            case "2":
                makeCoffee(LATTE_WATER_PER_CUP, LATTE_MILK_PER_CUP, LATTE_COFFEE_PER_CUP);
                processPayment(LATTE_PRICE);
                break;
            case "3":
                makeCoffee(CAPPUCCINO_WATER_PER_CUP, CAPPUCCINO_MILK_PER_CUP, CAPPUCCINO_COFFEE_PER_CUP);
                processPayment(CAPPUCCINO_PRICE);
                break;
            default:
                System.out.println("Unexpected option.");
                handleBuyAction();
        }
    }

    private static void makeCoffee(int water, int milk, int coffee) {
        if (CoffeeMachine.water < water) {
            System.out.println("Sorry, not enough water!");
            return;
        }

        if (CoffeeMachine.milk < milk) {
            System.out.println("Sorry, not enough milk!");
            return;
        }

        if (CoffeeMachine.coffee < coffee) {
            System.out.println("Sorry, not enough coffee bean!");
            return;
        }

        if (CoffeeMachine.coffee < 1) {
            System.out.println("Sorry, not enough disposable cups!");
            return;
        }

        CoffeeMachine.water -= water;
        CoffeeMachine.milk -= milk;
        CoffeeMachine.coffee -= coffee;
        CoffeeMachine.cups--;

        System.out.println("I have enough resources, making you a coffee!");
    }

    private static void processPayment(int price) {
        money += price;
    }

    private static void handleFillAction() {
        System.out.print("Write how many ml of water do you want to add:\n> ");
        water += scanner.nextInt();

        System.out.print("Write how many ml of milk do you want to add:\n> ");
        milk += scanner.nextInt();

        System.out.print("Write how many grams of coffee beans do you want to add:\n> ");
        coffee += scanner.nextInt();

        System.out.print("Write how many disposable cups of coffee do you want to add:\n >");
        cups += scanner.nextInt();
    }

    private static void handleTakeAction() {
        System.out.printf("I gave you $%d", money);
        money = 0;
    }
}
