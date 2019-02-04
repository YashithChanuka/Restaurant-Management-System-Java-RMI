package lk.ijse.sunrest.repository;

import java.util.List;

public interface CrudRepo<T,ID> extends SuperRepo {

    public boolean add(T entity)throws Exception;
    public boolean delete(T entity)throws Exception;
    public boolean update(T entity)throws Exception;
    public T search(ID id)throws Exception;
    public List<T> getAll()throws Exception;

}
