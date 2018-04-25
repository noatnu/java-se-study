package other.nio.lock;

import org.testng.annotations.Test;
import other.common.LogUtilA;
import other.nio.channel.PathEnum;
import other.nio.channel.URLDemo;
import zch.help.Zhou_String;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileLockDemo {
    private final Logger loggerA = LogUtilA.setLoggerHanlder(Level.ALL);

    @Test
    public void runTest(){
        ExecutorService executorService = Executors.newCachedThreadPool();
//        executorService.execute(isReadable());
        executorService.execute(isWritable());
        executorService.shutdown();
    }


    private Thread isReadable(){
        String path = PathEnum.PATH_ENUM.getVar();
        Thread thread = new Thread(()->{
            while (true){
                try {
                    File file = new File(path+"pcoli.html");
                    FileInputStream inputStream = new FileInputStream(file);
                    FileChannel channel = inputStream.getChannel();
                    ByteBuffer byteBuffer = ByteBuffer.allocateDirect((int)file.length());
                    FileLock fileLock = channel.tryLock(0,(int)file.length(),true);//共享锁
                    channel.read(byteBuffer);
                    fileLock.release();//释放锁
                    inputStream.close();
                    byteBuffer.flip();
                    byte[] bs = new byte[byteBuffer.remaining()];
                    byteBuffer.get(bs,0,byteBuffer.remaining());
                    print(bs);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        return thread;
    }

    private Thread isWritable(){
        String path = PathEnum.PATH_ENUM.getVar();
        Thread thread = new Thread(()->{
            try {
                File file = new File(path+ Zhou_String.toLowerCase(5)+".html");
                FileOutputStream outputStream = new FileOutputStream(file);
                FileChannel fileChannel = outputStream.getChannel();
                FileLock fileLock = fileChannel.lock();//独占锁
                ByteBuffer byteBuffer = ByteBuffer.wrap(URLDemo.getUrlDemo().urlDataTest("http://www.chinadaily.com.cn").getBytes());
                fileChannel.write(byteBuffer);

                fileLock.release();//释放锁
                fileChannel.close();
                outputStream.flush();
                outputStream.close();
            }catch (Exception e){

            }
        });
        return thread;
    }

    private void print(byte[] bs){
        System.out.println(new String(bs).toString());
    }
}
