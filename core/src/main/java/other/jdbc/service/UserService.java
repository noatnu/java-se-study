package other.jdbc.service;

import other.jdbc.dao.UserDao;
import other.jdbc.dao.UserDaoImpl;
import other.jdbc.domin.User;
import other.jdbc.util.DynamicProxy;

import java.util.List;


public class UserService {

    private volatile UserDao proxy = (UserDao) DynamicProxy.getDynamicProxy().dynamic(UserDao.class, UserDaoImpl.class);

    public boolean insert(User user) {
        try {
            return proxy.insert(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void batch(List<User> users) {
        try {
            proxy.batch(users);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean update(User user) {
        try {
            return proxy.update(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(String id) {
        try {
            return proxy.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public User findByUserId(String id) {
        try {
//            return proxy.findByUserId(id);
            return proxy.findById(id);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> find() {
        try {
            return proxy.find();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insert(User user, String username) {
        try {
            proxy.insert(user, username);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
