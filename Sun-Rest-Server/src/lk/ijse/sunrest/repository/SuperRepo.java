package lk.ijse.sunrest.repository;

import org.hibernate.Session;

public interface SuperRepo {

    public void setSession(Session session)throws Exception;

}
