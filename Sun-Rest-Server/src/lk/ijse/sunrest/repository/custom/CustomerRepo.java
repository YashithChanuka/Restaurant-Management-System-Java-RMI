package lk.ijse.sunrest.repository.custom;

import lk.ijse.sunrest.entity.Chef;
import lk.ijse.sunrest.entity.Customer;
import lk.ijse.sunrest.repository.CrudRepo;

import java.util.List;

public interface CustomerRepo extends CrudRepo<Customer, String> {

    public List<Customer> searchCustomerFromName(String name)throws Exception;

}
