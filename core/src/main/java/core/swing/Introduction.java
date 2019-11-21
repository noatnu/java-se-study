package core.swing;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.testng.annotations.Test;

import javax.swing.*;
import java.awt.*;

/**
 * Created by zch on 2019-11-21.
 */
public class Introduction {

    public long millis = 15000;

    /**
     * JFrame 窗口
     * *** JFrame 用来设计类似于 Windows 系统中窗口形式的界面。JFrame 是 Swing 组件的顶层容器，该类继承了 AWT 的 Frame 类，支持 Swing 体系结构的高级 GUI 属性。 ***
     * 当创建一个 JFrame 类的实例化对象后，其他组件并不能够直接放到容器上面，需要将组件添加至内容窗格，而不是直接添加至 JFrame 对象。示例代码如下：frame.getContentPane().add(b);
     */
    @Test(invocationCount = 1, threadPoolSize = 1)
    public void testJFrameDemo() {
        class JFrameDemo extends JFrame {
            JFrameDemo() {
                setTitle("Java 第一个 GUI 程序");    //设置显示窗口标题
                setSize(400, 200);    //设置窗口显示尺寸
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //置窗口是否可以关闭
                JLabel jl = new JLabel("这是使用JFrame类创建的窗口");    //创建一个标签
                Container c = this.getContentPane();    //获取当前窗口的内容窗格
                c.add(jl);    //将标签组件添加到内容窗格上
                setVisible(true);    //设置窗口是否可见
            }
        }
        new JFrameDemo();    //创建一个实例化对象
        end();
    }

    @Test(invocationCount = 1, threadPoolSize = 1)
    public void testJPanel(){
        JFrame jf=new JFrame("Java第二个GUI程序");    //创建一个JFrame对象
        jf.setBounds(300, 100, 400, 200);    //设置窗口大小和位置
        JPanel jp=new JPanel();    //创建一个JPanel对象
        JLabel jl=new JLabel("这是放在JPanel上的标签");    //创建一个标签
        jp.setBackground(Color.white);    //设置背景色
        jp.add(jl);    //将标签添加到面板
        jf.add(jp);    //将面板添加到窗口
        jf.setVisible(true);    //设置窗口可见
        end();
    }

    private void  end(){
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
