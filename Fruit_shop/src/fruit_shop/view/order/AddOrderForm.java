/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruit_shop.view.order;

import fruit_shop.utils.io.OrderDetailIOUtils;
import fruit_shop.Config;
import fruit_shop.SessionData;
import fruit_shop.model.database.customer.Customer;
import fruit_shop.model.database.customer.CustomerDAO;
import fruit_shop.model.database.order.Order;
import fruit_shop.model.database.order.OrderDAO;
import fruit_shop.model.database.order_detail.OrderDetail;
import fruit_shop.model.database.order_detail.OrderDetailDAO;
import fruit_shop.model.database.shipping_info.ShippingInfo;
import fruit_shop.model.database.shipping_info.ShippingInfoDAO;
import fruit_shop.model.database.shipping_info.ShippingInfoUtils;
import fruit_shop.model.database.staff.Staff;
import fruit_shop.utils.DateTimeUtils;
import fruit_shop.view.View;
import fruit_shop.utils.io.CustomerIOUtils;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author TINH HUYNH
 */
public class AddOrderForm extends View {

    private static AddOrderForm menu;

    public static AddOrderForm getInstance() {
        if (menu == null) {
            menu = new AddOrderForm();
            menu.setParentMenu(OrderMenu.getInstance());
        }
        return menu;
    }

    @Override
    public void print() {
        System.out.println("");
        System.out.println("========================== Add Order ==========================");

        boolean isContinue;
        do {
            // ask products
            List<OrderDetail> orderDetails = OrderDetailIOUtils.askAndGetOrderDetails();

            if (orderDetails.isEmpty()) {
                break;
            }

            // ask customer id
            Customer customer = null;
            int customerId = 0;
            do {
                System.out.println("");
                System.out.println("Enter customer id. Type 0 if customer is a guest");
                customerId = CustomerIOUtils.askAndGetId();
                if (customerId != 0) {
                    customer = new CustomerDAO().getCustomerById(customerId);
                    if (customer == null) {
                        System.out.println("Customer is not found");
                    } else {
                        System.out.println("Customer found: " + customer.getFirstName());
                        break;
                    }
                } else {
                    break;
                }
            } while (customerId != 0 || customer == null);

            // ask shipping info
            System.out.println("");
            boolean isShippingNeeded = ShippingInfoUtils.askShippingNeeded();
            ShippingInfo shippingInfo = null;
            if (isShippingNeeded) {
                shippingInfo = new ShippingInfo();
                String defaultAddress = customer != null ? customer.getAddress() : "";
                String defaultName = customer != null ? customer.getFullName() : "";
                String defaultPhone = customer != null ? customer.getPhone() : "";
                String address = ShippingInfoUtils.askAndGetShipAddress(defaultAddress);
                String name = ShippingInfoUtils.askAndGetShipName(defaultName);
                String phone = ShippingInfoUtils.askAndGetShipPhone(defaultPhone);
                Timestamp date = ShippingInfoUtils.askAndGetShipDate();
                float fee = ShippingInfoUtils.askAndGetFee();

                shippingInfo.setShippingAddress(address);
                shippingInfo.setShippingPhone(phone);
                shippingInfo.setShippingName(name);
                shippingInfo.setShippingDate(date);
                shippingInfo.setShippingFee(fee);
            }

            // prepare order
            Order order = new Order();
            order.setStaff((Staff) new Staff.Builder()
                    .id(SessionData.user.getId())
                    .build());
            order.setOrderDate(new Timestamp(System.currentTimeMillis()));
            order.setCustomer((Customer) new Customer.Builder()
                    .id(customerId)
                    .build());
            order.setOrderDetails(orderDetails);

            // print money detail 
            float totalMoney = 0;
            for (OrderDetail orderDetail : orderDetails) {
                totalMoney += orderDetail.getPrice() * orderDetail.getQuantity();
            }
            System.out.println("");
            System.out.printf("%-20s: %.0f VND\n", "Money", totalMoney);
            float shippingFee = 0;
            if (shippingInfo != null) {
                shippingFee = shippingInfo.getShippingFee();
            }
            System.out.printf("%-20s: %.0f VND\n", "Shipping fee", shippingFee);
            System.out.println("----------------------------------");
            System.out.printf("%-20s: %.0f VND\n", "Money must be paid", totalMoney + shippingFee);

            // add order and details + shipping info to db
            OrderDAO orderDAO = new OrderDAO();
            String searchIdKey = SessionData.user.getId() + "_" + DateTimeUtils
                    .convertTimestampToString(order.getOrderDate(), Config.SEARCH_ID_KEY_DATE_FORMAT);
            System.out.println("");
            System.out.println("Processing. Pleasw wait...");
            boolean result = orderDAO.addOrder(order, searchIdKey);
            if (result) {

                int id = orderDAO.getIdBySearchIdKey(searchIdKey);
                order.setId(id);
                OrderDetailDAO detailDAO = new OrderDetailDAO();
                for (OrderDetail orderDetail : orderDetails) {
                    orderDetail.setOrder(order);
                    detailDAO.addOrderDetail(orderDetail);
                }
                if (shippingInfo != null) {
                    shippingInfo.setOrder(order);
                    new ShippingInfoDAO().addShippingInfo(shippingInfo);
                }
                System.out.println("Order added successfully!");
            } else {
                System.out.println("Failed to add order!");
            }
            isContinue = askContinue();
        } while (isContinue);
        backToParentMenu();
    }

}
