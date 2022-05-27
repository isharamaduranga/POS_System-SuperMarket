package lk.ijse.pos.dto;

public class ItemDTO {
    private String itemID;
    private String itemDescription;
    private String packSize;
    private double unitPrice;
    private int qtyOnHand;

    public ItemDTO() {
    }

    public ItemDTO(String itemID, String itemDescription, String packSize, double unitPrice, int qtyOnHand) {
        this.itemID = itemID;
        this.itemDescription = itemDescription;
        this.packSize = packSize;
        this.unitPrice = unitPrice;
        this.qtyOnHand = qtyOnHand;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getPackSize() {
        return packSize;
    }

    public void setPackSize(String packSize) {
        this.packSize = packSize;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    @Override
    public String toString() {
        return "ItemDTO{" +
                "itemID='" + itemID + '\'' +
                ", itemDescription='" + itemDescription + '\'' +
                ", packSize='" + packSize + '\'' +
                ", unitPrice=" + unitPrice +
                ", qtyOnHand=" + qtyOnHand +
                '}';
    }
}
