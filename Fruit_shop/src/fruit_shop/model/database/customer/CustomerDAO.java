/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruit_shop.model.database.customer;

import fruit_shop.model.database.DAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TINH HUYNH
 */
public class CustomerDAO extends DAO {

    public Customer getCustomerById(int id) {
        Customer customer = null;
        if(id == 0){
            return customer;
        }
        prepareConnection();
        try {
            String sql = "SELECT %s, %s, %s, %s, "
                    + "%s, %s, %s, %s, %s "
                    + "FROM customer "
                    + "WHERE %s = ?";
            sql = String.format(sql,
                    CustomerSchema.COL_ID,
                    CustomerSchema.COL_FIRST_NAME,
                    CustomerSchema.COL_LAST_NAME,
                    CustomerSchema.COL_BIRTH_DATE,
                    CustomerSchema.COL_ADDRESS,
                    CustomerSchema.COL_PHONE,
                    CustomerSchema.COL_EMAIL,
                    CustomerSchema.COL_SEX,
                    CustomerSchema.COL_JOIN_DATE,
                    CustomerSchema.COL_ID);
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                customer = (Customer) new Customer.Builder()
                        .id(resultSet.getInt(CustomerSchema.COL_ID))
                        .firstName(resultSet.getString(CustomerSchema.COL_FIRST_NAME))
                        .lastName(resultSet.getString(CustomerSchema.COL_LAST_NAME))
                        .address(resultSet.getString(CustomerSchema.COL_ADDRESS))
                        .phone(resultSet.getString(CustomerSchema.COL_PHONE))
                        .email(resultSet.getString(CustomerSchema.COL_EMAIL))
                        .birthDate(resultSet.getTimestamp(CustomerSchema.COL_BIRTH_DATE))
                        .joinDate(resultSet.getTimestamp(CustomerSchema.COL_JOIN_DATE))
                        .sex(resultSet.getBoolean(CustomerSchema.COL_SEX))
                        .build();
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return customer;
    }

    public List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();
        prepareConnection();
        try {
            String sql = "SELECT %s, %s, %s, %s, "
                    + "%s, %s, %s, %s, %s "
                    + "FROM customer ";
            sql = String.format(sql,
                    CustomerSchema.COL_ID,
                    CustomerSchema.COL_FIRST_NAME,
                    CustomerSchema.COL_LAST_NAME,
                    CustomerSchema.COL_BIRTH_DATE,
                    CustomerSchema.COL_ADDRESS,
                    CustomerSchema.COL_PHONE,
                    CustomerSchema.COL_EMAIL,
                    CustomerSchema.COL_SEX,
                    CustomerSchema.COL_JOIN_DATE);
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Customer customer = (Customer) new Customer.Builder()
                        .id(resultSet.getInt(CustomerSchema.COL_ID))
                        .firstName(resultSet.getString(CustomerSchema.COL_FIRST_NAME))
                        .lastName(resultSet.getString(CustomerSchema.COL_LAST_NAME))
                        .address(resultSet.getString(CustomerSchema.COL_ADDRESS))
                        .phone(resultSet.getString(CustomerSchema.COL_PHONE))
                        .email(resultSet.getString(CustomerSchema.COL_EMAIL))
                        .birthDate(resultSet.getTimestamp(CustomerSchema.COL_BIRTH_DATE))
                        .joinDate(resultSet.getTimestamp(CustomerSchema.COL_JOIN_DATE))
                        .sex(resultSet.getBoolean(CustomerSchema.COL_SEX))
                        .build();
                if (customer.getId() > 0) {
                    customers.add(customer);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return customers;
    }

    public boolean addCustomer(Customer customer) {
        boolean result = false;
        prepareConnection();
        try {
            String sql = "INSERT INTO customer ("
                    + "%s, %s, %s, %s, %s, %s, %s, %s) "
                    + " VALUES(?,?,?,?,?,?,?,?) ";
            sql = String.format(sql,
                    CustomerSchema.COL_FIRST_NAME,
                    CustomerSchema.COL_LAST_NAME,
                    CustomerSchema.COL_BIRTH_DATE,
                    CustomerSchema.COL_ADDRESS,
                    CustomerSchema.COL_PHONE,
                    CustomerSchema.COL_EMAIL, 
                    CustomerSchema.COL_SEX,
                    CustomerSchema.COL_JOIN_DATE);
            statement = connection.prepareStatement(sql);
            statement.setString(1, customer.getFirstName());
            statement.setString(2, customer.getLastName());
            statement.setTimestamp(3, customer.getBirthday());
            statement.setString(4, customer.getAddress());
            statement.setString(5, customer.getPhone());
            statement.setString(6, customer.getEmail());
            statement.setBoolean(7, customer.isSex());
            statement.setTimestamp(8, customer.getJoinDate());
            result = statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean updateCustomer(Customer customer) {
        boolean result = false;
        try {
            prepareConnection();
            String sql = "UPDATE customer SET "
                    + CustomerSchema.COL_FIRST_NAME + " = ?, "
                    + CustomerSchema.COL_LAST_NAME + " = ?, "
                    + CustomerSchema.COL_BIRTH_DATE + " = ?, "
                    + CustomerSchema.COL_SEX + " = ?, "
                    + CustomerSchema.COL_PHONE + " = ?, "
                    + CustomerSchema.COL_EMAIL + " = ?, "
                    + CustomerSchema.COL_ADDRESS + " = ? ";
            sql += " WHERE " + CustomerSchema.COL_ID + " = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, customer.getFirstName());
            statement.setString(2, customer.getLastName());
            statement.setTimestamp(3, customer.getBirthday());
            statement.setBoolean(4, customer.isSex());
            statement.setString(5, customer.getPhone());
            statement.setString(6, customer.getEmail());
            statement.setString(7, customer.getAddress());
            statement.setInt(8, customer.getId());
            result = statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
