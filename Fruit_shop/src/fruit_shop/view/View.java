/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruit_shop.view;

import java.util.Scanner;

/**
 *
 * @author TINH HUYNH
 */
public abstract class View {

    protected Menu parentMenu;

    public abstract void print();

    public Menu getParentMenu() {
        return parentMenu;
    }

    public void setParentMenu(Menu parentMenu) {
        this.parentMenu = parentMenu;
    }

    protected void backToParentMenu() {
        if (parentMenu != null) {
            parentMenu.print();
        }
    }

    protected static Scanner getScanner() {
        return new Scanner(System.in);
    }

    protected void askGoBack() {
        String input = "";
        do {
            System.out.print("Type b/B to go back:");
            Scanner scanner = new Scanner(System.in);
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("b")) {
                parentMenu.print();
                break;
            }
        } while (!input.equalsIgnoreCase("b"));
    }

    protected boolean askContinue() {
        String input = "";
        boolean isContinue;
        System.out.print("Do you want to continue ? Type y for yes or any key for no: ");
        Scanner scanner = new Scanner(System.in);
        input = scanner.nextLine();
        return input.equalsIgnoreCase("y");
    }
    
    protected void clearConsole(){
        System.out.print("\033[H\033[2J");
    }

}
