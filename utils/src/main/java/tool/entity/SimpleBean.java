package tool.entity;

import java.io.Serializable;

/**
 * @Auther: zch
 * @Date: 2018/9/5 22:17
 * @Description:
 */
public class SimpleBean implements Serializable{
    private String name;
    private int num;
    private int id;
    private int pid;
    private String title;

    public String getName() {
        return name;
    }

    public int getNum() {
        return num;
    }

    public SimpleBean setName(String name) {
        this.name = name;
        return this;
    }

    public SimpleBean setNum(int num) {
        this.num = num;
        return this;
    }

    public int getId() {
        return id;
    }

    public SimpleBean setId(int id) {
        this.id = id;
        return this;
    }

    public int getPid() {
        return pid;
    }

    public SimpleBean setPid(int pid) {
        this.pid = pid;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public SimpleBean setTitle(String title) {
        this.title = title;
        return this;
    }

    public SimpleBean(){}

    /**
     * 对于简单的JavaBean也有几个名词：

     (1)VO：与简单Java对象对应，专门用于传递值的操作上

     (2)POJO：简单Java对象

     (3)TO：传输对象，进行远程传输时，对象所在的类必须实现java.io.Serializable接口。
     */
}
