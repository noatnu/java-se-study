package cn.threadcommunication.c1;

public class INITData {
    private String flag = "1";

    public void isA(Object lockA){
        try {
            synchronized (lockA){
                if (getFlag().equals("0")){
                    lockA.wait();
                }
                System.out.println("create data is "+System.currentTimeMillis());
                setFlag("0");
                lockA.notifyAll();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void isB(Object lockB){
        try {
            synchronized (lockB){
                if (getFlag().equals("1")){
                    lockB.wait();
                }
                System.out.println("remove data is "+System.currentTimeMillis());
                setFlag("1");
                lockB.notifyAll();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
