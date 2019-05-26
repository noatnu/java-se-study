package regex.code1;

import org.testng.annotations.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author noatnu
 * @Description
 * @createDate 2019/5/26
 **/
public class RegexTestB {


    /*
    * . 任何字符（与行结束符可能匹配也可能不匹配）
    * \d 数字：[0-9]
    * \D 非数字： [^0-9]
    * \s 空白字符：[ \t\n\x0B\f\r]
    * \S 非空白字符：[^\s]
    * \w 单词字符：[a-zA-Z_0-9]
     * \W 非单词字符：[^\w]
    **/

    private final String RESOURCES = "resources\\";
    private final String SUFFIX = ".text";

    /**
     * . 任何字符（与行结束符可能匹配也可能不匹配）
     */
    @Test
    public void testA(){
        handle(Pattern.compile(".+"),"In Beijing you Mexi-can");
    }

    /**
     * \d 数字：[0-9]
     */
    @Test
    public void testB(){
        handle(Pattern.compile("\\d+"),"Registration Number: 130349");
    }

    /**
     * \D 非数字： [^0-9]
     */
    @Test
    public void testC(){
        handle(Pattern.compile("\\D+"),"Registration Number: 130349");
    }

    /**
     * \s 空白字符：[ \t\n\x0B\f\r]
     */
    @Test
    public void testE(){
        Pattern pattern = Pattern.compile("\\s");
        Matcher matcher = pattern.matcher("About China Daily");
        if (matcher.find()) {
            System.out.println(matcher.group());
            System.out.println( "length:" + matcher.group().length());
        }
    }

    /**
     * \S 非空白字符：[^\s]
     */
    @Test
    public void testF(){
        //只会输入Expat 因为后面有一个字符的空白字符会中断匹配
        handle(Pattern.compile("\\S+"),"Expat Employment");
        handle(Pattern.compile("\\S+\\s?\\S+"),"Expat Employment");
    }

    /**
     * w 单词字符：[a-zA-Z_0-9]
     */
    @Test
    public void testG(){
        //到the就会中断匹配
        handle(Pattern.compile("\\w+"),"The peacekeepers, including several female members, get ready to board the plane, May 22, 2019. ");
    }

    /**
     * 非单词字符 \W 非单词字符：[^\w]
     */
    @Test
    public void testH(){
        handle(Pattern.compile("\\W+"),"3,000+ books in your pocket");
        //print : ,
        //为啥是,呢这是因为单词字符包括了大小写字母和数字
    }


    private void handle(Pattern pattern, String input) {
        //获得调用者的方法名
        String _methodName = new Exception().getStackTrace()[1].getMethodName();
        //获得当前的方法名
        String _thisMethodName = new Exception().getStackTrace()[0].getMethodName();
        System.out.println("thisMethodName:" + _thisMethodName);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            System.out.println(_methodName + ":" + matcher.group());
        }
    }

}
