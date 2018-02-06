/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruit_shop.model.database.shipping_info;

import fruit_shop.Config;
import fruit_shop.utils.DateTimeUtils;
import java.sql.Timestamp;
import java.util.Scanner;

/**
 *
 * @author TINH HUYNH
 */
public class ShippingInfoUtils {

    private static Scanner scanner = new Scanner(System.in);

    public static String askAndGetShipAddress(String defaultValue) {
        String shipAddress = "";
        boolean isBreak = false;
        do {
            String ask = "Address* : ";
            if (!defaultValue.isEmpty()) {
                ask = String.format("Address (hit Enter to use default value: %s) :", defaultValue);
            }
            System.out.print(ask);
            shipAddress = scanner.nextLine();
            if ((shipAddress.isEmpty() && !defaultValue.isEmpty())
                    || !shipAddress.isEmpty()) {
                if (shipAddress.isEmpty() && !defaultValue.isEmpty()) {
                    shipAddress = defaultValue;
                }
                isBreak = true;
            } else {
                isBreak = false;
                System.out.println("Address must not be empty!");
            }
        } while (!isBreak);
        return shipAddress;
    }

    public static String askAndGetShipName(String defaultValue) {
        String name = "";
        boolean isBreak = false;
        do {
            String ask = "Name* : ";
            if (!defaultValue.isEmpty()) {
                ask = String.format("Name (hit Enter to use default value: %s) :", defaultValue);
            }
            System.out.print(ask);
            name = scanner.nextLine();
            if ((name.isEmpty() && !defaultValue.isEmpty())
                    || !name.isEmpty()) {
                if (name.isEmpty() && !defaultValue.isEmpty()) {
                    name = defaultValue;
                }
                isBreak = true;
            } else {
                isBreak = false;
                System.out.println("Name must not be empty!");
            }
        } while (!isBreak);
        return name;
    }

    public static String askAndGetShipPhone(String defaultValue) {
        String phone = "";
        boolean isBreak = false;
        do {
            String ask = "Phone* : ";
            if (!defaultValue.isEmpty()) {
                ask = String.format("Phone (hit Enter to use default value: %s) :", defaultValue);
            }
            System.out.print(ask);
            phone = scanner.nextLine();
            if ((phone.isEmpty() && !defaultValue.isEmpty())
                    || !phone.isEmpty()) {
                if (phone.isEmpty() && !defaultValue.isEmpty()) {
                    phone = defaultValue;
                }
                isBreak = true;
            } else {
                isBreak = false;
                System.out.println("Phone must not be empty!");
            }
        } while (!isBreak);
        return phone;
    }

    public static Timestamp askAndGetShipDate() {
        Timestamp timestamp = null;
        boolean isBreak = false;
        String ask = "Date (dd/MM/yyyy)* (hit Enter or any string to use current date): ";
        System.out.print(ask);
        String input = scanner.nextLine();
        timestamp = DateTimeUtils.convertStringToTimeStamp(input,
                Config.NON_TIME_DATE_FORMAT);
        return timestamp;
    }

    public static boolean askShippingNeeded() {
        boolean result;
        System.out.print("Need shipping ? type Y or y for yes, or any key for no: ");
        result = scanner.nextLine().equalsIgnoreCase("y");
        return result;
    }

    public static float askAndGetFee() {
        float fee = 0.0f;
        boolean isBreak = false;
        do {
            try {
                System.out.print("Fee*: ");
                fee = Float.parseFloat(scanner.nextLine());
                isBreak = true;
            } catch (NumberFormatException e) {
                System.out.println("Fee must be a number!");
                isBreak = false;
            }
        } while (!isBreak);
        return fee;
    }
}
