package lk.ijse.sunrest.dto;

public class AdminDTO implements SuperDTO{

    private String adminID;
    private String userName;
    private String password;

    public AdminDTO() {
    }

    public AdminDTO(String adminID, String userName, String password) {
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
