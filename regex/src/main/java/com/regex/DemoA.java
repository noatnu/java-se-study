package com.regex;


import org.junit.jupiter.api.Test;
import tool.help.Zhou_StdRandom;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhou on 18-1-27.
 */
public class DemoA {
    private Logger logger = Logger.getLogger(this.toString());

    @Test
    public void isTest1() {//基础匹配
        String str = "A regular expression, specified as a string, must first be compiled into an instance of this class. ";
        Matcher matcher = Pattern.compile("regular").matcher(str);
//        while (matcher.find()) logger.info(matcher.group());
        System.out.println(matcher.matches());
    }

    @Test
    public void isTest2() {//.可以匹配任何字符
        StringBuilder builder = new StringBuilder(1024 * 2);
        builder.append("This").append(" ").append("interface").append(" ").append("contains").append(" ").append("query").append(" ").append("methods").append(" ").append("used").append(" ").append("to").append(" determine");
        Matcher matcher = Pattern.compile("in.").matcher(builder.toString());//.可以匹配任何字符,这表示in后面的任何字符存在都返回true
        while (matcher.find()) logger.info(matcher.group());
    }

    @Test
    public void isTest3() {//匹配单个字符
        StringBuilder builder = new StringBuilder(1024);
        builder.append("要么做第一个，要么做最好的一个");
        Matcher matcher = Pattern.compile("最").matcher(builder.toString());
        while (matcher.find()) logger.info(matcher.group());
    }

    @Test
    public void isTest4() {//使用转义字符  \\表示转义.
        String str = FileDemo.getStringBuilder(DOC.SRC.getName()).toString();
        System.out.println(str);
        Matcher matcher = Pattern.compile(".a.\\.java").matcher(str);//\\表示转义.
        while (matcher.find()) logger.info(matcher.group());
    }

    @Test
    public void isTest5() {//匹配一组字符
        String s = "The phrase \"regular expression\" is often abbreviated as RegEx or regex";//linux环境下转义""
        Matcher m = Pattern.compile("[Rr]eg[Ee]x").matcher(s);
        while (m.find()) logger.info(m.group());
        s = InputDemo.getText(DOC.INPUT.getName() + "isTest6B.txt");
        m = Pattern.compile("[ae].1\\.xlsx").matcher(s);
        /*这里的[ae]负责匹配字母a和字母e,而.负责匹配任意一位字母最后面的一位点被转义用来匹配'.'xlsx匹配xlsx*/
        while (m.find()) logger.info(m.group());
    }

    @Test
    public void isTest6() {
        /**
         * ^意思就是非　和C++ C Java　中的!是一个意思,这的[^0-9]取非0-9的字符,至于其它的匹配和isTest6B的含义一样
         * 一般来说^都是写在[]里面如:[^*]
         */
        String str = InputDemo.getText(DOC.INPUT.getName() + "isTest6B.txt");
        Matcher m = Pattern.compile("[ae].[^0-9]\\.xlsx").matcher(str);
        while (m.find()) logger.info(m.group());
    }

    @Test
    public void isTest7() {
        String str = InputDemo.getText(DOC.INPUT.getName() + "isTest7B.txt");//^a-zA-Z0-9表示#
        Matcher m = Pattern.compile("[^a-zA-Z0-9][A-Z0-9][A-Z0-9][A-Z0-9].[A-Za-z]").matcher(str);
        while (m.find()) logger.info(m.group());
    }

    @Test
    public void isTest8A() {
        StringBuilder builder = new StringBuilder(1024);
        builder.append("var myArray = new Array();\n" +
                "...\n" +
                "if(myArray[0] == 0){\n" +
                "\t...\n" +
                "}\n");//特殊字符都需要转义
        Matcher m = Pattern.compile("myArray\\[[0-9]\\]").matcher(builder.toString());
        while (m.find()) logger.info(m.group());
    }

    @Test
    public void isTest8B() {
        StringBuilder builder = new StringBuilder(1924);
        builder.append("11213\n" +
                "A1C2E3\n" +
                "48075\n" +
                "48237\n" +
                "M1B4F2\n" +
                "90046\n" +
                "H1H2H2");
        Matcher m = Pattern.compile("[a-zA-Z0-9_][\\d][\\w][\\d][\\w]").matcher(builder.toString());
        while (m.find()) logger.info(m.group());
    }

