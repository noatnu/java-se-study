package cn.threadcommunication.other;

public class DTest2 {
    public static void main(String[] args) {
        try {
            String lock = new String();
            System.out.println("进入synchronized method");
            synchronized(lock){
                System.out.println("第一行");
                lock.wait();
                System.out.println("wait()下的代码!");
            }
        }catch (InterruptedException e){
            System.out.println("异常!"+e.getMessage());
        }
        /**
         * 打印结果
         进入synchronized method
         第一行
            ...永远被阻塞
         */
    }
}