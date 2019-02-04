package lk.ijse.sunrest.observer;

import java.rmi.Remote;

public interface Observer extends Remote {

    public void update(String message)throws Exception;

}