    @Test
    public void isTest9A() {
        StringBuilder builder = new StringBuilder(1024);
        builder.append("gdry@nnn.com");
        Matcher m = Pattern.compile("\\w\\w\\w\\w@\\w\\w\\w\\.com").matcher(builder.toString());
        while (m.find()) logger.info(m.group());
        /**
         * 这个正则表达式本身没有任何错误，可它几乎没有任何实际的用处只能匹配这样的哈哈哈gdry@nnn.com
         * （虽然在语法方面没有任何问题，但这显然不是一个合法的地址），并且我们无法预知电子邮件的各个字段会有多少个字符．
         * 不是吗？
         */
        builder.delete(0, builder.toString().length());
        builder.append("unsolicited email to spam@forta.com (wouldn't it be nico if it were that simple , huh?).");
        logger.info(builder.toString());
        m = Pattern.compile("\\w+@\\w+\\.\\w+").matcher(builder.toString());
        while (m.find()) logger.info(m.group());
        builder.append("or email info@pivotal.io.  All such requests should clearly specify:JavaMailSenderImpl sender = new JavaMailSenderImpl();\n" +
                "sender.setHost(\"mail.host.com\");\n" +
                "\n" +
                "MimeMessage message = sender.createMimeMessage();\n" +
                "MimeMessageHelper helper = new MimeMessageHelper(message);\n" +
                "helper.setTo(\"test@host.com\");\n" +
                "helper.setText(\"Thank you for ordering!\");\n" +
                "\n" +
                "sender.send(message);sggs g sn sdd@ffg.com.cn");
        //假如字符中有点的情况主要对付多层域名的情况com.cn net.cn china.net等等
        m = Pattern.compile("\\w+@[\\w.]+\\.\\w+").matcher(builder.toString());
        while (m.find()) logger.info("--------->" + m.group());
    }

    @Test
    public void isTest9B() {
        /**
         * +匹配一个或多个字符，但是不匹配零个字符－+最少也要匹配一个字符，那么你想要匹配一个可有可无的字符
         也就是说可以出现零个或者多个的情况(这个其实和xml中的编写是一样的,当然你得用过)该当如何．
         这种匹配需要用*元字符来完成．*的用法与+完全一样　只要把它放在一个字符(或一个字符集合)的后面，就可以匹配该字符(或字符集合)连续出现零次或多次的情况．比如说,模式B.* Forta将匹配B Forta , B. Forta ,Ben Forta和其它有类似规律的组合
         */
        StringBuilder builder = new StringBuilder(1024);
        builder.append("http://sourceforge.net/projects/cglib/files/cglib3/3.0/cglib-3.0.jar/download ");
        builder.append("http://www.springsource.org/download ");
        builder.append("https://jira.spring.io/browse/SPR ");
        builder.append("https://github.com/spring-projects/spring-framework#readme");
        String regex = "http[s]?://[\\w]+\\.\\w+[/\\-\\w\\.]+[#]?\\w+";
        Matcher m = Pattern.compile(regex).matcher(builder.toString());
        while (m.find()) logger.info("<---------" + m.group());
        /**
         * ?元字符表示有且一个或者一个都没有 *表示有零个或者一个或者多个
         */
    }

    @Test
    public void isTest9C() {
        StringBuilder builder = new StringBuilder(1024);
        builder.append("http://sourceforge.net/projects/cglib/files/cglib3/3.0/cglib-3.0.jar/download ");
        builder.append("http://www.springsource.org/download ");
        builder.append("https://jira.spring.io/browse/SPR ");
        builder.append("https://github.com/spring-projects/spring-framework#readme");
        /**
         * 上面使用过? + *其实这些和xml语法差不多的
         * +表示一次以及多次
         * ?表示零次或者一次
         * *表示零次　或者一次　或者多次
         * 上面这些元字符很多样化了,但是还是不太够,如果能够限定次数就好了,那么答案是肯定的
         * 次数表示为强制
         * {6}即表示６次,另外这是１０进制表示法,16进制表示法{:xdigit} 必须是６次
         * 除此之外还可以表示区间{0,6}意思很明显的０到６包含０和６本身闭区间嘛　必须０－６
         */
        Matcher m = Pattern.compile("http[s]{0,1}:{0,1}/{2}[\\w\\.\\-/]{0,}#{0,1}[\\w]{0,}").matcher(builder.toString());
        while (m.find()) logger.info("--------->" + m.group());
        builder.delete(0, builder.length());//重复次数还可以只确定最小数目,而不写最大数目{2,}表示最小２次
        builder.append(Zhou_StdRandom.uniform(90, 1220) + ":" + " $" + Zhou_StdRandom.uniform(2000.2, 9000.0) + "\n");
        m = Pattern.compile("[\\d]{2,4}:[ ]\\$[\\d]{4,}[\\.]\\d{0,}").matcher(builder.toString());
        logger.info("logger:" + builder.toString());
        while (m.find()) logger.info("<..................." + m.group());
    }

    @Test
    public void isTest10A() {//位置匹配
        StringBuilder builder = new StringBuilder(1024 * 2);
        builder.append("The cat scattered his food all over the room.");
        Matcher m = Pattern.compile("cat").matcher(builder.toString());
        while (m.find()) logger.info("...................>" + m.group());
        //假如这样一匹配 则直接匹配出２个cat,那么就不符合我们的预期了,那么该当如何呢?
        //因此需要确定单词边界
        /**
         * 第一种边界(也是最常用的边界)是由限定符\b指定的单词边界．
         顾名思义，\b用来匹配一个单词的开始或者结尾
         */
        m = Pattern.compile("\\bcat\\b").matcher(builder.toString());
        while (m.find()) logger.info("<------------------" + m.group());
        //如果你想表明不匹配一个单词边界，请使用\B.
        builder.delete(0, builder.length());
        builder.append("dsgg #hfhdd& hfdrere");
        m = Pattern.compile("\\B#hfhdd&\\B").matcher(builder.toString());
        while (m.find()) logger.info("------------------>" + m.group());
    }

