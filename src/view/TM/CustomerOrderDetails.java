package view.TM;

public class CustomerOrderDetails {
    private String itemCode;
    private int qty;
    private double price;

    public CustomerOrderDetails() {
    }

    public CustomerOrderDetails(String itemCode, int qty, double price) {
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
}
