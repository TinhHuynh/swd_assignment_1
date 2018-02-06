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
public class AddStaffForm extends View {

    private static AddStaffForm form;

    public static AddStaffForm getInstance() {
        if (form == null) {
            form = new AddStaffForm();
            form.setParentMenu(StaffMenu.getInstance());
        }
        return form;
    }

    @Override
    public void print() {
        System.out.println("");
        System.out.println("========================== Add Staff =============================");
        printAddStaffForm();
    }

    private void printAddStaffForm() {
        System.out.println("Note: * is required information");
        boolean isContinue;
        do {
            System.out.println("");
            String username = StaffIOUtils.askAndGetUsername();
            String password = StaffIOUtils.askAndGetPassword();
            String firstName = StaffIOUtils.askAndGetFirstName();
            String lastName = StaffIOUtils.askAndGetLastName();
            int role = StaffIOUtils.askAndGetRole();
            float salary = StaffIOUtils.askAndGetSalary();
            Timestamp joinDate = new Timestamp(System.currentTimeMillis());
            Timestamp birthdate = StaffIOUtils.askAndGetBirthdate();
            boolean sex = StaffIOUtils.askAndGetSex();
            String phone = StaffIOUtils.askAndGetPhone();
            String email = StaffIOUtils.askAndGetEmail();
            String address = StaffIOUtils.askAndGetAddress();
            Staff staff = (Staff) new Staff.Builder()
                        .username(username)
                        .password(password)
                        .salary(salary)
                        .onWork(true)
                        .role(Role.parseRole(role))
                        .firstName(firstName)
                        .lastName(lastName)
                        .address(address)
                        .phone(phone)
                        .email(email)
                        .birthDate(birthdate)
                        .joinDate(joinDate)
                        .sex(sex)
                        .build();
            System.out.println("Processing. Please wait...");
            boolean result = new StaffDAO().addStaff(staff);
            if (result) {
                System.out.println("Added successfully!");
            } else {
                System.out.println("Failed to add staff!");
            }
            isContinue = askContinue();
        } while (isContinue);
        if (!isContinue) {
            backToParentMenu();
        }
    }

}
