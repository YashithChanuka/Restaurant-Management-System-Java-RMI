package lk.ijse.sunrest.business.custom;

import lk.ijse.sunrest.business.SuperBO;
import lk.ijse.sunrest.dto.ChefDTO;

import java.util.List;

public interface ChefBO extends SuperBO {

    public boolean addChef(ChefDTO chefDTO)throws Exception;
    public boolean deleteChef(ChefDTO chefDTO)throws Exception;
    public boolean updateChef(ChefDTO chefDTO)throws Exception;
    public ChefDTO searchChef(String id)throws Exception;
    public String searchChefFromName(String name)throws Exception;
    public List<ChefDTO> getAllChefs()throws Exception;
    public String getChefIDFor(String userName, String password)throws Exception;

}
