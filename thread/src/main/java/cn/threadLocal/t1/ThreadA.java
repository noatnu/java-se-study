package cn.threadLocal.t1;

import cn.threadLocal.ToolsLocal;

public class ThreadA implements Runnable {
    @Override
    public void run() {
        try {
            for (int i = 0; i < 100; i++) {
                Object value = this.getClass().getSimpleName()+" "+(i+1);
                ToolsLocal.t1.set(value);
                System.out.println(getClass().getSimpleName()+" "+ToolsLocal.t1.get());
                Thread.sleep(200);
            }
        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
}
