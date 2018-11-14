package com.regex;


import org.junit.jupiter.api.Test;
import tool.log.LoggerFactoryGET;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Auther: zch
 * @Date: 2018/8/9 21:56
 * @Description:
 */
public class DemoB {
    private Logger logger = LoggerFactoryGET.getLoggerFactory().getLoggerAll();

    @Test
    public void test1(){
        logger.info("学习开始了!"+System.nanoTime());
    }

    /**
     *
     * 功能描述: 基础匹配
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2018/8/9 22:15
     */
    @Test
    public void test2(){
        String str = "China's largest forest park reopens to the public after six years" ;
        Pattern pattern = Pattern.compile("public");
        Matcher matcher = pattern.matcher(str);
        printA(matcher);
    }

    /**
     *
     * 功能描述:  .表示可以匹配任何 而\s表示匹配空白字符
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2018/8/9 22:20
     */
    @Test
    public void test3(){
        String str = "Evaluation centers employed by the traffic authority have stated that the woman does not have mental problems and was not under the influence of alcohol or drugs during the incident.";
        Pattern pattern = Pattern.compile("stated\\sthat\\s..");
        Matcher matcher = pattern.matcher(str);
        printA(matcher);
    }

    /**
     *
     * 功能描述: 匹配单个字
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2018/8/9 22:27
     */
    @Test
    public void test4(){
        String str = "好看的灵魂千篇一律,有趣的灵魂万里挑一" ;
        Pattern pattern = Pattern.compile("灵");
        Matcher matcher = pattern.matcher(str);
        printA(matcher);
    }

    /**
     *
     * 功能描述: 使用转义字符 \\
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2018/8/9 22:32
     */
    @Test
    public void test5(){
        String str = this.getClass().getSimpleName()+".java" ;
        Pattern pattern = Pattern.compile(".....\\.java");
        Matcher matcher = pattern.matcher(str);
        printA(matcher);
    }

    /**
     *
     * 功能描述: 匹配一组字符 [xs] 表示既可以匹配单字符x又可以匹配字符s
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2018/8/9 22:46
     */
    @Test
    public void test6(){
        String str = "TAMPA, Florida－NASA is poised to launch a $1.5 billion spacecraft on a brutally " +
                "hot journey toward the sun, offering scientists the closest view of our strange and mysterious star.";
        Pattern pattern = Pattern.compile("[Tt][Ah][Me]");
        Matcher matcher = pattern.matcher(str);
        matcher = Pattern.compile("[cv][oa][nr][ti][ro][ou][ls]").matcher("Pesticides are used to control various pests of agricultural crops worldwide. " +
                "Despite their agricultural benefits");
        printA(matcher);
    }

   /**
    *
    * 功能描述: ^表示取反的意思,标准说法是非
    *
    * @param:
    * @return:
    * @auther: zch
    * @date: 2018/8/9 22:50
    */
    @Test
    public void test7(){
        String str = "http://journal.hep.com.cn/fib/EN/10.1007/s11515-018-1489-z " ;
        Pattern pattern = Pattern.compile("[^0-9][^0-9][^0-9][^0-9]://");
        Matcher matcher = pattern.matcher(str);
        printA(matcher);
    }

    private void printA(Matcher matcher){
        while (matcher.find()){
            logger.info("==> "+matcher.group());
        }
    }

}
