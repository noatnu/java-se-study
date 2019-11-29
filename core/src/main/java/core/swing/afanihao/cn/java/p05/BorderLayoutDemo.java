package core.swing.afanihao.cn.java.p05;

import core.swing.afanihao.cn.java.RunAbs;
import org.testng.annotations.Test;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

/**
 * Created by zch on 2019-11-28.
 * 边框布局管理器
 */
public class BorderLayoutDemo extends RunAbs {

    @Test
    public void testRun(){
        Consumer<String> consumer = ((s) ->{
            JFrame jFrame = new JFrame(s);
            Container container = jFrame.getContentPane();
            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jFrame.setVisible(true);//窗体显示
            jFrame.setResizable(false);
//            jFrame.setSize(1200, 1900);//大小
//            jFrame.setLocation(60, 60);

            LayoutManager layoutManager = new BorderLayout(2,4) ;
            container.setLayout(layoutManager);
            JButton button1=new JButton ("上");
            JButton button2=new JButton("左");
            JButton button3=new JButton("中");
            JButton button4=new JButton("右");
            JButton button5=new JButton("下");
            jFrame.add(button1,BorderLayout.NORTH);
            jFrame.add(button2,BorderLayout.WEST);
            jFrame.add(button3,BorderLayout.CENTER);
            jFrame.add(button4,BorderLayout.EAST);
            jFrame.add(button5,BorderLayout.SOUTH);
            jFrame.setBounds(300,200,600,300);
        });

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                consumer.accept("窗体");

            }
        });
        end();
    }
}
