package lk.ijse.sunrest.service.custom.impl;

import lk.ijse.sunrest.business.BOFactory;
import lk.ijse.sunrest.business.custom.ItemBO;
import lk.ijse.sunrest.dto.ItemDTO;
import lk.ijse.sunrest.observer.Observer;
import lk.ijse.sunrest.reservation.impl.ReservationsImpl;
import lk.ijse.sunrest.service.custom.ItemService;

import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ItemServiceImpl extends UnicastRemoteObject implements ItemService {

    ItemBO itemBO = BOFactory.getInstance().getSuperBO(BOFactory.BOTypes.ITEM);
    private static ArrayList<Observer> allItemObservers = new ArrayList<>();
    private static ReservationsImpl<ItemService> itemReservations = new ReservationsImpl<>();

    public ItemServiceImpl() throws Exception {

    }

    @Override
    public boolean addItem(ItemDTO itemDTO) throws Exception {
        itemBO.addItem(itemDTO);
        notifyAllObservers("");
        return true;
    }

    @Override
    public boolean deleteItem(ItemDTO itemDTO) throws Exception {
        if (itemReservations.reserve(itemDTO.getItemCode(), this, true)){
            boolean isDeleted = itemBO.deleteItem(itemDTO);
            if (isDeleted){
                if (itemReservations.checkState(itemDTO.getItemCode(), this)){
                    itemReservations.release(itemDTO.getItemCode(), this);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean updateItem(ItemDTO itemDTO) throws Exception {
        if (itemReservations.reserve(itemDTO.getItemCode(), this, true)){
            boolean isUpdated = itemBO.updateItem(itemDTO);
            if (isUpdated){
                if (itemReservations.checkState(itemDTO.getItemCode(), this)){
                    itemReservations.release(itemDTO.getItemCode(), this);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public ItemDTO searchItem(String id) throws Exception {
        return itemBO.searchItem(id);
    }

    @Override
    public List<ItemDTO> getAllItems() throws Exception {
        return itemBO.getAllItems();
    }

    @Override
    public List<ItemDTO> searchItemFromName(String name) throws Exception {
        return itemBO.searchItemFromName(name);
    }

    @Override
    public boolean reserved(Object id) throws Exception {
        return itemReservations.reserve(id, this, true);
    }

    @Override
    public boolean released(Object id) throws Exception {
        return itemReservations.release(id, this);
    }

    @Override
    public void register(Observer observer) throws Exception {
        allItemObservers.add(observer);
    }

    @Override
    public void unregister(Observer observer) throws Exception {
        allItemObservers.remove(observer);
    }

    @Override
    public void notifyAllObservers(String message) throws Exception {
        for (Observer allItemObserver : allItemObservers){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        allItemObserver.update(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

}
