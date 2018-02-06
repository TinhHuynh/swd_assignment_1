/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruit_shop.view.order;

import fruit_shop.view.Menu;
import fruit_shop.view.main.MainMenu;

/**
 *
 * @author TINH HUYNH
 */
public class OrderMenu extends Menu {

    private static OrderMenu menu;

    public static OrderMenu getInstance() {
        if (menu == null) {
            menu = new OrderMenu();
            menu.setParentMenu(MainMenu.getInstance());
        }
        return menu;
    }

    @Override
    public void setUpOptionItems() {
        optionItems = new String[]{
            "View Orders",
            "View Order",
            "Add Order",
            "Back"
        };
    }

    @Override
    public void handleOptionItemSelected(int index) {
        switch (index) {
            case 1:
                goToOrderTable();
                break;
            case 2:
                goToViewOrder();
                break;
            case 3:
                goToAddOrder();
                break;
            case 4:
                backToParentMenu();
                break;
            default:
                onInvalidIndexSeleted();
        }
    }

    @Override
    public void print() {
        System.out.println("");
        System.out.println("========================== Order Menu  ==========================");
        printOptionItems();
    }

    private void goToViewOrder() {
        ViewOrderForm.getInstance().print();
    }

    private void goToAddOrder() {
        AddOrderForm.getInstance().print();
    }

    private void goToOrderTable() {
        OrdersTable.getInstance().print();
    }

}
