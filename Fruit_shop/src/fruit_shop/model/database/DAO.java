/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruit_shop.model.database;

import fruit_shop.model.database.staff.StaffDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TINH HUYNH
 */
public abstract class DAO {

    protected Connection connection;
    protected PreparedStatement statement;
    protected ResultSet resultSet;
    
    protected void prepareConnection() {
        connection = DatabaseUtils.getConnection(DBConfig.DB_URL,
                DBConfig.USER_NAME,
                DBConfig.PASSWORD);
    }

    protected void closeConnection() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
