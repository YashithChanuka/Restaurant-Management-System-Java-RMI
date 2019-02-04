package lk.ijse.sunrest.service.custom;

import lk.ijse.sunrest.dto.CommonDTO;
import lk.ijse.sunrest.observer.Subject;
import lk.ijse.sunrest.reservation.Reservations;
import lk.ijse.sunrest.service.SuperService;

public interface NewCustomersOrdersTransService extends SuperService,Reservations,Subject {

    public boolean addNewCustomerOrder(CommonDTO commonDTO) throws Exception;

}
