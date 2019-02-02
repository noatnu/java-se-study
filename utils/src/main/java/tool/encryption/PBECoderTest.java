package tool.encryption;


import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @Author noatn
 * @Description
 * @createDate 2018/12/10
 **/
public class PBECoderTest {


    @Test
    public void test() throws Exception {
        String inputStr = "abc";
        System.err.println("原文: " + inputStr);
        byte[] input = inputStr.getBytes();

        String pwd = "efg";
        System.err.println("密码: " + pwd);

        byte[] salt = PBECoder.initSalt();

        byte[] data = PBECoder.encrypt(input, pwd, salt);

        System.err.println("加密后: " + PBECoder.encryptBASE64(data));

        byte[] output = PBECoder.decrypt(data, pwd, salt);
        String outputStr = new String(output);

        System.err.println("解密后: " + outputStr);
        assertEquals(inputStr, outputStr);
    }

}
