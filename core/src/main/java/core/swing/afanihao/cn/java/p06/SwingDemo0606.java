package core.swing.afanihao.cn.java.p06;

import core.swing.AfXLayout;
import core.swing.SwingUtils;
import core.swing.afanihao.cn.java.RunAbs;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import javax.swing.*;
import java.awt.*;

public class SwingDemo0606 extends RunAbs {

    @Test
    public void testRun(){
        JFrame jFrame = SwingUtils.getJFrame("布局练习") ;

        Container container = jFrame.getContentPane();
        //表格布局
        container.setLayout(new BorderLayout());
        JPanel top = new JPanel(new AfXLayout()) ;//水平布局
        JPanel lower = new JPanel(new BorderLayout()) ;
        container.add(top,BorderLayout.PAGE_START);
        container.add(lower,BorderLayout.CENTER);

        JLabel jLabel = SwingUtils.getJLabel(">>>");
        jLabel.setHorizontalAlignment(JLabel.CENTER);//文本居中
        Font font = new Font("微软雅黑",0,14) ;
        jLabel.setBackground(Color.BLACK);
        jLabel.setFont(font);
        JButton jButton = new JButton("发送") ;
        top.add(jLabel,"1w");
        top.add(jButton,"20%");
        JLabel jLabel1 = SwingUtils.getJLabel("。。。") ;
        lower.add(jLabel1,BorderLayout.CENTER);
        lower.setBackground(Color.green);
    }


    @AfterTest
    public void after(){
        end();
    }

}
