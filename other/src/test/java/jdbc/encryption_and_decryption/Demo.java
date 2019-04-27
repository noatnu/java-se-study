package jdbc.encryption_and_decryption;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by zhou on 17-12-14.
 */
public class Demo {
    public static void main(String[] args) throws IOException {
        BufferedInputStream inSalt = new BufferedInputStream(new FileInputStream("/home/zhou/ssh_java/maven/Encrypt_saltByte.txt"));
        byte[] saltByte = new byte[inSalt.available()];
        if ((inSalt.read(saltByte)) != -1) {
        }
        System.out.println(new String(saltByte));

        isN();

        isM();

        isK();
    }

    public static void isN() throws IOException {
        File file = new File("/home/zhou/ssh_java/maven/Encrypt_saltByte.txt");
        BufferedInputStream inSalt = new BufferedInputStream(new FileInputStream(file), 1024);//加一个缓冲区,不然就没必要用这个了
        byte[] saltByte = new byte[(int) file.length()];
        if (inSalt.available() != 0) {//这也可以写inSalt.available()!=0 inSalt.available()>0
            inSalt.read(saltByte);
        }
        inSalt.close();
        System.out.println(new String(saltByte));
    }

    public static void isM() throws IOException {
        File file = new File("/home/zhou/ssh_java/maven/Encrypt_saltByte.txt");
        BufferedInputStream inSalt = new BufferedInputStream(new FileInputStream(file), 1024);//加一个缓冲区,不然就没必要用这个了
        byte[] saltByte = new byte[(int) file.length()];
        int count = 0;
        int b;
        while ((b = inSalt.read()) != -1) {
            saltByte[count++] = (byte) b;
        }
        inSalt.close();
        System.out.println(new String(saltByte));
    }

    public static void isK() throws IOException {
        File file = new File("/home/zhou/ssh_java/maven/Encrypt_saltByte.txt");
        BufferedInputStream inSalt = new BufferedInputStream(new FileInputStream(file), 1024);//加一个缓冲区,不然就没
        byte[] saltByte = new byte[(int) file.length()];
        inSalt.read(saltByte);
        inSalt.close();
        System.out.println(new String(saltByte));
    }
}
