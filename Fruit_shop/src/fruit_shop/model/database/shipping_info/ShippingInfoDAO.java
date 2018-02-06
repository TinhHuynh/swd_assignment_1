/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruit_shop.model.database.shipping_info;

import fruit_shop.model.database.DAO;
import fruit_shop.model.database.order.Order;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TINH HUYNH
 */
public class ShippingInfoDAO extends DAO {

    public boolean addShippingInfo(ShippingInfo info) {

        boolean result = false;

        try {
            prepareConnection();
            String sql = "INSERT INTO shipping_info ("
                    + ShippingInfoShema.COL_ORDER_ID + ","
                    + ShippingInfoShema.COL_SHIP_ADDRESS + ","
                    + ShippingInfoShema.COL_SHIP_NAME + ","
                    + ShippingInfoShema.COL_SHIP_PHONE + ","
                    + ShippingInfoShema.COL_SHIP_DATE + ","
                    + ShippingInfoShema.COL_SHIP_FEE + ")";
            sql += " VALUES(?,?,?,?,?,?)";

            statement = connection.prepareStatement(sql);
            statement.setInt(1, info.getOrder().getId());
            statement.setString(2, info.getShippingAddress());
            statement.setString(3, info.getShippingName());
            statement.setString(4, info.getShippingPhone());
            statement.setTimestamp(5, info.getShippingDate());
            statement.setFloat(6, info.getShippingFee());
            result = statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ShippingInfoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public ShippingInfo getShippingInfoById(int id){
        ShippingInfo info = null;
        prepareConnection();
        try {
            String sql = "SELECT TOP 1 %s, %s, %s, %s, %s, %s "
                    + "FROM shipping_info "
                    + "WHERE %s = ?";
            sql = String.format(sql,
                    ShippingInfoShema.COL_ORDER_ID, ShippingInfoShema.COL_SHIP_ADDRESS,
                    ShippingInfoShema.COL_SHIP_NAME, ShippingInfoShema.COL_SHIP_PHONE,
                    ShippingInfoShema.COL_SHIP_DATE, ShippingInfoShema.COL_SHIP_FEE,
                    ShippingInfoShema.COL_ORDER_ID);
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                info = new ShippingInfo();
                Order order = new Order();
                order.setId(resultSet.getInt(ShippingInfoShema.COL_ORDER_ID));
                info.setOrder(order);
                info.setShippingAddress(resultSet.getString(ShippingInfoShema.COL_SHIP_ADDRESS));
                info.setShippingName(resultSet.getString(ShippingInfoShema.COL_SHIP_NAME));
                info.setShippingPhone(resultSet.getString(ShippingInfoShema.COL_SHIP_PHONE));
                info.setShippingDate(resultSet.getTimestamp(ShippingInfoShema.COL_SHIP_DATE));
                info.setShippingFee(resultSet.getFloat(ShippingInfoShema.COL_SHIP_FEE));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ShippingInfoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return info;
    }

}
