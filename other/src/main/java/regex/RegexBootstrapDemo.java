package regex;

import com.google.common.base.Objects;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.CharEncoding;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: zch
 * @date: 2019/5/20 14:34
 * @description:
 */
public class RegexBootstrapDemo {


    @Test
    public void testA() throws IOException {
        final String path = "D:\\temp\\declareApplyModel - 副本.jsp";
        List<String> strings = FileUtils.readLines(new File(path), CharEncoding.UTF_8);
        LinkedList<String> stringLinkedList = Lists.newLinkedList();
        Multimap multimap = getMultimap();
        Collection<String> collectionA = multimap.get("a");
        Collection<String> collectionB = multimap.get("b");
        StringBuilder stringBuilder = new StringBuilder(128);
        for (String s : strings) {
            String number = getNumber(s);
            Integer integer = 0;
            String a = null;
            String b = null;
            if (StringUtils.isNotEmpty(number)) {
                collectionA.stream().forEachOrdered(str -> {
                    String value = String.format("%s%s ", str, number);
                    stringBuilder.append(value);
                });
                a = s.replaceFirst("col-sm-offset-[0-9]", stringBuilder.toString());
                stringBuilder.delete(0, stringBuilder.toString().length());

                collectionB.stream().forEachOrdered(str -> {
                    String value = String.format("%s%s ", str, number);
                    stringBuilder.append(value);
                });
                b = s.replaceFirst("col-sm-[0-9]", stringBuilder.toString());
                stringBuilder.delete(0, stringBuilder.toString().length());
                if (!Objects.equal(a, s)) {
                    integer = 1;
                }
                if (!Objects.equal(b, s)) {
                    integer = 2;
                }
            }
            if (integer.intValue() == 0) {
                stringLinkedList.add(s);
            }
            if (integer.intValue() == 2) {
                if (StringUtils.isNotEmpty(b)) {
                    stringLinkedList.add(b);
                }
            }
            if (integer.intValue() == 1) {
                if (StringUtils.isNotEmpty(a)) {
                    stringLinkedList.add(a);
                }
            }
        }
        System.out.println(StringUtils.join(stringLinkedList, " "));
        FileUtils.writeLines(new File(path), stringLinkedList);
    }


    private String getNumber(String text) {
        if (StringUtils.isEmpty(text)) {
            return null;
        }
        if (org.apache.commons.lang.math.NumberUtils.isNumber(text)) {
            return text;
        }
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(text);
        String s = m.replaceAll("").trim();
        return StringUtils.isNotBlank(s) ? s : null;
    }

    private Multimap getMultimap() {
        Multimap<String, String> bootstraps = ArrayListMultimap.create();
        bootstraps.put("b", "col-xs-");
        bootstraps.put("b", "col-sm-");
        bootstraps.put("b", "col-md-");
        bootstraps.put("b", "col-lg-");

        bootstraps.put("a", "col-xs-offset-");
        bootstraps.put("a", "col-sm-offset-");
        bootstraps.put("a", "col-md-offset-");
        bootstraps.put("a", "col-lg-offset-");
        return bootstraps;
    }

}
