package lk.ijse.sunrest.reservation;

import java.rmi.Remote;

public interface Reservations extends Remote {

    public boolean reserved(Object id)throws Exception;
    public boolean released(Object id)throws Exception;

}
