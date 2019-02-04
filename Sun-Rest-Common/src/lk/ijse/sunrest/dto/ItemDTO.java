package lk.ijse.sunrest.dto;

public class ItemDTO implements SuperDTO{

    private String itemCode;
    private String itemName;
    private String description;
    private double sellingPrice;

    public ItemDTO() {
    }

    public ItemDTO(String itemCode) {
        this.itemCode = itemCode;
    }

    public ItemDTO(String itemCode, String itemName, String description, double sellingPrice) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.description = description;
        this.sellingPrice = sellingPrice;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    @Override
    public String toString() {
        return itemName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null){
            return false;
        }
        return (((ItemDTO)obj).getItemName().equals(this.itemName));
    }
}
