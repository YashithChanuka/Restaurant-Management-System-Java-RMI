package lk.ijse.sunrest.business.custom.impl;

import lk.ijse.sunrest.business.custom.CustomerBO;
import lk.ijse.sunrest.dto.CustomerDTO;
import lk.ijse.sunrest.entity.Customer;
import lk.ijse.sunrest.repository.RepoFactory;
import lk.ijse.sunrest.repository.custom.CustomerRepo;
import lk.ijse.sunrest.util.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {

    private CustomerRepo customerRepo;

    public CustomerBOImpl() throws Exception {
        customerRepo = RepoFactory.getInstance().getSuperRepo(RepoFactory.RepoTypes.CUSTOMER);
    }

    @Override
    public boolean addCustomer(CustomerDTO customerDTO) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        customerRepo.setSession(session);
        session.beginTransaction();
        boolean response = customerRepo.add(new Customer(customerDTO.getCustomerID(), customerDTO.getCustomerName(), customerDTO.getCustomerAddress(), customerDTO.getCustomerTele()));
        session.getTransaction().commit();
        session.close();
        return response;
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
        Session session = HibernateUtil.getSessionFactory().openSession();
        customerRepo.setSession(session);
        session.beginTransaction();

        List<Customer> all = customerRepo.getAll();
        ArrayList<CustomerDTO> customerDTOS = new ArrayList<>();
        for (Customer cus : all){
            customerDTOS.add(new CustomerDTO(cus.getCustomerID(),
                    cus.getCustomerName(),
                    cus.getCustomerAddress(),
                    cus.getCustomerTele()));
        }

        session.getTransaction().commit();
        session.close();
        return customerDTOS;
    }

    @Override
    public List<CustomerDTO> searchCustomerFromName(String name) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        customerRepo.setSession(session);
        session.beginTransaction();

        List<Customer> customers = customerRepo.searchCustomerFromName(name);
        ArrayList<CustomerDTO> customerDTOS = new ArrayList<>();
        for (Customer cus : customers){
            customerDTOS.add(new CustomerDTO(cus.getCustomerID(), cus.getCustomerName(), cus.getCustomerAddress(), cus.getCustomerTele()));
        }

        session.getTransaction().commit();
        session.close();
        return customerDTOS;
    }
}
