package collection;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.TreeSet;
import java.util.UUID;

/**
 * @author: zch
 * @Date: 2021-1-25 15:22
 * @Description:TreeSet是一个包含有序的且没有重复元素的集合， 通过TreeMap实现。TreeSet中含有一个"NavigableMap类型的成员变量"m，而m实际上是"TreeMap的实例"
 * 1、不能有重复的元素；
 * 2、具有排序功能；
 * 3、TreeSet中的元素必须实现Comparable接口并重写compareTo()方法，TreeSet判断元素是否重复 、以及确定元素的顺序 靠的都是这个方法；
 * ①对于Java类库中定义的类，TreeSet可以直接对其进行存储，如String，Integer等,因为这些类已经实现了Comparable接口);
 * ②对于自定义类，如果不做适当的处理，TreeSet中只能存储一个该类型的对象实例，否则无法判断是否重复。
 * 4、依赖TreeMap。
 * 5、相对HashSet,TreeSet的优势是有序，劣势是相对读取慢。根据不同的场景选择不同的集合。
 */
public class TreeSetDemo {

    @Test
    public void runA() {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(2);
        set.add(211);
        set.add(1);
        set.add(9);

        set.forEach(integer -> System.out.println(integer));
    }

    /**
     * 由于TreeSet是由TreeMap的实例实现 而TreeMp中的元素必须实现比较器接口
     */
    @Test
    public void runB() {
        Comparator<ExampleEntity> comparator = (((o1, o2) -> o1.hashCode() - o2.hashCode()));
        TreeSet<ExampleEntity> treeSet = new TreeSet<>(comparator);
        for (int i = 0; i < 12; i++) {
            treeSet.add(new ExampleEntity(UUID.randomUUID().toString(), RandomStringUtils.randomAscii(122)));
        }
        treeSet.forEach(o -> System.out.println(o));
    }

    @Test
    public void runC() {
        Comparator<ExampleEntity> comparator = (((o1, o2) -> o1.hashCode() - o2.hashCode()));
        TreeSet<ExampleEntity> treeSet = new TreeSet<>(comparator);
        ExampleEntity exampleEntity = new ExampleEntity("1", RandomStringUtils.randomAscii(122));
        treeSet.add(exampleEntity);
        treeSet.add(new ExampleEntity("2", RandomStringUtils.randomAscii(122)));
        treeSet.remove(exampleEntity);
        treeSet.forEach(o -> System.out.println(o));
    }

    @Test
    public void runD() {
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int i = 0; i < 12; i++) {
            treeSet.add(i);
        }
        //获取第一个元素
        System.out.println(treeSet.first());
        //获取最后一个元素
        System.out.println(treeSet.last());
        System.out.println(treeSet.size());
    }

    @Test
    public void runE() {
        TreeSet<Long> treeSet = new TreeSet<>();
        for (long i = 0; i < 12; i++) {
            treeSet.add(i);
        }
        System.out.println(treeSet.size());
        //删除第一个元素并返回删除的元素
        System.out.println(treeSet.pollFirst());
        //删除最后一个元素并返回删除的元素
        System.out.println(treeSet.pollLast());
        System.out.println(treeSet.size());
    }

}
