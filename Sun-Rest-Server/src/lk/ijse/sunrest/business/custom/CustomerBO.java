package lk.ijse.sunrest.business.custom;

import lk.ijse.sunrest.business.SuperBO;
import lk.ijse.sunrest.dto.ChefDTO;
import lk.ijse.sunrest.dto.CustomerDTO;

import java.util.List;

public interface CustomerBO extends SuperBO {

    public boolean addCustomer(CustomerDTO customerDTO)throws Exception;
    public boolean deleteCustomer(CustomerDTO customerDTO)throws Exception;
    public boolean updateCustomer(CustomerDTO customerDTO)throws Exception;
    public CustomerDTO searchCustomer(String id)throws Exception;
    public List<CustomerDTO> getAllCustomers()throws Exception;
    public List<CustomerDTO> searchCustomerFromName(String name)throws Exception;

}
