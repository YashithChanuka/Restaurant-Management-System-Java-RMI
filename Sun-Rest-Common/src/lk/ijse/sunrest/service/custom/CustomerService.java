package lk.ijse.sunrest.service.custom;

import lk.ijse.sunrest.dto.ChefDTO;
import lk.ijse.sunrest.dto.CustomerDTO;
import lk.ijse.sunrest.observer.Subject;
import lk.ijse.sunrest.reservation.Reservations;
import lk.ijse.sunrest.service.SuperService;

import java.util.List;

public interface CustomerService extends SuperService,Reservations,Subject {

    public boolean addCustomer(CustomerDTO customerDTO)throws Exception;
    public boolean deleteCustomer(CustomerDTO customerDTO)throws Exception;
    public  boolean updateCustomer(CustomerDTO customerDTO)throws Exception;
    public CustomerDTO searchCustomer(String id)throws Exception;
    public List<CustomerDTO> getAllCustomers()throws Exception;
    public List<CustomerDTO> searchCustomerFromName(String name)throws Exception;

}
