package lk.ijse.sunrest.business.custom;

import lk.ijse.sunrest.business.SuperBO;
import lk.ijse.sunrest.dto.ItemDTO;

import java.util.List;

public interface ItemBO extends SuperBO {

    public boolean addItem(ItemDTO itemDTO)throws Exception;
    public boolean deleteItem(ItemDTO itemDTO)throws Exception;
    public boolean updateItem(ItemDTO itemDTO)throws Exception;
    public ItemDTO searchItem(String id)throws Exception;
    public List<ItemDTO> getAllItems()throws Exception;
    public List<ItemDTO> searchItemFromName(String name)throws Exception;

}
