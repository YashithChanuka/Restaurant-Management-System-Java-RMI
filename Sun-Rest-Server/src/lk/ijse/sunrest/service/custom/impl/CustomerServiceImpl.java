package lk.ijse.sunrest.service.custom.impl;

import lk.ijse.sunrest.business.BOFactory;
import lk.ijse.sunrest.business.custom.CustomerBO;
import lk.ijse.sunrest.dto.CustomerDTO;
import lk.ijse.sunrest.observer.Observer;
import lk.ijse.sunrest.service.custom.CustomerService;

import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class CustomerServiceImpl extends UnicastRemoteObject implements CustomerService {

    private CustomerBO customerBO;

    public CustomerServiceImpl() throws Exception {
        customerBO = BOFactory.getInstance().getSuperBO(BOFactory.BOTypes.CUSTOMER);
    }

    @Override
    public boolean addCustomer(CustomerDTO customerDTO) throws Exception {
        return customerBO.addCustomer(customerDTO);
    }

    @Override
    public boolean deleteCustomer(CustomerDTO customerDTO) throws Exception {
        return false;
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) throws Exception {
        return false;
    }

    @Override
    public CustomerDTO searchCustomer(String id) throws Exception {
        return null;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() throws Exception {
        return customerBO.getAllCustomers();
    }

    @Override
    public List<CustomerDTO> searchCustomerFromName(String name) throws Exception {
        return customerBO.searchCustomerFromName(name);
    }

    @Override
    public void register(Observer observer) throws Exception {

    }

    @Override
    public void unregister(Observer observer) throws Exception {

    }

    @Override
    public void notifyAllObservers(String message) throws Exception {

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
