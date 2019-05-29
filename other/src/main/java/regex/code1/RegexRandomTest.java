package regex.code1;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;
import tool.utils.FileUtils;

import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: zch
 * @date: 2019/5/29 09:26
 * @description:
 */
public class RegexRandomTest {

    private final String RESOURCES = "resources\\";
    private final String SUFFIX = ".text";

    @Test
    public void testA()throws Exception{
        String pathA = FileUtils.getMainDataDir(this.getClass(), "") + RESOURCES + "c1" + SUFFIX;
        String pathB = FileUtils.getMainDataDir(this.getClass(), "") + RESOURCES + "c2" + SUFFIX;
        List<String> stringListA1 = org.apache.commons.io.FileUtils.readLines(new File(pathA)) ;
        List<String> stringListB1 = org.apache.commons.io.FileUtils.readLines(new File(pathB)) ;
        Pattern pattern = Pattern.compile("private\\s\\w+\\s");
        List<String> stringListA = Lists.newArrayList();
        List<String> stringListB = Lists.newArrayList();
        stringListA1.forEach(s -> {
            Matcher matcher = pattern.matcher(s) ;
            while (matcher.find()){
                if (StringUtils.isNotEmpty(matcher.group())){
                    String ss = org.apache.commons.lang.StringUtils.remove(matcher.group(),"private") ;
                    stringListA.add(StringUtils.trim(ss));
                }
            }
        });
        Pattern pattern1 = Pattern.compile("\\(\\w+\\.class\\)");
        stringListB1.forEach(s -> {
            Matcher matcher = pattern1.matcher(s) ;
            while (matcher.find()){
                if (StringUtils.isNotEmpty(matcher.group())){
                    String s1 = org.apache.commons.lang.StringUtils.remove(matcher.group(),"(") ;
                    String s2 = StringUtils.remove(s1,")") ;
                    String s3 = StringUtils.remove(s2,".class") ;
                    stringListB.add(StringUtils.trim(s3)) ;
                }
            }
        });
        System.out.println(stringListA.size());
        System.out.println(stringListB.size());
    }

}
