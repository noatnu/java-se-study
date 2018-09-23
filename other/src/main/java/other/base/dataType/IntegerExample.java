package other.base.dataType;

import org.testng.annotations.Test;

/**
 * @Author noatn
 * @Description
 * @createDate 2018/9/23
 **/
public class IntegerExample {

    /**
     * @Description:描述 获取二进制中补码中1位的数量
     * @Author:zch
     * @CreateDate:16:07 2018/9/23
     * @Param:
     * @return:
     * @Version:1.0
     */
    @Test
    public void bitCount(){
        int  i = 10 ;
        int count = Integer.bitCount(i);
        System.out.println(i+":中二进制补码的数量 "+count);
    }

    /**
     * @Description:描述获取byte值
     * @Author:zch
     * @CreateDate:16:11 2018/9/23
     * @Param:
     * @return:
     * @Version:1.0
     */
    @Test
    public void byteValue(){
        Integer toByte = new Integer("125");
        byte b1 = toByte.byteValue();
        System.out.println(b1);
    }

    @Test
    public void decode(){
        String str = new String("100");
        int a = Integer.decode(str);
        System.out.println(a);
    }
}
