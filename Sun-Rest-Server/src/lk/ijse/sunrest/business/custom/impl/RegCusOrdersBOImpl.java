package lk.ijse.sunrest.business.custom.impl;

import lk.ijse.sunrest.business.custom.RegCusOrdersTransBO;
import lk.ijse.sunrest.dto.RegCusCommonDTO;
import lk.ijse.sunrest.entity.Customer;
import lk.ijse.sunrest.entity.Item;
import lk.ijse.sunrest.entity.OrderDetail;
import lk.ijse.sunrest.repository.RepoFactory;
import lk.ijse.sunrest.repository.custom.CustomerRepo;
import lk.ijse.sunrest.repository.custom.ItemRepo;
import lk.ijse.sunrest.repository.custom.OrderDetailRepo;
import lk.ijse.sunrest.util.HibernateUtil;
import org.hibernate.Session;

public class RegCusOrdersBOImpl implements RegCusOrdersTransBO {

    private CustomerRepo customerRepo;

    private ItemRepo itemRepo;

    private OrderDetailRepo orderDetailRepo;

    public RegCusOrdersBOImpl() throws Exception {
        customerRepo = RepoFactory.getInstance().getSuperRepo(RepoFactory.RepoTypes.CUSTOMER);
        itemRepo = RepoFactory.getInstance().getSuperRepo(RepoFactory.RepoTypes.ITEM);
        orderDetailRepo = RepoFactory.getInstance().getSuperRepo(RepoFactory.RepoTypes.ORDERDETAIL);
    }

    @Override
    public boolean addRegCusOrder(RegCusCommonDTO regCusCommonDTO) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        customerRepo.setSession(session);
        itemRepo.setSession(session);
        orderDetailRepo.setSession(session);
        session.beginTransaction();

        String customerID = regCusCommonDTO.getCustomerID();
        Customer customer = customerRepo.search(customerID);

        String itemCode = regCusCommonDTO.getItemCode();
        Item item = itemRepo.search(itemCode);

        OrderDetail orderDetail = new OrderDetail(regCusCommonDTO.getOrderDetailDTO().getOrderID(),
                customer,item,
                regCusCommonDTO.getOrderDetailDTO().getDate(),
                regCusCommonDTO.getOrderDetailDTO().getOrderedQty(),
                regCusCommonDTO.getOrderDetailDTO().getTotalAmount(),
                regCusCommonDTO.getOrderDetailDTO().getStatus());

        boolean response = orderDetailRepo.add(orderDetail);

        session.getTransaction().commit();
        session.close();
        return response;
    }
}
