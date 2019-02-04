package lk.ijse.sunrest.entity;

import lk.ijse.sunrest.dto.ChefDTO;
import lk.ijse.sunrest.dto.CustomerDTO;
import lk.ijse.sunrest.dto.ItemDTO;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "OrderDetail")
public class OrderDetail {

    @Id
    private String orderID;
    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "customerID", referencedColumnName = "customerID", insertable = false, updatable = false)
    private Customer customer;
    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "itemCode", referencedColumnName = "itemCode", insertable = false, updatable = false)
    private Item item;
    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "chefID", referencedColumnName = "chefID", insertable = false, updatable = false)
    private Chef chef;
    @Temporal(TemporalType.DATE)
    private Date date;
    private int orderedQty;
    private double totalAmount;
    private String status;

    public OrderDetail() {
    }

    public OrderDetail(String orderID, Customer customer, Date date, int orderedQty, double totalAmount, String status) {
        this.orderID = orderID;
        this.customer = customer;
        this.date = date;
        this.orderedQty = orderedQty;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public OrderDetail(String orderID, Customer customer, Item item, Date date, int orderedQty, double totalAmount, String status) {
        this.orderID = orderID;
        this.customer = customer;
        this.item = item;
        this.date = date;
        this.orderedQty = orderedQty;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public OrderDetail(String orderID, Customer customer, Item item, Chef chef, Date date, int orderedQty, double totalAmount, String status) {
        this.orderID = orderID;
        this.customer = customer;
        this.item = item;
        this.chef = chef;
        this.date = date;
        this.orderedQty = orderedQty;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Chef getChef() {
        return chef;
    }

    public void setChef(Chef chef) {
        this.chef = chef;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getOrderedQty() {
        return orderedQty;
    }

    public void setOrderedQty(int orderedQty) {
        this.orderedQty = orderedQty;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
