package tool.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: zch
 * @Date: 2018/9/16 11:13
 * @Description:
 */
public class BookExample implements Serializable {
    private String id;
    private String title;
    private String label;
    private String author;
    private double price;
    private String content;
    private Date createTime;

    public String getId() {
        return id;
    }

    public BookExample setId(String id) {
        this.id = id;
        return  this;
    }

    public String getTitle() {
        return title;
    }

    public BookExample setTitle(String title) {
        this.title = title;
        return  this;
    }

    public String getLabel() {
        return label;
    }

    public BookExample setLabel(String label) {
        this.label = label;
        return  this;
    }

    public String getAuthor() {
        return author;
    }

    public BookExample setAuthor(String author) {
        this.author = author;
        return  this;
    }

    public double getPrice() {
        return price;
    }

    public BookExample setPrice(double price) {
        this.price = price;
        return  this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public BookExample setCreateTime(Date createTime) {
        this.createTime = createTime;
        return  this;
    }

    public String getContent() {
        return content;
    }

    public BookExample setContent(String content) {
        this.content = content;
        return  this;
    }

    @Override
    public String toString() {
        return "BookExample{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", label='" + label + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
