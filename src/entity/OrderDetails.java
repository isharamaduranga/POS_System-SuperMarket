/**
 * @author : Ishara Maduarnga
 * Project Name: SuperMarket
 * Date        : 5/27/2022
 * Time        : 4:00 PM
 * Year        : 2022
 */

package entity;

public class OrderDetails {
    private String OrderID;
    private String ItemCode;
    private int Orderqty;
    private double Discount;
    private double Price;

    public OrderDetails() {
    }

    public OrderDetails(String orderID, String itemCode, int orderqty, double discount, double price) {
        OrderID = orderID;
        ItemCode = itemCode;
        Orderqty = orderqty;
        Discount = discount;
        Price = price;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
    }

    public int getOrderqty() {
        return Orderqty;
    }

    public void setOrderqty(int orderqty) {
        Orderqty = orderqty;
    }

    public double getDiscount() {
        return Discount;
    }

    public void setDiscount(double discount) {
        Discount = discount;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "OrderID='" + OrderID + '\'' +
                ", ItemCode='" + ItemCode + '\'' +
                ", Orderqty=" + Orderqty +
                ", Discount=" + Discount +
                ", Price=" + Price +
                '}';
    }
}
