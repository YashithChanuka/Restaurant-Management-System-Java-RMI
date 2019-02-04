package lk.ijse.sunrest.service.custom.impl;

import lk.ijse.sunrest.business.BOFactory;
import lk.ijse.sunrest.business.custom.RegCusOrdersTransBO;
import lk.ijse.sunrest.dto.RegCusCommonDTO;
import lk.ijse.sunrest.observer.Observer;
import lk.ijse.sunrest.service.custom.RegCusOrdersTransService;

import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class RegCusOrdersTransServiceImpl extends UnicastRemoteObject implements RegCusOrdersTransService {

    private RegCusOrdersTransBO regCusOrdersTransBO;
    public static ArrayList<Observer> allRegCusOrdersObservers = new ArrayList<>();

    public RegCusOrdersTransServiceImpl() throws Exception {
        regCusOrdersTransBO = BOFactory.getInstance().getSuperBO(BOFactory.BOTypes.REGCUSORDER);
    }

    @Override
    public boolean addRegCusOrder(RegCusCommonDTO regCusCommonDTO) throws Exception {
        regCusOrdersTransBO.addRegCusOrder(regCusCommonDTO);
        notifyAllObservers("");
        return true;
    }

    @Override
    public void register(Observer observer) throws Exception {
        allRegCusOrdersObservers.add(observer);
    }

    @Override
    public void unregister(Observer observer) throws Exception {

    }

    @Override
    public void notifyAllObservers(String message) throws Exception {
        for (Observer observer : allRegCusOrdersObservers){
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
