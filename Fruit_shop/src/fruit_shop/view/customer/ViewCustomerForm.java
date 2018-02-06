/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruit_shop.view.customer;

import fruit_shop.utils.io.CustomerIOUtils;
import fruit_shop.model.database.customer.Customer;
import fruit_shop.model.database.customer.CustomerDAO;
import fruit_shop.view.View;

/**
 *
 * @author TINH HUYNH
 */
class ViewCustomerForm extends View{

   private static ViewCustomerForm form;
    
     public static ViewCustomerForm getInstance() {
        if (form == null) {
            form = new ViewCustomerForm();
            form.setParentMenu(CustomerMenu.getInstance());
        }
        return form;
    }

    @Override
    public void print() {
        System.out.println("");
        System.out.println("========================== View Customer ==========================");
        boolean isContinue;
        do {
            System.out.println("");
            int id = CustomerIOUtils.askAndGetId();
            Customer staff = new CustomerDAO().getCustomerById(id);
            System.out.println("Loading data. Please wait...");
            if (staff == null) {
                System.out.println("Staff is not found!");
            } else {
                System.out.println("");
                printCustomer(staff);
            }
            isContinue = askContinue();
        } while (isContinue);
        backToParentMenu();
    }

    private void printCustomer(Customer customer) {
        CustomerIOUtils.printCustomer(customer);
    }

}
