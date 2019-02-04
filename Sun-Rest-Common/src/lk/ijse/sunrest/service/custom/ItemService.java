package lk.ijse.sunrest.service.custom;

import lk.ijse.sunrest.dto.ItemDTO;
import lk.ijse.sunrest.observer.Subject;
import lk.ijse.sunrest.reservation.Reservations;
import lk.ijse.sunrest.service.SuperService;

import java.util.List;

public interface ItemService extends SuperService,Reservations,Subject {

    public boolean addItem(ItemDTO itemDTO)throws Exception;
    public boolean deleteItem(ItemDTO itemDTO)throws Exception;
    public boolean updateItem(ItemDTO itemDTO)throws Exception;
    public ItemDTO searchItem(String id)throws Exception;
    public List<ItemDTO> getAllItems() throws Exception;
    public List<ItemDTO> searchItemFromName(String name)throws Exception;

}
