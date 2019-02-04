package lk.ijse.sunrest.server;

import lk.ijse.sunrest.service.impl.ServiceFactoryImpl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class StartUp {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(6161);//---Create new RMI Registry with a port
            registry.rebind("Chanu",ServiceFactoryImpl.getInstance());//---Binding service objects to the RMI Registry
            System.out.println("Server has been statred...!!!");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
