package other.jdk8.example.stram;

import com.google.common.collect.Lists;
import org.testng.annotations.Test;
import tool.help.Zhou_StdRandom;
import tool.help.Zhou_Word;

import java.util.List;
import java.util.Spliterator;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author noatn
 * @Description
 * @createDate 2019/5/9
 **/
public class ExampleA2 {

    /**
     * 类似于Iterator这样的遍历
     */
    @Test
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
     * 直接foreach遍历
     */
    @Test
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
        //排序+遍历一起
        stringStream.forEachOrdered(System.out::println);//(Integer,String,这种面向程序员的类一般都实现了Comparable有 compareTo方法)
    }

}
