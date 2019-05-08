package other.jdk8.example.stram;

import com.google.common.collect.*;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import other.jdk8.entity.Student;
import tool.help.Zhou_StdRandom;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

/**
 * @Author noatn
 * @Description
 * @createDate 2019/4/27
 **/
public class ExampleC {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    //流创建
    @Test
    public void testA() {
        //泛型流
        Stream<String> stringStream = Arrays.stream(new String[]{UUID.randomUUID().toString(), UUID.randomUUID().toString()});
        //具体流,这个不可加<>了
        DoubleStream doubleStream = Arrays.stream(new double[]{2.02d, 2d, 22d});
        //或者由其他地方来源
        //如:
        List<String> stringList = Lists.newArrayList();
        Collection<String> stringCollection = Lists.newArrayList();
        Set<String> stringSet = Sets.newHashSet();
        Map<String, String> stringStringMap = Maps.newHashMap();
        Multimap<String, Student> stringStudentMultimap = ArrayListMultimap.create();
        stringList.stream();
        stringCollection.stream();
        stringSet.stream();
        stringStringMap.entrySet().stream();
        stringStudentMultimap.entries().stream();
        Stream<Double> streamA = Stream.of(1.2d, 2.3d);
        Stream<Double> streamB = streamA.parallel();
//        streamA.forEach(aDouble -> System.out.println(aDouble));
        //已经被消费了
        streamB.forEach(aDouble -> System.out.println(">> " + aDouble));
        //无限长度
        Stream<Double> streamC = Stream.generate(new Supplier<Double>() {
            @Override
            public Double get() {
                return Math.random();
            }
        }).limit(7);//不加limit就是无限
//        streamC.close();
            streamC.forEach(aDouble -> System.out.println("<< " + aDouble));
        Stream<Integer> streamD = Stream.iterate(2, new UnaryOperator<Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer;//这的integer就是2 相当于因子,因素
            }
        }).limit(20).skip(2);//同样如此

