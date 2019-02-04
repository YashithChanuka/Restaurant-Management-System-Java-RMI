package lk.ijse.sunrest.dto;

public class RegCusCommonDTO implements SuperDTO{

    private String customerID;
    private String itemCode;
    private OrderDetailDTO orderDetailDTO;

    public RegCusCommonDTO() {
    }

    public RegCusCommonDTO(String customerID, String itemCode, OrderDetailDTO orderDetailDTO) {
        this.customerID = customerID;
        this.itemCode = itemCode;
        this.orderDetailDTO = orderDetailDTO;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public OrderDetailDTO getOrderDetailDTO() {
        return orderDetailDTO;
    }

    public void setOrderDetailDTO(OrderDetailDTO orderDetailDTO) {
        this.orderDetailDTO = orderDetailDTO;
    }
}
