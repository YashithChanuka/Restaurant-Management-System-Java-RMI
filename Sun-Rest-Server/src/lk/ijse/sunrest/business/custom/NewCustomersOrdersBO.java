package lk.ijse.sunrest.business.custom;

import lk.ijse.sunrest.business.SuperBO;
import lk.ijse.sunrest.dto.CommonDTO;

public interface NewCustomersOrdersBO extends SuperBO {

    public boolean addNewCustomersOrders(CommonDTO commonDTO)throws Exception;

}
