package other.jdk8.example.stram;

import org.testng.annotations.Test;
import other.jdk8.entity.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author noatn
 * @Description
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

}
