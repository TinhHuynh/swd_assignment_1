/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruit_shop.view.main;

import fruit_shop.SessionData;
import fruit_shop.view.Menu;
import fruit_shop.view.customer.CustomerMenu;
import fruit_shop.view.staff.StaffMenu;
import fruit_shop.view.product.ProductMenu;
import fruit_shop.view.login.LoginForm;
import fruit_shop.view.order.OrderMenu;
import fruit_shop.view.statistic.StatisticMenu;

/**
 *
 * @author TINH HUYNH
 */
public class MainMenu extends Menu {

    private static final String[] ADMIN_OPTION_ITEMS = {
        "Products",
        "Orders",
        "Statistics",
        "Staff",
        "Customers",
        "Log Out"};

    private static final String[] CLERK_OPTION_ITEMS = {
        "Products",
        "Orders",
        "Customers",
        "Log Out"};

    private static MainMenu menu;

    public static MainMenu getInstance() {
        if (menu == null) {
            menu = new MainMenu();
            menu.setParentMenu(null);
        }
        return menu;
    }

    @Override
    public void setUpOptionItems() {
        switch (SessionData.user.getRole()) {
            case ADMIN:
                optionItems = ADMIN_OPTION_ITEMS;
                break;
            case CLERK:
                optionItems = CLERK_OPTION_ITEMS;
                break;
        }
    }

    @Override
    public void print() {
        System.out.println("");
        System.out.println("========================== Main Menu  ==========================");
        printOptionItems();
    }

    @Override
    public void handleOptionItemSelected(int index) {
        switch (SessionData.user.getRole()) {
            case ADMIN:
                handleAdminOptionItemSelected(index);
                break;
            case CLERK:
                handleClerkOptionItemSelected(index);
                break;
        }
    }

    private void handleAdminOptionItemSelected(int index) {
        switch (index) {
            case 1:
                goToFruitMenu();
                break;
            case 2:
                gotoOrderMenu();
                break;
            case 3:
                goToStatisticMenu();
                break;
            case 4:
                goToStaffMenu();
                break;
            case 5:
                goToCustomerMenu();
                break;
            case 6:
                goToLoginForm();
                break;
            default:
                onInvalidIndexSeleted();
        }
    }

    private void handleClerkOptionItemSelected(int index) {
        switch (index) {
            case 1:
                goToFruitMenu();
                break;
            case 2:
                gotoOrderMenu();
                break;
            case 3:
                goToCustomerMenu();
                break;
            case 4:
                goToLoginForm();
                break;
            default:
                onInvalidIndexSeleted();
        }
    }

    private void goToFruitMenu() {
        ProductMenu.getInstance().print();
    }

    private void goToLoginForm() {
        SessionData.user = null;
        LoginForm.getInstance().print();
    }

    private void gotoOrderMenu() {
        OrderMenu.getInstance().print();
    }

    private void goToStatisticMenu() {
        StatisticMenu.getInstance().print();
    }

    private void goToStaffMenu() {
        StaffMenu.getInstance().print();
    }

    private void goToCustomerMenu() {
        CustomerMenu.getInstance().print();
    }
}
