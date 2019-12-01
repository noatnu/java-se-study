package core.swing.afanihao.cn.java.p06;

import core.swing.AfAnyWhere;
import core.swing.AfMargin;
import core.swing.AfXLayout;
import core.swing.SwingUtils;
import core.swing.afanihao.cn.java.RunAbs;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import javax.swing.*;
import java.awt.*;

/**
 * 自由位置布局
 */
public class SwingDemo0605 extends RunAbs {

    @Test
    public void testRun(){
        JFrame jFrame = SwingUtils.getJFrame("线性布局") ;
        JLabel aLabel = SwingUtils.getJLabel("Hello,World") ;
        JLabel bLabel = SwingUtils.getJLabel("样例文本") ;
        JLabel cLabel = SwingUtils.getJLabel("Good Boy") ;
        JLabel dLabel = SwingUtils.getJLabel("占满剩余") ;

        Container container = jFrame.getContentPane();
        JPanel root = new JPanel() ;

        // 设置为自动位置布局
        root.setLayout(new AfAnyWhere());
        // 左上 ,相当于 new AfMargin(0,0,-1,-1)
        root.add(aLabel, AfMargin.TOP_LEFT);

        // 右上 ,相当于 new AfMargin(0,-1,-1,0)
        root.add(bLabel, AfMargin.TOP_RIGHT);

        // 中间 ,相当于 new AfMargin(-1, -1, -1, -1)
        root.add(cLabel, AfMargin.CENTER); // 中间
        cLabel.setPreferredSize(new Dimension(160,80));

        // 自由位置定义
        root.add(dLabel, new AfMargin(-1, 15, 20, 15));
        dLabel.setPreferredSize(new Dimension(0,40));
        container.add(root) ;
    }

    @AfterTest
    public void after(){
        end();
    }
}
