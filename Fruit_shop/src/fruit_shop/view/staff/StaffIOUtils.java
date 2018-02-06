/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruit_shop.view.staff;

import fruit_shop.Config;
import fruit_shop.model.database.staff.Role;
import fruit_shop.model.database.staff.Staff;
import fruit_shop.utils.DateTimeUtils;
import fruit_shop.utils.SexUtils;
import fruit_shop.utils.io.PersonIOUtils;
import java.sql.Timestamp;
import java.util.Scanner;

/**
 *
 * @author TINH HUYNH
 */
public class StaffIOUtils extends PersonIOUtils {

    private static Scanner scanner = new Scanner(System.in);

    public static int askAndGetId() {
        int id = -1;
        boolean isBreak = false;
        do {
            try {
                System.out.print("Staff id: ");
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

    public static String askAndGetUsername() {
        String username = "";
        boolean isBreak = false;
        do {
            System.out.print("Username*: ");
            username = scanner.nextLine();
            if (!username.isEmpty() || username.equals("\n")) {
                isBreak = true;
            } else {
                isBreak = false;
                System.out.println("Username must not be empty!");
            }
        } while (!isBreak);
        return username;
    }

    public static String askAndGetPassword() {
        String password = "";
        boolean isBreak = false;
        do {
            System.out.print("Password*: ");
            password = scanner.nextLine();
            if (!password.isEmpty() || password.equals("\n")) {
                isBreak = true;
            } else {
                isBreak = false;
                System.out.println("Password must not be empty!");
            }
        } while (!isBreak);
        return password;
    }

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

    public static void printStaff(Staff staff) {
        if (staff == null) {
            System.out.println("Staff is not found.");
        } else {
            System.out.println("Result:");
            System.out.printf("%-20s: %d\n", "Id", staff.getId());
            System.out.printf("%-20s: %s\n", "Username", staff.getUsername());
            System.out.printf("%-20s: %s\n", "Password", staff.getPassword());
            System.out.printf("%-20s: %s\n", "First name", staff.getFirstName());
            System.out.printf("%-20s: %s\n", "Last name", staff.getLastName());
            System.out.printf("%-20s: %s\n", "Role", staff.getRole().value());
            System.out.printf("%-20s: %.0f\n", "Salary", staff.getSalary());
            System.out.printf("%-20s: %s\n", "Join date", DateTimeUtils.convertTimestampToString(staff.getJoinDate(), Config.NON_TIME_DATE_FORMAT));
            System.out.printf("%-20s: %b\n", "On hired", staff.isOnWork());
            System.out.printf("%-20s: %s\n", "Birth date", DateTimeUtils.convertTimestampToString(staff.getBirthday(), Config.NON_TIME_DATE_FORMAT));
            System.out.printf("%-20s: %s\n", "Sex", SexUtils.parseToString(staff.isSex()));
            System.out.printf("%-20s: %s\n", "Phone", staff.getPhone());
            System.out.printf("%-20s: %s\n", "Email", staff.getEmail());
            System.out.printf("%-20s: %s\n", "Address", staff.getAddress());
        }
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

    public static int askAndGetRole() {
        int i = 1;
        System.out.println("Role list:");
        for (Role role : Role.values()) {
            System.out.println(i + " - " + role.value());
            i++;
        }
        int roleId = 0;
        boolean isBreak = false;
        do {
            try {
                System.out.print("Role id [1-2]*: ");
                roleId = Integer.parseInt(scanner.nextLine());
                if (roleId == 1 || roleId == 2) {
                    isBreak = true;
                } else {
                    isBreak = false;
                    System.out.println("Role id must be in range of [1-2]!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Category must be in range of [1-2]!");
                isBreak = false;
            }
        } while (!isBreak);
        return roleId;
    }

    public static float askAndGetSalary() {
        float salary = 0.0f;
        boolean isBreak = false;
        do {
            try {
                System.out.print("Salary: ");
                salary = Float.parseFloat(scanner.nextLine());
                isBreak = true;
            } catch (NumberFormatException e) {
                System.out.println("Salary must be a number");
                isBreak = false;
            }
        } while (!isBreak);
        return salary;
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
                sex = input == 1;
                isBreak = true;
            } catch (NumberFormatException e) {
                System.out.println("Please input 0 or 1!");
                isBreak = false;
            }
        } while (!isBreak);
        return sex;
    }

    public static boolean askAndGetOnWork() {
        boolean onWork = false;
        boolean isBreak = false;
        do {
            try {
                System.out.print("On hired ? (1 for yes, o for no): ");
                int input = Integer.parseInt(scanner.nextLine());
                onWork = input == 1;
                isBreak = true;
            } catch (NumberFormatException e) {
                System.out.println("Please input 0 or 1!");
                isBreak = false;
            }
        } while (!isBreak);
        return onWork;
    }


}
