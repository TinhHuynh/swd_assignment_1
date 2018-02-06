/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruit_shop.view.staff;

import fruit_shop.view.Menu;
import fruit_shop.view.main.MainMenu;

/**
 *
 * @author TINH HUYNH
 */
public class StaffMenu extends Menu {
    
    private static StaffMenu menu;

    public static StaffMenu getInstance() {
        if (menu == null) {
            menu = new StaffMenu();
            menu.setParentMenu(MainMenu.getInstance());
        }
        return menu;
    }

    @Override
    public void setUpOptionItems() {
        optionItems = new String[]{
            "View Staffs",
            "View Staff",
            "Add Staff",
            "Update Staff",
            "Back"};
    }

    @Override
    public void handleOptionItemSelected(int index) {
        switch (index) {
            case 1:
                goToStaffsTable();
                break;
            case 2:
                goToViewStaff();
                break;
            case 3:
                goToAddStaff();
                break;
            case 4:
                goToUpdateProduct();
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
        System.out.println("========================== Staff Menu ==========================");
        printOptionItems();
    }

    private void goToStaffsTable() {
        StaffsTable.getInstance().print();
    }

    private void goToViewStaff() {
        ViewStaffForm.getInstance().print();
    }

    private void goToAddStaff() {
        AddStaffForm.getInstance().print();
    }

    private void goToUpdateProduct() {
        UpdateStaffForm.getInstance().print();
    }
  
}
