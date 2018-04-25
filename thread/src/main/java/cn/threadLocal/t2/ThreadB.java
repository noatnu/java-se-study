package cn.threadLocal.t2;

import cn.threadLocal.ToolsLocal;

import java.util.Date;

public class ThreadB extends Thread {
    @Override
    public void run() {
        try {
            for (int i = 0; i < 20; i++) {
                if (ToolsLocal.t2.get() == null){
                    ToolsLocal.t2.set(new Date());
                }
                System.out.println(getClass().getSimpleName()+"  "+ToolsLocal.t2.get().getTime());
                Thread.sleep(500);
            }
        }catch (InterruptedException e){

        }
    }
}
