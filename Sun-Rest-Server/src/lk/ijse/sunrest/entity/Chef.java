package lk.ijse.sunrest.entity;

import lk.ijse.sunrest.dto.ChefDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Chef")
public class Chef {

    @Id
    private String chefID;
    private String chefName;
    private String address;
    private String gender;
    private String tele;
    private String email;
    private String userName;
    private String password;
    @OneToMany(mappedBy = "chef", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<OrderDetail> orderDetail = new ArrayList<>();

    public Chef() {
    }

    public Chef(String chefID, String chefName, String address, String gender, String tele, String email, String userName, String password) {
        this.chefID = chefID;
        this.chefName = chefName;
        this.address = address;
        this.gender = gender;
        this.tele = tele;
        this.email = email;
        this.userName = userName;
        this.password = password;
    }

    public Chef(String chefID, String chefName, String address, String gender, String tele, String email, String userName, String password, List<OrderDetail> orderDetail) {
        this.chefID = chefID;
        this.chefName = chefName;
        this.address = address;
        this.gender = gender;
        this.tele = tele;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.orderDetail = orderDetail;
    }

    public Chef(ChefDTO chefDTO) {

    }

    public String getChefID() {
        return chefID;
    }

    public void setChefID(String chefID) {
        this.chefID = chefID;
    }

    public String getChefName() {
        return chefName;
    }

    public void setChefName(String chefName) {
        this.chefName = chefName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<OrderDetail> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<OrderDetail> orderDetail) {
        this.orderDetail = orderDetail;
    }
}
