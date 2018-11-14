package tool.entity;

import java.util.Date;

/**
 * @Auther: zch
 * @Date: 2018/9/5 22:05
 * @Description:
 */
public class BiologyBeanPo {
    private double weight;
    private double area;
    private String name;
    private int num;
    private int id;
    private int parentId;
    private Integer _parentId;
    private String number;
    //父标识
    private int pid;
    private String title;
    private Date date;
    /**
     * 是否展开
     */
    private boolean open;
    public String getName() {
        return name;
    }

    public BiologyBeanPo setName(String name) {
        this.name = name;
        return this;
    }

    public int getNum() {
        return num;
    }

    public BiologyBeanPo setNum(int num) {
        this.num = num;
        return this;
    }

    public int getId() {
        return id;
    }

    public BiologyBeanPo setId(int id) {
        this.id = id;
        return this;
    }

    public int getPid() {
        return pid;
    }

    public BiologyBeanPo setPid(int pid) {
        this.pid = pid;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public BiologyBeanPo setTitle(String title) {
        this.title = title;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public BiologyBeanPo setDate(Date date) {
        this.date = date;
        return this;
    }

    public boolean isOpen() {
        return open;
    }

    public BiologyBeanPo setOpen(boolean open) {
        this.open = open;
        return this;
    }

    public int getParentId() {
        return this.pid;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public Integer get_parentId() {
        return _parentId;
    }

    public BiologyBeanPo set_parentId(Integer _parentId) {
        this._parentId = _parentId;
        return  this;
    }

    public String getNumber() {
        return number;
    }

    public BiologyBeanPo setNumber(String number) {
        this.number = number;
        return this;
    }

    public double getArea() {
        return area;
    }

    public BiologyBeanPo setArea(double area) {
        this.area = area;
        return  this;
    }

    public double getWeight() {
        return weight;
    }

    public BiologyBeanPo setWeight(double weight) {
        this.weight = weight;
        return  this;
    }
}
