package lk.ijse.sunrest.business.custom;

import lk.ijse.sunrest.business.SuperBO;
import lk.ijse.sunrest.dto.RegCusCommonDTO;

public interface RegCusOrdersTransBO extends SuperBO {

    public boolean addRegCusOrder(RegCusCommonDTO regCusCommonDTO)throws Exception;

}
