package core.swing.afanihao.cn.java.p04.p0403;

import core.swing.afanihao.cn.java.RunAbs;
import org.testng.annotations.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;

/**
 * Created by zch on 2019-11-26.
 * 复选框
 */

public class JCheckBoxDemo extends RunAbs {
    @Test
    public void testRun(){
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                JFrame jFrame = new JFrame(s) ;

                Container container = jFrame.getContentPane();

                JCheckBox cbx = new JCheckBox("我想订阅邮件通知");



                jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jFrame.setVisible(true);//窗体显示
                jFrame.setResizable(false);
                jFrame.setSize(800,400);//大小

                container.setLayout(new FlowLayout());//流式布局
                //16列
                JTextField jTextField = new JTextField(16);
                jTextField.setFont(new Font("宋体",0,14));
                jTextField.setEnabled(false);

                cbx.addActionListener(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //当复选框选中之后 那么文本可以编辑
                        if (cbx.isSelected()){
                            jTextField.setEnabled(true);
                        }else {
                            //不选中那么禁用
                            jTextField.setEnabled(false);
                        }
                    }
                });
                container.add(cbx) ;
                container.add(jTextField) ;


            }
        };



        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                consumer.accept("窗体");

            }
        });


        end();
    }


}
