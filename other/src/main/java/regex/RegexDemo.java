package regex;

import org.apache.commons.lang.CharEncoding;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;
import tool.utils.FileUtils;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @Author noatn
 * @Description
 * @createDate 2019/5/17
 **/
public class RegexDemo {

    private Logger logger = Logger.getLogger(this.toString());

    /**
     * 基础匹配
     */
    @Test
    public void testA() {
        //这里使用的是正则表达式是纯文本，它将匹配原始文本里的 Sunday
        Pattern pattern = Pattern.compile("Sunday");
        Matcher matcher = pattern.matcher("It was Sunday. I never get up early on Sundays. I sometimes stay in bed until lunch time. Last Sunday I got up very late. ");
        if (matcher.find()) {
            System.out.println(matcher.group());
        }
        //匹配一个字符
        pattern = Pattern.compile("最");
        matcher = pattern.matcher("要么做第一个，要么做最好的一个");
        if (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

    /**
     * .可以匹配任何字符
     */
    @Test
    public void testPoint() {
        //在正则表达式里，特殊字符（或字符集和）用来给出要搜索的东西。 .字符可以匹配任何一个单个的字符
        Pattern pattern = Pattern.compile("Cannes.");
        Matcher matcher = pattern.matcher("Celebrities shine at the opening of Cannes。");
        if (matcher.find()) {
            System.out.println(matcher.group());
        }
        List<String> stringList = Arrays.asList(new File(String.format("%shelp", FileUtils.getMainDataDir(this.getClass(), ""))).listFiles()).stream().map(File::getName).peek(s -> System.out.println()).collect(Collectors.toList());
        String string = "This interface contains query methods used to determine";
        pattern = Pattern.compile("in.");
        matcher = pattern.matcher(string);
        while (matcher.find()) System.out.println(matcher.group());
    }


    /**
     * 转义
     */
    @Test
    public void testEscape() {
        List<String> stringList = Arrays.asList(new File(FileUtils.getMainDataDir(this.getClass(), "")).listFiles()).stream().map(File::getName).peek(System.out::println).collect(Collectors.toList());
        handle(String.format("%s%s", this.getClass().getSimpleName(), "\\.java"), StringUtils.join(stringList, ","));
    }


    /*匹配单个字符*/
    @Test
    public void firstMatcher() {
        handle(".a.", "na1 na2 sa1");
    }

    /*匹配一组字符*/
    @Test
    public void testGroup() {//?
        List<String> stringList = Arrays.asList(new File(FileUtils.getMainDataDir(this.getClass(), "")).getParentFile().listFiles()).stream().map(File::getName).peek(s -> System.out.println()).collect(Collectors.toList());
        String str = StringUtils.join(stringList, ",");
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

    //组 匹配
    @Test
    public void testGroupRange() {
        handle("[ns]a.\\.xls", "sales1.xls orders3.xls sales2.xls na1.xls na2.xls ca1.xls");
    }

    //数字
    @Test
    public void sidingToSidingBlockNumber() {
        handle("[0-9][0-9][0-9][0-9]", "Copyright 1995 - 2019 . All rights reserved. The content (including but not limited to text, photo, multimedia information, etc) published in this site belongs to China Daily ");
    }

    /**
     * 取反 ^
     * ^意思就是非　和C++ C Java　中的!是一个意思,这的[^0-9]取非0-9的字符,至于其它的匹配和isTest6B的含义一样
     * 一般来说^都是写在[]里面如:[^*]
     * @throws Exception
     */
    @Test
    public void test6B()throws Exception {
        String path = FileUtils.getMainDataDir(this.getClass(), "")+"help\\isTest6B.txt" ;
        String str = org.apache.commons.io.FileUtils.readFileToString(new File(path), CharEncoding.UTF_8);
        handle("[ae].[^0-9]\\.xlsx",str);
    }


    @Test
    public void isTest7()throws Exception{
        String path = FileUtils.getMainDataDir(this.getClass(), "")+"help\\isTest7B.txt" ;
        String str = org.apache.commons.io.FileUtils.readFileToString(new File(path), CharEncoding.UTF_8);
        handle("[^a-zA-Z0-9][A-Z0-9][A-Z0-9][A-Z0-9].[A-Za-z]",str);
    }

    /**
     * \w 单词字符 简写==> [a-zA-Z_0-9]
     * \d 数字：[0-9]
     * @throws Exception
     */
    @Test
    public void isTest8B()throws Exception{
        String path = FileUtils.getMainDataDir(this.getClass(), "")+"help\\isTest8B.text" ;
        String str = org.apache.commons.io.FileUtils.readFileToString(new File(path), CharEncoding.UTF_8);
        handle("[a-zA-Z0-9_][\\d][\\w][\\d][\\w]",str);
    }

    @Test
    public void isTest8A()throws Exception{
        String path = FileUtils.getMainDataDir(this.getClass(), "")+"help\\isTest8A.text" ;
        String str = org.apache.commons.io.FileUtils.readFileToString(new File(path), CharEncoding.UTF_8);
        handle("[\\W]+",str);
    }

    /*
    Greedy 数量词
    X? X，一次或一次也没有
    X* X，零次或多次
    X+ X，一次或多次
    X{n} X，恰好 n 次
    X{n,} X，至少 n 次
    X{n,m} X，至少 n 次，但是不超过 m 次
    这其中X表示任意匹配的字符
    * */

    /**
     * + 一次或多次
     * @throws Exception
     */
    @Test
    public void isTest9A()throws Exception{
        handle("\\w\\w\\w\\w@\\w\\w\\w\\.com","gdry@nnn.com");
        /**
         * 这个正则表达式本身没有任何错误，可它几乎没有任何实际的用处只能匹配这样的哈哈哈gdry@nnn.com
         * （虽然在语法方面没有任何问题，但这显然不是一个合法的地址），并且我们无法预知电子邮件的各个字段会有多少个字符．
         * 不是吗？
         */
        String path = FileUtils.getMainDataDir(this.getClass(), "")+"help\\isTest9A.text" ;
        //假如字符中有点的情况主要对付多层域名的情况com.cn net.cn china.net等等
        handle("\\w+@[\\w.]+\\.\\w+",org.apache.commons.io.FileUtils.readFileToString(new File(path), CharEncoding.UTF_8));
    }

    private void handle(String regex, String input) {
        //获得调用者的方法名
        String _methodName = new Exception().getStackTrace()[1].getMethodName();
        //获得当前的方法名
        String _thisMethodName = new Exception().getStackTrace()[0].getMethodName();
        System.out.println(_thisMethodName);
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            System.out.println(_methodName + ":" + matcher.group());
        }
    }

}
