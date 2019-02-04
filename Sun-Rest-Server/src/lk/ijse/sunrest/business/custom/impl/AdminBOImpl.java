package lk.ijse.sunrest.business.custom.impl;

import lk.ijse.sunrest.business.custom.AdminBO;
import lk.ijse.sunrest.dto.AdminDTO;
import lk.ijse.sunrest.entity.Admin;
import lk.ijse.sunrest.repository.RepoFactory;
import lk.ijse.sunrest.repository.custom.AdminRepo;
import lk.ijse.sunrest.util.HibernateUtil;
import org.hibernate.Session;

public class AdminBOImpl implements AdminBO {

    private AdminRepo adminRepo;

    public AdminBOImpl() throws Exception {
        adminRepo = RepoFactory.getInstance().getSuperRepo(RepoFactory.RepoTypes.ADMIN);
    }

    @Override
    public AdminDTO searchAdmin(String id) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        adminRepo.setSession(session);
        session.beginTransaction();
        Admin search = adminRepo.search(id);
        session.getTransaction().commit();
        session.close();
        return new AdminDTO(search.getAdminID(), search.getUserName(), search.getPassword());
    }

    @Override
    public boolean updatePassword(String password) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        adminRepo.setSession(session);
        session.beginTransaction();
        boolean b = adminRepo.updatePassword(password);
        session.getTransaction().commit();
        session.close();
        return b;
    }
}
