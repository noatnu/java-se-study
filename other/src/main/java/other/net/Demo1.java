package other.net;


import tool.log.LogUtilA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Demo1 {
    private static int port = 5209;

    public static void main(String[] args) throws Exception {
        Demo1 demo1 = new Demo1();
        ExecutorService executorService = Executors.newCachedThreadPool();
        Thread servlet = demo1.servlet(port);
        Thread client = demo1.client(port);
        executorService.execute(servlet);
        executorService.execute(client);
    }

    public Thread client(int port) throws Exception {
        Logger logger = LogUtilA.setLoggerHanlder(Level.ALL);
        Thread thread = new Thread(() -> {
            Socket socket = null;
            BufferedReader reader = null;
            try {
//                socket = new Socket("192.168.1.2", 0 + port);
                socket = new Socket("127.0.0.1", 0 + port);
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String s;
                while ((s = reader.readLine()) != null) {
                    logger.info("info:" + s);
                }
            } catch (IOException e) {
                logger.warning("数据读取失败: " + e.getMessage());
            } finally {
                try {
                    reader.close();
                } catch (Exception e) {
                }
            }
        });
        return thread;
    }

    public Thread servlet(int port) throws Exception {
        Logger loggerA = LogUtilA.setLoggerHanlder(Level.ALL);
        Thread thread = new Thread(() -> {
            ServerSocket servlet = null;
            Socket socket = null;
            PrintWriter printWriter = null;
            try {
                servlet = new ServerSocket(0 + port);//指定绑定的端口，并监听此端口。
                loggerA.info("servlet start is success!");
            } catch (IOException e) {
                loggerA.warning("没有启动监听: " + e.getMessage());
            }

            try {
                socket = servlet.accept();//调用accept()方法开始监听，等待客户端的连接

                //获取输入流，并读取客户端信息
                printWriter = new PrintWriter(socket.getOutputStream());
                printWriter.println("现在时间是:" + new Date().toLocaleString());
                for (int i = 0; i < 7; i++) {
                    printWriter.println("time ms==>" + System.currentTimeMillis());
                }
                printWriter.flush();
            } catch (IOException e) {
                loggerA.warning("数据写入失败: " + e.getMessage());
            } finally {
                printWriter.close();
            }
        });
        return thread;
    }
}
