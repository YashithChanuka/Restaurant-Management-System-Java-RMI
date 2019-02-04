package lk.ijse.sunrest.proxy;

import lk.ijse.sunrest.service.ServiceFactory;
import lk.ijse.sunrest.service.custom.*;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ProxyHandeler implements ServiceFactory {

    private static ProxyHandeler proxyHandeler;
    private CustomerService customerService;
    private ChefService chefService;
    private ItemService itemService;
    private OrderDetailService orderDetailService;
    private NewCustomersOrdersTransService newCustomersOrdersTransService;
    private RegCusOrdersTransService regCusOrdersTransService;

    public static ProxyHandeler getInstance()throws Exception{
        if (proxyHandeler == null){
            proxyHandeler = new ProxyHandeler();
        }
        return proxyHandeler;
    }

    public ProxyHandeler() {
        try {
            ServiceFactory serviceFactory = (ServiceFactory) Naming.lookup("rmi://localhost:6161/Chanu");
            customerService = serviceFactory.getSuperService(ServiceTypes.CUSTOMER);
            chefService = serviceFactory.getSuperService(ServiceTypes.CHEF);
            itemService = serviceFactory.getSuperService(ServiceTypes.ITEM);
            orderDetailService = serviceFactory.getSuperService(ServiceTypes.ORDERDETAIL);
            newCustomersOrdersTransService = serviceFactory.getSuperService(ServiceTypes.NEWCUSTOMERSORDERSTRANS);
            regCusOrdersTransService = serviceFactory.getSuperService(ServiceTypes.REGCUSORDERS);

        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public <T> T getSuperService(ServiceTypes types) throws Exception {
        switch (types){
            case CUSTOMER:
                return (T) customerService;
            case CHEF:
                return (T) chefService;
            case ITEM:
                return (T) itemService;
            case ORDERDETAIL:
                return (T) orderDetailService;
            case NEWCUSTOMERSORDERSTRANS:
                return (T) newCustomersOrdersTransService;
            case REGCUSORDERS:
                return (T) regCusOrdersTransService;
            default:
                return null;
        }
    }
}
