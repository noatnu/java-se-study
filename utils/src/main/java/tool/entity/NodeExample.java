package tool.entity;

/**
 * @Auther: zch
 * @Date: 2018/9/5 23:12
 * @Description:
 */
public class NodeExample {
    public String id;
    public String text;
    public String parentId;

    /**
     * 孩子节点列表
     */
    private ChildrenExample children = new ChildrenExample();

    // 先序遍历，拼接JSON字符串
    public String toString() {
        String result = //"["
                "{"
                        + "\"text\":\"" + text + "\","
                        + "\"href\":\"" + id +"\"";
        if (children != null && children.getSize() != 0) {
            if (result.contains("nodes")) {
                result += ",";
            }else{
                result += ",\"nodes\":" + children.toString();
            }
        }
        return result + "}";
    }

    // 兄弟节点横向排序
    public void sortChildren() {
        if (children != null && children.getSize() != 0) {
            children.sortChildren();
        }
    }

    // 添加孩子节点
    public void addChild(NodeExample node) {
        this.children.addChild(node);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public ChildrenExample getChildren() {
        return children;
    }

    public void setChildren(ChildrenExample children) {
        this.children = children;
    }
}
