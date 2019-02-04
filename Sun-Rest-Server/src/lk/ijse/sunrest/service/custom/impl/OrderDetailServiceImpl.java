package lk.ijse.sunrest.service.custom.impl;

import lk.ijse.sunrest.business.BOFactory;
import lk.ijse.sunrest.business.custom.OrderDetailBO;
import lk.ijse.sunrest.dto.OrderDetailDTO;
import lk.ijse.sunrest.observer.Observer;
import lk.ijse.sunrest.service.custom.OrderDetailService;

import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailServiceImpl extends UnicastRemoteObject implements OrderDetailService {

    private OrderDetailBO orderDetailBO;
    public static ArrayList<Observer> allOrderDetailsObservers = new ArrayList<>();

    public OrderDetailServiceImpl() throws Exception {
        orderDetailBO = BOFactory.getInstance().getSuperBO(BOFactory.BOTypes.ORDERDETAIL);
    }

    @Override
    public boolean addOrderDetail(OrderDetailDTO orderDetailDTO) throws Exception {
        return false;
    }

    @Override
    public boolean deleteOrderDetail(OrderDetailDTO orderDetailDTO) throws Exception {
        return false;
    }

    @Override
    public boolean updateOrderDetail(OrderDetailDTO orderDetailDTO) throws Exception {
        return false;
    }

    @Override
    public OrderDetailDTO searchOrderDetail(String id) throws Exception {
        return orderDetailBO.searchOrderDetail(id);
    }

    @Override
    public List<OrderDetailDTO> getAllOrderDetails() throws Exception {
        return orderDetailBO.getAllOrderDetails();
    }

    @Override
    public List<OrderDetailDTO> getAllNewOrderDetails() throws Exception {
        List<OrderDetailDTO> allNewOrderDetails = orderDetailBO.getAllNewOrderDetails();
        return allNewOrderDetails;
    }

    @Override
    public List<OrderDetailDTO> getAllTodayOrderDetails(String date) throws Exception {
        return orderDetailBO.getAllTodayOrderDetails(date);
    }

    @Override
    public List<OrderDetailDTO> getAllFinishedOrderDetails() throws Exception {
        List<OrderDetailDTO> allFinishedOrderDetails = orderDetailBO.getAllFinishedOrderDetails();
        return allFinishedOrderDetails;
    }

    @Override
    public boolean updateOrderStatusKitchen(String orderID, String chefID) throws Exception {
        orderDetailBO.updateOrderStatusKitchen(orderID, chefID);
        notifyAllObservers("");
        return true;
    }

    @Override
    public boolean updateOrderStatusFinished(String orderID) throws Exception {
        orderDetailBO.updateOrderStatusFinished(orderID);
        notifyAllObservers("");
        return true;
    }

    @Override
    public void register(Observer observer) throws Exception {
        allOrderDetailsObservers.add(observer);
    }

    @Override
    public void unregister(Observer observer) throws Exception {

    }

    @Override
    public void notifyAllObservers(String message) throws Exception {
        for (Observer observer : allOrderDetailsObservers){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        observer.update(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    @Override
    public boolean reserved(Object id) throws Exception {
        return false;
    }

    @Override
    public boolean released(Object id) throws Exception {
        return false;
    }
}
