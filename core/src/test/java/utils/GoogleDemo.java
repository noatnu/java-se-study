package utils;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.apache.commons.collections.CollectionUtils;
import org.testng.annotations.Test;

import java.util.Collection;

/**
 * @Author noatn
 * @Description Multimap的实现
 * @createDate 2019/4/21
 **/
public class GoogleDemo {


    @Test
    public void testA() {
        //google guava的Multimaps：一键多值的Map
        /**
         实现	Key实现	Value实现
         ArrayListMultimap	HashMap	ArrayList
         HashMultimap	HashMap	HashSet
         LinkedListMultimap	LinkedHashMap	LinkedList
         LinkedHashMultimap	LinkedHashMap	LinkedHashSet
         TreeMultimap	TreeMap	TreeSet
         ImmutableListMultimap	ImmutableMap	ImmutableList
         ImmutableSetMultimap	ImmutableMap	ImmutableSet
         */
        Multimap<String, String> multimap = ArrayListMultimap.create();
        // 添加键值对
        multimap.put("Fruits", "Bannana");
        //给Fruits元素添加另一个元素
        multimap.put("Fruits", "Apple");
        multimap.put("Fruits", "Pear");
        multimap.put("Vegetables", "Carrot");
        System.out.println(multimap.size());
        multimap.keys().stream().forEach(s -> System.out.println("key:" + s));
        multimap.values().stream().forEach(s -> System.out.println("value:" + s));

        // 获得Fruits对应的所有的值
        Collection<String> fruits = multimap.get("Fruits");
        if (CollectionUtils.isNotEmpty(fruits)) {
            System.out.println(fruits);//[Bannana, Apple, Pear]
        }

        Collection<String> vegetables = multimap.get("Vegetables");
        System.out.println(vegetables); // [Carrot]

    }

}
