/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruit_shop.view.statistic;

import fruit_shop.model.database.order.OrderDAO;
import fruit_shop.model.database.order_detail.OrderDetailDAO;
import fruit_shop.model.database.statistic.ProductStatistic;
import fruit_shop.utils.DateTimeUtils;
import fruit_shop.view.Table;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author TINH HUYNH
 */
class ProductIncomeTable extends Table {

    private static ProductIncomeTable menu;

    public static ProductIncomeTable getInstance() {
        if (menu == null) {
            menu = new ProductIncomeTable();
            menu.setParentMenu(StatisticMenu.getInstance());
        }
        return menu;
    }

    @Override
    public void print() {
        System.out.println("");
        System.out.println("========================== Income by staff =============================");
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
        OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
        float totalMoney = orderDetailDAO.getTotalMoneyInDates(fromDate, toDate);
        int ordersCount = new OrderDAO().getNumOfOrdersByDates(fromDate, toDate);
        List<ProductStatistic> statistics = orderDetailDAO.getProductStatistic(fromDate, toDate);
        System.out.printf("Total money: %.0f VND\n", totalMoney);
        System.out.printf("Number of orders: %d\n", ordersCount);
        printStatistic(statistics);
        askGoBack();
    }

    private void printStatistic(List<ProductStatistic> statistics) {
        if (!statistics.isEmpty()) {
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-5s%-30s%-30s%s\n",
                    "Id", "Product Name", "Sold quantity(kg)", "Money(VND)");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
            statistics.forEach((statistic) -> {
                System.out.printf("%-5d%-30s%-30.3f%.0f\n",
                        statistic.getProduct().getId(),
                        statistic.getProduct().getName(),
                        statistic.getQuantity(),
                        statistic.getTotalMoney());
            });
        } else {
            System.out.println("There is no product sound found.");
        }
    }

}
