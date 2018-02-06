/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruit_shop.view.product;

import fruit_shop.utils.io.ProductIOUtils;
import fruit_shop.model.database.product.Product;
import fruit_shop.model.database.product.ProductDAO;
import fruit_shop.view.View;

/**
 *
 * @author TINH HUYNH
 */
public class ViewProductForm extends View {

    private static ViewProductForm view;

    public static ViewProductForm getInstance() {
        if (view == null) {
            view = new ViewProductForm();
            view.setParentMenu(ProductMenu.getInstance());
        }
        return view;
    }

    @Override
    public void print() {
        System.out.println("");
        System.out.println("==========================  View Product =============================");
        boolean isContinue;
        do {
            System.out.println("");
            int id = ProductIOUtils.askAndGetId();
            System.out.println("Loading data. Please wait...");
            System.out.println("");
            Product product = new ProductDAO().getProductById(id);
            printProduct(product);
            isContinue = askContinue();
        } while (isContinue);
        if (!isContinue) {
            backToParentMenu();
        }
    }

    private void printProduct(Product product) {
        ProductIOUtils.printProduct(product);
    }

}
