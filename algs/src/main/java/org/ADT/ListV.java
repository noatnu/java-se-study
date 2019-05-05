package org.ADT;
//简单表
public class ListV {

    private int init = 10;

    private Object[] objects;

    public ListV(int size) {
        this.init = size;
        objects = new Object[init];
    }

    public ListV() {
        objects = new Object[init];
    }

    public void add(Object o) {
        int size = init;
        for (int i = 0; i < objects.length; i++) {
            objects[i] = o;
        }
        Object[] arr = null;
        if (objects.length >= size - 1) {
            arr = new Object[size * 2];
            for (int i = 0; i < objects.length; i++) {
                arr[i] = objects[i];
            }
            objects = arr;
        }

    }

    public void setInit(int init) {
        this.init = init;
    }

    public Object[] getObjects() {
        return objects;
    }
}
