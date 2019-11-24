package core.swing.afanihao.cn.java.p02.p0202;

import core.swing.afanihao.cn.java.RunAbs;
import org.testng.annotations.Test;

import java.util.function.BiFunction;

public class SwingRun extends RunAbs {

    @Test
    public void testRun(){
        BiFunction biFunction = MyFrame.accept("窗体") ;

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

               Object object = biFunction.apply(500,600) ;
               if (object instanceof MyFrame){

                   MyFrame myFrame = (MyFrame)object;


               }
            }
        });

        end();
    }

}
