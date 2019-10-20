package regex.code1;

import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author zch
 * @Description
 * @createDate 2019/5/26
 **/
public class RegexTestC {
    List<String> stringList = Arrays.asList("中华人民共和国教育部","高等教育出版社","高教社考试","中国大学生网",
            "爱课程网","英才网联网","滇西开发网"," 大众摄影网",
            "中国教育新闻网 中国学术前沿期刊","高教社网上书城","新华网",
            "人民网","全国高校思想政治工作网","高校辅导员网络培训中心","中教华影全国校园电影院线");
    /**
     * 边界匹配器
     * ^ 行的开头
     * $ 行的结尾
     * \b 单词边界
     * \B 非单词边界
     * \A 输入的开头
     * \G 上一个匹配的结尾
     * \Z 输入的结尾，仅用于最后的结束符（如果有的话）
     * \z 输入的结尾
     */

    /**
     * ^ 行的开头
     */
    @Test
    public void testA(){
        String input = StringUtils.join(stringList," ") ;
        System.out.println(input);
//        handle(Pattern.compile("^高.+"),input); //假如不是以中开头那么就什么也获取不到
        handle(Pattern.compile("^中.{9}"),input);
    }

    /**
     * $ 行的结尾
     */
    @Test
    public void testB(){
        String input = StringUtils.join(stringList," ") ;
        System.out.println(input);
//        handle(Pattern.compile(".{11}A$"),input);//假如不是以'线'结尾那么什么也匹配不到
        handle(Pattern.compile(".{11}线$"),input);
    }

    /**
     * 结合^ 行的开头和 $ 行的结尾
     */
    @Test
    public void testC(){
        String input = "Honey from New Zealand, coffee from Rwanda, durian from Cambodia, as well as healthcare products from Australia, are now all available through online channels." ;
        input = "Honey" ;
        handle(Pattern.compile("^Honey$"),input);
        /**
         * 当 input 不是Honey那么即使输入中有这些字符也不会匹配,也就是说在^和$之间的字符必须和输入的字符一直否则无法匹配
         */
    }

    /**
     * \b 单词边界
     */
    @Test
    public void testD(){
        String input = "19 ministries to coordinate decisions about the economy and employment" ;
        handle(Pattern.compile("\\ba.{3}t\\b"),input);//about
    }

    /**
     * \B 非单词边界
     */
    @Test
    public void testE(){
        String input = "College graduates at a job fair in Henan University. [Photo/IC]" ;
        handle(Pattern.compile("\\B\\[.*\\]\\B"),input);
        /**
         * \\B表示边界开始的地方,最后的\\B表示结束的地方,而\\[表示转义[为普通字符
         */
    }

    /**
     * \A 输入的开头
     */
    @Test
    public void testG(){
        String input = "A photo taken on May 22, 2019 shows a multi-functional AI-based autonomous driving bus with the features of a giant panda in Tianjin." ;
        handle(Pattern.compile("\\AA.{5}"),input);
    }

    /**
     * \Z 输入的结尾，仅用于最后的结束符（如果有的话）
     * \z 输入的结尾
     */
    @Test
    public void testH(){
        String input = "Vatican treasures on show at Beijing's Palace Museum" ;
        handle(Pattern.compile(".{5}m\\z"),input);
    }

    /**
     * \G 上一个匹配的结尾 ps(这个毕竟复杂)
     * 参考https://blog.csdn.net/q107770540/article/details/6436973
     * 参考 https://www.yiibai.com/javaregex/javaregex_boundary_matcher_previous.html
     * https://www.jb51.net/article/131947.htm
     */
    @Test
    public void testI(){
        String input = "The 4 trillion-yuan stimulus package - equivalent to approximately 1.7 percent of China's nominal GDP - is of a large scale, and will help offset the drag of trade tensions on growth, Pang said." ;
        handle(Pattern.compile("\\bt.{6}n\\b"),input);
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
