package other.io;

import org.testng.annotations.Test;

import java.io.*;

public class ExampleA {

    @Test
    public void init() throws Exception {
        testB(new FileInputStream("D:\\cache\\test\\aab.txt"), new FileOutputStream("D:\\cache\\test\\aab2.txt"));
        testC("D:\\cache\\test\\联系信息.docx", "D:\\cache\\test\\联系信息3.docx");
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
        int count = 5;
        byte[] bytes = new byte[count];
        int len = -1;
        while ((len = input.read(bytes)) != -1) {
            out.write(bytes, 0, len);
        }
        input.close();
        out.flush();
        out.close();
    }

    public void testC(String input, String output) throws Exception {
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        inputStream = new FileInputStream(input);
        outputStream = new FileOutputStream(output);
        byte[] bytes = new byte[4];
        while ((inputStream.read(bytes, 0, bytes.length)) != -1) {
            outputStream.write(bytes, 0, bytes.length);
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }
}
