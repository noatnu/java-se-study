package regex.code1;

import org.apache.commons.lang.CharEncoding;
import org.testng.annotations.Test;
import tool.utils.FileUtils;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author noatnu
 * @Description
 * @createDate 2019/5/25
 **/
public class RegexTestA {

    /**
     * 字符类
     * [abc] a、b 或 c（简单类）
     * [^abc] 任何字符，除了 a、b 或 c（否定）
     * [a-zA-Z] a 到 z 或 A 到 Z，两头的字母包括在内（范围）
     * [a-d[m-p]] a 到 d 或 m 到 p：[a-dm-p]（并集）
     * [a-z&&[def]] d、e 或 f（交集）
     * [a-z&&[^bc]] a 到 z，除了 b 和 c：[ad-z]（减去）
     * [a-z&&[^m-p]] a 到 z，而非 m 到 p：[a-lq-z]（减去）
     */

    private final String RESOURCES = "resources\\";
    private final String SUFFIX = ".text";

    /**
     * 匹配纯文本
     *
     * @throws Exception
     */
    @Test
    public void testA() throws Exception {
        String _methodName = new Exception().getStackTrace()[0].getMethodName();
        String path = FileUtils.getMainDataDir(this.getClass(), "") + RESOURCES + _methodName + SUFFIX;
        handle(Pattern.compile("was"), org.apache.commons.io.FileUtils.readFileToString(new File(path), CharEncoding.UTF_8));
    }

    /**
     * [abc] a、b 或 c（简单类）
     */
    @Test
    public void testB(){
        handle(Pattern.compile("[w][h][o]"),"After May quits, who will be UK's next prime minister?");
    }

    /**
     * [^abc] 任何字符，除了 a、b 或 c（否定）
     */
    @Test
    public void testC(){
        handle(Pattern.compile("[U][K][^a-z]s"),"After May quits, who will be UK's next prime minister?");
    }

    /**
     * [a-zA-Z] a 到 z 或 A 到 Z，两头的字母包括在内（范围）
     */
    @Test
    public void testD(){
        String str = "こんばんは good evening" ;//空帮哇。晚上好。
        handle(Pattern.compile("[a-z]+.[a-z]+"),str);
    }

    /**
     * [a-d[m-p]] a 到 d 或 m 到 p：[a-dm-p]（并集）
     */
    @Test
    public void testE(){
        String str = "ごめんなさい I am sorry" ;//过门那赛。对不起。
        handle(Pattern.compile("[a-s][a-o][r-z]{2}[y]"),str);
    }

    /**
     * [a-z&&[def]] d、e 或 f（交集）
     */
    @Test
    public void testF(){
        handle(Pattern.compile("[a-z&&[sumer]]*"),"Tea dress: Key summer fashion piece");
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
