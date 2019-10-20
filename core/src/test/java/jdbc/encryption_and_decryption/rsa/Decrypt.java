package jdbc.encryption_and_decryption.rsa;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.math.BigInteger;
import java.security.interfaces.RSAPrivateCrtKey;

/**
 * Created by zhou on 17-12-15.
 */
public class Decrypt {
    public static void main(String[] args) throws Exception {
        isDecrypt();
    }

    public static void isDecrypt() throws Exception {
        //----------------读取密文------------------------
        File file = new File(GenkeyDemo.path + "" + Encrypt.KEYmi);
        byte[] bytes = new byte[(int) file.length()];
        int b, i = 0;
        BufferedInputStream iSreeam = new BufferedInputStream(new FileInputStream(file));
        iSreeam.read(bytes);
        iSreeam.close();
        BigInteger c = new BigInteger(new String(bytes, "UTF-8"));
        //---------------------得到私匙------------------
        FileInputStream fStream = new FileInputStream(GenkeyDemo.path + "" + GenkeyDemo.PRIVATEKey);
        ObjectInputStream oStream = new ObjectInputStream(fStream);
        RSAPrivateCrtKey privateCrtKey = (RSAPrivateCrtKey) oStream.readObject();
        //------------------------------------得到密匙计算两个重要参数d,n--------------------------
        BigInteger d = privateCrtKey.getPrivateExponent();
        BigInteger n = privateCrtKey.getModulus();
        System.out.println("解密的私匙的指数 d=" + d);
        System.out.println("解密私匙的模 n=" + n);
        //-------------------------解密处理-------------------------
        BigInteger m = c.modPow(d, n);
        byte[] mt = m.toByteArray();
        System.out.println("解密后的明文是:" + new String(mt));
    }
}
