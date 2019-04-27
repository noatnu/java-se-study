package cn.threadcommunication.p_r.test;

public class RUNDemo {
    public static void main(String[] args) {
        try {
            String lock = new String();
            Consumer c = new Consumer(lock);
            Producer p = new Producer(lock);
            ThreadP threadP = new ThreadP(p);
            ThreadC threadC = new ThreadC(c);

            threadP.start();
            threadC.start();
        } catch (Exception e) {

        }
    }
}
