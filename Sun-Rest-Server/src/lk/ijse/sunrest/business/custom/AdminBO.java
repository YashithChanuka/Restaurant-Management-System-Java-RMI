package lk.ijse.sunrest.business.custom;

import lk.ijse.sunrest.business.SuperBO;
import lk.ijse.sunrest.dto.AdminDTO;

public interface AdminBO extends SuperBO {

    public AdminDTO searchAdmin(String id)throws Exception;

    public boolean updatePassword(String password)throws Exception;

}
