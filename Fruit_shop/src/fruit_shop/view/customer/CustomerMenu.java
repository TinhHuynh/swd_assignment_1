/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruit_shop.view.customer;

import fruit_shop.view.Menu;
import fruit_shop.view.main.MainMenu;

/**
 *
 * @author TINH HUYNH
 */
public class CustomerMenu extends Menu {

   private static CustomerMenu menu;

    public static CustomerMenu getInstance() {
        if (menu == null) {
            menu = new CustomerMenu();
            menu.setParentMenu(MainMenu.getInstance());
        }
        return menu;
    }

    @Override
    public void setUpOptionItems() {
        optionItems = new String[]{
            "View customers",
            "View customer",
            "Add customer",
            "Update customer",
            "Back"};
    }

    @Override
    public void handleOptionItemSelected(int index) {
        switch (index) {
            case 1:
                goToCustomersTable();
                break;
            case 2:
                goToViewCustomer();
                break;
            case 3:
                goToAddCustomer();
                break;
            case 4:
                goToUpdateCustomer();
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
        System.out.println("========================== Product Menu ==========================");
        printOptionItems();
    }

    private void goToCustomersTable() {
        CustomersTable.getInstance().print();
    }

    private void goToViewCustomer() {
        ViewCustomerForm.getInstance().print();
    }

    private void goToAddCustomer() {
        AddCustomerForm.getInstance().print();
    }

    private void goToUpdateCustomer() {
        UpdateCustomerForm.getInstance().print();
    }
    
}
