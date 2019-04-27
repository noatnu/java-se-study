package other.jdbc.common;

public class SQLString {

    public static String insert() {
        final StringBuilder builder = new StringBuilder(1024);
        builder.append("insert into `jdbc-user`(id,birthday,name,username,password,address,account,`group`,sex,jurisdiction,permission,role,age) values((select UUID()),(select NOW()),?,?,?,?,?,?,?,?,?,?,?)");
        return builder.toString();
    }

    public static String select() {
        final StringBuilder builder = new StringBuilder(1024);
        builder.append("SELECT * from `jdbc-user`");
        return builder.toString();
    }

    public static String selectById() {
        final StringBuilder builder = new StringBuilder(1024);
        builder.append("select * from `jdbc-user` WHERE id=?");
        return builder.toString();
    }


    public static String delete() {
        final StringBuilder builder = new StringBuilder(1024);
        builder.append("delete from `jdbc-user` where id=?");
        return builder.toString();
    }

    public static String update() {
        final StringBuilder builder = new StringBuilder(1024);
        builder.append("UPDATE `jdbc-user` u SET u.role=?,u.username=?,u.name=?,u.sex=?,u.account=?,u.address=?,u.`group`=?,u.password=?,\n" +
                "  u.jurisdiction=?,u.permission=?,u.age=?,u.birthday=? WHERE u.id=?");
        return builder.toString();
    }
    /**
     id	varchar(50)	NO	PRI
     name	varchar(255)	YES
     username	varchar(255)	YES
     birthday	timestamp	YES
     address	varchar(255)	YES
     account	varchar(255)	YES
     group	varchar(255)	YES
     sex	varchar(255)	YES
     jurisdiction	varchar(255)	YES
     permission	varchar(255)	YES
     role	varchar(255)	YES
     age	int(30)	YES
     password	varchar(255)	YES
     */
}
