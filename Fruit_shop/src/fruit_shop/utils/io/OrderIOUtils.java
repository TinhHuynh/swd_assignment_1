/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruit_shop.utils.io;

import fruit_shop.Config;
import fruit_shop.model.database.order.Order;
import fruit_shop.utils.DateTimeUtils;
import fruit_shop.utils.DateTimeUtils;
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author TINH HUYNH
 */
public class OrderIOUtils {

    private static Scanner scanner = new Scanner(System.in);

    public static int askAndGetId() {
        int id = -1;
        boolean isBreak = false;
        do {
            try {
                System.out.print("Please enter a order id: ");
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

    public static int askAndGetCustomerId() {
        int id = -1;
        boolean isBreak = false;
        do {
            try {
                System.out.print("Customer id: ");
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

    public static Timestamp askAndGetDate() {
        Timestamp timestamp = null;
        String ask = "Date (dd/MM/yyyy)* (if input invalid, automatically get current date): ";
        System.out.print(ask);
        String input = scanner.nextLine();
        timestamp = DateTimeUtils.convertStringToTimeStamp(input,
                Config.NON_TIME_DATE_FORMAT);
        return timestamp;
    }
    
    public static void printOrder(List<Order> orders){
        if (!orders.isEmpty()) {
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-5s%-20s%-30s%-30s%-20s%s\n", 
                    "Id", "Date", "Staff", "Customer", "Num of products", "Money(VND)");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
            orders.forEach((order) -> {
                System.out.printf("%-5d%-20s%-30s%-30s%-20d%.0f\n",
                        order.getId(),
                        DateTimeUtils.convertTimestampToString(order.getOrderDate(), Config.ORDER_DATE_FORMAT),
                        order.getStaff().getId() + " - " + order.getStaff().getFullName(),
                        order.getCustomer().getId() + " - " + order.getCustomer().getFullName(),
                        order.getNumOfProducts(), 
                        order.getTotalMoney());
            });
        } else {
            System.out.println("There is no order.");
        }
    }
}
