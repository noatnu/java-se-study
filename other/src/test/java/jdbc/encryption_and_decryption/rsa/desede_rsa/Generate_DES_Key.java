package jdbc.encryption_and_decryption.rsa.desede_rsa;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.*;
import java.security.Key;

/**
 * Created by zhou on 17-12-15.
 */
public class Generate_DES_Key {
    public final static String DESede = "DESede";
    public final static int DESedeLendth = 168;
    public final static String path = "/home/zhou/ssh_java/maven/";
    /*DESede密匙*/
    public final static String DESedeFileName = "Generate_DES_Key_key.dat";
    /*用DESede加密过的文件*/
    public final static String DESBussiness = "Generate_DES_Key_bussiness.dat";

    public static void main(String[] args) throws Exception {
//        isCreateGenerate_DES_Key();
        isGenerate_DES_Key();
    }

    /**
     * 银行生成DESede密匙
     * @throws Exception
     */
    public static void isCreateGenerate_DES_Key() throws Exception {
        KeyGenerator kg = KeyGenerator.getInstance(DESede);
        //设置密匙长度为168位
        kg.init(DESedeLendth + 0);
        //生成密匙
        SecretKey key = kg.generateKey();
        byte[] bytes = key.getEncoded();
        File file = new File(path + "" + DESedeFileName);
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
        ObjectOutputStream objStream = new ObjectOutputStream(out);//把DESede的密匙写入文件中
        objStream.writeObject(key);//invalid stream header: 23DA3D9E注意必须用ObjectOutputStream写Object
        out.flush();
        objStream.flush();
        out.close();
        objStream.close();
        System.out.println("DESede的密匙已经写入");
    }

    /**
     * 银行用DESede来加密即将要发送给商户的数据
     * @throws Exception
     */
    public static void isGenerate_DES_Key()throws Exception{
        //从数据文件中读取每日交易数据
        File file = new File(path+""+"ming.txt");
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));//ming.txt交易数据
        byte[] bussinessData = new byte[(int)file.length()];
        in.read(bussinessData);
        in.close();
        //从Generate_DES_Key_key.dat处得到密匙
        BufferedInputStream inKey = new BufferedInputStream(new FileInputStream(path+""+DESedeFileName));
        ObjectInputStream inObjKey = new ObjectInputStream(inKey);
        Key key = (Key) inObjKey.readObject();
        inObjKey.close();
        Cipher cp = Cipher.getInstance(DESede);
        cp.init(Cipher.ENCRYPT_MODE,key);
        //加密每日交易数据文件
        byte[] pText = (new String(bussinessData,"UTF-8")).getBytes("UTF-8");
        byte[] cText = cp.doFinal(pText);//加密
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(path+""+DESBussiness));
        bufferedOutputStream.write(cText);
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
        System.out.println("交易数据已经用"+DESede+"加密");
    }
}
