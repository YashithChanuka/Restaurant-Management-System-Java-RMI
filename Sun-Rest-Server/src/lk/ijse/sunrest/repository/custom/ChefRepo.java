package lk.ijse.sunrest.repository.custom;

import lk.ijse.sunrest.entity.Chef;
import lk.ijse.sunrest.repository.CrudRepo;

import java.util.List;

public interface ChefRepo extends CrudRepo<Chef, String> {

    public List<Chef> getChefIDFor(String userName, String password)throws Exception;
    public List<Chef> searchChefFromName(String name)throws Exception;

}
