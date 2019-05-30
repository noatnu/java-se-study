package regex.code2;

import org.apache.commons.lang.CharEncoding;
import org.testng.annotations.Test;
import tool.utils.FileUtils;

import java.io.File;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author zch
 * @Description
 * @createDate 2019/5/30
 **/
public class ExampleDemo {

    private final String RESOURCES = "resources\\";
    private final String SUFFIX = ".text";

    private Pattern pattern = null;
    private Matcher matcher = null;

    /**
     * 查找替换指定字符串
     * 实现非终端添加和替换步骤 和 replace("target","replacement") 类似返回替换后的完整数据
     */
    @Test
    public void appendReplacement() {
        String input = "http://www.chinadaily.com.cn/";
        if (false) {
            pattern = Pattern.compile("\\.c");
            matcher = pattern.matcher(input);
            while (matcher.find()) {
                Matcher matcher1 = matcher.appendReplacement(new StringBuffer(8), "。C");
                //最好是不要使用返回的匹配器来操作
                while (matcher1.find()) {
                    System.out.println("=====>" + matcher1.group());
                }
            }
        }
        pattern = Pattern.compile("\\.c");
        matcher = pattern.matcher(input);
        StringBuffer stringBuffer = new StringBuffer(8);
        while (matcher.find()) {
            matcher.appendReplacement(stringBuffer, "。C");
        }
        //返回目标字符串缓冲区。
        matcher.appendTail(stringBuffer);
        System.out.println(stringBuffer.toString());
    }

    /**
     * 获取开始索引和结束索引
     */
    @Test
    public void startAndEnd(){
        String input = "Master US artist exhibits in HK" ;
        pattern = Pattern.compile("\\s") ;
        matcher = pattern.matcher(input);
        while (matcher.find()){
            System.out.println("start index:"+matcher.start());
            System.out.println("end index:"+matcher.end());
        }
    }

    /**
     * 获取捕获的数量
     * @throws Exception
     */
    @Test
    public void groupCount()throws Exception{
        String path = FileUtils.getMainDataDir(this.getClass(), "") + RESOURCES + "c1" + SUFFIX;
        String input = org.apache.commons.io.FileUtils.readFileToString(new File(path), CharEncoding.UTF_8) ;
        pattern = Pattern.compile("(\\d*元)") ;
        matcher = pattern.matcher(input);
        while (matcher.find()){
            System.out.println(matcher.group());
            System.out.println(matcher.groupCount());
        }
    }

    /**
     * 分割
     */
    @Test
    public void testA(){
        String input = "IT之家5月29日消息 目前距离WWDC 2019还有几天时间了，届时预计将推出iOS 13、watchOS 6和macOS 10.15等重大版本系统。根据外媒9to5Mac报道，目前已经收到了一些来自iOS 13系统截图，展示了一些新功能" ;
        pattern = Pattern.compile("iOS\\s\\d{2}") ;
        String[] strings = pattern.split(input);
        //注意s.split("regex") ; 字符串输入的是正则表达式，而上面输入的是要匹配的字符
        Arrays.stream(strings).forEachOrdered(s -> System.out.println("=="+s));
    }

    /**
     * 替换字符串
     */
    @Test
    public void testB(){
        String input = "英特尔i7-1065 G7核显跑分曝光：接近RX 460" ;
        // 正则表达式
        pattern = Pattern.compile("\\d*") ;
        // 操作的字符串
        matcher = pattern.matcher(input) ;
        ///替换后的字符串
        String text = matcher.replaceAll("");
        System.out.println(text);
    }

    /**
     * 替换妙用  提出数字
     */
    @Test
    public void testB1(){
        String input = "英特尔i7-1065 G7核显跑分曝光：接近RX 460" ;
        // 正则表达式
        pattern = Pattern.compile("[^\\d]*") ;//非数字
        // 操作的字符串
        matcher = pattern.matcher(input) ;
        ///替换后的字符串
        String text = matcher.replaceAll("");//提取出整个字符中的数字
        System.out.println(text);
    }

}
