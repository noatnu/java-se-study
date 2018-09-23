package other.io;

import org.testng.annotations.Test;

import java.io.*;

public class ExampleA {

    @Test
    public void init()throws Exception{
        testB(new FileInputStream("D:\\cache\\test\\aab.txt"),new FileOutputStream("D:\\cache\\test\\aab2.txt"));
    }

    @Test
    public void testA(String xx) {
        FileInputStream in = null;
        String pa = "D:\\cache\\test\\aab.txt";
        File file = new File(pa);
        try {
            in = new FileInputStream(file);
            byte[] temp = new byte[24];
            while (in.read(temp) != -1) {
                System.out.println(new String(temp, "UTF-8"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @Description:描述 input读取之后再写入out
     * @Author:zch
     * @CreateDate:15:01 2018/9/23
     * @Param:input,out
     * @Version:1.0
     */
    public void testB(InputStream input, OutputStream out) throws Exception {
        int count = 10;
        byte[] bytes = new byte[count];
        int len = -1;
        while ((len = input.read(bytes)) != -1) {
            out.write(bytes, 0, len);
        }
        input.close();
        out.flush();
        out.close();
    }
}
