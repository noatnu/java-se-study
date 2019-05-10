package other.jdk8.example.stram;

import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: zch
 * @date: 2019/5/10 18:25
 * @description:stream速度比较
 */
public class StreamSpeedDemo {

    /**使用迭代器遍历列表：
     * 110ms
     * Stream.iterate(2, n -> n + n * 2).limit(1000).collect(Collectors.toList());
     * @param integers
     * @return
     */
    private int iteratorMaxInteger(List<Integer> integers) {
        int max = Integer.MIN_VALUE;
        for (Iterator<Integer> it = integers.iterator(); it.hasNext(); ) {
            max = Integer.max(max, it.next());
        }
        return max;
    }

    /**不使用迭代器，使用 For-Each 循环遍历列表
     * 109ms
     * Stream.iterate(2, n -> n + n * 2).limit(1000).collect(Collectors.toList());
     * @param integers
     * @return
     */
    private int forEachLoopMaxInteger(List<Integer> integers) {
        int max = Integer.MIN_VALUE;
        for (Integer n : integers) {
            max = Integer.max(max, n);
        }
        return max;
    }

    /**使用简单的 for 循环和索引遍历列表：
     * 101ms
     * Stream.iterate(2, n -> n + n * 2).limit(1000).collect(Collectors.toList());
     * @param integers
     * @return
     */
    private int forMaxInteger(List<Integer> integers) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < integers.size(); i++) {
            max = Integer.max(max, integers.get(i));
        }
        return max;
    }

    /**
     * 141ms
     * 使用 Java 8 并行流遍历列表
     * @param integers
     * @return
     */
    private int parallelStreamMaxInteger(List<Integer> integers) {
        Optional<Integer> max = integers.parallelStream().reduce(Integer::max);
        return max.get();
    }

    /**
     * 110ms
     * 使用 lambda 表达式及流遍历列表
     * @param integers
     * @return
     */
    private int lambdaMaxInteger(List<Integer> integers ) {
        return integers.stream().reduce(Integer.MIN_VALUE, (a, b) -> Integer.max(a, b));
    }

    /**221ms
     * 使用流归约操作
     * @param integers
     * @return
     */
    private int streamMaxInteger(List<Integer> integers) {
        Optional<Integer> max = integers.stream().reduce(Integer::max);
        return max.get();
    }

    private void init() {
        List<Integer> integers = Stream.iterate(2, n -> n + n * 2).limit(1000).collect(Collectors.toList());
        int num = this.streamMaxInteger(integers);
        System.out.println("max:"+num);
    }

    @Test
    public void testRun() {
        //获取当前时间
        long startTime = System.currentTimeMillis();
        init();
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
    }
}
