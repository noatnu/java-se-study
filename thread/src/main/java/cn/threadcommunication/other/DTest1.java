package cn.threadcommunication.other;

public class DTest1 {
    public static void main(String[] args) {
        try {
            String nString = new String();
            nString.wait();
        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
}
