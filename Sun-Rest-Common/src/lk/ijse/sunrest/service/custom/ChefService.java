package lk.ijse.sunrest.service.custom;

import lk.ijse.sunrest.dto.ChefDTO;
import lk.ijse.sunrest.observer.Subject;
import lk.ijse.sunrest.reservation.Reservations;
import lk.ijse.sunrest.service.SuperService;

import java.util.List;

public interface ChefService extends SuperService,Reservations,Subject {

    public boolean addChef(ChefDTO chefDTO)throws Exception;
    public boolean deleteChef(ChefDTO chefDTO)throws Exception;
    public boolean updateChef(ChefDTO chefDTO)throws Exception;
    public ChefDTO searchChef(String id)throws Exception;
    public String searchChefFromName(String name)throws Exception;
    public List<ChefDTO> getAllChefs()throws Exception;
    public String getChefIDFor(String userName, String password)throws Exception;

}
