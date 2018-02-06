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
class AddCustomerForm extends View {

    private static AddCustomerForm form;

    public static AddCustomerForm getInstance() {
        if (form == null) {
            form = new AddCustomerForm();
            form.setParentMenu(CustomerMenu.getInstance());
        }
        return form;
    }

    @Override
    public void print() {
        System.out.println("");
        System.out.println("========================== Add Customer =============================");
        printAddCustomerForm();
    }

    private void printAddCustomerForm() {
        System.out.println("Note: * is required information");
        boolean isContinue;
        do {
            System.out.println("");
            String firstName = CustomerIOUtils.askAndGetFirstName();
            String lastName = CustomerIOUtils.askAndGetLastName();
            Timestamp joinDate = new Timestamp(System.currentTimeMillis());
            Timestamp birthdate = CustomerIOUtils.askAndGetBirthdate();
            boolean sex = CustomerIOUtils.askAndGetSex();
            String phone = CustomerIOUtils.askAndGetPhone();
            String email = CustomerIOUtils.askAndGetEmail();
            String address = CustomerIOUtils.askAndGetAddress();
            Customer customer = (Customer) new Customer.Builder()
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
            boolean result = new CustomerDAO().addCustomer(customer);
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
