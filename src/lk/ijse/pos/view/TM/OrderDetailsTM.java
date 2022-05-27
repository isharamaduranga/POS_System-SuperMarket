package lk.ijse.pos.view.TM;

public class OrderDetailsTM {
    private  String orderId;
    private String itemCode;
    private int orderQTY;
    private double discoUnt;
    private double total;

    public OrderDetailsTM() {
    }

    public OrderDetailsTM(String orderId, String itemCode, int orderQTY, double discoUnt, double total) {
        this.orderId = orderId;
        this.itemCode = itemCode;
        this.orderQTY = orderQTY;
        this.discoUnt = discoUnt;
        this.total = total;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public double getDiscoUnt() {
        return discoUnt;
    }

    public void setDiscoUnt(double discoUnt) {
        this.discoUnt = discoUnt;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "OrderDetailsTM{" +
                "orderId='" + orderId + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", orderQTY=" + orderQTY +
                ", discoUnt=" + discoUnt +
                ", total=" + total +
                '}';
    }
}
