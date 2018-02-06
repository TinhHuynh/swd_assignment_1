/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruit_shop.model.database.customer;

import fruit_shop.model.database.Person;

/**
 *
 * @author TINH HUYNH
 */
public class Customer extends Person {

    public static class Builder extends Person.Builder<Customer> {

        @Override
        public Customer build() {
            Customer customer = new Customer();
            customer.id = this.id;
            customer.firstName = this.firstName;
            customer.lastName = this.lastName;
            customer.address = this.address;
            customer.email = this.email;
            customer.phone = this.phone;
            customer.sex = this.sex;
            customer.joinDate = this.joinDate;
            customer.birthdate = this.birthDate;
            return customer;
        }

    }
}
