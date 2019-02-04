package lk.ijse.sunrest.service.impl;

import lk.ijse.sunrest.service.ServiceFactory;
import lk.ijse.sunrest.service.custom.impl.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServiceFactoryImpl extends UnicastRemoteObject implements ServiceFactory {

    public ServiceFactoryImpl() throws RemoteException {
    }

    private static ServiceFactoryImpl serviceFactory;

    public static ServiceFactoryImpl getInstance() throws Exception{
        if(serviceFactory == null){
            serviceFactory = new ServiceFactoryImpl();
        }
        return serviceFactory;
    }

    @Override
    public <T> T getSuperService(ServiceTypes types) throws Exception {
        switch (types) {
            case CUSTOMER:
                return (T) new CustomerServiceImpl();
            case CHEF:
                return (T) new ChefServiceImpl();
            case ITEM:
                return (T) new ItemServiceImpl();
            case ORDERDETAIL:
                return (T) new OrderDetailServiceImpl();
            case NEWCUSTOMERSORDERSTRANS:
                return (T) new NewCustomersOrdersTransServiceImpl();
            case REGCUSORDERS:
                return (T) new RegCusOrdersTransServiceImpl();
            case ADMIN:
                return (T) new AdminServiceImpl();
            default:
                return null;
        }
    }
}
