package lk.ijse.sunrest.repository.custom.impl;

import lk.ijse.sunrest.entity.Chef;
import lk.ijse.sunrest.repository.CrudRepoImpl;
import lk.ijse.sunrest.repository.custom.ChefRepo;

import java.util.List;

public class ChefRepoImpl extends CrudRepoImpl<Chef, String> implements ChefRepo {

    @Override
    public List<Chef> getChefIDFor(String userName, String password) throws Exception {
        List<Chef> all = null;
        try {
            all = session.createQuery("FROM Chef WHERE userName='" + userName + "' AND password='" + password + "'").list();
            return all;
        }catch (Exception ex){
            return null;
        }
    }

    @Override
    public List<Chef> searchChefFromName(String name) throws Exception {
        try {
            return session.createQuery("FROM Chef WHERE chefName='" + name + "'").list();
        }catch (Exception ex){
            return null;
        }
    }

}
