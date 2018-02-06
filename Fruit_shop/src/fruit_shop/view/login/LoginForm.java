/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruit_shop.view.login;

import fruit_shop.SessionData;
import java.util.Scanner;
import fruit_shop.model.database.staff.Staff;
import fruit_shop.model.database.staff.StaffDAO;
import fruit_shop.view.View;
import fruit_shop.view.main.MainMenu;
import java.io.Console;

/**
 *
 * @author TINH HUYNH
 */
public class LoginForm extends View {

    private static LoginForm loginActivity;

    private Scanner scanner;

    private LoginForm() {
        scanner = new Scanner(System.in);
    }

    public static LoginForm getInstance() {
        if (loginActivity == null) {
            loginActivity = new LoginForm();
        }
        return loginActivity;
    }

    private String askAndGetUsername() {
        System.out.print("Username: ");
        return scanner.nextLine();
    }

    private String askAndGetPassword() {
        Console console = System.console();
        String password = "";
        if (console == null) {
            System.out.print("Password: ");
            password = scanner.nextLine();
        } else {
             char passwordArray[] = console.readPassword("Password: ");
             password = new String(passwordArray);
        }
        return password;
    }

    private void verifyAccount(String username, String password) {
        System.out.println("Verifying. Please wait...");
        Staff user = new StaffDAO().verifyAccount(username, password);
        if (user == null) {
            System.out.println("Invalid username and password!. Please try again");
            printLoginForm();
        } else {
            SessionData.user = user;
            System.out.println("Login successfully !!!");
            System.out.println("Welcome " + SessionData.user.getFirstName() + "!");
            goToMainMenu();
        }
    }

    private void goToMainMenu() {
        MainMenu.getInstance().print();
    }

    @Override
    public void print() {
        System.out.println("");
        System.out.println("========================== Login Form  ==========================");
        printLoginForm();
    }

    private void printLoginForm() {
        String username = askAndGetUsername();
        String password = askAndGetPassword();
        verifyAccount(username, password);
    }

}
