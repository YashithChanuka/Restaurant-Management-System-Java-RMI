package lk.ijse.sunrest.business.custom;

import lk.ijse.sunrest.business.SuperBO;
import lk.ijse.sunrest.dto.ChefDTO;
import lk.ijse.sunrest.dto.CustomerDTO;
import lk.ijse.sunrest.dto.OrderDetailDTO;

import java.util.List;

public interface OrderDetailBO extends SuperBO {

    public boolean addOrderDetail(OrderDetailDTO orderDetailDTO)throws Exception;
    public boolean deleteOrderDetail(OrderDetailDTO orderDetailDTO)throws Exception;
    public boolean updateOrderDetail(OrderDetailDTO orderDetailDTO)throws Exception;
    public OrderDetailDTO searchOrderDetail(String id)throws Exception;
    public List<OrderDetailDTO> getAllOrderDetails()throws Exception;
    public List<OrderDetailDTO> getAllNewOrderDetails()throws Exception;
    public List<OrderDetailDTO> getAllTodayOrderDetails(String date)throws Exception;
    public List<OrderDetailDTO> getAllFinishedOrderDetails()throws Exception;
    public boolean updateOrderStatusKitchen(String orderID, String chefID)throws Exception;
    public boolean updateOrderStatusFinished(String orderID)throws Exception;

}
