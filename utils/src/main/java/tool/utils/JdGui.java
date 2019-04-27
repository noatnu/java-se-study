package tool.utils;

import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.List;

/**
 * @Author noatn
 * @Description 处理 Java反编译后得代码
 * @createDate 2019/4/14
 **/
public class JdGui {
    public static void main(String[] args) throws Exception {
        replaceAll(new File("C:\\Users\\13426\\Downloads\\jd-gui-windows-1.4.0\\aspose-words-16.4.0.jar.src\\com"));
    }

    public static void init() throws Exception {
        final String regex = "(/\\*([^*]|[\\r\\n]|(\\*+([^*/]|[\\r\\n])))*\\*+/|[ \\t]*//.*)";
        String path = "C:\\Users\\13426\\Downloads\\jd-gui-windows-1.4.0\\aspose-words-16.4.0.jar.src\\";
        path += "asposewobfuscated";
        File file = new File(path);
        if (file.isDirectory()) {
            List<File> fileList = Lists.newArrayList(file.listFiles());
            for (File s : fileList) {
                if (s.isFile()) {
                    String text = getFileText(s);
                    String val = text.replaceAll(regex, "");
                    System.out.println(text.length() + "-" + val.length());
                    org.apache.commons.io.FileUtils.writeByteArrayToFile(s, val.getBytes("UTF-8"), false);
                }
            }
        }
    }

    public static void replaceAll(File file) throws Exception {
        final String regex = "(/\\*([^*]|[\\r\\n]|(\\*+([^*/]|[\\r\\n])))*\\*+/|[ \\t]*//.*)";
        if (file.isDirectory()) {
            List<File> fileList = Lists.newArrayList(file.listFiles());
            if (CollectionUtils.isNotEmpty(fileList)) {
                for (File s : fileList) {
                    replaceAll(s);
                }
            }
        } else {
            if (file.isFile()) {
                String text = getFileText(file);
                String val = text.replaceAll(regex, "");
                System.out.println(text.length() + "-" + val.length());
                org.apache.commons.io.FileUtils.writeByteArrayToFile(file, val.getBytes("UTF-8"), false);
            }
        }
    }

    private static String getFileText(File file) throws Exception {
        return FileUtils.readFileToString(file, "UTF-8");
    }
}
