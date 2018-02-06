/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruit_shop.model.database;

/**
 *
 * @author TINH HUYNH
 */
public class DBConfig {
    public static String DB_NAME = "fruit_shop";
    public static String DB_URL = "jdbc:sqlserver://localhost:1433;"
            + "databaseName=" +DB_NAME +";"
            + "integratedSecurity=true";
    public static String USER_NAME = "sa";
    public static String PASSWORD = "huynhtinh1997";
}
