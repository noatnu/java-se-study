package jdbc.encryption_and_decryption.rsa.desede_rsa;

import javax.crypto.Cipher;
import java.io.*;
import java.security.Key;

/**
 * Created by zhou on 17-12-15.
 */
public class DEC_DESede {

    public static void main(String[] args)throws Exception {
        isDEC_DESede();
    }

    /**
     * 商户用DESede密匙解密数据得到银行提供给它的真实数据
     * @throws Exception
     */
    public static void  isDEC_DESede()throws Exception{
        //得到用DESede加密后的密文数据
        File file = new File(Generate_DES_Key.path+""+Generate_DES_Key.DESBussiness);
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
        byte[] bytes = new byte[(int)file.length()];
        in.read(bytes);
        in.close();
        //得到DESede密匙
        in = null;
        in = new BufferedInputStream(new FileInputStream(Generate_DES_Key.path+Enc_RSA.Dec_RSA));
        ObjectInputStream inStream = new ObjectInputStream(in);
        Key key = (Key) inStream.readObject();
        Cipher cp = Cipher.getInstance(Generate_DES_Key.DESede);
        cp.init(Cipher.DECRYPT_MODE,key);
        byte[] pText = cp.doFinal(bytes);
        System.out.println("银行传输过来的数据:"+new String(pText,"UTF-8"));
    }
}
