package other.jdk8.example.stram;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import other.jdk8.entity.Person;
import other.jdk8.entity.Student;
import tool.help.Zhou_StdRandom;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author noatn
 * @Description 中间操作
 * @createDate 2019/2/4
 **/
public class ExampleB {

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
     * map
     *
     * @throws Exception
     */
    @Test
    public void testMap() throws Exception {
        List<String> names = ExampleData.studentA.parallelStream().sorted().filter(student -> "计算机科学".equals(student.getMajor())).map(student -> student.getName()).collect(Collectors.toList());
        System.out.println(names);
    }

    /**
     * 按照Java团队的说法，peek()方法存在的主要目的是用调试，通过peek()方法可以看到流中的数据经过每个处理点时的状态
     * 和 Map类似
     */
    @org.testng.annotations.Test
    public void testPeek() {
        Person a = new Person("a", 18);
        Person b = new Person("b", 23);
        Person c = new Person("c", 34);
        Stream<Person> persons = Stream.of(a, b, c);
        {
//            persons.peek(person -> System.out.println("===>"+person));
        }
        {
            //当加入某些中间操作后就可以看出状态了
//            persons.filter(person -> person.getAge() > 2).peek(person -> System.out.println("<========"+person)).collect(Collectors.toList());
            //通过输出结果来看，peek()方法确实能够帮助我们观察传递给每个操作的数据。
        }
        {
            /*
             * 除去用于调试，peek()在需要修改元素内部状态的场景也非常有用，比如我们想将所有Person的名字修改为大写，
             * 当然也可以使用map()和flatMap实现，但是相比来说peek()更加方便，因为我们并不想替代流中的数据。
             * */
            persons.peek(person -> person.setName(person.getName().toUpperCase())).forEachOrdered(System.out::print);
        }
        {
            //根本不会执行peek方法,这是因为没有终端操作
            Stream.iterate(2,n -> n*2).limit(5).peek(integer -> System.out.println("integer:"+integer));
            //终端count()
            Stream.iterate(2,n -> n*2).limit(5).peek(integer -> System.out.println("integerA:"+integer)).count();
            //终端操作forEach()
            Stream.iterate(2,n -> n*2).limit(5).peek(integer -> System.out.println("integerB:"+integer)).forEach(integer -> System.out.println());
            //终端操作average()
            Stream.iterate(2,n -> n*2).limit(5).peek(integer -> System.out.println("integerC:"+integer)).mapToInt(Integer::intValue).average();
        }
    }

    /**
     * flatMap
     *
     * @throws Exception
     */
    @org.testng.annotations.Test
    public void testFlatMap() throws Exception {
        String[] strs = {"java8", "is", "easy", "to", "use"};
        List<String> distinctStrs = Arrays.stream(strs)
                .map(s -> s.split("")).flatMap(strings -> Arrays.stream(strings))// 映射成为Stream<String[]>
                .distinct().collect(Collectors.toList()); // 扁平化为Stream<String>
        System.out.println(distinctStrs);
        Stream<Person> personStream = Stream.generate(() -> new Person(RandomStringUtils.randomAlphabetic(4), RandomUtils.nextInt(2,100))).limit(12);
        Stream<String>  pS = personStream.flatMap(person -> {
            //这转换为了流 数组 或者List类型等
            return Arrays.stream(new String[]{Integer.toString(person.getAge()),person.getName()});
        });
        pS.forEachOrdered(System.out::println);
    }

    /**
     * filter 过滤
     *
     * @throws Exception
     */
    @Test
    public void testFilter() throws Exception {
        List<Student> whuStudents = ExampleData.studentA.parallelStream().sorted().filter(student -> "武汉大学".equals(student.getSchool())).collect(Collectors.toList());
        whuStudents.parallelStream().forEach(student -> System.out.println(student));
    }

    /**
     * distinct 去除重复
     *
     * @throws Exception
     */
    @Test
    public void testDistinct() throws Exception {
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
    public void testLimit() throws Exception {
        List<Student> civilStudents = ExampleData.studentA.parallelStream().sorted().filter(student -> "土木工程".equals(student.getMajor())).limit(2).collect(Collectors.toList());
        civilStudents.parallelStream().forEach(student -> System.out.println(student));
    }

    /**
     * skip skip操作与limit操作相反，如同其字面意思一样，是跳过前n个元素
     *
     * @throws Exception
     */
    @Test
    public void testSkip() throws Exception {
        List<Student> civilStudents = ExampleData.studentA.parallelStream().sorted().filter(student -> "土木工程".equals(student.getMajor())).skip(2).collect(Collectors.toList());
        civilStudents.parallelStream().forEach(student -> System.out.println(student));
    }

    public void before() {

    }

}
