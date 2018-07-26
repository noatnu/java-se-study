package other.nio.channel;

import help.Zhou_String;
import org.testng.annotations.Test;
import util.log.LogUtilA;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UseChannel {
    private final Logger loggerA = LogUtilA.setLoggerHanlder(Level.ALL);
    String path = PathEnum.PATH_ENUM.getVar();

    @Test
    public void readableByte(){
        try {
            File file = new File(path+"see.txt");
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect((int)file.length());
            loggerA.info(print(byteBuffer));
            FileInputStream inputStream = new FileInputStream(file);
            ReadableByteChannel readableByteChannel = inputStream.getChannel();
            readableByteChannel.read(byteBuffer);

            inputStream.close();
            readableByteChannel.close();

            loggerA.info(print(byteBuffer));
            byteBuffer.limit(byteBuffer.position()).position(0);//翻转

            byte[] bs = new byte[byteBuffer.remaining()];
            byteBuffer.get(bs,0,byteBuffer.remaining());
            print(bs);
        }catch (IOException e){
            loggerA.warning(""+e.getMessage());
        }
    }

    @Test
    public void writableByte(){
        byte[] bs = URLDemo.getUrlDemo().urlDataTest("http://www.chinadaily.com.cn").getBytes();
//        byte[] bs = Zhou_String.toLowerCase(100).getBytes();
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(bs.length);
        loggerA.info(print(byteBuffer));
        byteBuffer.put(bs,0,byteBuffer.limit());
        loggerA.info(print(byteBuffer));
        byteBuffer.flip();
        loggerA.info(print(byteBuffer));
        File file = new File(path+ Zhou_String.toLowerCase(5)+".html");
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            WritableByteChannel writableByteChannel = outputStream.getChannel();
            writableByteChannel.write(byteBuffer);
            outputStream.flush();
            outputStream.close();
            writableByteChannel.close();
            loggerA.info(print(byteBuffer));
            loggerA.info("success!");
        }catch (Exception e){
            loggerA.warning(""+e.getMessage());
        }
    }

    @Test
    public void copy(){
        File file = new File(path+"pcoli.html");
        try {
            FileOutputStream outputStream = new FileOutputStream(path+"bababa.html");
            FileInputStream inputStream = new FileInputStream(file);
            ReadableByteChannel readableByteChannel = inputStream.getChannel();
            WritableByteChannel writableByteChannel = outputStream.getChannel();
            channelCopyA(writableByteChannel,readableByteChannel);
            outputStream.close();
            inputStream.close();
        }catch (Exception e){
            loggerA.warning(""+e.getMessage());
            e.printStackTrace();
        }
    }

    private final void channelCopyA(WritableByteChannel writableByteChannel,ReadableByteChannel readableByteChannel){
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024*16);
        try {
            while ((readableByteChannel.read(byteBuffer))!=-1){
                byteBuffer.limit(byteBuffer.position()).position(0);
                writableByteChannel.write(byteBuffer);
                byteBuffer.compact();//压缩
            }

            byteBuffer.flip();
            while (byteBuffer.hasRemaining())writableByteChannel.write(byteBuffer);
        }catch (IOException e){
            loggerA.warning(""+e.getMessage());
        }
    }

    private String print(Buffer buffer){
        StringBuilder builder = new StringBuilder(1024);
        builder.append("缓冲区的容量:"+ buffer.capacity()+" 缓冲区的限制:"+ buffer.limit());
        builder.append(" 缓冲区的位置:"+ buffer.position());
        return builder.toString();
    }

    private void print(byte[] bs){
        loggerA.info(new String(bs));
    }

}
