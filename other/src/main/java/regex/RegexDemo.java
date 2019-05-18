package regex;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;
import tool.utils.FileUtils;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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

    /***
     * 匹配一个字符
     */
    @Test
    public void firstMatchWord() {
        Pattern pattern = Pattern.compile("最");
        Matcher matcher = pattern.matcher("要么做第一个，要么做最好的一个");
        if (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

    /**
     * 转义
     */
    @Test
    public void testEscape() {
        List<String> stringList = Arrays.asList(new File(FileUtils.getMainDataDir(this.getClass(), "")).listFiles()).stream().map(File::getName).peek(System.out::println).collect(Collectors.toList());
        Pattern pattern = Pattern.compile(String.format("%s%s", this.getClass().getSimpleName(), "\\.java"));
        Matcher matcher = pattern.matcher(StringUtils.join(stringList, ","));
        if (matcher.find()) {
            System.out.println("====" + matcher.group());
        }
    }

    /*匹配一组字符*/
    @Test
    public void testGroup() {//?
        List<String> stringList = Arrays.asList(new File(FileUtils.getMainDataDir(this.getClass(), "")).getParentFile().listFiles()).stream().map(File::getName).peek(s -> System.out.println()).collect(Collectors.toList());
        String str = StringUtils.join(stringList, ",") ;
        Pattern pattern = Pattern.compile(".?[rs].[gw]");//这的[or] 表示一组 匹配一位
        Matcher matcher = pattern.matcher(str);
        System.out.println(str);
        if (matcher.find()) {
            System.out.println(matcher.group());
        }
        String s = "The phrase regular expression is often abbreviated as RegEx or regex";
        Matcher m = Pattern.compile("[Rr]eg[Ee]x").matcher(s);
        while (m.find()) System.out.println(m.group());
    }


}
