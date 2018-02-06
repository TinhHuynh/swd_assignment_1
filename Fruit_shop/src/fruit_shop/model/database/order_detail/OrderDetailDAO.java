/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruit_shop.model.database.order_detail;

import fruit_shop.model.database.DAO;
import fruit_shop.model.database.product.Product;
import fruit_shop.model.database.statistic.ProductStatistic;
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
public class OrderDetailDAO extends DAO {

    public List<OrderDetail> getOrderDetaiilsByOrderId(int orderId) {
        List<OrderDetail> details = new ArrayList<>();
        prepareConnection();
        try {
            String sql = "SELECT %s, %s, %s "
                    + "FROM %s "
                    + "WHERE %s = ?";
            sql = String.format(sql,
                    OrderDetailSchema.COL_PRODUCT_ID, OrderDetailSchema.COL_QUANTITY,
                    OrderDetailSchema.COL_PRICE, OrderDetailSchema.TABLE_NAME,
                    OrderDetailSchema.COL_ORDER_ID);
            statement = connection.prepareStatement(sql);
            statement.setInt(1, orderId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                OrderDetail order = new OrderDetail();
                order.setProductId(resultSet.getInt(OrderDetailSchema.COL_PRODUCT_ID));
                order.setQuantity(resultSet.getFloat(OrderDetailSchema.COL_QUANTITY));
                order.setPrice(resultSet.getInt(OrderDetailSchema.COL_PRICE));
                details.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return details;
    }

    public boolean addOrderDetail(OrderDetail orderDetail) {
        boolean result = false;

        try {
            prepareConnection();
            String sql = "INSERT INTO order_detail ("
                    + OrderDetailSchema.COL_ORDER_ID + ", "
                    + OrderDetailSchema.COL_PRODUCT_ID + ", "
                    + OrderDetailSchema.COL_QUANTITY + ", "
                    + OrderDetailSchema.COL_PRICE;

            sql += ") VALUES(?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, orderDetail.getOrder().getId());
            statement.setInt(2, orderDetail.getProductId());
            statement.setFloat(3, orderDetail.getQuantity());
            statement.setFloat(4, orderDetail.getPrice());

            result = statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(OrderDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public float getTotalMoneyInDates(Timestamp fromDate, Timestamp toDate) {
        float totalMoney = 0.0f;
        prepareConnection();
        try {
            String sql = "SELECT SUM(order_detail.%s * order_detail.%s) AS total_money "
                    + "FROM %s "
                    + "JOIN [order] ON [order].id = order_detail.%s "
                    + "WHERE %s >= ? AND %s <= ?";
            sql = String.format(sql,
                    OrderDetailSchema.COL_PRICE, OrderDetailSchema.COL_QUANTITY,
                    OrderDetailSchema.TABLE_NAME,
                    OrderDetailSchema.COL_ORDER_ID,
                    "[order].order_date", "[order].order_date");
            statement = connection.prepareStatement(sql);
            statement.setTimestamp(1, fromDate);
            statement.setTimestamp(2, toDate);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                totalMoney = resultSet.getFloat("total_money");
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return totalMoney;
    }

    public List<ProductStatistic> getProductStatistic(Timestamp fromDate, Timestamp toDate) {
        List<ProductStatistic> statistics = new ArrayList<>();
        prepareConnection();
        try {
            String sql = "SELECT %s, product.name AS product_name, "
                    + "SUM(%s) AS quantity, SUM(%s*%s) AS total_money "
                    + "FROM order_detail "
                    + "JOIN product ON product.id = %s "
                    + "JOIN [order] ON [order].id = %s "
                    + "WHERE [order].order_date >= ? AND [order].order_date <= ? "
                    + "GROUP BY %s, product.name "
                    + "ORDER BY SUM(%s*%s) DESC";
            sql = String.format(sql,
                    OrderDetailSchema.COL_PRODUCT_ID,
                    OrderDetailSchema.COL_QUANTITY, OrderDetailSchema.COL_QUANTITY, OrderDetailSchema.COL_PRICE,
                    OrderDetailSchema.COL_PRODUCT_ID,
                    OrderDetailSchema.COL_ORDER_ID,
                    OrderDetailSchema.COL_PRODUCT_ID,
                    OrderDetailSchema.COL_QUANTITY, OrderDetailSchema.COL_PRICE);
            statement = connection.prepareStatement(sql);
            statement.setTimestamp(1, fromDate);
            statement.setTimestamp(2, toDate);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ProductStatistic statistic = new ProductStatistic();
                Product product = new Product();
                product.setId(resultSet.getInt(OrderDetailSchema.COL_PRODUCT_ID));
                product.setName(resultSet.getString("product_name"));
                statistic.setProduct(product);

                statistic.setTotalMoney(resultSet.getFloat("total_money"));
                statistic.setQuantity(resultSet.getFloat("quantity"));
                statistics.add(statistic);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return statistics;
    }

}
