package core.swing.afanihao.cn.java.p02.p0201;

import core.swing.afanihao.cn.java.RunAbs;
import org.apache.commons.lang.RandomStringUtils;
import org.testng.annotations.Test;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class SwingDemo extends RunAbs {

    @Test
    public void testRun() {

        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                JFrame jFrame = new JFrame(s);

                jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//窗口是否可以关闭
                Container containerPanel = jFrame.getContentPane();//内容面板
                containerPanel.setLayout(new FlowLayout());//FlowLayout（流式布局管理器）

                //向内容面板里面添加控件
                containerPanel.add(new JLabel("Hello world!"));
                containerPanel.add(new JButton("测试按钮"));

                //设置窗体大小
                jFrame.setSize(500, 600);
                //显示窗口
                jFrame.setVisible(true);
            }
        };


        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                consumer.accept(RandomStringUtils.randomAscii(12));
            }
        });

        end();
    }

}