    @Test
    public void isTest10B() {//位置匹配
        String str = InputDemo.getText(DOC.INPUT.getName() + "isTest10.txt");
        Matcher m = Pattern.compile(".+ina\\b").matcher(str);//匹配所有以ina的结尾
        while (m.find()) logger.info("<------------------" + m.group());
        m = Pattern.compile("\\bCBR.+").matcher(str);     //匹配以CBR开头的所有
        while (m.find()) logger.info("------------------>" + m.group());
    }

    @Test
    public void isTest10C() {
        //xml 都以<?xml开头　因此按照以前的思路
        String str = InputDemo.getText(DOC.INPUT.getName() + "XMLNews.xml");
        Matcher m = Pattern.compile("[\\w\\W]*<\\?xml.*\\?>[\\w\\W]*").matcher(str);
        while (m.find()) logger.info("<------------------" + m.group());
        logger.info("看起来似乎能够检验呢? 那么继续吧!");
        str = InputDemo.getText(DOC.INPUT.getName() + "note.xml");
        m = Pattern.compile("[\\w\\W]*<\\?xml.*\\?>[\\w\\W]*").matcher(str);
        while (m.find()) logger.info("------------------>" + m.group());
        //开头出现了中文:"故意留出前面一行用来干扰" 说明这不是标准格式啊
        logger.info("这里需要用到元字符 ^ 并且需要用到判断空白字符的元字符\\s");
        m = Pattern.compile("^\\s*<\\?xml.*\\?>[\\w\\W]*").matcher(str);
        while (m.find()) logger.info("<------------------" + m.group());
        logger.info("note.xml不能够被匹配出来");
    }

    @Test
    public void isTest11A() {//单行注释提取  多行注释提取
        String str = InputDemo.getText(DOC.INPUT.getName() + "isTest11A.js");
        logger.info(str);
        Matcher m = Pattern.compile("(?m)^\\s*//.*$").matcher(str);//$符号结尾,^开头,而(?m)则是分行匹配
        while (m.find()) logger.info("------------------>" + m.group());
        logger.info("多行注释提取");
        m = Pattern.compile("/\\*[\\*\\s]{0,}[\\w\\s\\W]{0,}.*/\\s{1,}").matcher(str);// /\*[\*\s]*[\w\W\s]*.*/\s{1,}
        while (m.find()) logger.info("<------------------" + m.group());
    }

    @Test
    public void isTest12A() {//子表达式
        String str = InputDemo.getText(DOC.INPUT.getName() + "IPAddress.txt");
        Matcher m = Pattern.compile("[\\d]{1,3}\\.[\\d]{1,3}\\.[\\d]{1,3}\\.[\\d]{1,3}").matcher(str);
        while (m.find()) logger.info("------------------>" + m.group());
        logger.info("虽然匹配出来了,但是我们发现哎呀我的妈　重复那么多有意思么　没意思啊," +
                "\n最主要的是它还可以匹配像999这样的数字,根据规定ipv4最大也就255但是这个哈哈逆天了(这个之后在考虑先考虑去掉重复问题)" +
                "\n下面对上面的正则表达式　用子表达式优化");
        m = Pattern.compile("(\\d{1,3}\\.){3}\\d{1,3}").matcher(str);
        while (m.find()) logger.info("<------------------" + m.group());
    }

    @Test
    public void isTest13A() {//这里需要注意的是回溯　　回的是变化的字符如1-6有123456我们不确定,还有就是只能回溯一次
        String str = InputDemo.getText(DOC.INPUT.getName() + "isTest13A.html");
        logger.info("普通写法");
        Matcher m = Pattern.compile("<[Hh][1-6][\\s\\S]*>.*</[Hh][1-6]>").matcher(str);
        while (m.find()) logger.info("------------------>" + m.group());
        logger.info("回溯");
        m = Pattern.compile("<[hH]([1-6])[\\s\\S]*>.*</[hH]\\1>").matcher(str);//这里必须得用子表达式包含变化的字符串不然回溯不起作用,回溯其实就是深度优先搜索算法的实现  ([1-6])要使用子表达式
        //另外:  <([hH])([1-6])[\s\S]*>.*</\1\1>  回溯只能在一个式子里面使用一次(至少Java里面是这样)==1次
        while (m.find()) logger.info("||<==logger:" + m.group());
        str = "<h1>标题1</h1><h2>标题2</h2><h3>标题3</h3><h4>标题4</h5>";
        m = Pattern.compile("<h([1-6])[\\s\\S]*>.*</h\\1>").matcher(str);
        while (m.find()) logger.info("||==>logger:" + m.group());
    }


}
