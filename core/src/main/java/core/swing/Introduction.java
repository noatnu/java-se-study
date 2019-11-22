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
    public void testJPanel() {
        JFrame jf = new JFrame("Java第二个GUI程序");    //创建一个JFrame对象
        jf.setBounds(300, 100, 400, 200);    //设置窗口大小和位置
        JPanel jp = new JPanel();    //创建一个JPanel对象
        JLabel jl = new JLabel("这是放在JPanel上的标签");    //创建一个标签
        jp.setBackground(Color.white);    //设置背景色
        jp.add(jl);    //将标签添加到面板
        jf.add(jp);    //将面板添加到窗口
        jf.setVisible(true);    //设置窗口可见
        end();
    }

    /**
     * BorderLayout（边框布局管理器）是 Window、JFrame 和 JDialog 的默认布局管理器。边框布局管理器将窗口分为 5 个区域：North、South、East、West 和 Center。其中，
     * North 表示北，将占据面板的上方；Soufe 表示南，将占据面板的下方；East表示东，将占据面板的右侧；West 表示西，将占据面板的左侧；
     * 中间区域 Center 是在东、南、西、北都填满后剩下的区域
     */
    @Test(invocationCount = 1, threadPoolSize = 1)
    public void testDemo3() {
        JFrame frame = new JFrame("Java第三个GUI程序");    //创建Frame窗口
        frame.setSize(400, 200);
        frame.setLayout(new BorderLayout());    //为Frame窗口设置布局为BorderLayout
        JButton button1 = new JButton("上");
        JButton button2 = new JButton("左");
        JButton button3 = new JButton("中");
        JButton button4 = new JButton("右");
        JButton button5 = new JButton("下");
//        frame.add(button1,BorderLayout.NORTH);
        frame.add(button2, BorderLayout.WEST);
        frame.add(button3, BorderLayout.CENTER);
        frame.add(button4, BorderLayout.EAST);
        frame.add(button5, BorderLayout.SOUTH);
        frame.setBounds(300, 200, 600, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        end();
    }

    /**
     * FlowLayout（流式布局管理器）是 JPanel 和 JApplet 的默认布局管理器。
     * FlowLayout 会将组件按照从上到下、从左到右的放置规律逐行进行定位。
     * 与其他布局管理器不同的是，FlowLayout 布局管理器不限制它所管理组件的大小，而是允许它们有自己的最佳大小。
     */
    @Test
    public void testDemo4() {
        JFrame jFrame = new JFrame("Java第四个GUI程序");    //创建Frame窗口
        JPanel jPanel = new JPanel();    //创建面板
        JButton btn1 = new JButton("1");    //创建按钮
        JButton btn2 = new JButton("2");
        JButton btn3 = new JButton("3");
        JButton btn4 = new JButton("4");
        JButton btn5 = new JButton("5");
        JButton btn6 = new JButton("6");
        JButton btn7 = new JButton("7");
        JButton btn8 = new JButton("8");
        JButton btn9 = new JButton("9");
        jPanel.add(btn1);    //面板中添加按钮
        jPanel.add(btn2);
        jPanel.add(btn3);
        jPanel.add(btn4);
        jPanel.add(btn5);
        jPanel.add(btn6);
        jPanel.add(btn7);
        jPanel.add(btn8);
        jPanel.add(btn9);
        //向JPanel添加FlowLayout布局管理器，将组件间的横向和纵向间隙都设置为20像素
        jPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 20));
        jPanel.setBackground(Color.gray);    //设置背景色
        jFrame.add(jPanel);    //添加面板到容器
        jFrame.setBounds(300, 200, 300, 150);    //设置容器的大小
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        end();
    }

    /**
     * CardLayout（卡片布局管理器）能够帮助用户实现多个成员共享同一个显不空间，并且一次只显示一个容器组件的内容。
     * CardLayout 布局管理器将容器分成许多层，每层的显示空间占据整个容器的大小，但是每层只允许放置一个组件。CardLayout 的构造方法如下。
     * CardLayout()：构造一个新布局，默认间隔为 0。
     * CardLayout(int hgap, int vgap)：创建布局管理器，并指定组件间的水平间隔（hgap）和垂直间隔（vgap）。
     */
    @Test
    public void testDemo5() {
        JFrame frame = new JFrame("Java第五个程序");    //创建Frame窗口
        JPanel p1 = new JPanel();    //面板1
        JPanel p2 = new JPanel();    //面板2
        JPanel cards = new JPanel(new CardLayout());    //卡片式布局的面板
        p1.add(new JButton("登录按钮"));
        p1.add(new JButton("注册按钮"));
        p1.add(new JButton("找回密码按钮"));
        p2.add(new JTextField("用户名文本框", 20));
        p2.add(new JTextField("密码文本框", 20));
        p2.add(new JTextField("验证码文本框", 20));
        cards.add(p1, "card1");    //向卡片式布局面板中添加面板1
        cards.add(p2, "card2");    //向卡片式布局面板中添加面板2
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, "card2");    //调用show()方法显示面板2
//        cl.show(cards, "card1");    //调用show()方法显示面板2
        frame.add(cards);
        frame.setBounds(300, 200, 400, 200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        end();
    }

    /**
     * GridLayout（网格布局管理器）为组件的放置位置提供了更大的灵活性。
     * 它将区域分割成行数（rows）和列数（columns）的网格状布局，组件按照由左至右、由上而下的次序排列填充到各个单元格中。
     */
    @Test
    public void testDemo6() {
        JFrame frame = new JFrame("GridLayou布局计算器");
        JPanel panel = new JPanel();    //创建面板
        //指定面板的布局为GridLayout，4行4列，间隙为5
        panel.setLayout(new GridLayout(4, 4, 5, 5));
        panel.add(new JButton("7"));    //添加按钮
        panel.add(new JButton("8"));
        panel.add(new JButton("9"));
        panel.add(new JButton("/"));
        panel.add(new JButton("4"));
        panel.add(new JButton("5"));
        panel.add(new JButton("6"));
        panel.add(new JButton("*"));
        panel.add(new JButton("1"));
        panel.add(new JButton("2"));
        panel.add(new JButton("3"));
        panel.add(new JButton("-"));
        panel.add(new JButton("0"));
        panel.add(new JButton("."));
        panel.add(new JButton("="));
        panel.add(new JButton("+"));
        frame.add(panel);    //添加面板到容器
        frame.setBounds(300, 200, 200, 150);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        end();
    }

    /**
     * 网格包布局管理器
     * GridBagLayout（网格包布局管理器）是在网格基础上提供复杂的布局，是最灵活、 最复杂的布局管理器。GridBagLayout 不需要组件的尺寸一致，
     * 允许组件扩展到多行多列。每个 GridBagLayout 对象都维护了一组动态的矩形网格单元，每个组件占一个或多个单元，所占有的网格单元称为组件的显示区域。
     * GridBagLayout 所管理的每个组件都与一个 GridBagConstraints 约束类的对象相关。这个约束类对象指定了组件的显示区域在网格中的位置，以及在其显示区域中应该如何摆放组件。
     * 除了组件的约束对象，GridBagLayout 还要考虑每个组件的最小和首选尺寸，以确定组件的大小。
     * 为了有效地利用网格包布局管理器，在向容器中添加组件时，必须定制某些组件的相关约束对象。GridBagConstraints 对象的定制是通过下列变量实现的。
     * 1. gridx 和 gridy
     * 用来指定组件左上角在网格中的行和列。容器中最左边列的 gridx 为 0，最上边行的 gridy 为 0。这两个变量的默认值是 GridBagConstraints.RELATIVE，表示对应的组件将放在前一个组件的右边或下面。
     * 2. gridwidth 和 gridheight
     * 用来指定组件显示区域所占的列数和行数，以网格单元而不是像素为单位，默认值为 1。
     * 3. fill
     * 指定组件填充网格的方式，可以是如下值：GridBagConstraints.NONE（默认值）、GridBagConstraints.HORIZONTAL（组件横向充满显示区域，但是不改变组件高度）、GridBagConstraints.VERTICAL（组件纵向充满显示区域，但是不改变组件宽度）以及 GridBagConstraints.BOTH（组件横向、纵向充满其显示区域）。
     * 4. ipadx 和 ipady
     * 指定组件显示区域的内部填充，即在组件最小尺寸之外需要附加的像素数，默认值为 0。
     * 5. insets
     * 指定组件显示区域的外部填充，即组件与其显示区域边缘之间的空间，默认组件没有外部填充。
     * 6. anchor
     * 指定组件在显示区域中的摆放位置。可选值有 GridBagConstraints.CENTER（默认值）、GridBagConstraints.NORTH、GridBagConstraints.
     * NORTHEAST、GridBagConstraints.EAST、GridBagConstraints.SOUTH、GridBagConstraints.SOUTHEAST、GridBagConstraints.WEST、GridBagConstraints.SOUTHWEST 以及 GridBagConstraints.NORTHWEST。
     * 7. weightx 和 weighty
     * 用来指定在容器大小改变时，增加或减少的空间如何在组件间分配，默认值为 0，即所有的组件将聚拢在容器的中心，多余的空间将放在容器边缘与网格单元之间。weightx 和 weighty 的取值一般在 0.0 与 1.0 之间，数值大表明组件所在的行或者列将获得更多的空间。
     */
    @Test
    public void testDemo7() {
        JFrame frame = new JFrame("拨号盘");
        GridBagLayout gbaglayout = new GridBagLayout();    //创建GridBagLayout布局管理器
        GridBagConstraints constraints = new GridBagConstraints();
        frame.setLayout(gbaglayout);    //使用GridBagLayout布局管理器
        constraints.fill = GridBagConstraints.BOTH;    //组件填充显示区域
        constraints.weightx = 0.0;    //恢复默认值
        constraints.gridwidth = GridBagConstraints.REMAINDER;    //结束行
        JTextField tf = new JTextField("13612345678");
        gbaglayout.setConstraints(tf, constraints);
        frame.add(tf);
        constraints.weightx = 0.5;    // 指定组件的分配区域
        constraints.weighty = 0.2;
        constraints.gridwidth = 1;
        makeButton("7", frame, gbaglayout, constraints);    //调用方法，添加按钮组件
        makeButton("8", frame, gbaglayout, constraints);
        constraints.gridwidth = GridBagConstraints.REMAINDER;    //结束行
        makeButton("9", frame, gbaglayout, constraints);
        constraints.gridwidth = 1;    //重新设置gridwidth的值

        makeButton("4", frame, gbaglayout, constraints);
        makeButton("5", frame, gbaglayout, constraints);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        makeButton("6", frame, gbaglayout, constraints);
        constraints.gridwidth = 1;

        makeButton("1", frame, gbaglayout, constraints);
        makeButton("2", frame, gbaglayout, constraints);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        makeButton("3", frame, gbaglayout, constraints);
        constraints.gridwidth = 1;

        makeButton("返回", frame, gbaglayout, constraints);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        makeButton("拨号", frame, gbaglayout, constraints);
        constraints.gridwidth = 1;
        frame.setBounds(400, 400, 400, 400);    //设置容器大小
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        end();
    }

    //向JFrame中添加JButton按钮
    private void makeButton(String title, JFrame frame, GridBagLayout gridBagLayout, GridBagConstraints constraints) {
        JButton button = new JButton(title);    //创建Button对象
        gridBagLayout.setConstraints(button, constraints);
        frame.add(button);
    }

    /**
     * BoxLayout（盒布局管理器）通常和 Box 容器联合使用，Box 类有以下两个静态方法。
     createHorizontalBox()：返回一个 Box 对象，它采用水平 BoxLayout，即 BoxLayout 沿着水平方向放置组件，让组件在容器内从左到右排列。
     createVerticalBox()：返回一个 Box 对象，它采用垂直 BoxLayout，即 BoxLayout 沿着垂直方向放置组件，让组件在容器内从上到下进行排列。
     Box 还提供了用于决定组件之间间隔的静态方法
     网格包布局	                                     说明
     static Component createHorizontalGlue()	创建一个不可见的、可以被水平拉伸和收缩的组件
     static Component createVerticalGlue()	创建一个不可见的、可以被垂直拉伸和收缩的组件
     static Component createHorizontalStrut(int width)	创建一个不可见的、固定宽度的组件
     static Component createVerticalStrut(int height)	创建一个不可见的、固定高度的组件
     static Component createRigidArea(Dimension d)	创建一个不可见的、总是具有指定大小的组件

     BoxLayout 类只有一个构造方法，如下所示。
     BoxLayout(Container c,int axis)

     使用盒式布局可以像使用流式布局一样简单地将组件安排在一个容器内。包含盒式布局的容器可以嵌套使用，最终达到类似于无序网格布局那样的效果，却不像使用无序网格布局那样麻烦。
     */
    @Test
    public void testDemo8(){

        JFrame frame=new JFrame("Java示例程序");
        Box b1=Box.createHorizontalBox();    //创建横向Box容器
        Box b2=Box.createVerticalBox();    //创建纵向Box容器
        frame.add(b1);    //将外层横向Box添加进窗体
        b1.add(Box.createVerticalStrut(200));    //添加高度为200的垂直框架
        b1.add(new JButton("西"));    //添加按钮1
        b1.add(Box.createHorizontalStrut(140));    //添加长度为140的水平框架
        b1.add(new JButton("东"));    //添加按钮2
        b1.add(Box.createHorizontalGlue());    //添加水平胶水
        b1.add(b2);    //添加嵌套的纵向Box容器
        //添加宽度为100，高度为20的固定区域
        b2.add(Box.createRigidArea(new Dimension(100,20)));
        b2.add(new JButton("北"));    //添加按钮3
        b2.add(Box.createVerticalGlue());    //添加垂直组件
        b2.add(new JButton("南"));    //添加按钮4
        b2.add(Box.createVerticalStrut(40));    //添加长度为40的垂直框架
        //设置窗口的关闭动作、标题、大小位置以及可见性等
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100,100,400,200);
        frame.setVisible(true);


        end();
    }


    private void end() {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
