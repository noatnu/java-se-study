package other.jdk8.example.stram;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import other.jdk8.entity.Student;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author noatn
 * @Description 末端操作
 * @createDate 2019/2/5
 **/
public class ExampleB {

    // 初始化
    List<Student> students = new ArrayList<Student>() {
        {
            add(new Student(20160002, "伯约", 21, 2, "信息安全", "武汉大学"));
            add(new Student(20160001, "孔明", 20, 1, "土木工程", "武汉大学"));
            add(new Student(20160003, "玄德", 22, 3, "经济管理", "武汉大学"));
            add(new Student(20160004, "云长", 21, 2, "信息安全", "武汉大学"));
            add(new Student(20161001, "翼德", 21, 2, "机械与自动化", "华中科技大学"));
            add(new Student(20161002, "元直", 23, 4, "土木工程", "华中科技大学"));
            add(new Student(20161003, "奉孝", 23, 4, "计算机科学", "华中科技大学"));
            add(new Student(20162001, "仲谋", 22, 3, "土木工程", "浙江大学"));
            add(new Student(20162002, "鲁肃", 23, 4, "计算机科学", "浙江大学"));
            add(new Student(20163001, "丁奉", 24, 5, "土木工程", "南京大学"));
        }
    };

    public static void main(String[] args) {
        double a = 86852572;
        double b = 52872280;
        System.out.println(a - b);
    }

    /**
     * map
     *
     * @throws Exception
     */
    @Test
    public void testA() throws Exception {
        List<String> names = students.parallelStream().sorted().filter(student -> "计算机科学".equals(student.getMajor())).map(student -> student.getName()).collect(Collectors.toList());
        System.out.println(names);
    }

    /**
     * flatMap
     *
     * @throws Exception
     */
    @Test
    public void testB() throws Exception {
        String[] strs = {"java8", "is", "easy", "to", "use"};
        List<String> distinctStrs = Arrays.stream(strs)
                .map(s -> s.split("")).flatMap(strings -> Arrays.stream(strings))// 映射成为Stream<String[]>
                .distinct().collect(Collectors.toList()); // 扁平化为Stream<String>
        System.out.println(distinctStrs);
    }

    /**
     * allMatch用于检测是否全部都满足指定的参数行为，如果全部满足则返回true
     *
     * @throws Exception
     */
    @Test
    public void testC() throws Exception {
        boolean isAdult = students.parallelStream().allMatch(student -> student.getAge() >= 18);
        System.out.println(isAdult ? "是" : "否");
    }

    /**
     * anyMatch则是检测是否存在一个或多个满足指定的参数行为，如果满足则返回true
     *
     * @throws Exception
     */
    @Test
    public void testD() throws Exception {
        boolean hasWhu = students.parallelStream().anyMatch(student -> "武汉大学".equals(student.getSchool()));
        System.out.println(hasWhu ? "是" : "否");
    }

    /**
     * noneMatch用于检测是否不存在满足指定行为的元素，如果不存在则返回true
     *
     * @throws Exception
     */
    @Test
    public void testF() throws Exception {
        boolean noneCs = students.parallelStream().noneMatch(student -> "计算机科学".equals(student.getMajor()));
        System.out.println(noneCs ? "是" : "否");
    }

    /**
     * findFirst findFirst用于返回满足条件的第一个元素
     *
     * @throws Exception
     */
    @Test
    public void testG() throws Exception {
        Optional<Student> optStu = students.stream().filter(student -> "土木工程".equals(student.getMajor())).findFirst();
        System.out.println(optStu.get());
    }

    /**
     * 归约 reduce接受两个参数
     *
     * @throws Exception
     */
    @org.testng.annotations.Test
    public void testH() throws Exception {
        List<Integer> integerList = students.stream().map(Student::getAge).collect(Collectors.toList());
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
        List<Integer> integerList = students.stream().map(Student::getAge).collect(Collectors.toList());

        //收集set
        Set<Long> longSet = students.stream().map(Student::getId).collect(Collectors.toSet());
    }

}
