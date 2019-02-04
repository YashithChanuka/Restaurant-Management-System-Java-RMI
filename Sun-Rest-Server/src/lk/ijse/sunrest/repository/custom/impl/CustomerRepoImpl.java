package lk.ijse.sunrest.repository.custom.impl;

import lk.ijse.sunrest.entity.Customer;
import lk.ijse.sunrest.repository.CrudRepoImpl;
import lk.ijse.sunrest.repository.custom.CustomerRepo;

import java.util.List;

public class CustomerRepoImpl extends CrudRepoImpl<Customer, String> implements CustomerRepo {

    @Override
    public List<Customer> searchCustomerFromName(String name) throws Exception {
        List<Customer> all = null;
        try {
            all = session.createQuery("FROM Customer WHERE customerName='" + name + "'").list();
            return all;
        }catch (Exception ex){
            return null;
        }
    }

}
