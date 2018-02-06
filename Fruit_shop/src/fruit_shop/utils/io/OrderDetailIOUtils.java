/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruit_shop.utils.io;

import fruit_shop.model.database.order_detail.OrderDetail;
import fruit_shop.model.database.product.Product;
import fruit_shop.model.database.product.ProductDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author TINH HUYNH
 */
public class OrderDetailIOUtils {

    private static Scanner scanner = new Scanner(System.in);

    public static List<OrderDetail> askAndGetOrderDetails() {
        List<OrderDetail> details = new ArrayList<>();
        boolean isBreak = false;
        do {
            Product product = null;
            do {
                System.out.println("");
                int productId = ProductIOUtils.askAndGetId();
                product = new ProductDAO().getProductById(productId);
                if (product == null) {

                    System.out.println("Product is not found.");
                    boolean isTryAgain = askGoToContinue();
                    if (!isTryAgain) {
                        isBreak = true;
                        break;
                    }
                }
            } while (product == null);
            if (product != null) {
                if (!product.isAvailable()) {
                    System.out.println("Product is not available.");
                } else {
                    if (!isBreak) {
                        float quantity = askAndGetQuantiy(product.getUnitsInStock());
                        float price = product.getUnitPrice();
                        OrderDetail detail = new OrderDetail();
                        detail.setProductId(product.getId());
                        detail.setQuantity(quantity);
                        detail.setPrice(price);
                        details.add(detail);
                    }
                }
            }
        } while (askGoToContinue());
        return details;
    }

    private static float askAndGetQuantiy(float unitsInStock) {
        float quantity = 0;
        boolean isBreak = false;
        do {
            try {
                System.out.print("Quantity*: ");
                quantity = Float.parseFloat(scanner.nextLine());
                if (quantity >= 0 && quantity <= unitsInStock) {
                    isBreak = true;
                } else {
                    isBreak = false;
                    System.out.printf("Quantity of units must be in range of [0-%f]!\n", unitsInStock);
                }
            } catch (NumberFormatException e) {
                System.out.printf("Quantity of units must be in range of [0-%f]!\n", unitsInStock);
                isBreak = false;
            }
        } while (!isBreak);
        return quantity;
    }

    private static boolean askGoToContinue() {
        String input = "";
        System.out.print("Do you want to continue ? Type y for yes or any key for no: ");
        scanner = new Scanner(System.in);
        input = scanner.nextLine();
        return input.equalsIgnoreCase("y");
    }

}
