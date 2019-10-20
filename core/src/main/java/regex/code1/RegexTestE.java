package regex.code1;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;
import tool.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @Author zch
 * @Description
 * @createDate 2019/5/29
 **/
public class RegexTestE {
    //Java正则贪婪量
    /*
    * * 重复零次或更多次
    * * 例如：a* 匹配零个或者多个 a
    * * + 重复一次或更多次
    * * 例如：a+ 匹配一个或者多个 a
    * * ? 重复零次或一次
    * * 例如：a? 匹配零个或一个 a
    * * {n} 重复 n 次
    * * 例如：a{4} 匹配 4 个 a
    * * {n,} 重复 n 次或更多次
    * * 例如：a{4,} 匹配至少 4 个 a
    * * {n,m} 重复 n 到 m 次
    * * 例如：a{4,10} 匹配 4~10 个 a
    * */

    /**
     * 在Java中数量词 +和?比*好使 似乎*不怎么灵
     */

    /**
     * ?表示匹配0次或者一次 {number} 表示匹配number次
     */
    @Test
    public void testA(){
        String input = "Important changes to gaokao exam policy" ;
        handle(Pattern.compile("c\\w{6}\\s?to"),input);
    }

    /**
     * {n,m} 次
     */
    @Test
    public void testB(){
        String path = FileUtils.getMainDataDir(this.getClass(), "") ;
        File[] files = new File(path+"resources").listFiles();
        List<String> stringList = Lists.newArrayList();
        Arrays.stream(files).forEachOrdered(file -> {
            if (file.isFile()){
                try {
                    stringList.addAll(org.apache.commons.io.FileUtils.readLines(file));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        //重复匹配至少3次
        handle(Pattern.compile(".{3,}"), StringUtils.join(stringList," "));
        //重复匹配3到10次
        handle(Pattern.compile(".{3,10}"), StringUtils.join(stringList," "));
    }

    /**
     * + 匹配1次到多次
     */
    @Test
    public void testE(){
        String input = "Representatives of 473 companies from home and abroad will also participate in the expo, up 82 percent from the year before.";
        handle(Pattern.compile("\\s\\w+\\s"),input);
    }

    /**
     * * 匹配零个或者多个
     */
    @Test
    public void testC(){
        String input = "夏，北京城故宫外的景山之巅，“神鸦”伴着蔚蓝的天空与落日的余晖，久久盘旋，不愿离去，似在诉说着什么，动人情肠。" ;
        handle(Pattern.compile("，*.{3}"),input);
    }

    /**
     * 转义
     */
    @Test
    public void testEscape() {
        List<String> stringList = Arrays.asList(new File(FileUtils.getMainDataDir(this.getClass(), "")).listFiles()).stream().map(File::getName).peek(System.out::println).collect(Collectors.toList());
        handle(Pattern.compile(String.format("%s%s", this.getClass().getSimpleName(), "\\.java")), StringUtils.join(stringList, ","));
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
