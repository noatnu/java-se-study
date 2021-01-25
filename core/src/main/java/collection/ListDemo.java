package collection;

import org.apache.commons.collections.CollectionUtils;
import org.testng.annotations.Test;
import tool.utils.ListUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ListDemo {

    @Test
    public void runB() {
        ArrayList<Integer> integerList = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            integerList.add(i);
        }
        integerList.forEach(o -> System.out.print(o + ","));
        System.out.println();
        long millis = System.currentTimeMillis();
        System.out.println(millis);
        List<Integer> integers = ListUtils.randomSoft2(integerList).stream().map(o -> (Integer) o).sorted().collect(Collectors.toList());
//        List<Integer> integers = ListUtils.randomSoft(integerList).stream().map(o -> (Integer) o).sorted().collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(integers)) {
            integers.forEach(o -> System.out.print(o + ","));
        }
        long timeMillis = System.currentTimeMillis();
        long l = (timeMillis - millis) / 1000;
        System.out.println("l:" + l);
        System.out.println(timeMillis);
    }

    @Test
    public void runA() {
        List<ExampleEntity> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ExampleEntity exampleEntity = new ExampleEntity(String.valueOf(i), org.apache.commons.lang.RandomStringUtils.randomAlphabetic(12)).setSofter(i);
            ExampleEntity exampleEntity1 = new ExampleEntity(String.valueOf(i + 1), org.apache.commons.lang.RandomStringUtils.randomAlphabetic(12)).setSofter(i);
            list.add(exampleEntity);
            list.add(exampleEntity1);
        }
        System.out.println("size:" + list.size());
        list.forEach(o -> System.out.println(o));

        System.out.println();

        ListUtils.sort(list, true, "id", "softer");
        list.forEach(o -> System.out.println(o));
    }

}
