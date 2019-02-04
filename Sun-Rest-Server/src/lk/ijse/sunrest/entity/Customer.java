package lk.ijse.sunrest.entity;

import lk.ijse.sunrest.dto.CustomerDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Customer")
public class Customer {

    @Id
    private String customerID;
    private String customerName;
    private String customerAddress;
    private String customerTele;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<OrderDetail> orderDetail = new ArrayList<>();

    public Customer() {
    }

    public Customer(CustomerDTO customerDTO) {
    }

    public Customer(String customerID) {
        this.customerID = customerID;
    }

    public Customer(String customerID, String customerName, String customerAddress, String customerTele) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerTele = customerTele;
    }

    public Customer(String customerID, String customerName, String customerAddress, String customerTele, List<OrderDetail> orderDetail) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerTele = customerTele;
        this.orderDetail = orderDetail;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerTele() {
        return customerTele;
    }

    public void setCustomerTele(String customerTele) {
        this.customerTele = customerTele;
    }

    public List<OrderDetail> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<OrderDetail> orderDetail) {
        this.orderDetail = orderDetail;
    }
}
