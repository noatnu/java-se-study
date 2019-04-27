package cn.threadcommunication.threadTransData;

import java.util.ArrayList;
import java.util.List;

public class MyList {

    private List<String> list = new ArrayList<>();

    public void add() {
        list.add("南京");
    }

    public int size() {
        return list.size();
    }

}