//        System.out.println("size:" + streamD.count());
        Stream<Integer> streamE = streamD.peek(integer -> Integer.valueOf(integer.intValue()*2));
        streamE.forEach(integer -> System.out.println("dhsssss"+integer));
    }

    /**
     * 简单统计,以及变量作用域
     */
    @Test
    public void testB() {
        Multimap<String, Double> multimap = ArrayListMultimap.create();

        for (int i = 0; i < 10; i++) {
            multimap.put(String.valueOf(Zhou_StdRandom.uniform(1, 20)), Zhou_StdRandom.uniform(0.01d, 100d));
        }

        multimap.entries().stream().forEach(stringDoubleEntry -> {
            System.out.println(stringDoubleEntry.getKey() + " " + stringDoubleEntry.getValue());
        });

        //变量作用域
        //lambda 表达式只能引用标记了 final 的外层局部变量，这就是说不能在lambda 内部修改定义在域外的局部变量，否则会编译错误。

        //错误写法
        double sum = 0;
        /*
        multimap.values().stream().forEach(aDouble -> {
            sum += aDouble.doubleValue();
        });
        */
        //正确写法
        sum = multimap.values().stream().mapToDouble(Double::doubleValue).sum();
        System.out.println("sum:" + sum);
        //或者你还需要筛选以下的话
        sum = multimap.values().stream().filter(aDouble -> aDouble > 50).distinct().mapToDouble(Double::doubleValue).sum();
        System.out.println("筛选sum:" + sum);
        //当然了这里不仅仅是sum [累加]还有,average()[平均数],max [最大数],min [最小数]
        //或者是你需要收集你赛选好的list
        List<String> doubleList = multimap.values().stream().filter(aDouble -> aDouble > 50).distinct().map(aDouble -> aDouble.toString()).collect(Collectors.toList());

        List<String> stringList = Lists.newArrayList();
        System.out.println(CollectionUtils.isEmpty(stringList));
        //流的操作元素数量可以为0，但是流不能是空的
        stringList.stream().filter(s -> s.length() > 2).forEach(s -> System.out.println("---" + s));
//        stringList = null;
        try {
            stringList.stream().filter(s -> s.length() > 2).forEach(s -> System.out.println("---" + s));
        } catch (Exception e) {
            System.out.println("空指针异常!" + e.getLocalizedMessage());
        }
        //同样不允许,因为stringList1被用于了一个流操作当中
        /*
        List<String> stringList1 = Lists.newArrayList();
        stringList.stream().forEach(s -> stringList1.add(String.valueOf(s.length())));
        stringList1 = stringList1.stream().distinct();
        */
        //另外就是在使用skip,以及limit以及findFirst方法的时候元素为0可能也有可能处问题
        //如
        try {
            String s = stringList.stream().findFirst().get();
            System.out.println(s);
        } catch (Exception e) {
            System.out.println("空指针异常!" + e.getLocalizedMessage());
        }
        //最好的办法是进行一个元素是否为0的判断 如:
        if (stringList.stream().count() >= 1) {
        }
        if (!stringList.isEmpty()) {
        }
    }

    @Test
    public void testC() {
        Random random = new Random(System.currentTimeMillis());
        final int num = (int) Math.floor(random.nextDouble() * 10) + Math.multiplyExact(random.nextInt(10), random.nextInt(20)) + random.nextInt() + random.hashCode();
        List<String> stringList = Arrays.asList(Double.toHexString(num * random.nextDouble()), Double.toHexString(num * random.nextDouble()), Double.toHexString(num * random.nextDouble()), Double.toHexString(num * random.nextDouble()), Double.toHexString(num * random.nextDouble()), Double.toHexString(num * random.nextDouble()), Double.toHexString(num * random.nextDouble()));
        stringList.stream().forEachOrdered(s -> {
            System.out.println("number:" + s);
        });
        Stream<String> stringStream = stringList.stream().onClose(() -> toString());
        stringStream.forEach(s -> System.out.println(s));
        Stream<String> stringStream2 = stringList.stream().parallel();
        stringStream2.forEach(s -> System.out.println(s));
    }

    /**
     * 函数接口使用
     */
    @Test
    public void testG() {
        fun((a, b) -> true);
        //或者
        fun((a, b) -> {
            return true;
        });
        //或者
        BiPredicate biPredicate = (a, b) -> true;
        boolean b = biPredicate.test("3", "2");
        System.out.println("biPredicate.test(\"3\",\"3\"):" + b);

        BiFunction<Integer, Integer, Integer> biFunction = (Integer::compareTo);
        BiFunction<Integer, Integer, Integer> biFunction1 = (a1, a2) -> a1 * a2;
        Integer sum = biFunction1.apply(2, 3);
        //假如你不传具体类型在方法里面，则只有返回根对象，或者说基类对象
        BiFunction biFunction2 = (b1, b2) -> new Object();
        //一共有43个初始函数式接口供我们使用
        //使用我们自己定义的这个函数接口
        AddFun addFun = ((a, b1) -> a + b1);
        addFun.add(2, 5);

    }

    //代表了一个接受两个输入参数的操作，并且不返回任何结果
    public void fun(BiPredicate biPredicate) {
        System.out.println("biPredicate:" + biPredicate);
        System.out.println("biPredicate.test(\"1\",\"1\")" + biPredicate.test("1", "1"));
    }

    //定义函数接口的两个原则是FunctionalInterface注解然后是接口只能有一个方法,两个必须同时满足
    @FunctionalInterface
    interface AddFun {
        public int add(int a, int b);
    }

    /*
        1
        BiConsumer<T,U>

        代表了一个接受两个输入参数的操作，并且不返回任何结果

        2

        BiFunction<T,U,R>

        代表了一个接受两个输入参数的方法，并且返回一个结果

        3

        BinaryOperator<T>

        代表了一个作用于于两个同类型操作符的操作，并且返回了操作符同类型的结果

        4

        BiPredicate<T,U>

        代表了一个两个参数的boolean值方法

        5

        BooleanSupplier

        代表了boolean值结果的提供方

        6

        Consumer<T>

        代表了接受一个输入参数并且无返回的操作

        7

        DoubleBinaryOperator

        代表了作用于两个double值操作符的操作，并且返回了一个double值的结果。

        8

        DoubleConsumer

        代表一个接受double值参数的操作，并且不返回结果。

        9

        DoubleFunction<R>

        代表接受一个double值参数的方法，并且返回结果

        10

        DoublePredicate

        代表一个拥有double值参数的boolean值方法

        11

        DoubleSupplier

        代表一个double值结构的提供方

        12

        DoubleToIntFunction

        接受一个double类型输入，返回一个int类型结果。

        13

        DoubleToLongFunction

        接受一个double类型输入，返回一个long类型结果

        14

        DoubleUnaryOperator

        接受一个参数同为类型double,返回值类型也为double。

        15

        Function<T,R>

        接受一个输入参数，返回一个结果。

        16

        IntBinaryOperator

        接受两个参数同为类型int,返回值类型也为int 。

        17

        IntConsumer

        接受一个int类型的输入参数，无返回值。

        18

        IntFunction<R>

        接受一个int类型输入参数，返回一个结果。

        19

        IntPredicate

        ：接受一个int输入参数，返回一个布尔值的结果。

        20

        IntSupplier

        无参数，返回一个int类型结果。

        21

        IntToDoubleFunction

        接受一个int类型输入，返回一个double类型结果。

        22

        IntToLongFunction

        接受一个int类型输入，返回一个long类型结果。

        23

        IntUnaryOperator

        接受一个参数同为类型int,返回值类型也为int 。

        24

        LongBinaryOperator

        接受两个参数同为类型long,返回值类型也为long。

        25

        LongConsumer

        接受一个long类型的输入参数，无返回值。

        26

        LongFunction<R>

        接受一个long类型输入参数，返回一个结果。

        27

        LongPredicate

        R接受一个long输入参数，返回一个布尔值类型结果。

        28

        LongSupplier

        无参数，返回一个结果long类型的值。

        29

        LongToDoubleFunction

        接受一个long类型输入，返回一个double类型结果。

        30

        LongToIntFunction

        接受一个long类型输入，返回一个int类型结果。

        31

        LongUnaryOperator

        接受一个参数同为类型long,返回值类型也为long。

        32

        ObjDoubleConsumer<T>

        接受一个object类型和一个double类型的输入参数，无返回值。

        33

        ObjIntConsumer<T>

        接受一个object类型和一个int类型的输入参数，无返回值。

        34

        ObjLongConsumer<T>

        接受一个object类型和一个long类型的输入参数，无返回值。

        35

        Predicate<T>

        接受一个输入参数，返回一个布尔值结果。

        36

        Supplier<T>

        无参数，返回一个结果。

        37

        ToDoubleBiFunction<T,U>

        接受两个输入参数，返回一个double类型结果

        38

        ToDoubleFunction<T>

        接受一个输入参数，返回一个double类型结果

        39

        ToIntBiFunction<T,U>

        接受两个输入参数，返回一个int类型结果。

        40

        ToIntFunction<T>

        接受一个输入参数，返回一个int类型结果。

        41

        ToLongBiFunction<T,U>

        接受两个输入参数，返回一个long类型结果。

        42

        ToLongFunction<T>

        接受一个输入参数，返回一个long类型结果。

        43

        UnaryOperator<T>

        接受一个参数为类型T,返回值类型也为T。
    * */
}
