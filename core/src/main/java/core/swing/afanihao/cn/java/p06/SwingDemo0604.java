package core.swing.afanihao.cn.java.p06;

import core.swing.AfXLayout;
import core.swing.SwingUtils;
import core.swing.afanihao.cn.java.RunAbs;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import javax.swing.*;
import java.awt.*;

/**
 * 线性布局
 */
public class SwingDemo0604 extends RunAbs {

    @Test
    public void testRun(){
        JFrame jFrame = SwingUtils.getJFrame("线性布局") ;
        JLabel aLabel = SwingUtils.getJLabel("a") ;
        JLabel bLabel = SwingUtils.getJLabel("b") ;
        JLabel cLabel = SwingUtils.getJLabel("c") ;
        JLabel dLabel = SwingUtils.getJLabel("d") ;

        Container container = jFrame.getContentPane();
        JPanel root = new JPanel() ;

        // 设置为横向布局
        // 注: AfXLayout 与 AfRowLayout等效
        root.setLayout(new AfXLayout(8));
        root.add(aLabel, "100px"); // 固定占100像素
        root.add(bLabel, "30%");  // 固定占30%
        root.add(cLabel, "auto"); // 按 Preferred Size 设置
        root.add(dLabel, "1w");   // 按权重设置，权重为1 weight
        container.add(root) ;
    }

    @AfterTest
    public void after(){
        end();
    }
}
