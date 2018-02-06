/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruit_shop.view.statistic;

import fruit_shop.model.database.order.OrderDAO;
import fruit_shop.model.database.order_detail.OrderDetailDAO;
import fruit_shop.model.database.statistic.StaffStatistic;
import fruit_shop.utils.DateTimeUtils;
import fruit_shop.view.Table;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author TINH HUYNH
 */
public class StaffIncomeTable extends Table {

    private static StaffIncomeTable menu;

    public static StaffIncomeTable getInstance() {
        if (menu == null) {
            menu = new StaffIncomeTable();
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
        OrderDAO orderDAO = new OrderDAO();
        float totalMoney = new OrderDetailDAO().getTotalMoneyInDates(fromDate, toDate);
        int ordersCount = orderDAO.getNumOfOrdersByDates(fromDate, toDate);
        List<StaffStatistic> statistics = orderDAO.getStaffStatistics(fromDate, toDate);
        System.out.printf("Total money: %.0f VND\n", totalMoney);
        System.out.printf("Number of orders: %d\n", ordersCount);
        printStatistic(statistics);
        askGoBack();
    }

    private void printStatistic(List<StaffStatistic> statistics) {
        if (!statistics.isEmpty()) {
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-5s%-30s%-30s%-30s%s\n",
                    "Id", "Staff name", "Number of orders", "Number of products", "Money(VND)");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
            statistics.forEach((statistic) -> {
                System.out.printf("%-5d%-30s%-30d%-30d%.0f\n",
                        statistic.getStaff().getId(),
                        statistic.getStaff().getFullName(),
                        statistic.getNumOfOrders(),
                        statistic.getNumOfProduct(),
                        statistic.getTotalMoney());
            });
        } else {
            System.out.println("There is no staff found.");
        }
    }
}
