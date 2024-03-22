package database;

import java.util.List;

public interface CRUD {
    public Object insert(Object object);

    public List<Object> findAll();

    public Object findById(int id);

    public boolean update(Object object);

    public boolean delete(Object object);


}
