package lk.ijse.sunrest.dto;

import java.util.Date;

public class OrderDetailDTO implements SuperDTO{

    private String orderID;
    private int orderedQty;
    private String status;
    private double totalAmount;
    private Date date;
    private String chefID;
    private CustomerDTO customerDTO;
    private String itemCode;
    private ItemDTO itemDTO;
    private String itemName;
    private String customerName;
    private String customerID;
    private String chefName;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(String orderID, int orderedQty, String status, double totalAmount, String itemName, String customerName) {
        this.orderID = orderID;
        this.orderedQty = orderedQty;
        this.status = status;
        this.totalAmount = totalAmount;
        this.itemName = itemName;
        this.customerName = customerName;
    }

    public OrderDetailDTO(String orderID, int orderedQty, String status, double totalAmount, String itemName, String customerName, String chefName) {
        this.orderID = orderID;
        this.orderedQty = orderedQty;
        this.status = status;
        this.totalAmount = totalAmount;
        this.itemName = itemName;
        this.customerName = customerName;
        this.chefName = chefName;
    }

    public OrderDetailDTO(String orderID, int orderedQty, double totalAmount, Date date, String chefName, String itemName, String customerName) {
        this.orderID = orderID;
        this.orderedQty = orderedQty;
        this.totalAmount = totalAmount;
        this.date = date;
        this.chefName = chefName;
        this.itemName = itemName;
        this.customerName = customerName;
    }

    public OrderDetailDTO(String orderID, int orderedQty, String status, double totalAmount, Date date) {
        this.orderID = orderID;
        this.orderedQty = orderedQty;
        this.status = status;
        this.totalAmount = totalAmount;
        this.date = date;
    }

    public OrderDetailDTO(String orderID, int orderedQty, String status, double totalAmount, Date date, CustomerDTO customerDTO) {
        this.orderID = orderID;
        this.orderedQty = orderedQty;
        this.status = status;
        this.totalAmount = totalAmount;
        this.date = date;
        this.customerDTO = customerDTO;
    }


    public OrderDetailDTO(String orderID, int orderedQty, String status, double totalAmount, Date date, CustomerDTO customerDTO, ItemDTO itemDTO) {
        this.orderID = orderID;
        this.orderedQty = orderedQty;
        this.status = status;
        this.totalAmount = totalAmount;
        this.date = date;
        this.customerDTO = customerDTO;
        this.itemDTO = itemDTO;
    }

    public OrderDetailDTO(String orderID, int orderedQty, String status, double totalAmount, Date date, String itemName, String customerName) {
        this.orderID = orderID;
        this.orderedQty = orderedQty;
        this.status = status;
        this.totalAmount = totalAmount;
        this.date = date;
        this.itemName = itemName;
        this.customerName = customerName;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public int getOrderedQty() {
        return orderedQty;
    }

    public void setOrderedQty(int orderedQty) {
        this.orderedQty = orderedQty;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getChefID() {
        return chefID;
    }

    public void setChefID(String chefID) {
        this.chefID = chefID;
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public ItemDTO getItemDTO() {
        return itemDTO;
    }

    public void setItemDTO(ItemDTO itemDTO) {
        this.itemDTO = itemDTO;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getChefName() {
        return chefName;
    }

    public void setChefName(String chefName) {
        this.chefName = chefName;
    }
}

