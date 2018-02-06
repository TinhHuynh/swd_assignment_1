/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruit_shop.view.order;

import fruit_shop.utils.io.OrderIOUtils;
import fruit_shop.model.database.order.Order;
import fruit_shop.model.database.order.OrderDAO;
import fruit_shop.utils.DateTimeUtils;
import fruit_shop.view.Table;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author TINH HUYNH
 */
public class OrdersTable extends Table {

    private static OrdersTable menu;

    public static OrdersTable getInstance() {
        if (menu == null) {
            menu = new OrdersTable();
            menu.setParentMenu(OrderMenu.getInstance());
        }
        return menu;
    }

    @Override
    public void print() {
        System.out.println("");
        System.out.println("========================== Order Table =============================");
        Timestamp fromDate = null;
        Timestamp toDate = null;
        boolean isDatesValid;
        do {
            isDatesValid = false;
            System.out.println("");
            System.out.println("From date");
            fromDate = OrderIOUtils.askAndGetDate();
            System.out.println("To date");
            toDate = OrderIOUtils.askAndGetDate();

            if (toDate.before(fromDate)) {
                System.out.println("End Date must be after start date");
            } else {
                toDate = DateTimeUtils.addMoreDays(toDate, 1);
                isDatesValid = true;
            }
        } while (!isDatesValid);
        System.out.println("Loading data. Please wait...");
        System.out.println("");
        List<Order> orders = new OrderDAO().getOrdersByDates(fromDate, toDate);
        OrderIOUtils.printOrder(orders);
        askGoBack();
    }
    
    
}
