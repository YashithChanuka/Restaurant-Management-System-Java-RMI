package lk.ijse.sunrest.repository.custom;

import lk.ijse.sunrest.entity.Item;
import lk.ijse.sunrest.repository.CrudRepo;

import java.util.List;

public interface ItemRepo extends CrudRepo<Item, String> {

    public List<Item> searchItemFromName(String name)throws Exception;

}
