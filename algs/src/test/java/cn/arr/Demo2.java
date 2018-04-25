package cn.arr;

import common.Logger;
import org.array.ToObjectArray;
import org.testng.annotations.Test;

import java.util.Arrays;

public class Demo2 {
    private Logger logger = Logger.getLogger(getClass());

    @Test
    public void testArr(){
        logger.info(Arrays.toString(ToObjectArray.getToObjectArray().getUsers()));
    }
}
