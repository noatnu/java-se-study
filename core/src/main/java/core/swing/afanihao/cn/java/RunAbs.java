package core.swing.afanihao.cn.java;

import java.io.Serializable;
//http://afanihao.cn/
public abstract class RunAbs implements Serializable {

    public void end() {
        long millis = 15 * 1000;
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void endStatic() {
        long millis = 15 * 1000;
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
