package lk.ijse.sunrest.business.custom.impl;

import lk.ijse.sunrest.business.custom.NewCustomersOrdersBO;
import lk.ijse.sunrest.dto.CommonDTO;
import lk.ijse.sunrest.entity.Customer;
import lk.ijse.sunrest.entity.Item;
import lk.ijse.sunrest.entity.OrderDetail;
import lk.ijse.sunrest.repository.RepoFactory;
import lk.ijse.sunrest.repository.custom.CustomerRepo;
import lk.ijse.sunrest.repository.custom.ItemRepo;
import lk.ijse.sunrest.repository.custom.OrderDetailRepo;
import lk.ijse.sunrest.util.HibernateUtil;
import org.hibernate.Session;

public class NewCustomersOrdersBOImpl implements NewCustomersOrdersBO {

    private CustomerRepo customerRepo;

    private OrderDetailRepo orderDetailRepo;

    private ItemRepo itemRepo;

    public NewCustomersOrdersBOImpl() throws Exception {
        customerRepo = RepoFactory.getInstance().getSuperRepo(RepoFactory.RepoTypes.CUSTOMER);
        orderDetailRepo = RepoFactory.getInstance().getSuperRepo(RepoFactory.RepoTypes.ORDERDETAIL);
        itemRepo = RepoFactory.getInstance().getSuperRepo(RepoFactory.RepoTypes.ITEM);
    }

    @Override
    public boolean addNewCustomersOrders(CommonDTO commonDTO) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        customerRepo.setSession(session);
        orderDetailRepo.setSession(session);
        itemRepo.setSession(session);
        session.beginTransaction();

        Customer customer = new Customer(commonDTO.getCustomerDTO().getCustomerID(),
                commonDTO.getCustomerDTO().getCustomerName(),
                commonDTO.getCustomerDTO().getCustomerAddress(),
                commonDTO.getCustomerDTO().getCustomerTele());

        boolean res1 = customerRepo.add(customer);
        if (!res1){
            return false;
        }

        String itemCode = commonDTO.getItemCode();
        Item item = itemRepo.search(itemCode);

        OrderDetail orderDetail = new OrderDetail(commonDTO.getOrderDetailDTO().getOrderID(),
                customer,item,
                commonDTO.getOrderDetailDTO().getDate(),
                commonDTO.getOrderDetailDTO().getOrderedQty(),
                commonDTO.getOrderDetailDTO().getTotalAmount(),
                commonDTO.getOrderDetailDTO().getStatus());

        boolean res3 = orderDetailRepo.add(orderDetail);
        if (!res3){
            return false;
        }

        session.getTransaction().commit();
        session.close();
        return true;
    }
}
