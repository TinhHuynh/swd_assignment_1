/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruit_shop.model.database.staff;

import fruit_shop.model.database.DAO;
import static fruit_shop.model.database.staff.StaffSchema.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TINH HUYNH
 */
public class StaffDAO extends DAO {

    public Staff verifyAccount(String username, String password) {
        Staff result = null;
        try {
            prepareConnection();

            String sql = "SELECT "
                    + TABLE_NAME + "." + COL_ID + ", " + TABLE_NAME + "." + COL_FIRST_NAME + ", " + " [role].name as [role] "
                    + "FROM " + TABLE_NAME + " "
                    + "INNER JOIN role ON " + TABLE_NAME + "." + COL_ROLE_ID + " = [role].id "
                    + "WHERE " + COL_USERNAME + " = ? "
                    + "AND " + COL_PASSWORD + " = ? "
                    + "AND " + COL_ON_WORK + " = 1";
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Staff.Builder builder = (Staff.Builder) new Staff.Builder()
                        .role(Role.parseRole(resultSet.getString("role")))
                        .id(resultSet.getInt(COL_ID))
                        .firstName(resultSet.getString(COL_FIRST_NAME));
                result = builder.build();
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<Staff> getStaffs() {
        List<Staff> staffs = new ArrayList<>();
        prepareConnection();
        try {
            String sql = "SELECT staff.%s, staff.%s, staff.%s, staff.%s, "
                    + "staff.%s, staff.%s, staff.%s, staff.%s, staff.%s, "
                    + "staff.%s, staff.%s, staff.%s, "
                    + "staff.%s, role.name AS role_name "
                    + "FROM staff "
                    + "INNER JOIN [role] ON [role].id = %s";
            sql = String.format(sql,
                    StaffSchema.COL_ID, StaffSchema.COL_USERNAME,
                    StaffSchema.COL_PASSWORD, StaffSchema.COL_FIRST_NAME,
                    StaffSchema.COL_LAST_NAME, StaffSchema.COL_BIRTH_DATE,
                    StaffSchema.COL_ADDRESS, StaffSchema.COL_PHONE,
                    StaffSchema.COL_EMAIL, StaffSchema.COL_SEX,
                    StaffSchema.COL_JOIN_DATE, StaffSchema.COL_SALARY,
                    StaffSchema.COL_ON_WORK,
                    StaffSchema.COL_ROLE_ID);
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Staff staff = (Staff) new Staff.Builder()
                        .username(resultSet.getString(StaffSchema.COL_USERNAME))
                        .password(resultSet.getString(StaffSchema.COL_PASSWORD))
                        .salary(resultSet.getFloat(StaffSchema.COL_SALARY))
                        .onWork(resultSet.getBoolean(StaffSchema.COL_ON_WORK))
                        .role(Role.parseRole(resultSet.getString("role_name")))
                        .id(resultSet.getInt(StaffSchema.COL_ID))
                        .firstName(resultSet.getString(StaffSchema.COL_FIRST_NAME))
                        .lastName(resultSet.getString(StaffSchema.COL_LAST_NAME))
                        .address(resultSet.getString(StaffSchema.COL_ADDRESS))
                        .phone(resultSet.getString(StaffSchema.COL_PHONE))
                        .email(resultSet.getString(StaffSchema.COL_EMAIL))
                        .birthDate(resultSet.getTimestamp(StaffSchema.COL_BIRTH_DATE))
                        .joinDate(resultSet.getTimestamp(StaffSchema.COL_JOIN_DATE))
                        .sex(resultSet.getBoolean(StaffSchema.COL_SEX))
                        .build();
                staffs.add(staff);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return staffs;
    }

    public Staff getStaffById(int id) {
        Staff staff = null;
        prepareConnection();
        try {
            String sql = "SELECT staff.%s, staff.%s, staff.%s, staff.%s, "
                    + "staff.%s, staff.%s, staff.%s, staff.%s, staff.%s, "
                    + "staff.%s, staff.%s, staff.%s, "
                    + "staff.%s, role.name AS role_name "
                    + "FROM staff "
                    + "INNER JOIN [role] ON [role].id = %s "
                    + "WHERE staff.%s = ?";
            sql = String.format(sql,
                    StaffSchema.COL_ID, StaffSchema.COL_USERNAME,
                    StaffSchema.COL_PASSWORD, StaffSchema.COL_FIRST_NAME,
                    StaffSchema.COL_LAST_NAME, StaffSchema.COL_BIRTH_DATE,
                    StaffSchema.COL_ADDRESS, StaffSchema.COL_PHONE,
                    StaffSchema.COL_EMAIL, StaffSchema.COL_SEX,
                    StaffSchema.COL_JOIN_DATE, StaffSchema.COL_SALARY,
                    StaffSchema.COL_ON_WORK,
                    StaffSchema.COL_ROLE_ID,
                    StaffSchema.COL_ID);
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                staff = (Staff) new Staff.Builder()
                        .username(resultSet.getString(StaffSchema.COL_USERNAME))
                        .password(resultSet.getString(StaffSchema.COL_PASSWORD))
                        .salary(resultSet.getFloat(StaffSchema.COL_SALARY))
                        .onWork(resultSet.getBoolean(StaffSchema.COL_ON_WORK))
                        .role(Role.parseRole(resultSet.getString("role_name")))
                        .id(resultSet.getInt(StaffSchema.COL_ID))
                        .firstName(resultSet.getString(StaffSchema.COL_FIRST_NAME))
                        .lastName(resultSet.getString(StaffSchema.COL_LAST_NAME))
                        .address(resultSet.getString(StaffSchema.COL_ADDRESS))
                        .phone(resultSet.getString(StaffSchema.COL_PHONE))
                        .email(resultSet.getString(StaffSchema.COL_EMAIL))
                        .birthDate(resultSet.getTimestamp(StaffSchema.COL_BIRTH_DATE))
                        .joinDate(resultSet.getTimestamp(StaffSchema.COL_JOIN_DATE))
                        .sex(resultSet.getBoolean(StaffSchema.COL_SEX))
                        .build();
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return staff;
    }

    public boolean addStaff(Staff staff) {
        boolean result = false;
        prepareConnection();
        try {
            String sql = "INSERT INTO staff (staff.%s, staff.%s, staff.%s, "
                    + "staff.%s, staff.%s, staff.%s, staff.%s, staff.%s, "
                    + "staff.%s, staff.%s, staff.%s, "
                    + "staff.%s, staff.%s) "
                    + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?) ";
            sql = String.format(sql,
                    StaffSchema.COL_USERNAME,
                    StaffSchema.COL_PASSWORD, StaffSchema.COL_FIRST_NAME,
                    StaffSchema.COL_LAST_NAME, StaffSchema.COL_BIRTH_DATE,
                    StaffSchema.COL_ADDRESS, StaffSchema.COL_PHONE,
                    StaffSchema.COL_EMAIL, StaffSchema.COL_SEX,
                    StaffSchema.COL_JOIN_DATE, StaffSchema.COL_SALARY,
                    StaffSchema.COL_ON_WORK, StaffSchema.COL_ROLE_ID);
            statement = connection.prepareStatement(sql);
            statement.setString(1, staff.getUsername());
            statement.setString(2, staff.getPassword());
            statement.setString(3, staff.getFirstName());
            statement.setString(4, staff.getLastName());
            statement.setTimestamp(5, staff.getBirthday());
            statement.setString(6, staff.getAddress());
            statement.setString(7, staff.getPhone());
            statement.setString(8, staff.getEmail());
            statement.setBoolean(9, staff.isSex());
            statement.setTimestamp(10, staff.getJoinDate());
            statement.setFloat(11, staff.getSalary());
            statement.setBoolean(12, staff.isOnWork());
            statement.setInt(13, staff.getRole().id());
            result = statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean updateStaff(Staff staff) {
        boolean result = false;
        try {
            prepareConnection();
            String sql = "UPDATE staff SET "
                    + StaffSchema.COL_PASSWORD + " = ?, "
                    + StaffSchema.COL_FIRST_NAME + " = ?, "
                    + StaffSchema.COL_LAST_NAME + " = ?, "
                    + StaffSchema.COL_ROLE_ID + " = ?, "
                    + StaffSchema.COL_SALARY + " = ?, "
                    + StaffSchema.COL_ON_WORK + " = ?, "
                    + StaffSchema.COL_BIRTH_DATE + " = ?, "
                    + StaffSchema.COL_SEX + " = ?, "
                    + StaffSchema.COL_PHONE + " = ?, "
                    + StaffSchema.COL_EMAIL + " = ?, "
                    + StaffSchema.COL_ADDRESS + " = ? ";
            sql += " WHERE " + StaffSchema.COL_ID + " = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, staff.getPassword());
            statement.setString(2, staff.getFirstName());
            statement.setString(3, staff.getLastName());
            statement.setInt(4, staff.getRole().id());
            statement.setFloat(5, staff.getSalary());
            statement.setBoolean(6, staff.isOnWork());
            statement.setTimestamp(7, staff.getBirthday());
            statement.setBoolean(8, staff.isSex());
            statement.setString(9, staff.getPhone());
            statement.setString(10, staff.getEmail());
            statement.setString(11, staff.getAddress());
            statement.setInt(12, staff.getId());
            result = statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
