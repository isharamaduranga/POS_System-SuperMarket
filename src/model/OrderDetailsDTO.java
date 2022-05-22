package model;

public class OrderDetailsDTO {
    private String orderID;
    private String itemCode;
    private int orderQTY;
    private double discount;
    private double total;

    public OrderDetailsDTO() {
    }

    public OrderDetailsDTO(String orderID, String itemCode, int orderQTY, double discount, double total) {
        this.orderID = orderID;
        this.itemCode = itemCode;
        this.orderQTY = orderQTY;
        this.discount = discount;
        this.total = total;
    }

    public OrderDetailsDTO(String orderID, String itemCode, int orderQTY, double discount) {
        this.orderID = orderID;
        this.itemCode = itemCode;
        this.orderQTY = orderQTY;
        this.discount = discount;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getOrderQTY() {
        return orderQTY;
    }

    public void setOrderQTY(int orderQTY) {
        this.orderQTY = orderQTY;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "OrderDetailsDTO{" +
                "orderID='" + orderID + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", orderQTY=" + orderQTY +
                ", discount=" + discount +
                ", total=" + total +
                '}';
    }
}
