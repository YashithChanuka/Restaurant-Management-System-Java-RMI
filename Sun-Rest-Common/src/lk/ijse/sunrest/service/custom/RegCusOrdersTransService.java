package lk.ijse.sunrest.service.custom;

import lk.ijse.sunrest.dto.RegCusCommonDTO;
import lk.ijse.sunrest.observer.Subject;
import lk.ijse.sunrest.reservation.Reservations;
import lk.ijse.sunrest.service.SuperService;

public interface RegCusOrdersTransService extends SuperService,Reservations,Subject {

    public boolean addRegCusOrder(RegCusCommonDTO regCusCommonDTO)throws Exception;

}
