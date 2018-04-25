package jdbc.nio.channels;


import zch.help.Zhou_String;
import zch.help.Zhou_Word;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.WritableByteChannel;

/**
 * Created by zhou on 17-12-24.
 */
public class FileLockDemo {
    private final int size = 10;//
    private final long time = 2000;
    protected final String NAME_LOCK = "lockFileLockDemo.txt";

    public static void main(String[] args) throws IOException {
        FileLockDemo lockDemo = new FileLockDemo();
        Runnable runnable = lockDemo.isWriteLock();
        Thread thread = new Thread(runnable, "线程1");
//        thread.start();
        Thread t1 = new Thread(lockDemo.isReadable(),"xx");
        Thread t2 = new Thread(lockDemo.isReadable(),"yy");
//        t1.start();
//        t2.start();
        for (int i = 0; i < 10; i++) {
            new Thread(lockDemo.isReadable(), Zhou_String.toLowerCase(4)).start();
        }
    }

    public final Runnable isReadable(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    File file = new File(DocDemo.path + NAME_LOCK);
                    FileInputStream in = new FileInputStream(file);
                    FileChannel channel = in.getChannel();
                    FileLock fileLock;
//                    fileLock = channel.lock(0,1000,false);//如果是独占锁则没有一个能获取到锁
                    fileLock = channel.tryLock(0,1000,true);//共享锁,则可以有一个能捕获到IO锁
//                    fileLock = channel.lock(0,Long.MAX_VALUE,true);//不能这样设置
                    ByteBuffer buffer = ByteBuffer.allocate(100);
                    WritableByteChannel writableByteChannel = Channels.newChannel(System.out);
                    while ((channel.read(buffer))!=-1){
                        buffer.flip();
                        writableByteChannel.write(buffer);
                        if (buffer.hasRemaining())writableByteChannel.write(buffer);
                        buffer.clear();
                        Thread.sleep(time);
                        System.out.println("休眠中!");
                        fileLock.release();
                    }
                }catch (Exception ex){
                    System.out.println("Exception "+ex.getMessage());
                    ex.printStackTrace();
                }
            }
        };
        return runnable;
    }

    public final Runnable isWriteLock() {
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                try {
                    File file = new File(DocDemo.path + NAME_LOCK);
                    FileOutputStream output = new FileOutputStream(file, false);
                    FileChannel channel = output.getChannel();
                    FileLock fileLock = channel.lock();//加锁,并且是独占锁
                    /*上面的lock()实际和这个一致channel.lock(0L,Long.MAX_VALUE,false)*/
                    ByteBuffer buffer = ByteBuffer.allocate(1024 * 10);
                    long start = System.currentTimeMillis();
                    for (int i = 0; i < size; i++) {
                        buffer.put(
                                (Zhou_String.toOther(9)+"    "+Zhou_Word.getEnglishName() + " " + Zhou_Word.getChineseName_Random() + "\n"
                                ).getBytes("UTF-8"));
                        buffer.flip();
                        channel.write(buffer);
                        while (buffer.hasRemaining()) {//漏网之鱼
                            channel.write(buffer);
                        }
                        buffer.clear();
                    }
                    long end = (System.currentTimeMillis()-start)/1000;
                    System.out.println("write is time: "+end+" ;开始休眠!");
                    Thread.sleep(time);
                    fileLock.release();//释放锁
                    System.out.println("put data is end!");
                } catch (IOException ex) {
                    System.out.println("IO ERROR" + ex.getMessage());
                } catch (InterruptedException e) {
                    System.out.println("thread 中断 " + e.getMessage());
                }
            }
        };
        return runnable1;
    }
}
