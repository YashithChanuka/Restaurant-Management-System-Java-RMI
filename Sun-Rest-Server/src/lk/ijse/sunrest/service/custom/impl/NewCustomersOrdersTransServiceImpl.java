package lk.ijse.sunrest.service.custom.impl;

import lk.ijse.sunrest.business.BOFactory;
import lk.ijse.sunrest.business.custom.NewCustomersOrdersBO;
import lk.ijse.sunrest.dto.CommonDTO;
import lk.ijse.sunrest.observer.Observer;
import lk.ijse.sunrest.service.custom.NewCustomersOrdersTransService;

import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class NewCustomersOrdersTransServiceImpl extends UnicastRemoteObject implements NewCustomersOrdersTransService {

    private NewCustomersOrdersBO newCustomersOrdersBO;
    public static ArrayList<Observer> allNewCustomersOrdersObservers = new ArrayList<>();

    public NewCustomersOrdersTransServiceImpl() throws Exception {
        newCustomersOrdersBO = BOFactory.getInstance().getSuperBO(BOFactory.BOTypes.NEWCUSTOMERSORDERSTRANS);
    }



    @Override
    public boolean addNewCustomerOrder(CommonDTO commonDTO) throws Exception {
        newCustomersOrdersBO.addNewCustomersOrders(commonDTO);
        notifyAllObservers("");
        return true;
    }

    @Override
    public void register(Observer observer) throws Exception {
        allNewCustomersOrdersObservers.add(observer);
    }

    @Override
    public void unregister(Observer observer) throws Exception {

    }

    @Override
    public void notifyAllObservers(String message) throws Exception {
        for (Observer observer : allNewCustomersOrdersObservers){
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
