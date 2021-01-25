package collection;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import java.util.*;

public class TreeMapDemo {

    @Test
    public void runA(){
        TreeMap<Integer,String> treeMap = new TreeMap<>() ;
        treeMap.put(1, RandomStringUtils.randomNumeric(128)) ;
        //覆盖
        treeMap.put(1, RandomStringUtils.randomNumeric(128)) ;
        System.out.println(treeMap.size());
    }

    @Test
    public void runB(){
        Comparator<ExampleEntity> comparator = (((o1, o2) -> o1.getSofter().compareTo(o2.getSofter()))) ;
        TreeMap<ExampleEntity, List<String>>  treeMap = new TreeMap<>(comparator) ;
//        TreeMap<ExampleEntity, List<String>>  treeMap = new TreeMap<>() ;
        for (int i = 0; i < 100; i++) {
            ExampleEntity exampleEntity = new ExampleEntity(UUID.randomUUID().toString(), org.apache.commons.lang.RandomStringUtils.randomAlphabetic(12)).setSofter(2);
            List<String> stringList = Arrays.asList(RandomStringUtils.random(4),RandomStringUtils.random(4),RandomStringUtils.random(4)) ;
            treeMap.put(exampleEntity,stringList) ;
        }
        //print 1
        System.out.println(treeMap.size());
    }

    @Test
    public void runC(){
        TreeMap<Integer,String> treeMap = new TreeMap<>() ;
        treeMap.put(2,RandomStringUtils.randomNumeric(4)) ;
        treeMap.put(3,RandomStringUtils.randomNumeric(4)) ;
        treeMap.put(7,RandomStringUtils.randomNumeric(4)) ;
        System.out.println(treeMap.size());
    }




}
