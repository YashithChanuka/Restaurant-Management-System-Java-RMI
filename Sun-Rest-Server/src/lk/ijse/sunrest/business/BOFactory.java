package lk.ijse.sunrest.business;

import lk.ijse.sunrest.business.custom.impl.*;

public class BOFactory {

    private static BOFactory boFactory;

    public static BOFactory getInstance()throws Exception{
        if (boFactory == null){
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public enum BOTypes{
        CUSTOMER,CHEF,ITEM,ORDERDETAIL,NEWCUSTOMERSORDERSTRANS,REGCUSORDER,ADMIN;
    }

    public <T>T getSuperBO(BOTypes types) throws Exception {
        switch (types){
            case CUSTOMER:
                return (T) new CustomerBOImpl();
            case CHEF:
                return (T) new ChefBOImpl();
            case ITEM:
                return (T) new ItemBOImpl();
            case ORDERDETAIL:
                return (T) new OrderDetailBOImpl();
            case NEWCUSTOMERSORDERSTRANS:
                return (T) new NewCustomersOrdersBOImpl();
            case REGCUSORDER:
                return (T) new RegCusOrdersBOImpl();
            case ADMIN:
                return (T) new AdminBOImpl();
            default:
                return null;
        }
    }

}
