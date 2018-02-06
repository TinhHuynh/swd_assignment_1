/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruit_shop.view.product;

import fruit_shop.utils.io.ProductIOUtils;
import fruit_shop.model.database.product.Category;
import fruit_shop.model.database.product.Product;
import fruit_shop.model.database.product.ProductDAO;
import fruit_shop.view.View;

/**
 *
 * @author TINH HUYNH
 */
public class UpdateProductForm extends View {

    private static UpdateProductForm form;

    public static UpdateProductForm getInstance() {
        if (form == null) {
            form = new UpdateProductForm();
            form.setParentMenu(ProductMenu.getInstance());
        }
        return form;
    }

    @Override
    public void print() {
        clearConsole();
        System.out.println("");
        System.out.println("========================== Update Product =============================");
        printUpdateProductForm();
    }

    private void printUpdateProductForm() {
        ProductDAO dao = new ProductDAO();
        Product product = null;
        boolean isContinue;
        do {
            System.out.println("");
            int id = ProductIOUtils.askAndGetId();
            product = dao.getProductById(id);
            if (product == null) {
                System.out.println("Product is not found!");
            } else {
                System.out.println("Product is found!");
                System.out.println("");
                ProductIOUtils.printProduct(product);
                System.out.println("");

                String name = ProductIOUtils.askAndGetName();
                float unitPrice = ProductIOUtils.askAndGetUnitPrice();
                float unitsInStock = ProductIOUtils.askAndGetUnitsInStock();
                int categoryId = ProductIOUtils.askAndGetCategory();
                String origin = ProductIOUtils.askAndGetOrigin();
                String description = ProductIOUtils.askAndGetDescription();
                boolean available = ProductIOUtils.askAndGetAvailable();
                product = new Product.Builder()
                        .id(id)
                        .name(name)
                        .unitPrice(unitPrice)
                        .unitsInStock(unitsInStock)
                        .category(Category.parseCategory(categoryId))
                        .origin(origin)
                        .description(description)
                        .available(available)
                        .build();
                boolean result = dao.updateProduct(product);
                if (result) {
                    System.out.println("Updated successfully!");
                } else {
                    System.out.println("Failed to update product!");
                }
            }
            isContinue = askContinue();
        } while (isContinue);
        if (!isContinue) {
            backToParentMenu();
        }
    }
}
