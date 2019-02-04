package lk.ijse.sunrest.business.custom.impl;

import lk.ijse.sunrest.business.custom.ItemBO;
import lk.ijse.sunrest.dto.ItemDTO;
import lk.ijse.sunrest.entity.Item;
import lk.ijse.sunrest.repository.RepoFactory;
import lk.ijse.sunrest.repository.custom.ItemRepo;
import lk.ijse.sunrest.util.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {

    private ItemRepo itemRepo;

    public ItemBOImpl() throws Exception {
        itemRepo = RepoFactory.getInstance().getSuperRepo(RepoFactory.RepoTypes.ITEM);
    }

    @Override
    public boolean addItem(ItemDTO itemDTO) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        itemRepo.setSession(session);
        session.beginTransaction();
        boolean response = itemRepo.add(new Item(itemDTO.getItemCode(), itemDTO.getItemName(), itemDTO.getDescription(), itemDTO.getSellingPrice()));
        session.getTransaction().commit();
        session.close();
        return response;
    }

    @Override
    public boolean deleteItem(ItemDTO itemDTO) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        itemRepo.setSession(session);
        session.beginTransaction();
        boolean response = itemRepo.delete(new Item(itemDTO.getItemCode(), itemDTO.getItemName(), itemDTO.getDescription(), itemDTO.getSellingPrice()));
        session.getTransaction().commit();
        session.close();
        return response;
    }

    @Override
    public boolean updateItem(ItemDTO itemDTO) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        itemRepo.setSession(session);
        session.beginTransaction();
        boolean response = itemRepo.update(new Item(itemDTO.getItemCode(), itemDTO.getItemName(), itemDTO.getDescription(), itemDTO.getSellingPrice()));
        session.getTransaction().commit();
        session.close();
        return response;
    }

    @Override
    public ItemDTO searchItem(String id) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        itemRepo.setSession(session);
        session.beginTransaction();
        Item search = itemRepo.search(id);
        session.getTransaction().commit();
        session.close();
        return new ItemDTO(search.getItemCode(), search.getItemName(), search.getDescription(), search.getSellingPrice());
    }

    @Override
    public List<ItemDTO> getAllItems() throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        itemRepo.setSession(session);
        session.beginTransaction();

        List<Item> all = itemRepo.getAll();
        ArrayList<ItemDTO> itemDTOS = new ArrayList<>();
        for (Item item : all){
            itemDTOS.add(new ItemDTO(item.getItemCode(), item.getItemName(), item.getDescription(), item.getSellingPrice()));
        }

        session.getTransaction().commit();
        session.close();
        return itemDTOS;
    }

    @Override
    public List<ItemDTO> searchItemFromName(String name) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        itemRepo.setSession(session);
        session.beginTransaction();

        List<Item> items = itemRepo.searchItemFromName(name);
        ArrayList<ItemDTO> itemDTOS = new ArrayList<>();
        for (Item item : items){
            itemDTOS.add(new ItemDTO(item.getItemCode(), item.getItemName(), item.getDescription(), item.getSellingPrice()));
        }

        session.getTransaction().commit();
        session.close();
        return itemDTOS;
    }
}
