package lk.ijse.sunrest.business.custom.impl;

import lk.ijse.sunrest.business.custom.OrderDetailBO;
import lk.ijse.sunrest.dto.CustomerDTO;
import lk.ijse.sunrest.dto.ItemDTO;
import lk.ijse.sunrest.dto.OrderDetailDTO;
import lk.ijse.sunrest.entity.Chef;
import lk.ijse.sunrest.entity.OrderDetail;
import lk.ijse.sunrest.repository.RepoFactory;
import lk.ijse.sunrest.repository.custom.OrderDetailRepo;
import lk.ijse.sunrest.util.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailBOImpl implements OrderDetailBO {

    private OrderDetailRepo orderDetailRepo;

    public OrderDetailBOImpl() throws Exception {
        orderDetailRepo = RepoFactory.getInstance().getSuperRepo(RepoFactory.RepoTypes.ORDERDETAIL);
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
        Session session = HibernateUtil.getSessionFactory().openSession();
        orderDetailRepo.setSession(session);
        session.beginTransaction();

        OrderDetail search = orderDetailRepo.search(id);

        session.getTransaction().commit();
        session.close();
        return new OrderDetailDTO(search.getOrderID(),
                search.getOrderedQty(),
                search.getStatus(),
                search.getTotalAmount(),
                search.getDate(),
                search.getCustomer().getCustomerID(),
                search.getItem().getItemCode());
    }

    @Override
    public List<OrderDetailDTO> getAllOrderDetails() throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        orderDetailRepo.setSession(session);
        session.beginTransaction();

        List<OrderDetail> all = orderDetailRepo.getAll();
        ArrayList<OrderDetailDTO> orderDetailDTOS = new ArrayList<>();
        for (OrderDetail or : all){
            CustomerDTO customerDTO = new CustomerDTO(or.getCustomer().getCustomerID(), or.getCustomer().getCustomerName(), or.getCustomer().getCustomerAddress(), or.getCustomer().getCustomerTele());
//            ItemDTO itemDTO = new ItemDTO(or.getItem().getItemCode(), or.getItem().getItemName(), or.getItem().getDescription(), or.getItem().getSellingPrice());
//            System.out.println(itemDTO.getItemCode());
            orderDetailDTOS.add(new OrderDetailDTO(or.getOrderID(),
                    or.getOrderedQty(),
                    or.getStatus(),
                    or.getTotalAmount(),
                    or.getDate(),
                    customerDTO));
        }

        session.getTransaction().commit();
        session.close();
        return orderDetailDTOS;
    }

    @Override
    public List<OrderDetailDTO> getAllNewOrderDetails() throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        orderDetailRepo.setSession(session);
        session.beginTransaction();

        List<OrderDetail> all = orderDetailRepo.getAllNewOrders();
        ArrayList<OrderDetailDTO> orderDetailDTOS = new ArrayList<>();
        for (OrderDetail or : all){
            CustomerDTO customerDTO = new CustomerDTO(or.getCustomer().getCustomerID(), or.getCustomer().getCustomerName(), or.getCustomer().getCustomerAddress(), or.getCustomer().getCustomerTele());
            ItemDTO itemDTO = new ItemDTO(or.getItem().getItemCode(), or.getItem().getItemName(), or.getItem().getDescription(), or.getItem().getSellingPrice());
            Chef chef = or.getChef();
            orderDetailDTOS.add(new OrderDetailDTO(or.getOrderID(),
                    or.getOrderedQty(),
                    or.getStatus(),
                    or.getTotalAmount(),
                    itemDTO.getItemName(),
                    customerDTO.getCustomerName()));
        }

        session.getTransaction().commit();
        session.close();
        return orderDetailDTOS;

    }

    @Override
    public List<OrderDetailDTO> getAllTodayOrderDetails(String date) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        orderDetailRepo.setSession(session);
        session.beginTransaction();

        List<OrderDetail> all = orderDetailRepo.getAllTodayOrders(date);
        ArrayList<OrderDetailDTO> orderDetailDTOS = new ArrayList<>();
        for (OrderDetail or : all){
            CustomerDTO customerDTO = new CustomerDTO(or.getCustomer().getCustomerID(), or.getCustomer().getCustomerName(), or.getCustomer().getCustomerAddress(), or.getCustomer().getCustomerTele());
            ItemDTO itemDTO = new ItemDTO(or.getItem().getItemCode(), or.getItem().getItemName(), or.getItem().getDescription(), or.getItem().getSellingPrice());
            Chef chef = or.getChef();
            orderDetailDTOS.add(new OrderDetailDTO(or.getOrderID(),
                    or.getOrderedQty(),
                    or.getStatus(),
                    or.getTotalAmount(),
                    itemDTO.getItemName(),
                    customerDTO.getCustomerName(),
                    chef.getChefName()));
        }

        session.getTransaction().commit();
        session.close();
        return orderDetailDTOS;
    }

    @Override
    public List<OrderDetailDTO> getAllFinishedOrderDetails() throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        orderDetailRepo.setSession(session);
        session.beginTransaction();

        List<OrderDetail> all = orderDetailRepo.getAllFinishedOrders();
        ArrayList<OrderDetailDTO> orderDetailDTOS = new ArrayList<>();
        for (OrderDetail or : all){
            CustomerDTO customerDTO = new CustomerDTO(or.getCustomer().getCustomerID(), or.getCustomer().getCustomerName(), or.getCustomer().getCustomerAddress(), or.getCustomer().getCustomerTele());
            ItemDTO itemDTO = new ItemDTO(or.getItem().getItemCode(), or.getItem().getItemName(), or.getItem().getDescription(), or.getItem().getSellingPrice());
            Chef chef = or.getChef();
            orderDetailDTOS.add(new OrderDetailDTO(or.getOrderID(),
                    or.getOrderedQty(),
                    or.getTotalAmount(),
                    or.getDate(),
                    chef.getChefName(),
                    itemDTO.getItemName(),
                    customerDTO.getCustomerName()));
        }

        session.getTransaction().commit();
        session.close();
        return orderDetailDTOS;
    }

    @Override
    public boolean updateOrderStatusKitchen(String orderID, String chefID) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        orderDetailRepo.setSession(session);
        session.beginTransaction();

        boolean b = orderDetailRepo.updateOrderStatusKitchen(orderID, chefID);

        session.getTransaction().commit();
        session.close();
        return b;
    }

    @Override
    public boolean updateOrderStatusFinished(String orderID) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        orderDetailRepo.setSession(session);
        session.beginTransaction();

        boolean b = orderDetailRepo.updateOrderStatusFinished(orderID);

        session.getTransaction().commit();
        session.close();
        return b;
    }
}
