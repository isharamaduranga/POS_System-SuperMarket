/**
 * @author : Ishara Maduarnga
 * Project Name: SuperMarket
 * Date        : 5/27/2022
 * Time        : 3:58 PM
 * Year        : 2022
 */

package lk.ijse.pos.entity;

public class Item {
    private String ItemCode;
    private String Description;
    private String PackSize;
    private double UnitPrice;
    private int QtyOnHand;

    public Item() {
    }

    public Item(String itemCode, String description, String packSize, double unitPrice, int qtyOnHand) {
        ItemCode = itemCode;
        Description = description;
        PackSize = packSize;
        UnitPrice = unitPrice;
        QtyOnHand = qtyOnHand;
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

    @Override
    public String toString() {
        return "Item{" +
                "ItemCode='" + ItemCode + '\'' +
                ", Description='" + Description + '\'' +
                ", PackSize='" + PackSize + '\'' +
                ", UnitPrice=" + UnitPrice +
                ", QtyOnHand=" + QtyOnHand +
                '}';
    }
}
