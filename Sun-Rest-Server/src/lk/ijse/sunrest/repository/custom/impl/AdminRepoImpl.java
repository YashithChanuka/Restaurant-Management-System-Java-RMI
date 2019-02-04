package lk.ijse.sunrest.repository.custom.impl;

import lk.ijse.sunrest.entity.Admin;
import lk.ijse.sunrest.repository.CrudRepoImpl;
import lk.ijse.sunrest.repository.custom.AdminRepo;
import org.hibernate.query.Query;

public class AdminRepoImpl extends CrudRepoImpl<Admin, String> implements AdminRepo {
    @Override
    public boolean updatePassword(String password) throws Exception {
        Query query = session.createQuery("UPDATE Admin SET password='" + password + "' WHERE adminID='A001'");
        int i = query.executeUpdate();
        if (i>0){
            return true;
        }else {
            return false;
        }
    }
}
