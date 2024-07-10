package za.ac.tut.bl;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author sifiso
 */
public interface DAOInterface <T>{
    
    public Boolean add(T t) throws SQLException;
    public Boolean delete(Integer ID) throws SQLException;
    public Boolean update(T t) throws SQLException;
    public T get(Integer Id) throws SQLException;
    public List<T>getAll() throws SQLException; 
}
