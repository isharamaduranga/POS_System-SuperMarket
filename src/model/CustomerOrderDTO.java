package model;

public class CustomerOrderDTO {
    private String itemCode;
    private int qty;
    private double price;

    public CustomerOrderDTO() {
    }

    public CustomerOrderDTO(String itemCode, int qty, double price) {
        this.itemCode = itemCode;
        this.qty = qty;
        this.price = price;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "CustomerOrderDTO{" +
                "itemCode='" + itemCode + '\'' +
                ", qty=" + qty +
                ", price=" + price +
                '}';
    }
}
