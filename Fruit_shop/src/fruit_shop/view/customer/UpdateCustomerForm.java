/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruit_shop.view.customer;

import fruit_shop.model.database.customer.Customer;
import fruit_shop.model.database.customer.CustomerDAO;
import fruit_shop.model.database.staff.Staff;
import fruit_shop.utils.io.CustomerIOUtils;
import fruit_shop.view.View;
import java.sql.Timestamp;

/**
 *
 * @author TINH HUYNH
 */
class UpdateCustomerForm extends View {

     private static UpdateCustomerForm form;

    public static UpdateCustomerForm getInstance() {
        if (form == null) {
            form = new UpdateCustomerForm();
            form.setParentMenu(CustomerMenu.getInstance());
        }
        return form;
    }

    @Override
    public void print() {
        System.out.println("");
        System.out.println("========================== Update Customer =============================");
        printUpdateStaffForm();
    }        
        
    private void printUpdateStaffForm() {
        CustomerDAO dAO = new CustomerDAO();
        boolean isContinue;
        do {
            System.out.println("");
            int id = CustomerIOUtils.askAndGetId();
            Customer customer = dAO.getCustomerById(id);
            if (customer == null) {
                System.out.println("Staff is not found!");
            } else {
                System.out.println("Staff is found!");
                System.out.println("");
                CustomerIOUtils.printCustomer(customer);
                System.out.println("");
                String firstName = CustomerIOUtils.askAndGetFirstName();
                String lastName = CustomerIOUtils.askAndGetLastName();
                Timestamp birthdate = CustomerIOUtils.askAndGetBirthdate();
                boolean sex = CustomerIOUtils.askAndGetSex();
                String phone = CustomerIOUtils.askAndGetPhone();
                String email = CustomerIOUtils.askAndGetEmail();
                String address = CustomerIOUtils.askAndGetAddress();
                customer = (Customer) new Customer.Builder()
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
                boolean result = dAO.updateCustomer(customer);
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
