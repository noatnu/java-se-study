package other.base.dateType;


import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @Author noatn
 * @Description
 * @createDate 2018/9/23
 **/
public class DateExampleA {

    /**
     * @Description:描述时间比较
     * @Author:zch
     * @CreateDate:22:25 2018/9/23
     * @Param:
     * @return:
     * @Version:1.0
     */
    @Test
    public void testA() {
        Date beforeDate = new Date(302672606563l);
        System.out.println("以前的时间 " + beforeDate);
        Date nowDate = new Date();
        boolean flag = nowDate.after(beforeDate);
        System.out.println(flag);
    }

    /**
     * @Description:描述获得时间
     * @Author:zch
     * @CreateDate:22:29 2018/9/23
     * @Param:
     * @return:
     * @Version:1.0
     */
    @Test
    public void testB() {
        Date date = new Date();
        long value = date.getTime();
        System.out.println("毫秒:" + value);
        date.setTime(value + 222);
    }

    @Test
    public void testC() {
        Locale[] locales = Locale.getAvailableLocales();//获得所有已安装语言环境的数组
        for (int i = 0; i < locales.length; i++) {
            System.out.println(locales[i] + " " + locales[i].getCountry());//语言地区和对应代码
        }
        Locale locale = Locale.getDefault();//获取默认语言环境
        System.out.println(locale);
        System.out.println(locale.getDisplayCountry());//获得地区/国家名字
    }

    @Test
    public void testD(){
        Date date = new Date();
        System.out.println(date);
        DateFormat df = DateFormat.getDateInstance();//获取具有默认语言环境风格的日期格式器
        String format = df.format(date);
        System.out.println(format);

        DateFormat dateFormat = DateFormat.getDateTimeInstance();//默认环境中的时间
        format = dateFormat.format(date);
        System.out.println(format);

        DateFormat dateFormat1 = DateFormat.getInstance();//默认环境中的日期和时间
        format = dateFormat1.format(date);
        System.out.println(format);
    }

    @Test
    public void testE(){
        Date date = null;
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        String val = "2012-09-22 9:6:20" ;
        try {
            date = df.parse(val);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(date);
    }

}
