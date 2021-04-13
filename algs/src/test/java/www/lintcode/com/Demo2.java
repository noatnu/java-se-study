package www.lintcode.com;

import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;

import java.util.*;
import java.util.stream.Collectors;

public class Demo2 {

    @Test
    public void testRun() {
        List<String> strings1 = Lists.newArrayList("通风", "日照", "采光");
        List<String> strings2 = Lists.newArrayList("通风", "日照", "采光", "隔音");
        List<String> strings3 = Lists.newArrayList("隔音");
        List<String> strings4 = Lists.newArrayList("通风", "隔音");
        List<String> strings5 = Lists.newArrayList("通风");

        List<String> stringList = new ArrayList<>();
        stringList.addAll(strings1);
        stringList.addAll(strings2);
        stringList.addAll(strings3);
        stringList.addAll(strings4);
        stringList.addAll(strings5);
        stringList = stringList.stream().distinct().collect(Collectors.toList());

        Map<String, Integer> integerMap = new HashMap<>(stringList.size());
        for (int i = 0; i < stringList.size(); i++) {
            integerMap.put(stringList.get(i), i);
        }
        List<Integer> integers1 = strings1.stream().map(s -> integerMap.get(s)).collect(Collectors.toList());
        List<Integer> integers2 = strings2.stream().map(s -> integerMap.get(s)).collect(Collectors.toList());
        List<Integer> integers3 = strings3.stream().map(s -> integerMap.get(s)).collect(Collectors.toList());
        List<Integer> integers4 = strings4.stream().map(s -> integerMap.get(s)).collect(Collectors.toList());
        List<Integer> integers5 = strings5.stream().map(s -> integerMap.get(s)).collect(Collectors.toList());

        List<Integer> integers = integerMap.values().stream().collect(Collectors.toList());

        //找出最少的组合来包含他们

        System.out.println(Arrays.toString(integers1.toArray()));
        System.out.println(Arrays.toString(integers2.toArray()));
        System.out.println(Arrays.toString(integers3.toArray()));
        System.out.println(Arrays.toString(integers4.toArray()));
        System.out.println(Arrays.toString(integers5.toArray()));
        System.out.println(Arrays.toString(integers.toArray()));

        List<List<Integer>> listList = new ArrayList<>();
        for (int i = 0; i < integers.size(); i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                list.add(integers.get(j));
            }
            listList.add(list);
        }
        Map<Integer, Integer> integerIntegerMap = new HashMap<>();
        for (int i = 0; i < listList.size(); i++) {
            List<Integer> integerList = listList.get(i);
            int count = 0;
            if (integers1.containsAll(integerList)) {
                count++;
            }
            if (integers2.containsAll(integerList)) {
                count++;
            }
            if (integers3.containsAll(integerList)) {
                count++;
            }
            if (integers4.containsAll(integerList)) {
                count++;
            }
            if (integers5.containsAll(integerList)) {
                count++;
            }
            integerIntegerMap.put(i, count);
        }
        if (integerIntegerMap.isEmpty()) {

        }

    }

    @Test
    public void testRun2() {
        List<String> strings1 = Lists.newArrayList("通风", "日照", "采光");
        List<String> strings2 = Lists.newArrayList("通风", "日照", "采光", "隔音");
        List<String> strings3 = Lists.newArrayList("隔音");
        List<String> strings4 = Lists.newArrayList("通风", "隔音");
        List<String> strings5 = Lists.newArrayList("通风");

        List<List<String>> stringList = new ArrayList<>();
        stringList.add(strings1);
        stringList.add(strings2);
        stringList.add(strings3);
        stringList.add(strings4);
        stringList.add(strings5);

        List<List<String>> result = new ArrayList<>() ;
        gettingList(stringList,result) ;

        List<String> strings = goodList(stringList);
        System.out.println(StringUtils.join(strings, ","));
    }

    @Test
    public void testRun3(){
        List<List<String>> stringList = new ArrayList<>();
        List<List<String>> stringList2 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int anInt = RandomUtils.nextInt(5, 20);
            List<String> strings = new ArrayList<>(anInt) ;
            List<String> strings2 = new ArrayList<>(anInt) ;
            for (int j = 0; j < anInt; j++) {
                strings.add(String.valueOf(j)) ;
                strings2.add(String.valueOf(j)) ;
            }
            stringList.add(strings); ;
            stringList2.add(strings2); ;
        }
        List<List<String>> result = new ArrayList<>() ;
        gettingList(stringList,result) ;
        System.out.println(StringUtils.join(result, ","));
    }

    private void gettingList(List<List<String>> stringList ,List<List<String>> result){
        List<String> strings = goodList(stringList);
        result.add(strings) ;
        Iterator<List<String>> iterator = stringList.iterator();
        while (iterator.hasNext()) {
            List<String> next = iterator.next();
            if (CollectionUtils.isSubCollection(strings, next)){
                Collection subtract = CollectionUtils.subtract(next, strings);
                next.clear();
                next.addAll(subtract);
                if (subtract.size() == 0){
                    iterator.remove();
                }
            }
        }
        if (CollectionUtils.isNotEmpty(stringList)){
            gettingList(stringList, result);
        }
    }

    private List<String> goodList(List<List<String>> cons) {
        List<String> stringList = new ArrayList<>();
        for (List<String> list : cons) {
            stringList.addAll(list);
        }
        stringList = stringList.stream().distinct().collect(Collectors.toList());
        List<List<String>> listList = new ArrayList<>();
        for (int i = 0; i < stringList.size(); i++) {
            List<String> list = new ArrayList<>(i);
            for (int j = 0; j <= i; j++) {
                list.add(stringList.get(j));
            }
            listList.add(list);
        }
        Map<Integer, Integer> integerIntegerMap = new HashMap<>();
        for (int i = 0; i < listList.size(); i++) {
            List<String> strings = listList.get(i);
            int count = 0;
            for (List<String> con : cons) {
                if (CollectionUtils.isSubCollection(strings, con)) {
                    Collection intersection = CollectionUtils.intersection(strings, con);
                    count += intersection.size();
                }
            }
            integerIntegerMap.put(i, count);
        }
        int asInt = integerIntegerMap.values().stream().mapToInt(Integer::intValue).max().getAsInt();
        Integer key = integerIntegerMap.entrySet().stream().filter(integerIntegerEntry -> integerIntegerEntry.getValue().intValue() == asInt).findFirst().get().getKey();
        return listList.get(key);
    }





}
