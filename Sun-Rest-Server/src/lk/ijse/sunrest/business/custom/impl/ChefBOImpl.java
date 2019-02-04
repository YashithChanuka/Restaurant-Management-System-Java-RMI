package lk.ijse.sunrest.business.custom.impl;

import lk.ijse.sunrest.business.custom.ChefBO;
import lk.ijse.sunrest.dto.ChefDTO;
import lk.ijse.sunrest.entity.Chef;
import lk.ijse.sunrest.repository.RepoFactory;
import lk.ijse.sunrest.repository.custom.ChefRepo;
import lk.ijse.sunrest.util.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class ChefBOImpl implements ChefBO {

    private ChefRepo chefRepo;

    public ChefBOImpl() throws Exception {
        chefRepo = RepoFactory.getInstance().getSuperRepo(RepoFactory.RepoTypes.CHEF);
    }

    @Override
    public boolean addChef(ChefDTO chefDTO) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        chefRepo.setSession(session);
        session.beginTransaction();
        boolean response = chefRepo.add(new Chef(chefDTO.getChefID(), chefDTO.getChefName(), chefDTO.getAddress(), chefDTO.getGender(), chefDTO.getTele(), chefDTO.getEmail(), chefDTO.getUserName(), chefDTO.getPassword()));
        session.getTransaction().commit();
        session.close();
        return response;
    }

    @Override
    public boolean deleteChef(ChefDTO chefDTO) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        chefRepo.setSession(session);
        session.beginTransaction();
        boolean response = chefRepo.delete(new Chef(chefDTO.getChefID(), chefDTO.getChefName(), chefDTO.getAddress(), chefDTO.getGender(), chefDTO.getTele(), chefDTO.getEmail(), chefDTO.getUserName(), chefDTO.getPassword()));
        session.getTransaction().commit();
        session.close();
        return response;
    }

    @Override
    public boolean updateChef(ChefDTO chefDTO) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        chefRepo.setSession(session);
        session.beginTransaction();
        boolean response = chefRepo.update(new Chef(chefDTO.getChefID(), chefDTO.getChefName(), chefDTO.getAddress(), chefDTO.getGender(), chefDTO.getTele(), chefDTO.getEmail(), chefDTO.getUserName(), chefDTO.getPassword()));
        session.getTransaction().commit();
        session.close();
        return response;
    }

    @Override
    public ChefDTO searchChef(String id) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        chefRepo.setSession(session);
        session.beginTransaction();
        Chef search = chefRepo.search(id);
        session.getTransaction().commit();
        session.close();
        return new ChefDTO(search.getChefID(), search.getChefName(), search.getAddress(), search.getGender(), search.getTele(), search.getEmail(), search.getUserName(), search.getPassword());
    }

    @Override
    public String searchChefFromName(String name) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        chefRepo.setSession(session);
        session.beginTransaction();

        List<Chef> chefs = chefRepo.searchChefFromName(name);
        ArrayList<ChefDTO> chefDTOS = new ArrayList<>();

        for (Chef ch : chefs){
            chefDTOS.add(new ChefDTO(ch.getChefID(),
                    ch.getChefName(),
                    ch.getAddress(),
                    ch.getGender(),
                    ch.getTele(),
                    ch.getEmail(),
                    ch.getUserName(),
                    ch.getPassword()));
        }

        ChefDTO chefDTO = chefDTOS.get(0);
        String chefID = chefDTO.getChefID();
        return chefID;

    }

    @Override
    public List<ChefDTO> getAllChefs() throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        chefRepo.setSession(session);
        session.beginTransaction();

        List<Chef> all = chefRepo.getAll();
        ArrayList<ChefDTO> chefDTOS = new ArrayList<>();
        for (Chef ch : all){
            chefDTOS.add(new ChefDTO(ch.getChefID(),
                    ch.getChefName(),
                    ch.getAddress(),
                    ch.getGender(),
                    ch.getTele(),
                    ch.getEmail(),
                    ch.getUserName(),
                    ch.getPassword()));
        }
        session.getTransaction().commit();
        session.close();
        return chefDTOS;
    }

    @Override
    public String getChefIDFor(String userName, String password) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        chefRepo.setSession(session);
        session.beginTransaction();

        List<Chef> chefIDFor = chefRepo.getChefIDFor(userName, password);
        ArrayList<ChefDTO> chefDTOS = new ArrayList<>();

        System.out.println(chefIDFor);

        for (Chef ch : chefIDFor){
            chefDTOS.add(new ChefDTO(ch.getChefID(),
                    ch.getChefName(),
                    ch.getAddress(),
                    ch.getGender(),
                    ch.getTele(),
                    ch.getEmail(),
                    ch.getUserName(),
                    ch.getPassword()));
        }

        ChefDTO chefDTO = chefDTOS.get(0);
        String chefID = chefDTO.getChefID();

        session.getTransaction().commit();
        session.close();
        return chefID;
    }
}
