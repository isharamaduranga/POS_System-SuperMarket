/**
 * @author : Ishara Maduarnga
 * Project Name: SuperMarket
 * Date        : 5/27/2022
 * Time        : 4:00 PM
 * Year        : 2022
 */

package lk.ijse.pos.entity;

import java.time.LocalDate;

public class Order {
    private String OrderID;
    private String OrderDate;
    private String CusID;

    public Order() {
    }

    public Order(String orderID, String orderDate, String cusID) {
        OrderID = orderID;
        OrderDate = orderDate;
        CusID = cusID;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String orderDate) {
        OrderDate = orderDate;
    }

    public String getCusID() {
        return CusID;
    }

    public void setCusID(String cusID) {
        CusID = cusID;
    }

    @Override
    public String toString() {
        return "Order{" +
                "OrderID='" + OrderID + '\'' +
                ", OrderDate=" + OrderDate +
                ", CusID='" + CusID + '\'' +
                '}';
    }
}
