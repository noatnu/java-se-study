package zch.row_mapper;

import java.io.Serializable;
import java.sql.Time;

public class Login_log implements Serializable {
    private String id;
    private String name;
    private String ip;
    private Time time;
    private Integer login_Number;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Integer getLogin_Number() {
        return login_Number;
    }

    public void setLogin_Number(Integer login_Number) {
        this.login_Number = login_Number;
    }

    @Override
    public String toString() {
        return "Login_log{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", ip='" + ip + '\'' +
                ", time=" + time +
                ", login_Number='" + login_Number + '\'' +
                '}';
    }
}
