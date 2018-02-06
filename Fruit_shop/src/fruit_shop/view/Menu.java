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
public abstract class Menu extends View {

    protected String[] optionItems;

    protected Scanner scanner;

    public Menu() {
        scanner = new Scanner(System.in);
    }

    public abstract void setUpOptionItems();

    public abstract void handleOptionItemSelected(int index);

    public void printOptionItems() {
        setUpOptionItems();
        int index = 1;
        for (String optionItem : optionItems) {
            String formatItem = String.format("%d - %s",
                    index, optionItem);
            System.out.println(formatItem);
            index++;
        }
        System.out.print("Select an option item (1-" + optionItems.length + "): ");
        try {
            int selectedIndex = Integer.parseInt(scanner.nextLine());
            handleOptionItemSelected(selectedIndex);
        } catch (NumberFormatException ex) {
            onInvalidIndexSeleted();
        }
    }

    protected void onInvalidIndexSeleted() {
        System.out.println("Invalid selected index. Please type again!");
        printOptionItems();
    }

}
