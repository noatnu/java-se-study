package tool.other;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by kings on 2018-6-5.
 */
public class FileUtils {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static FileUtils fileUtils = new FileUtils();


    /**
     * 传入 字节流和文件名称
     * @param fileName
     * @param bodys
     * @return
     */
    public ResponseEntity<byte[]> createResponse(String fileName, byte[] bodys) {
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<byte[]> responseEntity = null;
        String downloadFileName = null;
        try {
            downloadFileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
        } catch (UnsupportedEncodingException e) {
            logger.error("抛出异常:" + e.getMessage());
        }
        //解决下载显示的文件名问题
        headers.setContentDispositionFormData("attachment", downloadFileName);
        //二进制流数据(最常见的文件下载)
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        responseEntity = new ResponseEntity<byte[]>(bodys, headers, HttpStatus.CREATED);
        return responseEntity;
    }

    /**
     *
     * @param zipPathAndName 压缩成的文件路径
     * @return 返回一个压缩后的字节流
     * @throws IOException
     */
    public static byte[] getZipFile(List<File> fileList, String zipPathAndName)throws IOException{
        File zipFile = new File(zipPathAndName);
        // 文件输出流
        if (!zipFile.exists()){
        }
        FileOutputStream outputStream = new FileOutputStream(zipPathAndName);
        // 压缩流
        ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);

        int size = fileList.size();
        // 压缩列表中的文件
        for (int i = 0; i < size; i++) {
            File file = fileList.get(i);
            zipFile(file, zipOutputStream);
        }
        zipOutputStream.flush();
        // 关闭压缩流、文件流
        zipOutputStream.close();
        outputStream.close();
        return org.apache.commons.io.FileUtils.readFileToByteArray(zipFile);
    }

    /**
     * 将文件数据写入文件压缩流
     *
     * @param file            带压缩文件
     * @param zipOutputStream 压缩文件流
     * @throws IOException
     */
    private static void zipFile(File file, ZipOutputStream zipOutputStream)throws IOException{
        if (file.exists()) {
            if (file.isFile()) {
                FileInputStream fis = new FileInputStream(file);
                BufferedInputStream bis = new BufferedInputStream(fis);
                ZipEntry entry = new ZipEntry(file.getName());
                zipOutputStream.putNextEntry(entry);

                final int MAX_BYTE = 10 * 1024 * 1024; // 最大流为10MB
                long streamTotal = 0; // 接收流的容量
                int streamNum = 0; // 需要分开的流数目
                int leaveByte = 0; // 文件剩下的字符数
                byte[] buffer; // byte数据接受文件的数据

                streamTotal = bis.available(); // 获取流的最大字符数
                streamNum = (int) Math.floor(streamTotal / MAX_BYTE);
                leaveByte = (int) (streamTotal % MAX_BYTE);

                if (streamNum > 0) {
                    for (int i = 0; i < streamNum; i++) {
                        buffer = new byte[MAX_BYTE];
                        bis.read(buffer, 0, MAX_BYTE);
                        zipOutputStream.write(buffer, 0, MAX_BYTE);
                    }
                }

                // 写入剩下的流数据
                buffer = new byte[leaveByte];
                bis.read(buffer, 0, leaveByte); // 读入流
                zipOutputStream.write(buffer, 0, leaveByte); // 写入流
                zipOutputStream.closeEntry(); // 关闭当前的zip entry

                // 关闭输入流
                bis.close();
                fis.close();
            }
        }
    }



    public static FileUtils getFileUtils() {
        return fileUtils;
    }

    private FileUtils() {
    }
}
