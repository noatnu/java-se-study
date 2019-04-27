package tool.encryption;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * @Author noatn
 * @Description
 * @createDate 2018/12/10
 **/
public class DESCoderTest {

    @Test
    public void test() throws Exception {
        String inputStr = "DES";
        String key = DESCoder.initKey();
        System.err.println("原文:\t" + inputStr);

        System.err.println("密钥:\t" + key);

        byte[] inputData = inputStr.getBytes();
        inputData = DESCoder.encrypt(inputData, key);

        System.err.println("加密后:\t" + DESCoder.encryptBASE64(inputData));

        byte[] outputData = DESCoder.decrypt(inputData, key);
        String outputStr = new String(outputData);

        System.err.println("解密后:\t" + outputStr);

        assertEquals(inputStr, outputStr);

        /**
         * DES有很多同胞兄弟，如DESede(TripleDES)、AES、Blowfish、RC2、RC4(ARCFOUR)。这里就不过多阐述了，大同小异，只要换掉ALGORITHM换成对应的值，
         * 同时做一个代码替换SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);就可以了，此外就是密钥长度不同了
         */
    }
}
