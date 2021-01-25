package collection;

import org.testng.annotations.Test;
import tool.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;

public class ListDemo {

    @Test
    public void runB(){
        ArrayList<Integer> integerList = new ArrayList<>() ;
        for (int i = 0; i < 15; i++) {
            integerList.add(i) ;
        }
        integerList.forEach(o -> System.out.println(o));
        System.out.println();
        List<Object> objectList = ListUtils.randomSoft(integerList);
        objectList.forEach(o -> System.out.println(o));
    }

    @Test
    public void runA(){
        List<ExampleEntity> list = new ArrayList<>() ;
        for (int i = 0; i < 10; i++) {
            ExampleEntity exampleEntity = new ExampleEntity(String.valueOf(i), org.apache.commons.lang.RandomStringUtils.randomAlphabetic(12)).setSofter(i);
            ExampleEntity exampleEntity1 = new ExampleEntity(String.valueOf(i+1), org.apache.commons.lang.RandomStringUtils.randomAlphabetic(12)).setSofter(i);
            list.add(exampleEntity) ;
            list.add(exampleEntity1) ;
        }
        System.out.println("size:"+list.size());
        list.forEach(o -> System.out.println(o));

        System.out.println();

        ListUtils.sort(list,true,"id","softer");
        list.forEach(o -> System.out.println(o));
    }

}
