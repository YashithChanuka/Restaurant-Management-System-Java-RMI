package lk.ijse.sunrest.repository.custom;

import lk.ijse.sunrest.entity.Chef;
import lk.ijse.sunrest.entity.Customer;
import lk.ijse.sunrest.entity.OrderDetail;
import lk.ijse.sunrest.repository.CrudRepo;

import java.util.List;

public interface OrderDetailRepo extends CrudRepo<OrderDetail, String> {

    public List<OrderDetail> getAllNewOrders() throws Exception;

    public List<OrderDetail> getAllTodayOrders(String date) throws Exception;

    public List<OrderDetail> getAllFinishedOrders() throws Exception;

    public boolean updateOrderStatusKitchen(String orderID, String chefID)throws Exception;

    public boolean updateOrderStatusFinished(String orderID)throws Exception;

}
