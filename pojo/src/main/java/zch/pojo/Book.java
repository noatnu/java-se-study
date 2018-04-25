package zch.pojo;

import java.util.HashMap;
import java.util.Map;

public class Book {
    private String author;
    private String id;
    private String bookmark;
    public static Map<String,Object> map = new HashMap<String, Object>();

    public static Map<String, Object> getMap() {
        map.put("china","中国");
        map.put("america","美国");
        return map;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookmark() {
        return bookmark;
    }

    public void setBookmark(String bookmark) {
        this.bookmark = bookmark;
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", id='" + id + '\'' +
                ", bookmark='" + bookmark + '\'' +
                '}';
    }
}
