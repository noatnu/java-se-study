package jdbc.encryption_and_decryption.rsa.desede_rsa;

import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * Created by zhou on 17-12-15.
 */
public class Enc_RSA {
    /*RSA公匙  (商户)*/
    public final static String RSA_PUBLIC_FileName = "Enc_RSA_public.dat";
    /*RSA私匙  (商户)*/
    public final static String RSA_PRIVATE_FileName = "Enc_RSA_private.dat";
    /*用RSA公匙对DES密匙加密的文件 (银行) */
    public final static String ENC_RSA = "ENC_RSA.dat";

    /*商户解密得到的DESede密匙 和 DESedeFileName一致*/
    public final static String Dec_RSA = "Dec_RSA.dat";

    public final static String RSA = "RSA";

    public static void main(String[] args) throws Exception {
//        isCreateRSA();
//        isEnc_RSA();
        isDec_RSA();
    }

    /**
     * 创建RSA的公匙和密匙  (商户)
     *
     * @throws Exception
     */
    public static void isCreateRSA() throws Exception {
        //-------------------创建密匙对生成器-----------
        KeyPairGenerator kpg = KeyPairGenerator.getInstance(RSA);
        //--------------初始化密匙生成器---------------------------
        kpg.initialize(1024);//长度1024
        //---------------------生成密匙对-----------------------
        KeyPair kp = kpg.genKeyPair();
        //---------------获取公匙和私匙------------------------
        PublicKey publicKey = kp.getPublic();
        PrivateKey privateKey = kp.getPrivate();
        //---------------------保存公匙到文件-------------
        BufferedOutputStream bStream = new BufferedOutputStream(new FileOutputStream(Generate_DES_Key.path + "" + RSA_PUBLIC_FileName));
        ObjectOutputStream out = new ObjectOutputStream(bStream);
        out.writeObject(publicKey);
        bStream.flush();
        out.flush();
        bStream.close();
        out.close();
        bStream = null;
        out = null;
        //保存私匙到文件
        bStream = new BufferedOutputStream(new FileOutputStream(Generate_DES_Key.path + "" + RSA_PRIVATE_FileName));
        out = new ObjectOutputStream(bStream);
        out.writeObject(privateKey);
        bStream.flush();
        out.flush();
        bStream.close();
        out.close();
        System.out.println("end!");
    }

    /**
     * 利用RSA的公匙对DESede密匙进行加密
     *
     * @throws Exception
     */
    public static void isEnc_RSA() throws Exception {
        //得到商户的RSA公匙
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(Generate_DES_Key.path + "" + Enc_RSA.RSA_PUBLIC_FileName));
        ObjectInputStream inS = new ObjectInputStream(in);
        RSAPublicKey publicKey = (RSAPublicKey) inS.readObject();
        BigInteger e = publicKey.getPublicExponent();
        BigInteger n = publicKey.getModulus();
        in.close();
        inS.close();
        in = null;
        //得到DESede密匙
        File file = new File(Generate_DES_Key.path + "" + Generate_DES_Key.DESedeFileName);
        in = new BufferedInputStream(new FileInputStream(file));
        byte[] bytes = new byte[(int) file.length()];
        in.read(bytes);
        in.close();
        //用商户公匙对DES密匙加密
        BigInteger m = new BigInteger(bytes);
        BigInteger c = m.modPow(e, n);
        System.out.println("c:" + c);
        String cs = c.toString();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Generate_DES_Key.path + "" + ENC_RSA)));
        writer.write(cs, 0, cs.length());
        writer.flush();
        writer.close();
    }

    /**
     * 商户对得到的DESede密匙解密(银行由商户提供的公匙加密DESede密匙且此密匙就是发送过来的密文的密匙)
     *
     * @throws Exception
     */
    public static void isDec_RSA() throws Exception {
        //商户通过自己的RSA私匙对（DESede密文）解密
        File file = new File(Generate_DES_Key.path + "" + ENC_RSA);
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
        byte[] bytes = new byte[(int) file.length()];
        in.read(bytes);
        in.close();
        BigInteger c = new BigInteger(new String(bytes, "UTF-8").toString());
        //得到商户自己的密匙
        ObjectInputStream b = new ObjectInputStream(new FileInputStream(Generate_DES_Key.path + "" + RSA_PRIVATE_FileName));
        RSAPrivateKey privateKey = (RSAPrivateKey) b.readObject();
        b.close();
        BigInteger d = privateKey.getPrivateExponent();
        BigInteger n = privateKey.getModulus();
        //用RSA私匙解密得到DESede密匙
        BigInteger m = c.modPow(d, n);
        System.out.println("m:" + m);
        byte[] mt = m.toByteArray();
        byte[] buf = new byte[1024];
        ByteArrayInputStream byteArray = new ByteArrayInputStream(buf);
        byteArray.read(mt);
        ObjectInputStream isT = new ObjectInputStream(byteArray);
        Key key = (Key) isT.readObject();
        System.out.println("测试:" + key.toString());
        BufferedOutputStream oStream = new BufferedOutputStream(new FileOutputStream(Generate_DES_Key.path + "" + Dec_RSA));
        //为商户保存解密得到的DESede密匙
        oStream.write(mt);
        oStream.flush();
        oStream.close();
    }
}
