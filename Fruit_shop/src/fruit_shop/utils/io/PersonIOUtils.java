/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruit_shop.utils.io;

import fruit_shop.Config;
import fruit_shop.utils.DateTimeUtils;
import java.sql.Timestamp;
import java.util.Scanner;

/**
 *
 * @author TINH HUYNH
 */
public class PersonIOUtils {

    private static Scanner scanner = new Scanner(System.in);

    public static String askAndGetFirstName() {
        String firstName = "";
        boolean isBreak = false;
        do {
            System.out.print("First name*: ");
            firstName = scanner.nextLine();
            if (!firstName.isEmpty() || firstName.equals("\n")) {
                isBreak = true;
            } else {
                isBreak = false;
                System.out.println("First name must not be empty!");
            }
        } while (!isBreak);
        return firstName;
    }

    public static String askAndGetLastName() {
        String lastName = "";
        boolean isBreak = false;
        do {
            System.out.print("Last name*: ");
            lastName = scanner.nextLine();
            if (!lastName.isEmpty() || lastName.equals("\n")) {
                isBreak = true;
            } else {
                isBreak = false;
                System.out.println("Last name must not be empty!");
            }
        } while (!isBreak);
        return lastName;
    }

    public static Timestamp askAndGetBirthdate() {
        Timestamp timestamp = null;
        String ask = "Birth Date (dd/MM/yyyy)* (if input invalid, automatically get current date): ";
        System.out.print(ask);
        String input = scanner.nextLine();
        timestamp = DateTimeUtils.convertStringToTimeStamp(input,
                Config.NON_TIME_DATE_FORMAT);
        return timestamp;
    }

    public static boolean askAndGetSex() {
        boolean sex = false;
        boolean isBreak = false;
        do {
            try {
                System.out.print("Sex (1 for male, o for female): ");
                int input = Integer.parseInt(scanner.nextLine());
                if (input == 0 || input == 1) {
                    isBreak = true;
                    sex = input == 1;
                } else {
                    System.out.println("Please input 0 or 1!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please input 0 or 1!");
                isBreak = false;
            }
        } while (!isBreak);
        return sex;
    }

    public static String askAndGetPhone() {
        String phone = "";
        boolean isBreak = false;
        do {
            System.out.print("Phone*: ");
            phone = scanner.nextLine();
            if (!phone.isEmpty() || phone.equals("\n")) {
                isBreak = true;
            } else {
                isBreak = false;
                System.out.println("Phone must not be empty!");
            }
        } while (!isBreak);
        return phone;
    }

    public static String askAndGetEmail() {
        System.out.print("Email: ");
        String email = scanner.nextLine();
        return email;
    }

    public static String askAndGetAddress() {
        System.out.print("Address: ");
        String address = scanner.nextLine();
        return address;
    }

}
