package lk.ijse.sunrest.repository.custom.impl;

import lk.ijse.sunrest.entity.OrderDetail;
import lk.ijse.sunrest.repository.CrudRepoImpl;
import lk.ijse.sunrest.repository.custom.OrderDetailRepo;
import org.hibernate.query.Query;

import java.util.List;

public class OrderDetailRepoImpl extends CrudRepoImpl<OrderDetail, String> implements OrderDetailRepo {

    @Override
    public List<OrderDetail> getAllNewOrders() throws Exception {
        List<OrderDetail> all = null;
        try {
            all = session.createQuery("FROM OrderDetail WHERE status='NEW'").list();
            return all;
        }catch (Exception ex){
            return null;
        }
    }

    @Override
    public List<OrderDetail> getAllTodayOrders(String date) throws Exception {
        List<OrderDetail> all = null;
        try {
            Query query = session.createQuery("FROM OrderDetail WHERE date='"+date+"' AND status='FINISHED' OR status='KITCHEN'");
            all = query.list();
            return all;
        }catch (Exception ex){
            return null;
        }

    }

    @Override
    public List<OrderDetail> getAllFinishedOrders() throws Exception {
        List<OrderDetail> all = null;
        try {
            Query query = session.createQuery("FROM OrderDetail WHERE status='FINISHED'");
            all = query.list();
            return all;
        }catch (Exception ex){
            return null;
        }
    }

    @Override
    public boolean updateOrderStatusKitchen(String orderID, String chefID) throws Exception {
        Query query = session.createQuery("UPDATE OrderDetail SET status='KITCHEN',chef_chefID='"+chefID+"'WHERE orderID='" + orderID + "'");
        int i = query.executeUpdate();
        if (i>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean updateOrderStatusFinished(String orderID) throws Exception {
        Query query = session.createQuery("UPDATE OrderDetail SET status='FINISHED' WHERE orderID='" + orderID + "'");
        int i = query.executeUpdate();
        if (i>0){
            return true;
        }else{
            return false;
        }
    }
}
