package jdbc.encryption_and_decryption.rsa;

import java.io.*;
import java.math.BigInteger;
import java.security.interfaces.RSAPublicKey;

/**
 * Created by zhou on 17-12-15.
 */
public class Encrypt {
    public final static String KEYmi = "Keymi.dat";

    public static void main(String[] args) throws Exception {
        isEncrypt();
    }

    public static void isEncrypt() throws Exception {
        final String str = "hao are you";
        //---------------------读取公匙-------------------------------
        BufferedInputStream bInputS = new BufferedInputStream(new FileInputStream(GenkeyDemo.path+""+GenkeyDemo.PUBLICkey));
        ObjectInputStream in = new ObjectInputStream(bInputS);
        RSAPublicKey publicKey = (RSAPublicKey) in.readObject();
        //-----------------得到公匙的两个重要参数e,n-----------------------
        BigInteger e = publicKey.getPublicExponent();
        BigInteger n = publicKey.getModulus();
        System.out.println("加密密匙e:" + e);
        System.out.println("取模的模数n:" + n);
        //-------------将明文转换为大整数----------------
        byte[] strByte = str.getBytes("UTF-8");
        BigInteger m = new BigInteger(strByte);
        //--------------------------加密处理----------------------------
        BigInteger codeStringBigint = m.modPow(e, n);
        System.out.println("加密后的密文是:" + codeStringBigint);
        //---------------------保存密文-----------------------
        String codeString = codeStringBigint.toString();
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(GenkeyDemo.path + "" + Encrypt.KEYmi));
        out.write(codeString.getBytes("UTF-8"));
        out.flush();
        out.close();
    }
}
