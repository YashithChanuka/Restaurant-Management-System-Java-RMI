package lk.ijse.sunrest.repository;

import lk.ijse.sunrest.repository.custom.impl.*;

public class RepoFactory {

    private static RepoFactory repoFactory;

    public static RepoFactory getInstance()throws Exception{
        if (repoFactory == null){
            repoFactory = new RepoFactory();
        }
        return repoFactory;
    }

    public enum RepoTypes{
        CUSTOMER,CHEF,ITEM,ORDERDETAIL,ADMIN;
    }

    public <T>T getSuperRepo(RepoTypes types){
        switch (types){
            case CUSTOMER:
                return (T) new CustomerRepoImpl();
            case CHEF:
                return (T) new ChefRepoImpl();
            case ITEM:
                return (T) new ItemRepoImpl();
            case ORDERDETAIL:
                return (T) new OrderDetailRepoImpl();
            case ADMIN:
                return (T) new AdminRepoImpl();
            default:
                return null;
        }
    }

}
