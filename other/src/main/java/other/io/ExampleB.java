package other.io;

import org.testng.annotations.Test;

import java.io.*;
import java.util.Enumeration;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * @Author zch
 * @Description 文件压缩与解压
 * @createDate 2018/10/9
 **/
public class ExampleB {

    @Test
    public void testA(){
        File[] files = {new File("E:\\doc\\test\\0d018.xlsx"),new File("E:\\doc\\test\\HouseExample.xlsx")};
        File zip = new File("E:\\doc\\zip\\"+ UUID.randomUUID().toString().substring(0,5)+".zip");
        try {
            zipFiles(files,zip);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testB(){
        try {
            unZipFiles(new File("E:\\doc\\zip\\e3140.zip"),"E:\\doc\\unzip\\");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 功能:解压缩
     * @param zipFile：需要解压缩的文件
     * @param descDir：解压后的目标目录
     */
    public static void unZipFiles(File zipFile,String descDir)throws Exception{
        ZipFile zf = new ZipFile(zipFile);
        for (Enumeration entries = zf.entries(); entries.hasMoreElements();){
            ZipEntry zipEntry = (ZipEntry)entries.nextElement();
            String entryName = zipEntry.getName();
            InputStream in = zf.getInputStream(zipEntry);
            OutputStream out = new FileOutputStream(descDir+entryName);
            byte[] bytes = new byte[1024];
            int len ;
            while ((len=in.read(bytes))> 0){
                out.write(bytes,0,len);
            }
            in.close();
            out.close();
        }
    }

    /**
     * 功能:压缩多个文件成一个zip文件
     *
     * @param srcFile：源文件列表
     * @param zipFile：压缩后的文件
     */
    public static void zipFiles(File[] srcFile, File zipFile)throws Exception {
        byte[] buf = new byte[1024];
        //ZipOutputStream类：完成文件或文件夹的压缩
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFile));
        for (int i = 0; i < srcFile.length; i++) {
            FileInputStream in = new FileInputStream(srcFile[i]);
            out.putNextEntry(new ZipEntry(srcFile[i].getName()));
            int len ;
            while ((len=in.read(buf))> 0){
                out.write(buf,0,len);
            }
            out.closeEntry();
            in.close();
        }
        out.close();
    }
}
