/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruit_shop.model.database;

import java.sql.Timestamp;

/**
 *
 * @author TINH HUYNH
 */
public abstract class Person {

    protected int id;
    protected String firstName;
    protected String lastName;
    protected String address;
    protected String phone;
    protected String email;
    protected Timestamp birthdate;
    // 0 is female, 1 is male
    protected boolean sex;
    protected Timestamp joinDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public Timestamp getBirthday() {
        return birthdate;
    }

    public void setBirthday(Timestamp birthdate) {
        this.birthdate = birthdate;
    }

    public Timestamp getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Timestamp joinDate) {
        this.joinDate = joinDate;
    }

    public String getFullName() {
        return lastName + " " + firstName;
    }

    public abstract static class Builder<T extends Person> {

        protected int id;
        protected String firstName;
        protected String lastName;
        protected Timestamp birthDate;
        protected String address;
        protected String phone;
        protected String email;
        // 1 is male, 0 is female 
        protected boolean sex;
        protected Timestamp joinDate;

        public Builder id(int id) {
            this.id = id;
            return Builder.this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return Builder.this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return Builder.this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return Builder.this;
        }

        public Builder address(String address) {
            this.address = address;
            return Builder.this;
        }

        public Builder email(String email) {
            this.email = email;
            return Builder.this;
        }

        public Builder birthDate(Timestamp birthDate) {
            this.birthDate = birthDate;
            return Builder.this;
        }

        public Builder sex(boolean isMale) {
            this.sex = isMale;
            return Builder.this;
        }

        public Builder joinDate(Timestamp joinDate) {
            this.joinDate = joinDate;
            return Builder.this;
        }

        public abstract T build();

    }
}
