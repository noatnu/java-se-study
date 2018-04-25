package other.jdbc.dao;

import org.apache.ibatis.jdbc.SQL;
import other.jdbc.domin.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    public boolean insert(User user)throws SQLException;

    public void batch(List<User> users)throws SQLException;

    public boolean update(User user)throws SQLException;

    public boolean delete(String id)throws SQLException;

    public User findByUserId(String id)throws SQLException;

    @Deprecated
    public User findById(String id)throws SQLException;

    public List<User> find()throws SQLException;

    @Deprecated
    public void insert(User user,String username)throws SQLException;

}
