/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruit_shop.model.database.order;

import fruit_shop.model.database.DAO;
import fruit_shop.model.database.customer.Customer;
import fruit_shop.model.database.product.ProductDAO;
import fruit_shop.model.database.staff.Staff;
import fruit_shop.model.database.statistic.CustomerStatistic;
import fruit_shop.model.database.statistic.StaffStatistic;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TINH HUYNH
 */
public class OrderDAO extends DAO {

    public Order getOrderById(int id) {
        Order order = null;
        prepareConnection();
        try {
            String sql = "SELECT TOP 1 [order].%s, [order].%s, [order].%s, [order].%s, "
                    + "staff.first_name AS staff_first_name, staff.last_name AS staff_last_name, "
                    + "customer.first_name AS customer_first_name, customer.last_name AS customer_last_name "
                    + "FROM [order] "
                    + "JOIN staff ON [order].%s = staff.id "
                    + "JOIN customer ON [order].%s = customer.id "
                    + "WHERE [order].id = ?";
            sql = String.format(sql,
                    OrderSchema.COL_ID, OrderSchema.COL_ORDER_DATE,
                    OrderSchema.COL_STAFF_ID, OrderSchema.COL_CUSTOMER_ID,
                    OrderSchema.COL_STAFF_ID, OrderSchema.COL_CUSTOMER_ID);
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                order = new Order();
                order.setId(resultSet.getInt(OrderSchema.COL_ID));
                order.setOrderDate(resultSet.getTimestamp(OrderSchema.COL_ORDER_DATE));
                order.setStaff((Staff) new Staff.Builder()
                        .id(resultSet.getInt(OrderSchema.COL_STAFF_ID))
                        .firstName(resultSet.getString("staff_first_name"))
                        .lastName(resultSet.getString("staff_last_name"))
                        .build());
                order.setCustomer((Customer) new Customer.Builder()
                        .id(resultSet.getInt(OrderSchema.COL_CUSTOMER_ID))
                        .firstName(resultSet.getString("customer_first_name"))
                        .lastName(resultSet.getString("customer_last_name"))
                        .build());
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return order;
    }

    public boolean addOrder(Order order, String searchIdKey) {
        boolean result = false;

        try {
            prepareConnection();
            String sql = "INSERT INTO [order] ("
                    + OrderSchema.COL_STAFF_ID + ","
                    + OrderSchema.COL_CUSTOMER_ID + ","
                    + OrderSchema.COL_ORDER_DATE + ", "
                    + "search_id_key"
                    + ")";

            sql += " VALUES(?,?,?,?)";

            statement = connection.prepareStatement(sql);
            statement.setInt(1, order.getStaff().getId());
            statement.setInt(2, order.getCustomer().getId());
            statement.setTimestamp(3, order.getOrderDate());
            statement.setString(4, searchIdKey);
            result = statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Deprecated
    public int getIdByStaffIdAndOrderDate(int staffId, Timestamp orderDate) {
        int id = 0;
        prepareConnection();
        try {
            String sql = "SELECT TOP 1 %s "
                    + "FROM [order] "
                    + "WHERE %s = ? AND %s = ?";
            sql = String.format(sql,
                    OrderSchema.COL_ID, OrderSchema.COL_STAFF_ID,
                    OrderSchema.COL_ORDER_DATE);
            statement = connection.prepareStatement(sql);
            statement.setInt(1, staffId);
            statement.setTimestamp(2, orderDate);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt(OrderSchema.COL_ID);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return id;
    }

    public int getIdBySearchIdKey(String searchIdKey) {
        int id = 0;
        prepareConnection();
        try {
            String sql = "SELECT TOP 1 %s "
                    + "FROM [order] "
                    + "WHERE %s = ? ";
            sql = String.format(sql,
                    OrderSchema.COL_ID,
                    "search_id_key");
            statement = connection.prepareStatement(sql);
            statement.setString(1, searchIdKey);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt(OrderSchema.COL_ID);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return id;
    }

    public List<Order> getOrdersByDates(Timestamp fromDate, Timestamp toDate) {
        List<Order> orders = new ArrayList<>();
        prepareConnection();
        try {
            String sql = "SELECT [order].%s, [order].%s, [order].%s, [order].%s, "
                    + "staff.first_name AS staff_first_name, staff.last_name AS staff_last_name, "
                    + "customer.first_name AS customer_first_name, customer.last_name AS customer_last_name, "
                    + "SUM(order_detail.price*order_detail.quantity) AS total_money, "
                    + "COUNT(DISTINCT order_detail.product_id) AS num_of_products "
                    + "FROM [order] "
                    + "JOIN staff ON staff.id = [order].%s "
                    + "JOIN customer ON customer.id = [order].%s "
                    + "JOIN order_detail ON order_detail.order_id = [order].%s "
                    + "WHERE [order].%s >= ? AND [order].%s <= ? "
                    + "GROUP BY [order].%s, %s, %s, %s, staff.first_name, staff.last_name, customer.first_name, customer.last_name";
            sql = String.format(sql,
                    OrderSchema.COL_ID, OrderSchema.COL_ORDER_DATE, OrderSchema.COL_STAFF_ID, OrderSchema.COL_CUSTOMER_ID,
                    OrderSchema.COL_STAFF_ID,
                    OrderSchema.COL_CUSTOMER_ID,
                    OrderSchema.COL_ID,
                    OrderSchema.COL_ORDER_DATE, OrderSchema.COL_ORDER_DATE,
                    OrderSchema.COL_ID, OrderSchema.COL_ORDER_DATE, OrderSchema.COL_STAFF_ID, OrderSchema.COL_CUSTOMER_ID);
            statement = connection.prepareStatement(sql);
            statement.setTimestamp(1, fromDate);
            statement.setTimestamp(2, toDate);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt(OrderSchema.COL_ID));
                order.setOrderDate(resultSet.getTimestamp(OrderSchema.COL_ORDER_DATE));
                order.setStaff((Staff) new Staff.Builder()
                        .id(resultSet.getInt(OrderSchema.COL_STAFF_ID))
                        .firstName(resultSet.getString("staff_first_name"))
                        .lastName(resultSet.getString("staff_last_name"))
                        .build());
                order.setCustomer((Customer) new Customer.Builder()
                        .id(resultSet.getInt(OrderSchema.COL_CUSTOMER_ID))
                        .firstName(resultSet.getString("customer_first_name"))
                        .lastName(resultSet.getString("customer_last_name"))
                        .build());
                order.setTotalMoney(resultSet.getFloat("total_money"));
                order.setNumOfProducts(resultSet.getInt("num_of_products"));
                orders.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return orders;
    }

    public int getNumOfOrdersByDates(Timestamp fromDate, Timestamp toDate) {
        int count = 0;
        prepareConnection();
        try {
            String sql = "SELECT COUNT(%s) AS count_order "
                    + "FROM %s "
                    + "WHERE %s >= ? AND %s <= ?";
            sql = String.format(sql,
                    OrderSchema.COL_ID,
                    OrderSchema.TABLE_NAME,
                    OrderSchema.COL_ORDER_DATE, OrderSchema.COL_ORDER_DATE);
            statement = connection.prepareStatement(sql);
            statement.setTimestamp(1, fromDate);
            statement.setTimestamp(2, toDate);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt("count_order");
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return count;
    }

    public List<StaffStatistic> getStaffStatistics(Timestamp fromDate, Timestamp toDate) {
        List<StaffStatistic> statistics = new ArrayList<>();
        prepareConnection();
        try {
            String sql = "SELECT %s, staff.first_name AS staff_first_name, staff.last_name AS staff_last_name, "
                    + "COUNT(DISTINCT [order].%s) AS order_count, "
                    + "COUNT(DISTINCT order_detail.product_id) AS product_count, "
                    + "SUM(order_detail.quantity*order_detail.price) AS total_money "
                    + "FROM [order] "
                    + "JOIN order_detail ON order_detail.order_id = [order].%s "
                    + "JOIN staff ON staff.id = [order].%s "
                    + "WHERE [order].%s >= ? AND [order].%s <= ? "
                    + "GROUP BY [order].%s, staff.first_name, staff.last_name "
                    + "ORDER BY SUM(quantity*price) DESC";
            sql = String.format(sql,
                    OrderSchema.COL_STAFF_ID,
                    OrderSchema.COL_ID,
                    OrderSchema.COL_ID,
                    OrderSchema.COL_STAFF_ID,
                    OrderSchema.COL_ORDER_DATE, OrderSchema.COL_ORDER_DATE,
                    OrderSchema.COL_STAFF_ID);
            statement = connection.prepareStatement(sql);
            statement.setTimestamp(1, fromDate);
            statement.setTimestamp(2, toDate);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                StaffStatistic statistic = new StaffStatistic();
                statistic.setStaff((Staff) new Staff.Builder()
                        .id(resultSet.getInt(OrderSchema.COL_STAFF_ID))
                        .firstName(resultSet.getString("staff_first_name"))
                        .lastName(resultSet.getString("staff_last_name"))
                        .build());

                statistic.setTotalMoney(resultSet.getFloat("total_money"));
                statistic.setNumOfOrders(resultSet.getInt("order_count"));
                statistic.setNumOfProduct(resultSet.getInt("product_count"));
                statistics.add(statistic);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return statistics;
    }

    public List<CustomerStatistic> getCustomerStatistics(Timestamp fromDate, Timestamp toDate) {
        List<CustomerStatistic> statistics = new ArrayList<>();
        prepareConnection();
        try {
            String sql = "SELECT %s, customer.first_name AS customer_first_name, customer.last_name AS customer_last_name, "
                    + "COUNT(DISTINCT [order].%s) AS order_count, "
                    + "COUNT(DISTINCT order_detail.product_id) AS product_count, "
                    + "SUM(order_detail.quantity*order_detail.price) AS total_money "
                    + "FROM [order] "
                    + "JOIN order_detail ON order_detail.order_id = [order].%s "
                    + "JOIN customer ON customer.id = [order].%s "
                    + "WHERE [order].%s >= ? AND [order].%s <= ? "
                    + "GROUP BY [order].%s, customer.first_name, customer.last_name "
                    + "ORDER BY SUM(quantity*price) DESC";
            sql = String.format(sql,
                    OrderSchema.COL_CUSTOMER_ID,
                    OrderSchema.COL_ID,
                    OrderSchema.COL_ID,
                    OrderSchema.COL_CUSTOMER_ID,
                    OrderSchema.COL_ORDER_DATE, OrderSchema.COL_ORDER_DATE,
                    OrderSchema.COL_CUSTOMER_ID);
            statement = connection.prepareStatement(sql);
            statement.setTimestamp(1, fromDate);
            statement.setTimestamp(2, toDate);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                CustomerStatistic statistic = new CustomerStatistic();
                statistic.setCustomer((Customer) new Customer.Builder()
                        .id(resultSet.getInt(OrderSchema.COL_CUSTOMER_ID))
                        .firstName(resultSet.getString("customer_first_name"))
                        .lastName(resultSet.getString("customer_last_name"))
                        .build());

                statistic.setTotalMoney(resultSet.getFloat("total_money"));
                statistic.setNumOfOrders(resultSet.getInt("order_count"));
                statistic.setNumOfProducts(resultSet.getInt("product_count"));
                statistics.add(statistic);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return statistics;
    }

}
