/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruit_shop.model.database.order;

import fruit_shop.model.database.customer.Customer;
import fruit_shop.model.database.shipping_info.ShippingInfo;
import fruit_shop.model.database.order_detail.OrderDetail;
import fruit_shop.model.database.staff.Staff;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author TINH HUYNH
 */
public class Order {
    private int id;
    private Timestamp orderDate;
    private Staff staff;
    private Customer customer;
    private List<OrderDetail> orderDetails;
    private ShippingInfo shippingInfo;
    private float totalMoney;
    private int numOfProducts;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public ShippingInfo getShippingInfo() {
        return shippingInfo;
    }

    public void setShippingInfo(ShippingInfo shippingInfo) {
        this.shippingInfo = shippingInfo;
    }

    public float getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(float totalMoney) {
        this.totalMoney = totalMoney;
    }

    public int getNumOfProducts() {
        return numOfProducts;
    }

    public void setNumOfProducts(int numOfProducts) {
        this.numOfProducts = numOfProducts;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", orderDate=" + orderDate + ", staff=" + staff + ", customer=" + customer + ", orderDetails=" + orderDetails + ", shippingInfo=" + shippingInfo + ", totalMoney=" + totalMoney + ", numOfProducts=" + numOfProducts + '}';
    }
    
    
    
    
}
