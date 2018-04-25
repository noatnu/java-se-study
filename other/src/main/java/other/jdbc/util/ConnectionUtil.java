package other.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    private static volatile ConnectionUtil connectionUtil = new ConnectionUtil();
    // JDBC driver name and database URL
    private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String DB_URL = "jdbc:mysql://localhost:3306/spring?serverTimezone=UTC&characterEncoding=utf8&useUnicode=true&useSSL=false";

    //  Database credentials
    private  final String USER = "blake";
    private  final String PASS = "123456";

    public  Connection getConnection(){
        Connection conn = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        }catch (SQLException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return conn;
    }

    public static ConnectionUtil getConnectionUtil() {
        if (connectionUtil==null)connectionUtil = new ConnectionUtil();
        return connectionUtil;
    }

    private ConnectionUtil(){}
}
