package zch.row_mapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginRowMapper implements RowMapper<Login_log> {
    public Login_log mapRow(ResultSet rs, int rowNum) throws SQLException {
        Login_log login_log = new Login_log();
        login_log.setTime(rs.getTime("LOGIN_TIME"));
        login_log.setId(rs.getString("LOGIN_LOG_ID"));
        login_log.setIp(rs.getString("IP_ADDRESS"));
        login_log.setLogin_Number(rs.getInt("login_Number"));
        login_log.setName(rs.getString("LOGIN_NAME"));
        return login_log;
    }

}
