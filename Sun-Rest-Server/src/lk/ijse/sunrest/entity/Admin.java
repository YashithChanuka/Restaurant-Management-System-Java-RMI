package lk.ijse.sunrest.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Admin")
public class Admin {

    @Id
    private String adminID;
    private String userName;
    private String password;

    public Admin() {
    }

    public Admin(String adminID, String userName, String password) {
        this.adminID = adminID;
        this.userName = userName;
        this.password = password;
    }

    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
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
}
