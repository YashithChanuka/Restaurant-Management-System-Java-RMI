package lk.ijse.sunrest.dto;

public class CustomerDTO implements SuperDTO{

    private String customerID;
    private String customerName;
    private String customerAddress;
    private String customerTele;

    public CustomerDTO() {
    }

    public CustomerDTO(String customerID) {
        this.customerID = customerID;
    }

    public CustomerDTO(String customerID, String customerName, String customerAddress, String customerTele) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerTele = customerTele;
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

    @Override
    public String toString() {
        return customerName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null){
            return false;
        }
        return (((CustomerDTO)obj).customerName.equals(this.customerName));
    }
}
