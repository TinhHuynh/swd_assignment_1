/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruit_shop.view.product;

import fruit_shop.view.Menu;
import fruit_shop.view.main.MainMenu;

/**
 *
 * @author TINH HUYNH
 */
public class ProductMenu extends Menu {

    private static ProductMenu menu;

    public static ProductMenu getInstance() {
        if (menu == null) {
            menu = new ProductMenu();
            menu.setParentMenu(MainMenu.getInstance());
        }
        return menu;
    }

    @Override
    public void setUpOptionItems() {
        optionItems = new String[]{
            "View products",
            "View product",
            "Add product",
            "Update product",
            "Back"};
    }

    @Override
    public void print() {
        System.out.println("");
        System.out.println("========================== Product Menu  ==========================");
        printOptionItems();
    }

    @Override
    public void handleOptionItemSelected(int index) {
        switch (index) {
            case 1:
                goToProductsTable();
                break;
            case 2:
                goToViewProduct();
                break;
            case 3:
                goToAddProduct();
                break;
            case 4:
                goToUpdateProduct();
                break;
            case 5:
                backToParentMenu();
                break;
            default:
                onInvalidIndexSeleted();
        }
    }

    private void goToProductsTable() {
        ProductsTable.getInstance().print();
    }

    private void goToViewProduct() {
        ViewProductForm.getInstance().print();
    }

    private void goToAddProduct() {
        AddProductForm.getInstance().print();
    }

    private void goToUpdateProduct() {
        UpdateProductForm.getInstance().print();
    }

}
