package other.jdk8.example.stram;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import other.jdk8.entity.Student;
import tool.help.Zhou_StdRandom;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author noatn
 * @Description
 * @createDate 2019/2/4
 **/
public class ExampleA {
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

    @Test
    public void test() throws Exception {
        List<Integer> numS = Lists.newArrayList();
        for (int i = 0; i < 20; i++) {
            numS.add(Zhou_StdRandom.uniform(1, 674));
        }
//        List<Integer> evens = new ArrayList<>();
//        for (final Integer num : numS) {
//            if (num % 2 == 0) {
//                evens.add(num);
//            }
//        }

        List<Integer> evens = numS.stream().filter(integer -> integer % 2 == 0).collect(Collectors.toList());
        evens.stream().sorted().filter(integer -> true).forEach(integer -> System.out.println(integer));
    }

    /**
     * filter 过滤
     *
     * @throws Exception
     */
    @Test
    public void testB() throws Exception {
        List<Student> whuStudents = students.parallelStream().sorted().filter(student -> "武汉大学".equals(student.getSchool())).collect(Collectors.toList());
        whuStudents.parallelStream().forEach(student -> System.out.println(student));
    }

    /**
     * distinct 去除重复
     *
     * @throws Exception
     */
    @Test
    public void testC() throws Exception {
        List<Integer> numS = Lists.newArrayList();
        for (int i = 0; i < 1000; i++) {
            numS.add(Zhou_StdRandom.uniform(1, 24));
        }
        List<Integer> evens = numS.parallelStream().sorted().filter(integer -> integer % 2 == 0).distinct().collect(Collectors.toList());
        System.out.println(evens);
    }

    /**
     * limit返回包含前n个元素的流，当集合大小小于n时，则返回实际长度
     *
     * @throws Exception
     */
    @Test
    public void testD() throws Exception {
        List<Student> civilStudents = students.parallelStream().sorted().filter(student -> "土木工程".equals(student.getMajor())).limit(2).collect(Collectors.toList());
        civilStudents.parallelStream().forEach(student -> System.out.println(student));
    }

    /**
     * skip skip操作与limit操作相反，如同其字面意思一样，是跳过前n个元素
     *
     * @throws Exception
     */
    @Test
    public void testE() throws Exception {
        List<Student> civilStudents = students.parallelStream().sorted().filter(student -> "土木工程".equals(student.getMajor())).skip(2).collect(Collectors.toList());
        civilStudents.parallelStream().forEach(student -> System.out.println(student));
    }

    @BeforeAll
    public void before() {

    }

}
