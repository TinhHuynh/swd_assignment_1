/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruit_shop.utils.io;

import fruit_shop.model.database.product.Category;
import fruit_shop.model.database.product.Product;
import java.util.Scanner;

/**
 *
 * @author TINH HUYNH
 */
public class ProductIOUtils {

    private static Scanner scanner = new Scanner(System.in);

    public static int askAndGetId() {
        int id = -1;
        boolean isBreak = false;
        do {
            try {
                System.out.print("Please enter a product id: ");
                id = Integer.parseInt(scanner.nextLine());
                if (id >= 0) {
                    isBreak = true;
                } else {
                    isBreak = false;
                    System.out.println("Id must be an non-negative integer. Please try again!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Id must be an non-negative integer. Please try again!");
                isBreak = false;
            }
        } while (!isBreak);
        return id;
    }

    public static String askAndGetName() {

        String name = "";
        boolean isBreak = false;
        do {
            System.out.print("Name*: ");
            name = scanner.nextLine();
            if (!name.isEmpty() || name.equals("\n")) {
                isBreak = true;
            } else {
                isBreak = false;
                System.out.println("Name must not be empty!");
            }
        } while (!isBreak);
        return name;
    }

    public static float askAndGetUnitPrice() {

        float unitPrice = 0.0f;
        boolean isBreak = false;
        do {
            try {
                System.out.print("Unit price [10000 - 100000]*: ");
                unitPrice = Float.parseFloat(scanner.nextLine());
                if (unitPrice >= 1000 && unitPrice <= 100000) {
                    isBreak = true;
                } else {
                    isBreak = false;
                    System.out.println("Unit price must be in range of [1000-10000]!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Unit price must be in range of [1000-10000]!");
                isBreak = false;
            }
        } while (!isBreak);
        return unitPrice;
    }

    public static float askAndGetUnitsInStock() {
        float unitsInStock = 0;
        boolean isBreak = false;
        do {
            try {
                System.out.print("Amount of units in stock [0-50]*: ");
                unitsInStock = Float.parseFloat(scanner.nextLine());
                if (unitsInStock >= 0 && unitsInStock <= 50) {
                    isBreak = true;
                } else {
                    isBreak = false;
                    System.out.println("Quantity of units must be in range of [0-50]!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Quantity of units must be in range of [0-50]!");
                isBreak = false;
            }
        } while (!isBreak);
        return unitsInStock;
    }

    public static int askAndGetCategory() {
        int i = 0;
        System.out.println("Category list:");
        for (Category category : Category.values()) {
            System.out.println(i + " - " + category.value());
            i++;
        }
        int categoryId = 0;
        boolean isBreak = false;
        do {
            try {
                System.out.printf("Category id [0-%d]*: ", Category.values().length - 1);
                categoryId = Integer.parseInt(scanner.nextLine());
                if (categoryId >= 0 && categoryId <= Category.values().length - 1) {
                    isBreak = true;
                } else {
                    isBreak = false;
                    System.out.println("Category must be in range of [0-6]!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Category must be in range of [0-6]!");
                isBreak = false;
            }
        } while (!isBreak);
        return categoryId;
    }

    public static String askAndGetOrigin() {

        System.out.print("Origin: ");
        String origin = scanner.nextLine();
        return origin;
    }

    public static String askAndGetDescription() {

        System.out.print("Description: ");
        String description = scanner.nextLine();
        return description;
    }

    public static boolean askAndGetAvailable() {
        boolean result = false;
        boolean isBreak;
        do {
            System.out.print("Available (type 1 for true, 0 for false): ");
            try {
                int input = Integer.parseInt(scanner.nextLine());
                if (input == 0 || input == 1) {
                    result = input == 1;
                    isBreak = true;
                } else {
                    isBreak = false;
                    System.out.println("Please type 0 or 1!");
                }
            } catch (NumberFormatException ex) {
                isBreak = false;
                System.out.println("Please type 0 or 1!!");
            }
        } while (!isBreak);
        return result;
    }

    public static void printProduct(Product product) {
        if (product == null) {
            System.out.println("Product is not found.");
        } else {
            System.out.println("Result:");
            System.out.printf("%-20s: %d\n", "Id", product.getId());
            System.out.printf("%-20s: %s\n", "Name", product.getName());
            System.out.printf("%-20s: %s\n", "Unit", product.getUnit());
            System.out.printf("%-20s: %.0f VND\n", "Unit price", product.getUnitPrice());
            System.out.printf("%-20s: %.3f kg\n", "Units in stock", product.getUnitsInStock());
            System.out.printf("%-20s: %s\n", "Category", product.getCategory().value());
            System.out.printf("%-20s: %s\n", "Origin", product.getOrigin());
            System.out.printf("%-20s: %b\n", "Available", product.isAvailable());
            System.out.printf("%-20s: %s\n", "Description", product.getDescription());
        }
    }

}
