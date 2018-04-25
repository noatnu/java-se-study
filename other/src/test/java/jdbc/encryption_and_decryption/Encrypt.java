package jdbc.encryption_and_decryption;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Random;
import java.util.UUID;

/**
 * Created by zhou on 17-12-13.
 * 基于密码密匙的加密和解密
 */
public class Encrypt {
    private final static String PBE = "PBEWithMD5AndDES";//固定,获取jdk中的实例
    private final static int saltSIZE = 8;//盐的固定字节
    private final static int EACHSIZE = 1000;//密码器迭代次数（非固定）

    public static void main(String[] args) {
        final String passSalt = UUID.randomUUID().toString();
        isEncrypt(passSalt);
        System.out.println("自定义密码密匙:"+passSalt);
        isDecrypt(passSalt);
    }

    /**
     * passSalt 密码密匙
     *
     * @param passSalt
     */
    public static void isEncrypt(String passSalt) {
        String str = "hao are you";
        try {
            //-------得到用于加密的'密码密匙'--------------
            char[] passwordString = passSalt.toCharArray();
            PBEKeySpec PBKs = new PBEKeySpec(passwordString);
            //--------------由密码密匙生成密匙------------------
            SecretKeyFactory SeFac = SecretKeyFactory.getInstance(PBE);
            SecretKey key = SeFac.generateSecret(PBKs);
            //------------生成盐---------------
            byte[] saltByte = new byte[saltSIZE];
            Random randomData = new Random();
            //Random提供的method nextBytes()提供随机字节并且填充到byte数组
            randomData.nextBytes(saltByte);
            //-----------------创建并初始化密码器---------------------
            Cipher cp = Cipher.getInstance(PBE);
            PBEParameterSpec pbeps = new PBEParameterSpec(saltByte, EACHSIZE);// 迭代计数1000次
            cp.init(Cipher.ENCRYPT_MODE, key, pbeps);
            //-------------加密---------------------
            byte[] encodeString = cp.doFinal(str.getBytes("UTF-8"));
            //--------------------把加密的结果和盐写入到文件中--------------
            FileOutputStream fos = new FileOutputStream("/home/zhou/ssh_java/maven/Encrypt_saltByte.txt");
            fos.write(saltByte);
            fos.flush();
            fos.close();
            FileOutputStream fosX = new FileOutputStream("/home/zhou/ssh_java/maven/Encrypt_encodeString.txt");
            fosX.write(encodeString);
            fosX.flush();
            fosX.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * passSalt 密码密匙
     *
     * @param passSalt
     */
    public static void isDecrypt(String passSalt) {
        try {
            char[] passwordString = passSalt.toCharArray();
            PBEKeySpec pbks = new PBEKeySpec(passwordString);
            //----------------由密码密匙生成密匙--------------
            SecretKeyFactory SeFac = SecretKeyFactory.getInstance(PBE);
            SecretKey key = SeFac.generateSecret(pbks);
            //从文件流处得到加密的密文和盐
            File file1 = new File("/home/zhou/ssh_java/maven/Encrypt_saltByte.txt");
            File file2 = new File("/home/zhou/ssh_java/maven/Encrypt_encodeString.txt");
            BufferedInputStream inSalt = new BufferedInputStream(new FileInputStream(file1),1024);
            BufferedInputStream inPass = new BufferedInputStream(new FileInputStream(file2),1024);
            byte[] saltByte = new byte[(int) file1.length()];
            byte[] passByte = new byte[(int) file2.length()];
            inSalt.read(saltByte);
            inPass.read(passByte);
            inSalt.close();
            inPass.close();

            //---------创建并且初始化密码器---------------------
            Cipher cp = Cipher.getInstance(PBE);
            PBEParameterSpec pbeps = new PBEParameterSpec(saltByte,EACHSIZE);// 迭代计数1000次
            cp.init(Cipher.DECRYPT_MODE,key,pbeps);
            //-------------------解密--------------------
            byte[] strByte = cp.doFinal(passByte);
            System.out.println(new String(strByte));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
