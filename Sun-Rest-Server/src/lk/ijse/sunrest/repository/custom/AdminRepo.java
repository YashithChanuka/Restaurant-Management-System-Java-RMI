package lk.ijse.sunrest.repository.custom;

import lk.ijse.sunrest.entity.Admin;
import lk.ijse.sunrest.repository.CrudRepo;

public interface AdminRepo extends CrudRepo<Admin, String> {

    public boolean updatePassword(String password)throws Exception;

}
