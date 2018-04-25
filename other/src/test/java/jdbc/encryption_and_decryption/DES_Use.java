package jdbc.encryption_and_decryption;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Key;

/**
 * Created by zhou on 17-12-13.
 * DESede加密算法
 */
public class DES_Use {
    public static void main(String[] args) {
        isDESede();
    }

    public static void isDESede(){
        final String DESNAME = "DESede";//这里必须是DESede
        String password = "hello world";
        /*加密密码字符串*/
        String codeEnd = "";
        try {
            /**--------------------------密匙生成器----------------------**/
            KeyGenerator keygen = KeyGenerator.getInstance(DESNAME);
            keygen.init(168);//设置密匙长度为168位
            //---------------------生成密匙----------------------
            SecretKey key = keygen.generateKey();//get 密匙字节码 byte[] keyByte = key.getEncoded();

            ByteArrayOutputStream bs = new ByteArrayOutputStream(1024);
            ObjectOutputStream os = new ObjectOutputStream(bs);
            os.writeObject(key);//写入对象
            byte[] bytes = bs.toByteArray();//写入到内存中(实际中写入到数据库中或者本地磁盘中)
            ByteArrayInputStream is = new ByteArrayInputStream(bytes);//从内存中读取出来(实际中从数据库或者本地读取)
            ObjectInputStream ios  = new ObjectInputStream(is);
            Key key1 = (Key) ios.readObject();//对象恢复
            if (key.equals(key1)) System.out.println("key一致! 密匙恢复成功");

            //---------创建密码器------------------
            Cipher cp = Cipher.getInstance(DESNAME);
            //---------初始化密码器---------------
            cp.init(Cipher.ENCRYPT_MODE,key);

            //------------------加密---------------------
            byte[] codeByteEnd = cp.doFinal(password.getBytes());//get 加密字节码
            System.out.println(new String(codeByteEnd,"UTF-8"));

            //-------------------解密-------------------
            // 重新初始化密码器
            cp.init(Cipher.DECRYPT_MODE,key1);//Cipher.DECRYPT_MODE表示解密
            byte[] decodeByte = cp.doFinal(codeByteEnd);
            System.out.println("解密后的密码:"+new String(decodeByte));
        }catch (Exception ex){
            System.out.println("exception "+ex.getMessage());
        }
    }

}
