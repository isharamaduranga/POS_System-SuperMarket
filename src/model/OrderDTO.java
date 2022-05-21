package model;

public class OrderDTO {
    private String orderID;
    private String orderDate;
    private String cusID;

    public OrderDTO() {
    }

    public OrderDTO(String orderID, String orderDate, String cusID) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.cusID = cusID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getCusID() {
        return cusID;
    }

    public void setCusID(String cusID) {
        this.cusID = cusID;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderID='" + orderID + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", cusID='" + cusID + '\'' +
                '}';
    }
}
