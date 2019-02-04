package lk.ijse.sunrest.service;

import java.rmi.Remote;

public interface ServiceFactory extends Remote {

    public enum ServiceTypes{
        CUSTOMER,CHEF,ITEM,ORDERDETAIL,NEWCUSTOMERSORDERSTRANS,REGCUSORDERS,ADMIN;
    }

    public <T>T getSuperService(ServiceTypes types)throws Exception;

}
