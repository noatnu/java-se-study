package jdbc.encryption_and_decryption;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.security.Key;
import java.util.HashMap;

/**
 * Created by zhou on 17-12-14.
 */
public class EncryptAndDecrypt {
    public static void main(String[] args) throws Exception {
        final HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("jiami", "encrypt");
        map.put("jiemi", "decrypt");
        String[] strings = {"ming.txt", (String) map.get("jiami")};
        isENcrypt(strings);
    }

    public static void isENcrypt(String... args) throws Exception {
        final String DESede = "DESede";
        final String filepath = "/home/zhou/ssh_java/maven/";
        //要做加密或者解密处理的文件名
        String dataFileName = args[0];
        //是加密还是解密,是加密则为"encrypt",是解密则为"decrypt"
        String opMode = args[1];
        //密匙存放的文件名
        String keyFileName = "key1.txt";
        //--------------生成密匙-----------------------
        FileInputStream keyFis = new FileInputStream(filepath + keyFileName);
        ObjectInputStream oIs = new ObjectInputStream(keyFis);
        Key key = (Key) oIs.readObject();
        //---------------------创建并初始化密码器---------------------
        Cipher cp = Cipher.getInstance(DESede);
        if (opMode.equalsIgnoreCase("encrypt")) {
            cp.init(Cipher.ENCRYPT_MODE, key);
        } else if (opMode.equalsIgnoreCase("decrypt")) {
            cp.init(Cipher.DECRYPT_MODE, key);
        } else {
            return;
        }
        //-------------------创建CipherInputStream对象-----------------------
        FileInputStream dataFis = new FileInputStream(filepath + dataFileName);
        CipherInputStream cis = new CipherInputStream(dataFis, cp);
        //--------------------如果是加密操作-----------------------
        if (opMode.equalsIgnoreCase("encrypt")) {
            //---------------创建文件输出流-------------------------
            FileOutputStream fos = new FileOutputStream(filepath + "mi.txt");
            int b;
            System.out.print("加密后的密文是:");
            while ((b = cis.read()) != -1) {
                System.out.println((char) b);
                fos.write(b);
            }
            cis.close();
            fos.flush();
            fos.close();
            System.out.println("密文已经写入到文件mi.txt中");
        }
        //---------------------如果是解密操作-------------------------
        if (opMode.equalsIgnoreCase("decrypt")) {
            int b = 0;
            System.out.print("解密后的密文是:");
            while ((b = cis.read()) != -1) {
                System.out.println((char) b);
            }
        }

    }
}
