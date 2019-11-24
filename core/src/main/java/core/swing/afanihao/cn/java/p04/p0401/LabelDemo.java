package core.swing.afanihao.cn.java.p04.p0401;

import core.swing.afanihao.cn.java.RunAbs;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class LabelDemo extends RunAbs {

    @Test
    public void testRun(){
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                JFrame jFrame = new JFrame(s) ;

                Container container = jFrame.getContentPane();

                JLabel jLabel = new JLabel();
                jLabel.setText(RandomStringUtils.random(22));
                jLabel.setFont(new Font("微软雅黑",0,14));
                jLabel.setForeground(Color.BLUE);//字体颜色
                jLabel.setToolTipText("http:localhost:8080");
                jLabel.setBackground(Color.green);
                jLabel.setSize(200,300);



                jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jFrame.setVisible(true);
                jFrame.setResizable(false);
                jFrame.setSize(400,600);

                container.setLayout(new FlowLayout());

                container.add(jLabel) ;
            }
        };

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                consumer.accept("窗体");

            }
        });


        end();
    }

}
