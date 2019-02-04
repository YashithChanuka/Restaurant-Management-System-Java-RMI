package lk.ijse.sunrest.service.custom.impl;

import lk.ijse.sunrest.business.BOFactory;
import lk.ijse.sunrest.business.custom.AdminBO;
import lk.ijse.sunrest.dto.AdminDTO;
import lk.ijse.sunrest.service.custom.AdminService;

import java.rmi.server.UnicastRemoteObject;

public class AdminServiceImpl extends UnicastRemoteObject implements AdminService {

    private AdminBO adminBO;

    public AdminServiceImpl() throws Exception {
        adminBO = BOFactory.getInstance().getSuperBO(BOFactory.BOTypes.ADMIN);
    }

    @Override
    public AdminDTO searchAdmin(String id) throws Exception {
        return adminBO.searchAdmin(id);
    }

    @Override
    public boolean updatePassword(String password) throws Exception {
        return adminBO.updatePassword(password);
    }
}
