/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruit_shop.utils.io;

import fruit_shop.Config;
import fruit_shop.model.database.customer.Customer;
import fruit_shop.utils.DateTimeUtils;
import fruit_shop.utils.SexUtils;
import java.util.Scanner;

/**
 *
 * @author TINH HUYNH
 */
public class CustomerIOUtils extends PersonIOUtils{

    private static Scanner scanner = new Scanner(System.in);

    public static int askAndGetId() {
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

    public static void printCustomer(Customer customer) {
        if (customer == null) {
            System.out.println("Customer is not found.");
        } else {
            System.out.println("Result:");
            System.out.printf("%-20s: %d\n", "Id", customer.getId());
            System.out.printf("%-20s: %s\n", "First name", customer.getFirstName());
            System.out.printf("%-20s: %s\n", "Last name", customer.getLastName());
            System.out.printf("%-20s: %s\n", "Join date", DateTimeUtils.convertTimestampToString(customer.getJoinDate(), Config.NON_TIME_DATE_FORMAT));
            System.out.printf("%-20s: %s\n", "Birth date", DateTimeUtils.convertTimestampToString(customer.getBirthday(), Config.NON_TIME_DATE_FORMAT));
            System.out.printf("%-20s: %s\n", "Sex", SexUtils.parseToString(customer.isSex()));
            System.out.printf("%-20s: %s\n", "Phone", customer.getPhone());
            System.out.printf("%-20s: %s\n", "Email", customer.getEmail());
            System.out.printf("%-20s: %s\n", "Address", customer.getAddress());
        }
    }

   
}
