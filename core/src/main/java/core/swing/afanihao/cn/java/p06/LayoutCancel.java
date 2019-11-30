package core.swing.afanihao.cn.java.p06;

import core.swing.SwingUtils;
import core.swing.afanihao.cn.java.RunAbs;
import org.testng.annotations.Test;

import javax.swing.*;
import java.awt.*;

/**
 * 位置布局
 */
public class LayoutCancel extends RunAbs {

    @Test
    public void task(){
        JFrame jFrame = SwingUtils.getJFrame();
        Container container = jFrame.getContentPane();
        container.setLayout(null);
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);
        JLabel jLabel1 = SwingUtils.getJLabel();
        JLabel jLabel2 = SwingUtils.getJLabel();
        jLabel1.setBounds(new Rectangle(0,0,100,100));
        jLabel2.setBounds(new Rectangle(0,100,100,100));
        jPanel.add(jLabel1);
        jPanel.add(jLabel2);
        jPanel.setBounds(0,0,350,400);
        container.add(jPanel) ;
        container.setBounds(0,0,400,600);
        end();

    }


}
