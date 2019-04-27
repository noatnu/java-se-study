package other.jdbc.dao;

import other.jdbc.common.SQLString;
import other.jdbc.domin.User;
import other.jdbc.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private volatile ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
    private PreparedStatement pstmt = null;
    private Connection conn = null;

    public UserDaoImpl() {
    }

    /**
     * 事物保存点 回滚
     *
     * @param user
     * @param username
     * @throws SQLException
     */
    @Override
    public void insert(User user, String username) throws SQLException {
        init();
        Savepoint savepointA = null;
        Savepoint savepointB = null;
        try {
            conn.setAutoCommit(false);
            savepointA = conn.setSavepoint("savepointA");
            pstmt = conn.prepareStatement(SQLString.insert(), Statement.RETURN_GENERATED_KEYS);//用占位符的SQl语句,返回自动生成的键的标志
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getUsername());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getAddress());
            pstmt.setString(5, user.getAccount());
            pstmt.setString(6, user.getGroup());
            pstmt.setString(7, user.getSex());
            pstmt.setString(8, user.getJurisdiction());
            pstmt.setString(9, user.getPermission());
            pstmt.setString(10, user.getRole());
            pstmt.setInt(11, user.getAge());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            if (conn != null) conn.rollback(savepointA);
            System.out.println("事物点 归滚:" + e.getSQLState() + " " + e.getMessage());
        }

        try {
            savepointB = conn.setSavepoint("savepointB");
            pstmt = conn.prepareStatement(SQLString.insert(), Statement.RETURN_GENERATED_KEYS);//用占位符的SQl语句,返回自动生成的键的标志
            pstmt.setString(1, user.getName());
            pstmt.setString(2, username);
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getAddress());
            pstmt.setString(5, user.getAccount());
            pstmt.setString(6, user.getGroup());
            pstmt.setString(7, user.getSex());
            pstmt.setString(8, user.getJurisdiction());
            pstmt.setString(9, user.getPermission());
            pstmt.setString(10, user.getRole());
            pstmt.setInt(11, user.getAge());
            pstmt.executeUpdate();
        } catch (Exception e) {
            if (conn != null) conn.rollback(savepointB);
            System.out.println("事物点 归滚:" + e.getMessage());
        }

        conn.commit();
        pstmt.close();
        end();
    }

    @Override
    public boolean insert(User user) throws SQLException {
        init();
        pstmt = conn.prepareStatement(SQLString.insert(), Statement.RETURN_GENERATED_KEYS);//用占位符的SQl语句,返回自动生成的键的标志
        pstmt.setString(1, user.getName());
        pstmt.setString(2, user.getUsername());
        pstmt.setString(3, user.getPassword());
        pstmt.setString(4, user.getAddress());
        pstmt.setString(5, user.getAccount());
        pstmt.setString(6, user.getGroup());
        pstmt.setString(7, user.getSex());
        pstmt.setString(8, user.getJurisdiction());
        pstmt.setString(9, user.getPermission());
        pstmt.setString(10, user.getRole());
        pstmt.setInt(11, user.getAge());
        int num = pstmt.executeUpdate();
        pstmt.close();
        end();
        return num > 0;
    }

    /**
     * 事物回滚
     *
     * @param user
     * @return
     * @throws SQLException
     */
    @Override
    public boolean update(User user) throws SQLException {
        init();
        int i = 0;
        int num = 0;
        try {
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(SQLString.update());
            pstmt.setString(++i + 0, user.getRole());
            pstmt.setString(++i + 0, user.getUsername());
            pstmt.setString(++i + 0, user.getName());
            pstmt.setString(++i + 0, user.getSex());
            pstmt.setString(++i + 0, user.getAccount());
            pstmt.setString(++i + 0, user.getAddress());
            pstmt.setString(++i + 0, user.getGroup());
            pstmt.setString(++i + 0, user.getPassword());
            pstmt.setString(++i + 0, user.getJurisdiction());
            pstmt.setString(++i + 0, user.getPermission());
            pstmt.setInt(++i + 0, user.getAge());
            pstmt.setDate(++i + 0, user.getBirthday());

            pstmt.setString(++i + 0, user.getId());
            num = pstmt.executeUpdate();
            int op = 0;
//            System.out.println(9/op);
            //回滚事物!/ by zero
            conn.commit();
        } catch (Exception e) {
            if (conn != null) conn.rollback();
            System.out.println("回滚事物!" + e.getMessage());
        } finally {
            pstmt.close();
            end();
        }
        return num > 0;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        init();
        pstmt = conn.prepareStatement(SQLString.delete());
        int i = 0;
        pstmt.setString(++i, id);
        i = pstmt.executeUpdate();
        pstmt.close();
        end();
        return i > 0;
    }

    @Override
    public User findByUserId(String id) throws SQLException {
        User user = null;
        init();
        pstmt = conn.prepareStatement(SQLString.selectById());
        pstmt.setString(1, id);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            user = new User();
            int age = rs.getInt("age");
            user.setAge(age);
            Date birthday = rs.getDate("birthday");
            user.setBirthday(birthday);
            user.setPassword(rs.getString("password"));
            user.setPermission(rs.getString("permission"));
            user.setRole(rs.getString("role"));
            user.setJurisdiction(rs.getString("jurisdiction"));
            user.setSex(rs.getString("sex"));
            user.setGroup(rs.getString("group"));
            user.setAccount(rs.getString("account"));
            user.setAddress(rs.getString("address"));
            user.setUsername(rs.getString("username"));
            user.setName(rs.getString("name"));
            user.setId(rs.getString("id"));
        }
        pstmt.close();
        rs.close();
        end();
        return user;
    }

    @Override
    public User findById(String id) throws SQLException {
        User user = null;
        init();
        Statement stmt = null;
        String sql = "SELECT * FROM `jdbc-user` u WHERE u.id='" + id + "'";
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            user = new User();
            int age = rs.getInt("age");
            user.setAge(age);
            Date birthday = rs.getDate("birthday");
            user.setBirthday(birthday);
            user.setPermission(rs.getString("permission"));
            user.setPassword(rs.getString("password"));
            user.setRole(rs.getString("role"));
            user.setJurisdiction(rs.getString("jurisdiction"));
            user.setSex(rs.getString("sex"));
            user.setGroup(rs.getString("group"));
            user.setAccount(rs.getString("account"));
            user.setAddress(rs.getString("address"));
            user.setUsername(rs.getString("username"));
            user.setName(rs.getString("name"));
            user.setId(rs.getString("id"));
        }
        rs.close();
        stmt.close();
        end();
        return user;
    }

    @Override
    public List<User> find() throws SQLException {
        List<User> users = new ArrayList<>();
        User user = null;
        init();
        pstmt = conn.prepareStatement(SQLString.select());
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            user = new User();
            int age = rs.getInt("age");
            user.setAge(age);
            Date birthday = rs.getDate("birthday");
            user.setBirthday(birthday);
            user.setPassword(rs.getString("password"));
            user.setPermission(rs.getString("permission"));
            user.setRole(rs.getString("role"));
            user.setJurisdiction(rs.getString("jurisdiction"));
            user.setSex(rs.getString("sex"));
            user.setGroup(rs.getString("group"));
            user.setAccount(rs.getString("account"));
            user.setAddress(rs.getString("address"));
            user.setUsername(rs.getString("username"));
            user.setName(rs.getString("name"));
            user.setId(rs.getString("id"));
            users.add(user);
        }
        pstmt.close();
        rs.close();
        end();
        return users;
    }

    /**
     * 批处理
     *
     * @param users
     * @throws SQLException
     */
    @Override
    public void batch(List<User> users) throws SQLException {
        init();
        conn.setAutoCommit(false);
        pstmt = conn.prepareStatement(SQLString.insert());//用占位符的SQl语句
        User user = null;
        for (int i = 0; i < users.size() - 1; i++) {
            user = users.get(i);
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getUsername());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getAddress());
            pstmt.setString(5, user.getAccount());
            pstmt.setString(6, user.getGroup());
            pstmt.setString(7, user.getSex());
            pstmt.setString(8, user.getJurisdiction());
            pstmt.setString(9, user.getPermission());
            pstmt.setString(10, user.getRole());
            pstmt.setInt(11, user.getAge());
            pstmt.addBatch();
            if (i % 100 == 0) {//当100后执行一次
                pstmt.executeBatch();//执行批处理
            }
        }
        pstmt.executeBatch();
        pstmt.close();
        conn.commit();
        end();
    }

    private void init() {
        threadLocal.set(ConnectionUtil.getConnectionUtil().getConnection());
        conn = threadLocal.get();
    }

    private void end() {
        try {
            if (!conn.isClosed()) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
