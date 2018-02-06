/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruit_shop.model.database.product;

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
public class ProductDAO extends DAO {

    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        prepareConnection();
        try {
            String sql = "SELECT product.%s, product.%s, product.%s, product.%s, "
                    + "product.%s, category.name AS category, product.%s, product.%s, product.%s "
                    + "FROM product "
                    + "INNER JOIN category ON product.%s = category.id";
            sql = String.format(sql,
                    ProductSchema.COL_ID, ProductSchema.COL_NAME,
                    ProductSchema.COL_UNIT, ProductSchema.COL_UNIT_PRICE,
                    ProductSchema.COL_UNITS_IN_STOCK, ProductSchema.COL_ORIGIN,
                    ProductSchema.COL_DESCRIPTION,
                    ProductSchema.COL_AVAILABLE,
                    ProductSchema.COL_CATEGORY_ID);
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product.Builder()
                        .id(resultSet.getInt(ProductSchema.COL_ID))
                        .name(resultSet.getString(ProductSchema.COL_NAME))
                        .unit(resultSet.getString(ProductSchema.COL_UNIT))
                        .unitPrice(resultSet.getFloat(ProductSchema.COL_UNIT_PRICE))
                        .unitsInStock(resultSet.getFloat(ProductSchema.COL_UNITS_IN_STOCK))
                        .category(Category.parseCategory(resultSet.getString("category")))
                        .origin(resultSet.getString(ProductSchema.COL_ORIGIN))
                        .description(resultSet.getString(ProductSchema.COL_DESCRIPTION))
                        .available(resultSet.getBoolean(ProductSchema.COL_AVAILABLE))
                        .build();
                products.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return products;
    }

    public Product getProductById(int id) {
        Product product = null;
        prepareConnection();
        try {
            String sql = "SELECT TOP 1 product.%s, product.%s, product.%s, product.%s, "
                    + "product.%s, category.name AS category, product.%s, product.%s, product.%s "
                    + "FROM product "
                    + "INNER JOIN category ON product.%s = category.id "
                    + "WHERE product.%s = ?";
            sql = String.format(sql,
                    ProductSchema.COL_ID, ProductSchema.COL_NAME,
                    ProductSchema.COL_UNIT, ProductSchema.COL_UNIT_PRICE,
                    ProductSchema.COL_UNITS_IN_STOCK, ProductSchema.COL_ORIGIN,
                    ProductSchema.COL_DESCRIPTION, ProductSchema.COL_AVAILABLE,
                    ProductSchema.COL_CATEGORY_ID,
                    ProductSchema.COL_ID);
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                product = new Product.Builder()
                        .id(resultSet.getInt(ProductSchema.COL_ID))
                        .name(resultSet.getString(ProductSchema.COL_NAME))
                        .unit(resultSet.getString(ProductSchema.COL_UNIT))
                        .unitPrice(resultSet.getFloat(ProductSchema.COL_UNIT_PRICE))
                        .unitsInStock(resultSet.getFloat(ProductSchema.COL_UNITS_IN_STOCK))
                        .category(Category.parseCategory(resultSet.getString("category")))
                        .origin(resultSet.getString(ProductSchema.COL_ORIGIN))
                        .description(resultSet.getString(ProductSchema.COL_DESCRIPTION))
                        .available(resultSet.getBoolean(ProductSchema.COL_AVAILABLE))
                        .build();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return product;
    }

    public boolean addProduct(Product product) {
        boolean result = false;

        try {
            prepareConnection();
            String sql = "INSERT INTO product ("
                    + ProductSchema.COL_NAME + ","
                    + ProductSchema.COL_UNIT + ","
                    + ProductSchema.COL_UNIT_PRICE + ","
                    + ProductSchema.COL_UNITS_IN_STOCK + ","
                    + ProductSchema.COL_ORIGIN + ","
                    + ProductSchema.COL_CATEGORY_ID + ","
                    + ProductSchema.COL_AVAILABLE + ","
                    + ProductSchema.COL_DESCRIPTION;

            sql += ") VALUES(?,?,?,?,?,?,?,?)";

            statement = connection.prepareStatement(sql);
            statement.setString(1, product.getName());
            statement.setString(2, "kg");
            statement.setFloat(3, product.getUnitPrice());
            statement.setFloat(4, product.getUnitsInStock());
            statement.setString(5, product.getOrigin());
            statement.setInt(6, product.getCategory().id());
            statement.setBoolean(7, true);
            statement.setString(8, product.getDescription());
            result = statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public boolean updateProduct(Product product) {
        boolean result = false;

        try {
            prepareConnection();
            String sql = "UPDATE product SET "
                    + ProductSchema.COL_NAME + " = ?, "
                    + ProductSchema.COL_UNIT_PRICE + " = ?, "
                    + ProductSchema.COL_UNITS_IN_STOCK + " = ?, "
                    + ProductSchema.COL_ORIGIN + " = ?, "
                    + ProductSchema.COL_DESCRIPTION + " = ?, "
                    + ProductSchema.COL_CATEGORY_ID + " = ?, "
                    + ProductSchema.COL_AVAILABLE + " = ? ";
            sql += " WHERE " + ProductSchema.COL_ID + " = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, product.getName());
            statement.setFloat(2, product.getUnitPrice());
            statement.setFloat(3, product.getUnitsInStock());
            statement.setString(4, product.getOrigin());
            statement.setString(5, product.getDescription());
            statement.setInt(6, product.getCategory().id());
            statement.setBoolean(7, product.isAvailable());
            statement.setInt(8, product.getId());
            result = statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
