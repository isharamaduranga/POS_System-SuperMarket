/**
 * @author : Ishara Maduarnga
 * Project Name: SuperMarket
 * Date        : 5/27/2022
 * Time        : 9:29 PM
 * Year        : 2022
 */

package lk.ijse.pos.entity;

public class Custom {
    private String CusID;
    private String CusTitle;
    private String CusName;
    private String CusAddress;
    private String City;
    private String Povince;
    private String PostCode;

    private String ItemCode;
    private String Description;
    private String PackSize;
    private double UnitPrice;
    private int QtyOnHand;

    private String OrderID;
    private String OrderDate;

    private int Orderqty;
    private double Discount;
    private double Price;

    public Custom() {
    }

    public Custom(String orderID,String orderDate,String cusID,String itemCode,int orderqty,double discount,double price) {
        CusID = cusID;
        ItemCode = itemCode;
        OrderID = orderID;
        OrderDate = orderDate;
        Orderqty = orderqty;
        Discount = discount;
        Price = price;
    }

    public Custom(String cusID, String cusTitle, String cusName, String cusAddress, String city, String povince, String postCode, String itemCode, String description, String packSize, double unitPrice, int qtyOnHand, String orderID, String orderDate, int orderqty, double discount, double price) {
        CusID = cusID;
        CusTitle = cusTitle;
        CusName = cusName;
        CusAddress = cusAddress;
        City = city;
        Povince = povince;
        PostCode = postCode;
        ItemCode = itemCode;
        Description = description;
        PackSize = packSize;
        UnitPrice = unitPrice;
        QtyOnHand = qtyOnHand;
        OrderID = orderID;
        OrderDate = orderDate;
        Orderqty = orderqty;
        Discount = discount;
        Price = price;
    }



    public String getCusID() {
        return CusID;
    }

    public void setCusID(String cusID) {
        CusID = cusID;
    }

    public String getCusTitle() {
        return CusTitle;
    }

    public void setCusTitle(String cusTitle) {
        CusTitle = cusTitle;
    }

    public String getCusName() {
        return CusName;
    }

    public void setCusName(String cusName) {
        CusName = cusName;
    }

    public String getCusAddress() {
        return CusAddress;
    }

    public void setCusAddress(String cusAddress) {
        CusAddress = cusAddress;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getPovince() {
        return Povince;
    }

    public void setPovince(String povince) {
        Povince = povince;
    }

    public String getPostCode() {
        return PostCode;
    }

    public void setPostCode(String postCode) {
        PostCode = postCode;
    }

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPackSize() {
        return PackSize;
    }

    public void setPackSize(String packSize) {
        PackSize = packSize;
    }

    public double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        UnitPrice = unitPrice;
    }

    public int getQtyOnHand() {
        return QtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        QtyOnHand = qtyOnHand;
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
        return "Custom{" +
                "CusID='" + CusID + '\'' +
                ", CusTitle='" + CusTitle + '\'' +
                ", CusName='" + CusName + '\'' +
                ", CusAddress='" + CusAddress + '\'' +
                ", City='" + City + '\'' +
                ", Povince='" + Povince + '\'' +
                ", PostCode='" + PostCode + '\'' +
                ", ItemCode='" + ItemCode + '\'' +
                ", Description='" + Description + '\'' +
                ", PackSize='" + PackSize + '\'' +
                ", UnitPrice=" + UnitPrice +
                ", QtyOnHand=" + QtyOnHand +
                ", OrderID='" + OrderID + '\'' +
                ", OrderDate='" + OrderDate + '\'' +
                ", Orderqty=" + Orderqty +
                ", Discount=" + Discount +
                ", Price=" + Price +
                '}';
    }
}
