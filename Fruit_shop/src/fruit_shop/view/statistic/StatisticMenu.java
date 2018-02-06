/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruit_shop.view.statistic;

import fruit_shop.view.Menu;
import fruit_shop.view.main.MainMenu;

/**
 *
 * @author TINH HUYNH
 */
public class StatisticMenu extends Menu {

    private static StatisticMenu menu;

    public static StatisticMenu getInstance() {
        if (menu == null) {
            menu = new StatisticMenu();
            menu.setParentMenu(MainMenu.getInstance());
        }
        return menu;
    }

    @Override
    public void setUpOptionItems() {
        optionItems = new String[]{
            "Income by orders",
            "Income by employees",
            "Income by products",
            "Income by customers",
            "Back"
        };
    }

    @Override
    public void handleOptionItemSelected(int index) {
        switch (index) {
            case 1:
                goToOrdersIncomeTable();
                break;
            case 2:
                goToEmployeesIncomeTable();
                break;
            case 3:
                goToProductsIncomeTable();
                break;
            case 4:
                goToCustmersIncomeTable();
                break;
            case 5:
                backToParentMenu();
                break;
            default:
                onInvalidIndexSeleted();
        }
    }

    @Override
    public void print() {
        System.out.println("");
        System.out.println("========================== Statistic Menu  ==========================");
        printOptionItems();
    }

    private void goToOrdersIncomeTable() {
        OrdersIncomeTable.getInstance().print();
    }

    private void goToEmployeesIncomeTable() {
        StaffIncomeTable.getInstance().print();
    }

    private void goToProductsIncomeTable() {
        ProductIncomeTable.getInstance().print();
    }

    private void goToCustmersIncomeTable() {
        CustomerIncomeTable.getInstance().print();
    }

}
