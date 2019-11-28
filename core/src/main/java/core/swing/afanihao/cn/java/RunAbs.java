package core.swing.afanihao.cn.java;


import java.awt.*;
import java.io.Serializable;
import java.util.function.Function;

//http://afanihao.cn/
public abstract class RunAbs implements Serializable {

    public void end() {
        long millis = 15 * 1000;
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void endStatic() {
        long millis = 15 * 1000;
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 随机颜色
     * HSB色彩模式即色度、饱和度、亮度模式。它采用颜色的三属性来表色，即将颜色三属性进行量化，
     * 饱和度和亮度以百分比值（0%-100%）表示，色度以角度（0°-360°）表示 [1]  。 HSB色彩模式以人类对颜色的感觉为基础，描述了颜色的三种基本特性 [2]  。
     * @author zch
     * @return Color
     */
    public static Color getHSBColorRandom() {
        float s = 100f-3f;
        float b = 100f-2f;
        float h = 360f-1f;
        Function<Float, Float> function = new Function<Float, Float>() {
            @Override
            public Float apply(Float max) {
                float min = 0.000000000000000000000000000000000000000000001f;
                return org.apache.commons.lang3.RandomUtils.nextFloat(min,max);
            }
        };
        return Color.getHSBColor(function.apply(s),function.apply(b),function.apply(h));
    }

}
