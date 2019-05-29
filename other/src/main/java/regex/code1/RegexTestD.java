package regex.code1;

import org.testng.annotations.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author zch
 * @Description
 * @createDate 2019/5/29
 **/
public class RegexTestD {
    //组
    /**
     * [XY] 表示匹配单个字符里面符合的任意XY
     */

    @Test
    public void testA() {
        String input = "Theater that strikes the right note";
        handle(Pattern.compile("th[ae].?"), input);
        //[ae]表示方括号号内的任意一个匹配即可
    }

    @Test
    public void testB(){
        String input = "Theater that strikes the right note";
//        handle(Pattern.compile("th(e)"), input);//the
        handle(Pattern.compile("th(.)"), input);//the tha
        //(e) 表示匹配e单个位子的字符
    }

    private void handle(Pattern pattern, String input) {
        //获得调用者的方法名
        String _methodName = new Exception().getStackTrace()[1].getMethodName();
        //获得当前的方法名
        String _thisMethodName = new Exception().getStackTrace()[0].getMethodName();
        System.out.println("thisMethodName:" + _thisMethodName);
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            System.out.println(_methodName + ":" + matcher.group());
        }
    }

}
