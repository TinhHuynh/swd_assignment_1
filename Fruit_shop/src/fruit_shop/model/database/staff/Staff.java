/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruit_shop.model.database.staff;

import fruit_shop.model.database.Person;
import java.sql.Timestamp;

/**
 *
 * @author TINH HUYNH
 */
public class Staff extends Person {

    private String username;
    private String password;
    private float salary;
    private Role role;
    private boolean onWork;

 
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isOnWork() {
        return onWork;
    }

    public void setOnWork(boolean onWork) {
        this.onWork = onWork;
    }
    
    public static class Builder extends Person.Builder<Staff>{
        private String username;
        private String password;
        private float salary;
        private Role role;
        private boolean onWork;
        
        public Builder username(String username) {
            this.username = username;
            return Builder.this;
        }

        public Builder password(String password) {
            this.password = password;
            return Builder.this;
        }

        public Builder role(Role role) {
            this.role = role;
            return Builder.this;
        }
        
        public Builder salary(float salary) {
            this.salary = salary;
            return Builder.this;
        }
        
        public Builder onWork(boolean onWork) {
            this.onWork = onWork;
            return Builder.this;
        }

        @Override
        public Staff build() {
            Staff staff = new Staff();
            staff.id = this.id;
            staff.username = this.username;
            staff.password = this.password;
            staff.address = this.address;
            staff.phone = this.phone;
            staff.email = this.email;
            staff.birthdate = this.birthDate;
            staff.joinDate = this.joinDate;
            staff.sex = this.sex;
            staff.role = this.role;
            staff.firstName = this.firstName;
            staff.lastName = this.lastName;
            staff.salary = this.salary;
            staff.onWork = this.onWork;
            return staff;
        }

    }
}
