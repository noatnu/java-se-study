package jdbc.nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 * Created by zhou on 17-12-26.
 */
public class ConnectAsync {
    public static void main(String[] args) throws IOException{
        isit();
    }
    public static void isit(String... args) throws IOException {
        String host = "localhost";
        int port = 8080;
        if (args.length == 2) {
            host = args[0];
            port = Integer.parseInt(args[1]);
        }
        InetSocketAddress addr = new InetSocketAddress(host, port);
        SocketChannel sc = SocketChannel.open();
        sc.configureBlocking(false);
        System.out.println("initiating connection");
        sc.connect(addr);
        /**如果尝试异步连接失败,那么下次调用 finishConnect( )方法会产生一个适当的经检查的异常以
         指出问题的性质。通道然后就会被关闭并将不能被连接或再次使用。*/
        while (!sc.finishConnect()) {
            doSomethingUseful();
        }
        System.out.println("connection established");
        // Do something with the connected socket
        // The SocketChannel is still nonblocking
        sc.close();
    }

    private static void doSomethingUseful() {
        System.out.println("doing something useless");
    }
}
