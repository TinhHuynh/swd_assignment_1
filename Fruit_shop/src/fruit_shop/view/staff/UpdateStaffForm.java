/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruit_shop.view.staff;

import fruit_shop.model.database.staff.Role;
import fruit_shop.model.database.staff.Staff;
import fruit_shop.model.database.staff.StaffDAO;
import fruit_shop.view.View;
import java.sql.Timestamp;

/**
 *
 * @author TINH HUYNH
 */
public class UpdateStaffForm extends View {

    private static UpdateStaffForm form;

    public static UpdateStaffForm getInstance() {
        if (form == null) {
            form = new UpdateStaffForm();
            form.setParentMenu(StaffMenu.getInstance());
        }
        return form;
    }

    @Override
    public void print() {
        System.out.println("");
        System.out.println("========================== Update Staff =============================");
        printUpdateStaffForm();
    }

    private void printUpdateStaffForm() {
        StaffDAO dAO = new StaffDAO();
        boolean isContinue;
        do {
            System.out.println("");
            int id = StaffIOUtils.askAndGetId();
            Staff staff = dAO.getStaffById(id);
            if (staff == null) {
                System.out.println("Staff is not found!");
            } else {
                System.out.println("Staff is found!");
                System.out.println("");
                StaffIOUtils.printStaff(staff);
                System.out.println("");
                String password = StaffIOUtils.askAndGetPassword();
                String firstName = StaffIOUtils.askAndGetFirstName();
                String lastName = StaffIOUtils.askAndGetLastName();
                int role = StaffIOUtils.askAndGetRole();
                float salary = StaffIOUtils.askAndGetSalary();
                boolean onWork = StaffIOUtils.askAndGetOnWork();
                Timestamp birthdate = StaffIOUtils.askAndGetBirthdate();
                boolean sex = StaffIOUtils.askAndGetSex();
                String phone = StaffIOUtils.askAndGetPhone();
                String email = StaffIOUtils.askAndGetEmail();
                String address = StaffIOUtils.askAndGetAddress();
                staff = (Staff) new Staff.Builder()
                        .password(password)
                        .salary(salary)
                        .onWork(onWork)
                        .role(Role.parseRole(role))
                        .id(id)
                        .firstName(firstName)
                        .lastName(lastName)
                        .address(address)
                        .phone(phone)
                        .email(email)
                        .birthDate(birthdate)
                        .sex(sex)
                        .build();
                System.out.println("Processing. Please wait...");
                boolean result = dAO.updateStaff(staff);
                if (result) {
                    System.out.println("Updated successfully!");
                } else {
                    System.out.println("Failed to update staff!");
                }
            }
            isContinue = askContinue();
        } while (isContinue);
        backToParentMenu();
    }

}
