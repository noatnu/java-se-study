package jdbc.nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.ServerSocketChannel;

/**
 * Created by zhou on 17-12-25.
 */
public class Demo {
    public static void main(String[] args) throws IOException {
        long x = (Long.MAX_VALUE / (1024 * 1024 * 1024));
        System.out.println(x);
        System.out.println(x / 1024);
        System.out.println("Int " + (Integer.MAX_VALUE / (1024 * 1024)) / 1024);
        isit();
    }

    public final static void isit() throws IOException {
        /**
         * ServerSocketChannel 是一个基于通道的 socket 监听器。它同我们所熟悉的 java.net.ServerSocket
         执行相同的基本任务,不过它增加了通道语义,因此能够在非阻塞模式下运行。
         */

        /**
         * 用静态的 open( )工厂方法创建一个新的 ServerSocketChannel 对象,将会返回同一个未绑定的java.net.ServerSocket 关联的通道
         */
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        /*该对等 ServerSocket 可以通过在返回的 ServerSocketChannel 上调
        用 socket( )方法来获取。作为 ServerSocketChannel 的对等体被创建的 ServerSocket 对象依赖通道实现。*/
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(8080));
    }
}
/**
 * DatagramChannel、SocketChannel 和 ServerSocketChannel
 **/