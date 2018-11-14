package tool.entity;

import java.io.Serializable;

/**
 * @Author noatn
 * @Description
 * @createDate 2018/10/1
 **/
public class RegionPo implements Serializable {
    public static final String selectList = "SELECT * from region WHERE parent_id ='0'";
    public static final String findById = "SELECT * from region WHERE parent_id =?";
    public static final String findRegionPoById = "SELECT * from region WHERE parent_id =";
    private int id;
    private int parentId;
    private String name;

    public int getId() {
        return id;
    }

    public RegionPo setId(int id) {
        this.id = id;
        return this;
    }

    public int getParentId() {
        return parentId;
    }

    public RegionPo setParentId(int parentId) {
        this.parentId = parentId;
        return this;
    }

    public String getName() {
        return name;
    }

    public RegionPo setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "RegionPo{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                '}';
    }
}
