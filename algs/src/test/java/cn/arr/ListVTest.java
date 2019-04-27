package cn.arr;

import org.ADT.ListV;
import org.junit.jupiter.api.Test;


public class ListVTest {

    @Test
    public void testListV() {
        ListV listV = new ListV();
        for (int i = 0; i < 12; i++) {
            listV.add(new Object());
        }
        Object[] objects = listV.getObjects();
        int j = 0;
        for (Object o : objects) {
            if (o != null) j++;
        }
        System.out.println("index:" + j);
    }
}
