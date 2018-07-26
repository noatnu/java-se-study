package other.nio.channel;

import org.testng.annotations.Test;
import util.log.LogUtilA;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreateChannel {
    private final Logger loggerA = LogUtilA.setLoggerHanlder(Level.ALL);
    String path = PathEnum.PATH_ENUM.getVar();

    //通道的创建

    @Test
    public void createFileReadChannel(){
        try {
            File file = new File(path+"cache/panda.jpeg");
            FileInputStream inputStream = new FileInputStream(file);
            ReadableByteChannel readableByteChannel = inputStream.getChannel();
            loggerA.info(readableByteChannel.isOpen()+"");
        }catch (IOException e){
            loggerA.warning(""+e.getMessage());
        }
    }

    @Test
    public void createFileWriteChannel(){
        try {
            File file = new File(path+"cache/bibi.txt");
            FileOutputStream outputStream = new FileOutputStream(file);
            WritableByteChannel writableByteChannel = outputStream.getChannel();
            loggerA.info(writableByteChannel.isOpen()+"");
        }catch (IOException e){
            loggerA.warning(""+e.getMessage());
        }
    }

    @Test
    public void createFileChannel(){
        try {
            File file = new File(path+"cache/bibiA.txt");
            RandomAccessFile accessFile = new RandomAccessFile(file,"rw");
            FileChannel fileChannel = accessFile.getChannel();
            loggerA.info(""+fileChannel.isOpen());
        }catch (IOException e){
            loggerA.warning(""+e.getMessage());
        }
    }

    @Test
    public void createSocketChannel(){
        try {
            ServerSocket serverSocket = new ServerSocket(0+8090);
            Socket socket = new Socket("127.0.0.1",5209);
            SocketChannel socketChannel = socket.getChannel();
            loggerA.info("is "+socketChannel.isOpen());
        }catch (Exception e){
            loggerA.warning(""+e.getMessage());
        }
    }
}
