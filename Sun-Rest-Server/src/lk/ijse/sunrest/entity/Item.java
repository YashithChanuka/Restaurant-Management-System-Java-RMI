package lk.ijse.sunrest.entity;

import lk.ijse.sunrest.dto.ItemDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Item")
public class Item {

    @Id
    private String itemCode;
    private String itemName;
    private String description;
    private double sellingPrice;
    @OneToMany(mappedBy = "item", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<OrderDetail> orderDetail = new ArrayList<>();

    public Item() {
    }

    public Item(ItemDTO itemDTO) {
    }

    public Item(String itemCode, String itemName, String description, double sellingPrice) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.description = description;
        this.sellingPrice = sellingPrice;
    }

    public Item(String itemCode, String itemName, String description, double sellingPrice, List<OrderDetail> orderDetail) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.description = description;
        this.sellingPrice = sellingPrice;
        this.orderDetail = orderDetail;
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

    public List<OrderDetail> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<OrderDetail> orderDetail) {
        this.orderDetail = orderDetail;
    }
}
