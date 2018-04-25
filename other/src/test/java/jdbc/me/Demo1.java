package jdbc.me;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import common.Logger;
import org.testng.annotations.Test;
import other.jdbc.util.ConnectionUtil;
import other.jdbc.util.ConnectionXMLUtil;

import java.sql.Connection;

public class Demo1 {
    private Logger logger = Logger.getLogger(getClass());

    @Test
    public void testConn(){
        Connection connection = ConnectionUtil.getConnectionUtil().getConnection();
        logger.info(connection);

    }
}
