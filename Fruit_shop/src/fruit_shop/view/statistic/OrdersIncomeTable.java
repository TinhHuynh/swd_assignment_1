/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruit_shop.view.statistic;

import fruit_shop.model.database.order.Order;
import fruit_shop.model.database.order.OrderDAO;
import fruit_shop.model.database.order_detail.OrderDetailDAO;
import fruit_shop.utils.DateTimeUtils;
import fruit_shop.view.Table;
import fruit_shop.utils.io.OrderIOUtils;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author TINH HUYNH
 */
public class OrdersIncomeTable extends Table {

    private static OrdersIncomeTable menu;

    public static OrdersIncomeTable getInstance() {
        if (menu == null) {
            menu = new OrdersIncomeTable();
            menu.setParentMenu(StatisticMenu.getInstance());
        }
        return menu;
    }

    @Override
    public void print() {
        System.out.println("");
        System.out.println("========================== Income by orders =============================");
        Timestamp fromDate = null;
        Timestamp toDate = null;
        boolean isDatesValid;
        do {
            isDatesValid = false;
            System.out.println("");
            System.out.println("From date");
            fromDate = StatisticIOUtils.askAndGetDate();
            System.out.println("To date");
            toDate = StatisticIOUtils.askAndGetDate();

            if (toDate.before(fromDate)) {
                System.out.println("End Date must be after start date");
            } else {
                toDate = DateTimeUtils.addMoreDays(toDate, 1);
                isDatesValid = true;
            }
        } while (!isDatesValid);
        System.out.println("Loading data. Please wait...");
        System.out.println("");
        OrderDAO orderDAO = new OrderDAO();
        float totalMoney = new OrderDetailDAO().getTotalMoneyInDates(fromDate, toDate);
        int ordersCount = orderDAO.getNumOfOrdersByDates(fromDate, toDate);
        List<Order> orders = orderDAO.getOrdersByDates(fromDate, toDate);
        System.out.printf("Total money: %.0f VND\n", totalMoney);
        System.out.printf("Number of orders: %d\n", ordersCount);
        OrderIOUtils.printOrder(orders);
        askGoBack();
    }

}
