package lk.ijse.sunrest.dto;

public class CommonDTO implements SuperDTO{

    private CustomerDTO customerDTO;
    private String itemCode;
    private OrderDetailDTO orderDetailDTO;

    public CommonDTO() {
    }

    public CommonDTO(CustomerDTO customerDTO, String itemCode, OrderDetailDTO orderDetailDTO) {
        this.customerDTO = customerDTO;
        this.itemCode = itemCode;
        this.orderDetailDTO = orderDetailDTO;
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

    public OrderDetailDTO getOrderDetailDTO() {
        return orderDetailDTO;
    }

    public void setOrderDetailDTO(OrderDetailDTO orderDetailDTO) {
        this.orderDetailDTO = orderDetailDTO;
    }
}
