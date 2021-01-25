package collection;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class CollectionDemo {

    @Test
    public void runA() {
        Collection<String> collection = new ArrayList<>();
        Stream.generate(new Supplier<Integer>() {
            @Override
            public Integer get() {
                return null;
            }
        }).limit(20).forEach(integer -> collection.add(RandomStringUtils.randomAlphabetic(5)));
        Iterator<String> iterator = collection.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Test
    public void runB() {
        Collection<String> collection = new ArrayList<>();
        Stream.generate(new Supplier<Integer>() {
            @Override
            public Integer get() {
                return null;
            }
        }).limit(20).forEach(integer -> collection.add(RandomStringUtils.randomAlphabetic(5)));
        System.out.println(((ArrayList<String>) collection).get(1));
        collection.clear();
        System.out.println(collection.size());
    }

    @Test
    public void runC() {
        Collection<String> collection = new ArrayList<>();
        Stream.generate(new Supplier<Integer>() {
            @Override
            public Integer get() {
                return null;
            }
        }).limit(20).forEach(integer -> collection.add(RandomStringUtils.randomAlphabetic(5)));
       ((ArrayList<String>) collection).remove(19);
        System.out.println(collection.size());
    }

    @Test
    public void runD() {
        Collection<String> collection = new ArrayList<>();
        Stream.generate(new Supplier<Integer>() {
            @Override
            public Integer get() {
                return null;
            }
        }).limit(20).forEach(integer -> collection.add(RandomStringUtils.randomAlphabetic(5)));
        String str = "a" ;
        ((ArrayList<String>) collection).add(str);
        boolean contains = collection.contains(str);
        System.out.println(contains);
    }


}
