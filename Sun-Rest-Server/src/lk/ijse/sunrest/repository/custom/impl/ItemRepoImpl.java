package lk.ijse.sunrest.repository.custom.impl;

import lk.ijse.sunrest.entity.Item;
import lk.ijse.sunrest.repository.CrudRepoImpl;
import lk.ijse.sunrest.repository.custom.ItemRepo;

import java.util.List;

public class ItemRepoImpl extends CrudRepoImpl<Item, String> implements ItemRepo {

    @Override
    public List<Item> searchItemFromName(String name) throws Exception {
        List<Item> all = null;
        try {
            all = session.createQuery("FROM Item WHERE itemName='" + name + "'").list();
            return all;
        }catch (Exception ex){
            return null;
        }
    }

}
