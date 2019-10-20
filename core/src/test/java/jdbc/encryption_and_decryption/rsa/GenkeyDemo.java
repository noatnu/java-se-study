package jdbc.encryption_and_decryption.rsa;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * Created by zhou on 17-12-15.
 */
public class GenkeyDemo {
    public final static String RSA = "RSA";
    public final static String PUBLICkey = "publicKey.dat";
    public final static String PRIVATEKey = "privateKey.dat";
    public static String path = "/home/zhou/ssh_java/maven/";

    public static void main(String[] args) throws Exception {
        isGenkey();
        System.out.println("over!");
    }

    public static void isGenkey() throws Exception {
        //-------------------创建密匙对生成器-----------
        KeyPairGenerator kpg = KeyPairGenerator.getInstance(RSA);
        //--------------初始化密匙生成器---------------------------
        kpg.initialize(1024);
        //---------------------生成密匙对-----------------------
        KeyPair kp = kpg.genKeyPair();
        //---------------获取公匙和私匙------------------------
        PublicKey publicKey = kp.getPublic();
        PrivateKey privateKey = kp.getPrivate();
        //---------------------保存公匙到文件-------------
        BufferedOutputStream bStream = new BufferedOutputStream(new FileOutputStream(path + "" + PUBLICkey));
        ObjectOutputStream out = new ObjectOutputStream(bStream);
        ByteArrayOutputStream in = new ByteArrayOutputStream(1024);

        out.writeObject(publicKey);
        bStream.flush();
        bStream.close();
        out.flush();
        out.close();
        bStream = null;
        out = null;
        //保存私匙到文件
        bStream = new BufferedOutputStream(new FileOutputStream(path + "" + PRIVATEKey));
        out = new ObjectOutputStream(bStream);
        out.writeObject(privateKey);
        bStream.flush();
        bStream.close();
        out.flush();
        out.close();
    }
}
