/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruit_shop.view.product;

import fruit_shop.model.database.product.Product;
import fruit_shop.model.database.product.ProductDAO;
import fruit_shop.view.Table;
import java.util.List;

/**
 *
 * @author TINH HUYNH
 */
public class ProductsTable extends Table {

    private static ProductsTable table;

    public static ProductsTable getInstance() {
        if (table == null) {
            table = new ProductsTable();
            table.setParentMenu(ProductMenu.getInstance());
        }
        return table;
    }

    @Override
    public void print() {
        System.out.println("");
        System.out.println("========================== Product Table =============================");
        System.out.println("");
        System.out.println("Loading data. Please wait...");
        System.out.println("");
        List<Product> products = new ProductDAO().getProducts();
        printProducts(products);
        System.out.println("");
        askGoBack();
    }

    private void printProducts(List<Product> products) {
        if (!products.isEmpty()) {
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-5s%-25s%-10s%-20s%-20s%-20s%-25s%-20s%s\n",
                    "Id", "Name", "Unit", "Unit price(VND)", "Units in stock(kg)", "Category", "Origin", "Available", "Description");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            products.forEach((product) -> {
                System.out.printf("%-5d%-25s%-10s%-20.0f%-20.3f%-20s%-25s%-20b%s\n",
                        product.getId(),
                        product.getName(), product.getUnit(), product.getUnitPrice(),
                        product.getUnitsInStock(), product.getCategory().value(),
                        product.getOrigin(), product.isAvailable(), product.getDescription());
            });
        } else {
            System.out.println("There is no product in the database. Please add one!");
        }
    }
}
