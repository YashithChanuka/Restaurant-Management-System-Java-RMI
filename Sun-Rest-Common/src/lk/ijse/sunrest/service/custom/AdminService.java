package lk.ijse.sunrest.service.custom;

import lk.ijse.sunrest.dto.AdminDTO;
import lk.ijse.sunrest.service.SuperService;

public interface AdminService extends SuperService {

    public AdminDTO searchAdmin(String id)throws Exception;

    public boolean updatePassword(String password)throws Exception;

}
