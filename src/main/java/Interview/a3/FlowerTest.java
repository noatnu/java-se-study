package Interview.a3;

import java.util.Objects;

public class FlowerTest implements Cloneable {
    private String name;

    public FlowerTest(String name) {
        this.name = name;
    }

    public FlowerTest() {
        this("alice");
    }

    public static void main(String[] args) throws Exception {
        FlowerTest flowerTest = FlowerTest.class.newInstance();
        flowerTest.print();

        FlowerTest flowerTest2 = (FlowerTest) flowerTest.clone();
        flowerTest2.print();

        flowerTest.name = "张三";

        flowerTest2.print();
        flowerTest.print();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlowerTest that = (FlowerTest) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }

    public void print() {
        System.out.println("FlowerTest.print " + name + " " + System.currentTimeMillis());
        System.out.println("FlowerTest.print " + hashCode());
    }

}
