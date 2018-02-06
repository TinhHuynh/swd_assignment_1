/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruit_shop.view.staff;

import fruit_shop.model.database.staff.Staff;
import fruit_shop.model.database.staff.StaffDAO;
import fruit_shop.view.View;

/**
 *
 * @author TINH HUYNH
 */
public class ViewStaffForm extends View {
    
    private static ViewStaffForm form;
    
     public static ViewStaffForm getInstance() {
        if (form == null) {
            form = new ViewStaffForm();
            form.setParentMenu(StaffMenu.getInstance());
        }
        return form;
    }

    @Override
    public void print() {
        System.out.println("");
        System.out.println("========================== View Staff ==========================");
        boolean isContinue;
        do {
            System.out.println("");
            int id = StaffIOUtils.askAndGetId();
            Staff staff = new StaffDAO().getStaffById(id);
            System.out.println("Loading data. Please wait...");
            if (staff == null) {
                System.out.println("Staff is not found!");
            } else {
                System.out.println("");
                printStaff(staff);
            }
            isContinue = askContinue();
        } while (isContinue);
        backToParentMenu();
    }

    private void printStaff(Staff staff) {
        StaffIOUtils.printStaff(staff);
    }
    
    
}
