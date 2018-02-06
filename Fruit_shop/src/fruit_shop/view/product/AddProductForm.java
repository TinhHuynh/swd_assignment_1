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
public class AddProductForm extends View {

    private static AddProductForm form;

    public static AddProductForm getInstance() {
        if (form == null) {
            form = new AddProductForm();
            form.setParentMenu(ProductMenu.getInstance());
        }
        return form;
    }

    @Override
    public void print() {
        clearConsole();
        System.out.println("");
        System.out.println("==========================  Add Product =============================");
        printAddProductForm();
    }

    private void printAddProductForm() {
        System.out.println("Note: * is required information");
        boolean isContinue;
        do {
            System.out.println("");
            String name = ProductIOUtils.askAndGetName();
            float unitPrice = ProductIOUtils.askAndGetUnitPrice();
            float unitsInStock = ProductIOUtils.askAndGetUnitsInStock();
            int categoryId = ProductIOUtils.askAndGetCategory();
            String origin = ProductIOUtils.askAndGetOrigin();
            String description = ProductIOUtils.askAndGetDescription();
            Product product = new Product.Builder()
                    .name(name)
                    .unitPrice(unitPrice)
                    .unitsInStock(unitsInStock)
                    .origin(origin)
                    .category(Category.parseCategory(categoryId))
                    .description(description)
                    .build();
            System.out.println("Processing. Please wait...");
            boolean result = new ProductDAO().addProduct(product);
            if (result) {
                System.out.println("Added successfully!");
            } else {
                System.out.println("Failed to add product!");
            }
            isContinue = askContinue();
        } while (isContinue);
        if (!isContinue) {
            backToParentMenu();
        }
    }
}
