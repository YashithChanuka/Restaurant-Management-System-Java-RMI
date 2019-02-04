package lk.ijse.sunrest.service.custom.impl;

import lk.ijse.sunrest.business.BOFactory;
import lk.ijse.sunrest.business.custom.ChefBO;
import lk.ijse.sunrest.dto.ChefDTO;
import lk.ijse.sunrest.observer.Observer;
import lk.ijse.sunrest.reservation.impl.ReservationsImpl;
import lk.ijse.sunrest.service.custom.ChefService;

import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ChefServiceImpl extends UnicastRemoteObject implements ChefService {

    ChefBO chefBO = BOFactory.getInstance().getSuperBO(BOFactory.BOTypes.CHEF);
    private static ArrayList<Observer> allChefObservers = new ArrayList<>();
    private static ReservationsImpl<ChefService> chefReservations = new ReservationsImpl<>();

    public ChefServiceImpl() throws Exception {

    }

    @Override
    public boolean addChef(ChefDTO chefDTO) throws Exception {
        boolean isReserved = chefReservations.reserve(chefDTO.getChefID(), this, true);
        if (isReserved){
            return chefBO.addChef(chefDTO);
        }else{
            return false;
        }
    }

    @Override
    public boolean deleteChef(ChefDTO chefDTO) throws Exception {
        if (chefReservations.reserve(chefDTO.getChefID(), this, true)){
            boolean isDeleted = chefBO.deleteChef(chefDTO);
            if (isDeleted){
                if (chefReservations.checkState(chefDTO.getChefID(), this)){
                    chefReservations.release(chefDTO.getChefID(), this);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean updateChef(ChefDTO chefDTO) throws Exception {
       if (chefReservations.reserve(chefDTO.getChefID(), this, true)){
           boolean isUpdated = chefBO.updateChef(chefDTO);
           if (isUpdated){
               if (chefReservations.checkState(chefDTO.getChefID(), this)){
                   chefReservations.release(chefDTO.getChefID(), this);
                   return true;
               }
           }
       }
       return false;
    }

    @Override
    public ChefDTO searchChef(String id) throws Exception {
        return chefBO.searchChef(id);
    }

    @Override
    public String searchChefFromName(String name) throws Exception {
        return chefBO.searchChefFromName(name);
    }

    @Override
    public List<ChefDTO> getAllChefs() throws Exception {
        return chefBO.getAllChefs();
    }

    @Override
    public String getChefIDFor(String userName, String password) throws Exception {
        return chefBO.getChefIDFor(userName,password);
//        String chefIDFor = null;
//        if (chefReservations.reserve(userName,this, true)){
//            Alert alert = new Alert(Alert.AlertType.ERROR,"Already logged in...!!!",ButtonType.OK);
//            alert.showAndWait();
//        }else{
//            chefIDFor = chefBO.getChefIDFor(userName, password);
//        }
//        return chefIDFor;
    }

    @Override
    public boolean reserved(Object id) throws Exception {
        return chefReservations.reserve(id, this, true);
    }

    @Override
    public boolean released(Object id) throws Exception {
        return chefReservations.release(id, this);
    }

    @Override
    public void register(Observer observer) throws Exception {
        allChefObservers.add(observer);
    }

    @Override
    public void unregister(Observer observer) throws Exception {
        allChefObservers.remove(observer);
    }

    @Override
    public void notifyAllObservers(String message) throws Exception {
        for (Observer chefObserver : allChefObservers){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        chefObserver.update(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

}
