package cn.arr;

import common.Logger;
import org.array.ToArray;
import org.testng.annotations.Test;

import java.util.Arrays;

public class Demo1 {

    private Logger logger = Logger.getLogger(getClass());

    @Test
    public void testArr(){
        int[] arr = ToArray.getToArray().toIntArray();
        logger.info(Arrays.toString(arr));
    }
}
