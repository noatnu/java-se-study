package jdbc.nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by zhou on 17-12-27.
 */
public class Demo1 {
    public static void main(String[] args) throws IOException {
        isit();
    }

    public static void isit() throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);

        /**
         * 为了接收连接，我们需要一个 ServerSocketChannel。事实上，我们要监听的每一个端口都需要有一个 ServerSocketChannel 。
         * 对于每一个端口，我们打开一个 ServerSocketChannel
         */
        ServerSocket serverSocket = serverSocketChannel.socket();
        InetSocketAddress address = new InetSocketAddress(8090);
        serverSocket.bind(address);
        System.out.println("--------------->");

        /*register() 的第一个参数总是这个 Selector。第二个参数是 OP_ACCEPT，这里它指定我们想要监听 accept 事件，也就是在新的连接建立时所发生的事件。
        这是适用于 ServerSocketChannel 的唯一事件类型。*/
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        int num = selector.select();
        System.out.println(num);
        Set<SelectionKey> selectedKeys = selector.selectedKeys();
        Iterator<SelectionKey> it = selectedKeys.iterator();
        while (it.hasNext()){
            SelectionKey key = it.next();
            System.out.println(key.readyOps());
        }
        System.out.println("end!");
    }
}
