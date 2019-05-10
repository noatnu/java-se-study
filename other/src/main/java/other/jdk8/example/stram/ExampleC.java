package other.jdk8.example.stram;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.testng.collections.Maps;
import other.jdk8.entity.Student;
import tool.help.Zhou_StdRandom;
import tool.help.Zhou_Word;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author noatn
 * @Description 末端操作
 * @createDate 2019/2/5
 **/
public class ExampleC {



    /**
     * allMatch用于检测是否全部都满足指定的参数行为，如果全部满足则返回true
     *
     * @throws Exception
     */
    @Test
    public void testC() throws Exception {
        boolean isAdult = ExampleData.studentA.parallelStream().allMatch(student -> student.getAge() >= 18);
        System.out.println(isAdult ? "是" : "否");
    }

    /**
     * anyMatch则是检测是否存在一个或多个满足指定的参数行为，如果满足则返回true
     *
     * @throws Exception
     */
    @Test
    public void testD() throws Exception {
        boolean hasWhu = ExampleData.studentA.parallelStream().anyMatch(student -> "武汉大学".equals(student.getSchool()));
        System.out.println(hasWhu ? "是" : "否");
    }

    /**
     * noneMatch用于检测是否不存在满足指定行为的元素，如果不存在则返回true
     *
     * @throws Exception
     */
    @Test
    public void testF() throws Exception {
        boolean noneCs = ExampleData.studentA.parallelStream().noneMatch(student -> "计算机科学".equals(student.getMajor()));
        System.out.println(noneCs ? "是" : "否");
    }

    /**
     * findFirst findFirst用于返回满足条件的第一个元素
     *
     * @throws Exception
     */
    @Test
    public void testG() throws Exception {
        Optional<Student> optStu = ExampleData.studentA.stream().filter(student -> "土木工程".equals(student.getMajor())).findFirst();
        System.out.println(optStu.get());
    }

    /**
     * 归约 reduce接受两个参数
     *
     * @throws Exception
     */
    @org.testng.annotations.Test
    public void testH() throws Exception {
        List<Integer> integerList = ExampleData.studentA.stream().map(Student::getAge).collect(Collectors.toList());
        Stream<Integer> integerStream = integerList.stream();
        Integer sum = integerStream.reduce(new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) {
                return integer + integer2;
            }
        }).get();
        System.out.println(StringUtils.join(integerList, ","));
        System.out.println("sum:" + sum);

        int product = integerList.stream().reduce(2, new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) {
                return integer + integer2;
            }
        });
        //刚好把2加上去
        System.out.println("product:" + product);
    }

    /**
     * collect 收集
     */
    @org.testng.annotations.Test
    public void testJ(){
        //收集List
        List<Integer> integerList = ExampleData.studentA.stream().map(Student::getAge).collect(Collectors.toList());

        //收集set
        Set<Long> longSet = ExampleData.studentA.stream().map(Student::getId).collect(Collectors.toSet());

    }

    /**
     * 类似于Iterator这样的遍历
     */
    @org.testng.annotations.Test
    public void testSpliterator(){
        Stream<String> stringStream = Stream.generate(new Supplier<String>() {
            @Override
            public String get() {
                return Zhou_Word.getEnglishName();
            }
        }).limit(12);
        List<String> stringList = Lists.newArrayList(stringStream.collect(Collectors.toList()));
        Spliterator<String> stringSpliterator = stringList.stream().spliterator();
        while (stringSpliterator.tryAdvance(s -> System.out.println(">>>"+s)));
        //或者
//        while (stringSpliterator.tryAdvance(System.out::println));
        //或者
        stringList.stream().spliterator().forEachRemaining(s -> System.out.println("==="+s));
    }

    /**
     * 排序
     */
    @org.testng.annotations.Test
    public void sortedTest(){
        Stream<Integer> integerStream = Stream.iterate(2,(seed) -> Zhou_StdRandom.uniform(10,100)+seed).limit(50);
        //这里需要注意的是创建的是Integer流,因为Integer已经实现了Comparable接口里面有compareTo方法
        integerStream.sorted().forEachOrdered(System.out::println);
        //假如没有实现Comparable接口那么就需要手动实现了
        ExampleData.studentA.stream().sorted((o1, o2) -> new Integer(o1.getAge()).compareTo(new Integer(o2.getAge()))).forEach(System.out::println);
        //可以看到年龄最小的在第一个
        //当然了还可以使用google架包里的
        Ordering<Student> studentOrdering = Ordering.from((o1, o2) -> new Integer(o1.getAge()).compareTo(new Integer(o2.getAge())));
        List<Student> studentList = ExampleData.studentA ;
        //反序
        studentList.sort(studentOrdering.reverse());
        studentList.stream().forEachOrdered(student -> System.out.println("====>"+student));
    }

    /**
     * 直接foreach遍历
     */
    @org.testng.annotations.Test
    public void testForeach(){
        //这种创建无限流适合数字类型
        Stream<Integer> stringStream = Stream.iterate(2, new UnaryOperator<Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer* Zhou_StdRandom.uniform(Zhou_StdRandom.uniform(10,20))+2;
            }
        }).limit(20);
//        stringStream.forEach(integer -> System.out.println(integer));
//        stringStream.forEach(System.out::print);
        //forEach 和 forEachOrdered在Java 8 Stream的区别
        //主要区别在并行处理上，forEach是并行处理的，forEachOrder是按顺序处理的，显然前者速度更快。
        stringStream.forEachOrdered(System.out::println);

        Stream.of("AAA","BBB","CCC").parallel().forEach(s->System.out.println("Output ==>"+s));
        Stream.of("AAA","BBB","CCC").parallel().forEachOrdered(s->System.out.println("Output =<="+s));
    }

}
