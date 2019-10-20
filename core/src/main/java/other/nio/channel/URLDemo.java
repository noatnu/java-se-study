package other.nio.channel;


import tool.log.LogUtilA;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLDemo {
    private static URLDemo urlDemo = new URLDemo();
    private final Logger loggerA = LogUtilA.setLoggerHanlder(Level.ALL);

    private URLDemo() {
    }

    public static URLDemo getUrlDemo() {
        return urlDemo;
    }

    public String urlDataTest(String urlPath) {
        StringBuilder builder = new StringBuilder(1024 * 1000);
//        String urlPath = "http://www.chinadaily.com.cn/";
        try {
            URL url = new URL(urlPath);
            //通过URL的openStrean方法获取URL对象所表示的自愿字节输入流
            InputStream inputStream = url.openStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String readData = null;
            while ((readData = bufferedReader.readLine()) != null) {
                builder.append(readData);
            }
            inputStream.close();
            bufferedReader.close();
        } catch (Exception e) {
            loggerA.warning("" + e.getMessage());
        }
        return builder.toString();
    }

    private String regex(String input) {
        String s = null;//columnists 专栏作家
        //http://www.chinadaily.com.cn/opinion/columnists.html
        Pattern pattern = Pattern.compile("http[s]?://[w]{3}[\\.]chinadaily[\\.]com[\\.]cn/opinion/[a-z]+[\\.]html");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            s = matcher.group();
        }
        return s;
    }
}
