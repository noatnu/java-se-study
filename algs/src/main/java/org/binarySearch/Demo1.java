package org.binarySearch;

import org.array.ToArray;
import org.array.ToObjectArray;
import org.testng.annotations.Test;
import zch.pojo.User;

import java.util.Arrays;

public class Demo1 {

    @Test
    public void testBinarySearch_backward(){//56
        System.out.println(ToArray.toFinalArray().length);
        Integer index = BinarySearchUtil.binarySearch_backward(ToArray.toFinalArray(),56);
        System.out.println("index:"+index+" value:"+ToArray.toFinalArray()[index]);
    }

    @Test
    public void testBinarySearch_forward(){//32
        System.out.println(ToArray.toFinalArray().length);
        Integer index = BinarySearchUtil.binarySearch_forward(ToArray.toFinalArray(),32);
        System.out.println("index:"+index+" value:"+ToArray.toFinalArray()[index]);
    }

    @Test
    public void testBinarySearch(){
        int[] arr = ToArray.toFinalArray();
        Arrays.sort(arr);
        Integer index = BinarySearchUtil.binarySearch(arr,32);
        System.out.println("index:"+index);
        System.out.println("value:"+arr[index]);
    }

    @Test
    public void testBinarySearchObject(){
        User user = new User();
        user.setUsername("zch");
        User[] users = ToObjectArray.getToObjectArray().getUsers();
        Arrays.sort(users);
        Integer index = BinarySearchUtil.binarySearch(users,user);
        System.out.println(index);
        System.out.println(users[index]);
    }

}
