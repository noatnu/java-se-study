package regex;

import org.testng.annotations.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author noatn
 * @Description
 * @createDate 2019/5/17
 **/
public class RegexDemo {


    /**
     * 基础匹配
     */
    @Test
    public void testA() {
        Pattern pattern = Pattern.compile("to");
        Matcher matcher = pattern.matcher("Customers like to grab a chance to pick up a doll");
        if (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

    /**
     * .可以匹配任何字符
     */
    @Test
    public void testPoint() {
        Pattern pattern = Pattern.compile("Cannes.");
        Matcher matcher = pattern.matcher("Celebrities shine at the opening of Cannes。");
        if (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

    @Test
    public void firstMatchWord(){
        Pattern pattern = Pattern.compile("最");
        Matcher matcher = pattern.matcher("要么做第一个，要么做最好的一个");
        if (matcher.find()) {
            System.out.println(matcher.group());
        }
    }


}
