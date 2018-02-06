/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruit_shop.view.customer;

import fruit_shop.Config;
import fruit_shop.model.database.customer.Customer;
import fruit_shop.model.database.customer.CustomerDAO;
import fruit_shop.utils.DateTimeUtils;
import fruit_shop.view.Table;
import java.util.List;

/**
 *
 * @author TINH HUYNH
 */
class CustomersTable extends Table {

    private static CustomersTable table;

    public static CustomersTable getInstance() {
        if (table == null) {
            table = new CustomersTable();
            table.setParentMenu(CustomerMenu.getInstance());
        }
        return table;
    }

    @Override
    public void print() {
        System.out.println("");
        System.out.println("========================== Product Table =============================");
        System.out.println("");
        System.out.println("Loading data. Please wait...");
        System.out.println("");
        List<Customer> staffs = new CustomerDAO().getCustomers();
        printCustomers(staffs);
        System.out.println("");
        askGoBack();
    }

    private void printCustomers(List<Customer> customers) {
        if (!customers.isEmpty()) {
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-10s%-20s%-25s%-20s%-20s%-25s%s\n",
                    "Id", "First name", "Last name", "Join date", "Phone", "Email", "Address");
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            customers.forEach((customer) -> {
                System.out.printf("%-10d%-20s%-25s%-20s%-20s%-25s%s\n",
                        customer.getId(), 
                        customer.getFirstName(), customer.getLastName(),
                        DateTimeUtils.convertTimestampToString(customer.getJoinDate(), Config.NON_TIME_DATE_FORMAT),
                        customer.getPhone(), customer.getEmail(), customer.getAddress());
            });
        } else {
            System.out.println("There is no customer in the database. Please add one!");
        }
    }
}
