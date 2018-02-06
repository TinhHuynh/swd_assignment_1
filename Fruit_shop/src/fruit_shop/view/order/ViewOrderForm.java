/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruit_shop.view.order;

import fruit_shop.utils.io.OrderIOUtils;
import fruit_shop.Config;
import fruit_shop.model.database.order.Order;
import fruit_shop.model.database.order.OrderDAO;
import fruit_shop.model.database.order_detail.OrderDetail;
import fruit_shop.model.database.order_detail.OrderDetailDAO;
import fruit_shop.model.database.product.Product;
import fruit_shop.model.database.product.ProductDAO;
import fruit_shop.model.database.shipping_info.ShippingInfo;
import fruit_shop.model.database.shipping_info.ShippingInfoDAO;
import fruit_shop.utils.DateTimeUtils;
import fruit_shop.view.View;
import java.util.List;

/**
 *
 * @author TINH HUYNH
 */
public class ViewOrderForm extends View {

    private static ViewOrderForm form;

    public static ViewOrderForm getInstance() {
        if (form == null) {
            form = new ViewOrderForm();
            form.setParentMenu(OrderMenu.getInstance());
        }
        return form;
    }

    @Override
    public void print() {
        System.out.println("");
        System.out.println("========================== View Order ==========================");
        boolean isContinue;
        do {
            System.out.println("");
            int id = OrderIOUtils.askAndGetId();
            Order order = new OrderDAO().getOrderById(id);
            System.out.println("Loading data. Please wait...");
            if (order == null) {
                System.out.println("Order is not found!");
            } else {
                List<OrderDetail> details = new OrderDetailDAO().getOrderDetaiilsByOrderId(id);
                order.setOrderDetails(details);
                System.out.println("");
                System.out.println("Result:");

                printOrder(order);
            }
            isContinue = askContinue();
        } while (isContinue);
        backToParentMenu();
    }

    private void printOrder(Order order) {
        System.out.printf("ID: %d\n", order.getId());
        System.out.printf("Date: %s\n", DateTimeUtils.convertTimestampToString(order.getOrderDate(), Config.ORDER_DATE_FORMAT));
        System.out.printf("Staff: %d - %s\n", order.getStaff().getId(), order.getStaff().getFullName());
        System.out.printf("Customer: %d - %s\n", order.getCustomer().getId(), order.getCustomer().getFullName());
        System.out.println("");
        System.out.printf("%-20s%-25s%-20s%-20s%-20s\n", "Product Id", "Product Name", "Quantity(kg)", "Price(VND)", "Total price(VND)");
        float totalMoney = 0.0f;
        for (OrderDetail orderDetail : order.getOrderDetails()) {
            Product product = new ProductDAO().getProductById(orderDetail.getProductId());
            System.out.printf("%-20d%-25s%-20.3f%-20.0f%-20.0f\n",
                    orderDetail.getProductId(),
                    product.getName(), orderDetail.getQuantity(), orderDetail.getPrice(),
                    orderDetail.getPrice() * orderDetail.getQuantity());
            totalMoney += orderDetail.getPrice() * orderDetail.getQuantity();
        }
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.printf("Total: %83.0f VND\n", totalMoney);

        // shipping info
        ShippingInfo info = new ShippingInfoDAO().getShippingInfoById(order.getId());
        if (info != null) {
            System.out.println("");
            System.out.println("************** Shipping Information **************");
            System.out.printf("Date: %s\n", DateTimeUtils.convertTimestampToString(info.getShippingDate(), Config.NON_TIME_DATE_FORMAT));
            System.out.printf("Addrress: %s\n", info.getShippingAddress());
            System.out.printf("Name: %s\n", info.getShippingName());
            System.out.printf("Phone: %s\n", info.getShippingPhone());
            System.out.printf("Fee: %.0f VND\n", info.getShippingFee());
        }
    }

}
