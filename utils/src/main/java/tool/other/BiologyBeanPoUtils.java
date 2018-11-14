package tool.other;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;
import tool.entity.*;
import tool.help.Zhou_StdRandom;
import tool.help.Zhou_String;
import tool.help.Zhou_Word;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Auther: zch
 * @Date: 2018/9/5 22:06
 * @Description:
 */
public class BiologyBeanPoUtils {
    public List<BookExample> examples = Lists.newArrayList();
    private static BiologyBeanPoUtils biologyBeanPoUtils = new BiologyBeanPoUtils();

    public List<NodeExample> nodeExampleList(){
        List<NodeExample> exampleList = Lists.newArrayList();
        NodeExample nodeExample = null;
        for (int i = 0; i < 10; i++) {
            ChildrenExample childrenExample = new ChildrenExample();
            nodeExample = new NodeExample();
            nodeExample.setId(Zhou_String.toMath(12));
            nodeExample.setText(Zhou_String.toLowerCase(11));
            for (int j = 10; j < 100; j++) {
                NodeExample nodeExample1 = new NodeExample();
                nodeExample1.setText(Zhou_String.toUpperCase(8));
                nodeExample1.setId(Zhou_String.toMath(7));
                childrenExample.addChild(nodeExample1);
            }
            nodeExample.setChildren(childrenExample);
            exampleList.add(nodeExample);
        }
        return exampleList;
    }

    public List<SimpleBean> simpleBeanList(){
        List<SimpleBean> simpleBeans = Lists.newArrayList();
        final int num = 10;
        SimpleBean simpleBean = null;
        SimpleBean simple = null;
        simpleBeans.add(new SimpleBean().setId(0).setNum(0).setName("alice").setPid(0).setTitle("root"));
        for (int i = 1; i < num; i++) {
            simpleBean = new SimpleBean();
            simpleBean.setPid(0);
            simpleBean.setId(i);
            simpleBean.setName(Zhou_Word.getChineseName());
            simpleBean.setNum(Zhou_StdRandom.uniform(3,100));
            simpleBean.setTitle(Zhou_String.toLowerCase(5));
            simpleBeans.add(simpleBean);
            for (int j = 1; j < num; j++) {
                simple = new SimpleBean();
                simple.setPid(i);
                simple.setId(j);
                simple.setName(Zhou_Word.getChineseName());
                simple.setNum(Zhou_StdRandom.uniform(3,100));
                simple.setTitle(Zhou_String.toLowerCase(5));
                simpleBeans.add(simple);
            }
        }
        return simpleBeans;
    }

    public List<BiologyBeanPo> prototypeObj() {
        List<BiologyBeanPo> biologyBeanPoList = Lists.newArrayList();
        PinYinUtil pinYinUtil = new PinYinUtil();
        Map<String, String> map = Maps.newHashMap();
        map.put("a1", pinYinUtil.getStringPinYin("水生生物"));
        map.put("a2", pinYinUtil.getStringPinYin("陆地生物"));
        map.put("a3", pinYinUtil.getStringPinYin("飞翔生物"));
        map.put("a4", pinYinUtil.getStringPinYin("两栖生物"));
        BiologyBeanPo biologyBeanPo = null;
        int m = 0;
        biologyBeanPoList.add(new BiologyBeanPo().setPid(0).setId(1).setTitle(map.get("a1")).setDate(new Date()).setName("水生生物").setOpen(true).setNumber(String.format("%d",m++)).setArea(Zhou_StdRandom.uniform(1.0,150.0)));
        biologyBeanPoList.add(new BiologyBeanPo().setPid(0).setId(2).setTitle(map.get("a2")).setDate(new Date()).setName("陆地生物").setOpen(true).setNumber(String.format("%d",m++)).setArea(Zhou_StdRandom.uniform(1.0,150.0)));
        biologyBeanPoList.add(new BiologyBeanPo().setPid(0).setId(3).setTitle(map.get("a3")).setDate(new Date()).setName("飞翔生物").setNumber(String.format("%d",m++)).setArea(Zhou_StdRandom.uniform(1.0,150.0)));
        biologyBeanPoList.add(new BiologyBeanPo().setPid(0).setId(4).setTitle(map.get("a4")).setDate(new Date()).setName("两栖生物").setOpen(true).setNumber(String.format("%d",m++)).setArea(Zhou_StdRandom.uniform(1.0,150.0)));
        final int num = 10;
        final int v = 4;
        for (int j = 1; j <= v; j++) {
            for (int i = 1; i < num; i++) {
                String a = String.valueOf(j);
                String k = String.format("%s%d", a, i);
                if (StringUtils.isNumeric(k)) {
                    int n = Integer.parseInt(k);
                    if (StringUtils.isNumeric(a)) {
                        if (i == 1) {
                            biologyBeanPo = new BiologyBeanPo();
                            biologyBeanPo.set_parentId(Integer.parseInt(a)).setPid(Integer.parseInt(a)).setId(n).setDate(new Date()).setOpen(true);
                            if (a.equals("1")) {
                                biologyBeanPo.setTitle("鲨鱼").setName("鲨鱼");
                                int temp = Zhou_StdRandom.uniform(2,10);
                                biologyBeanPoList.add(new BiologyBeanPo().set_parentId(biologyBeanPo.getId()).setPid(biologyBeanPo.getId()).setName("姥鲨").setTitle("姥鲨").setDate(new Date()).setId(Zhou_StdRandom.uniform(1000+ ++temp,2000+ ++temp)).setArea(Zhou_StdRandom.uniform(1.0,150.0)));
                                biologyBeanPoList.add(new BiologyBeanPo().set_parentId(biologyBeanPo.getId()).setPid(biologyBeanPo.getId()).setName("斗鲨鱼").setTitle("斗鲨鱼").setDate(new Date()).setId(Zhou_StdRandom.uniform(1000+ ++temp,2000+ ++temp)).setArea(Zhou_StdRandom.uniform(1.0,150.0)));
                                biologyBeanPoList.add(new BiologyBeanPo().set_parentId(biologyBeanPo.getId()).setPid(biologyBeanPo.getId()).setName("巨齿鲨").setTitle("巨齿鲨").setDate(new Date()).setId(Zhou_StdRandom.uniform(1000+ ++temp,2000+ ++temp)).setArea(Zhou_StdRandom.uniform(1.0,150.0)));
                                biologyBeanPoList.add(new BiologyBeanPo().set_parentId(biologyBeanPo.getId()).setPid(biologyBeanPo.getId()).setName("哥布林鲨").setTitle("哥布林鲨").setDate(new Date()).setId(Zhou_StdRandom.uniform(1000+ ++temp,2000+ ++temp)).setArea(Zhou_StdRandom.uniform(1.0,150.0)));
                                biologyBeanPoList.add(new BiologyBeanPo().set_parentId(biologyBeanPo.getId()).setPid(biologyBeanPo.getId()).setName("雪茄达摩鲨").setTitle("雪茄达摩鲨").setDate(new Date()).setId(Zhou_StdRandom.uniform(1000+ ++temp,2000+ ++temp)).setArea(Zhou_StdRandom.uniform(1.0,150.0)));
                                biologyBeanPoList.add(new BiologyBeanPo().set_parentId(biologyBeanPo.getId()).setPid(biologyBeanPo.getId()).setName("皱鳃鲨").setTitle("皱鳃鲨").setDate(new Date()).setId(Zhou_StdRandom.uniform(1000+ ++temp,2000+ ++temp)).setArea(Zhou_StdRandom.uniform(1.0,150.0)));
                                biologyBeanPoList.add(new BiologyBeanPo().set_parentId(biologyBeanPo.getId()).setPid(biologyBeanPo.getId()).setName("长尾鲨").setTitle("长尾鲨").setDate(new Date()).setId(Zhou_StdRandom.uniform(1000+ ++temp,2000+ ++temp)).setArea(Zhou_StdRandom.uniform(1.0,150.0)));
                                biologyBeanPoList.add(new BiologyBeanPo().set_parentId(biologyBeanPo.getId()).setPid(biologyBeanPo.getId()).setName("巨口鲨").setTitle("巨口鲨").setDate(new Date()).setId(Zhou_StdRandom.uniform(1000+ ++temp,2000+ ++temp)).setArea(Zhou_StdRandom.uniform(1.0,150.0)));
                            }
                            if (a.equals("2")) {
                                biologyBeanPo.setTitle("大象");
                                biologyBeanPo.setName("大象");
                            }
                            if (a.equals("3")) {
                                biologyBeanPo.setTitle("勺嘴鹬");
                                biologyBeanPo.setName("勺嘴鹬");
                            }
                            if (a.equals("4")) {
                                biologyBeanPo.setTitle("青蛙");
                                biologyBeanPo.setName("青蛙");
                            }
                            biologyBeanPoList.add(biologyBeanPo);
                        }
                        if (i == 2) {
                            biologyBeanPo = new BiologyBeanPo();
                            biologyBeanPo.set_parentId(Integer.parseInt(a)).setPid(Integer.parseInt(a)).setId(n).setDate(new Date()).setOpen(true);
                            if (a.equals("1")) {
                                biologyBeanPo.setTitle("鲸鱼").setName("鲸鱼");
                                biologyBeanPoList.add(new BiologyBeanPo().set_parentId(biologyBeanPo.getId()).setPid(biologyBeanPo.getId()).setName("蓝鲸").setTitle("蓝鲸").setDate(new Date()).setId(Zhou_StdRandom.uniform(1000,2000)).setArea(Zhou_StdRandom.uniform(1.0,150.0)));
                                biologyBeanPoList.add(new BiologyBeanPo().set_parentId(biologyBeanPo.getId()).setPid(biologyBeanPo.getId()).setName("虎鲸").setTitle("虎鲸").setDate(new Date()).setId(Zhou_StdRandom.uniform(1001,2001)).setArea(Zhou_StdRandom.uniform(1.0,150.0)));
                                biologyBeanPoList.add(new BiologyBeanPo().set_parentId(biologyBeanPo.getId()).setPid(biologyBeanPo.getId()).setName("逆戟鲸").setTitle("逆戟鲸").setDate(new Date()).setId(Zhou_StdRandom.uniform(1002,2002)).setArea(Zhou_StdRandom.uniform(1.0,150.0)));
                                biologyBeanPoList.add(new BiologyBeanPo().set_parentId(biologyBeanPo.getId()).setPid(biologyBeanPo.getId()).setName("角鲸").setTitle("角鲸").setDate(new Date()).setId(Zhou_StdRandom.uniform(1004,2004)).setArea(Zhou_StdRandom.uniform(1.0,150.0)));
                                biologyBeanPoList.add(new BiologyBeanPo().set_parentId(biologyBeanPo.getId()).setPid(biologyBeanPo.getId()).setName("白鲸").setTitle("白鲸").setDate(new Date()).setId(Zhou_StdRandom.uniform(1004,2004)).setArea(Zhou_StdRandom.uniform(1.0,150.0)));
                            }
                            if (a.equals("2")) {
                                biologyBeanPo.setTitle("狮子");
                                biologyBeanPo.setName("狮子");
                            }
                            if (a.equals("3")) {
                                biologyBeanPo.setTitle("白腰滨鹬");
                                biologyBeanPo.setName("白腰滨鹬");
                            }
                            if (a.equals("4")) {
                                biologyBeanPo.setTitle("牛蛙");
                                biologyBeanPo.setName("牛蛙");
                            }
                            biologyBeanPoList.add(biologyBeanPo);
                        }
                        if (i == 3) {
                            biologyBeanPo = new BiologyBeanPo();
                            biologyBeanPo.set_parentId(Integer.parseInt(a)).setPid(Integer.parseInt(a)).setId(n).setDate(new Date()).setOpen(false);
                            if (a.equals("1")) {
                                biologyBeanPo.setTitle("海豹").setName("海豹");
                            }
                            if (a.equals("2")) {
                                biologyBeanPo.setTitle("老虎").setName("老虎");
                                int temp = Zhou_StdRandom.uniform(2,10);
                                biologyBeanPoList.add(new BiologyBeanPo().set_parentId(biologyBeanPo.getId()).setPid(biologyBeanPo.getId()).setName("东北虎").setTitle("东北虎").setDate(new Date()).setId(Zhou_StdRandom.uniform(1000+ ++temp,2000+ ++temp)).setArea(Zhou_StdRandom.uniform(1.0,150.0)));
                                biologyBeanPoList.add(new BiologyBeanPo().set_parentId(biologyBeanPo.getId()).setPid(biologyBeanPo.getId()).setName("华南虎").setTitle("华南虎").setDate(new Date()).setId(Zhou_StdRandom.uniform(1000+ ++temp,2000+ ++temp)).setArea(Zhou_StdRandom.uniform(1.0,150.0)));
                                biologyBeanPoList.add(new BiologyBeanPo().set_parentId(biologyBeanPo.getId()).setPid(biologyBeanPo.getId()).setName("巴厘虎").setTitle("巴厘虎").setDate(new Date()).setId(Zhou_StdRandom.uniform(1000+ ++temp,2000+ ++temp)).setArea(Zhou_StdRandom.uniform(1.0,150.0)));
                                biologyBeanPoList.add(new BiologyBeanPo().set_parentId(biologyBeanPo.getId()).setPid(biologyBeanPo.getId()).setName("印度支那虎").setTitle("印度支那虎").setDate(new Date()).setId(Zhou_StdRandom.uniform(1000+ ++temp,2000+ ++temp)).setArea(Zhou_StdRandom.uniform(1.0,150.0)));
                                biologyBeanPoList.add(new BiologyBeanPo().set_parentId(biologyBeanPo.getId()).setPid(biologyBeanPo.getId()).setName("马来虎").setTitle("马来虎").setDate(new Date()).setId(Zhou_StdRandom.uniform(1000+ ++temp,2000+ ++temp)).setArea(Zhou_StdRandom.uniform(1.0,150.0)));
                                biologyBeanPoList.add(new BiologyBeanPo().set_parentId(biologyBeanPo.getId()).setPid(biologyBeanPo.getId()).setName("爪哇虎").setTitle("爪哇虎").setDate(new Date()).setId(Zhou_StdRandom.uniform(1000+ ++temp,2000+ ++temp)).setArea(Zhou_StdRandom.uniform(1.0,150.0)));
                                biologyBeanPoList.add(new BiologyBeanPo().set_parentId(biologyBeanPo.getId()).setPid(biologyBeanPo.getId()).setName("苏门答腊虎").setTitle("苏门答腊虎").setDate(new Date()).setId(Zhou_StdRandom.uniform(1000+ ++temp,2000+ ++temp)).setArea(Zhou_StdRandom.uniform(1.0,150.0)));
                                biologyBeanPoList.add(new BiologyBeanPo().set_parentId(biologyBeanPo.getId()).setPid(biologyBeanPo.getId()).setName("孟加拉虎").setTitle("孟加拉虎").setDate(new Date()).setId(Zhou_StdRandom.uniform(1000+ ++temp,2000+ ++temp)).setArea(Zhou_StdRandom.uniform(1.0,150.0)));
                                biologyBeanPoList.add(new BiologyBeanPo().set_parentId(biologyBeanPo.getId()).setPid(biologyBeanPo.getId()).setName("里海虎").setTitle("里海虎").setDate(new Date()).setId(Zhou_StdRandom.uniform(1000+ ++temp,2000+ ++temp)).setArea(Zhou_StdRandom.uniform(1.0,150.0)));
                            }
                            if (a.equals("3")) {
                                biologyBeanPo.setTitle("岩滨鹬");
                                biologyBeanPo.setName("岩滨鹬");
                            }
                            if (a.equals("4")) {
                                biologyBeanPo.setTitle("蟾蜍");
                                biologyBeanPo.setName("蟾蜍");
                            }
                            biologyBeanPoList.add(biologyBeanPo);
                        }
                        if (i == 4) {
                            biologyBeanPo = new BiologyBeanPo();
                            biologyBeanPo.set_parentId(Integer.parseInt(a)).setPid(Integer.parseInt(a)).setId(n).setDate(new Date()).setOpen(false).setArea(Zhou_StdRandom.uniform(1.0,150.0));
                            if (a.equals("1")) {
                                biologyBeanPo.setTitle("水母Jellyfish");
                                biologyBeanPo.setName("水母Jellyfish");
                            }
                            if (a.equals("2")) {
                                biologyBeanPo.setTitle("猴子");
                                biologyBeanPo.setName("猴子");
                            }
                            if (a.equals("3")) {
                                biologyBeanPo.setTitle("高跷鹬");
                                biologyBeanPo.setName("高跷鹬");
                            }
                            if (a.equals("4")) {
                                biologyBeanPo.setTitle("大鲵");
                                biologyBeanPo.setName("大鲵");
                            }
                            biologyBeanPoList.add(biologyBeanPo);
                        }
                        if (i == 5) {
                            biologyBeanPo = new BiologyBeanPo();
                            biologyBeanPo.set_parentId(Integer.parseInt(a)).setPid(Integer.parseInt(a)).setId(n).setDate(new Date()).setOpen(false).setArea(Zhou_StdRandom.uniform(1.0,150.0));
                            if (a.equals("1")) {
                                biologyBeanPo.setTitle("章鱼Octopus");
                                biologyBeanPo.setName("章鱼Octopus");
                            }
                            if (a.equals("2")) {
                                biologyBeanPo.setTitle("犀牛");
                                biologyBeanPo.setName("犀牛");
                            }
                            if (a.equals("3")) {
                                biologyBeanPo.setTitle("铜翅水雉");
                                biologyBeanPo.setName("铜翅水雉");
                            }
                            if (a.equals("4")) {
                                biologyBeanPo.setTitle("河马");
                                biologyBeanPo.setName("河马");
                            }
                            biologyBeanPoList.add(biologyBeanPo);
                        }
                        if (i==6){
                            biologyBeanPo = new BiologyBeanPo();
                            biologyBeanPo.set_parentId(Integer.parseInt(a)).setPid(Integer.parseInt(a)).setId(n).setDate(new Date()).setOpen(false).setArea(Zhou_StdRandom.uniform(1.0,150.0));
                            switch (a){
                                case "1":
                                    biologyBeanPo.setTitle("乌贼 cuttlefish").setName("乌贼 cuttlefish");
                                    break;
                                case "2":
                                    biologyBeanPo.setTitle("熊").setName("熊");
                                    break;
                                case "3":
                                    biologyBeanPo.setTitle("大石鸻").setName("大石鸻");
                                    break;
                                case "4":
                                    biologyBeanPo.setTitle("大鲵").setName("大鲵");
                                    break;
                            }
                            biologyBeanPoList.add(biologyBeanPo);
                        }
                        if (i==7){
                            biologyBeanPo = new BiologyBeanPo();
                            biologyBeanPo.set_parentId(Integer.parseInt(a)).setPid(Integer.parseInt(a)).setId(n).setDate(new Date()).setOpen(false).setArea(Zhou_StdRandom.uniform(1.0,150.0));
                            switch (a){
                                case "1":
                                    biologyBeanPo.setTitle("海参").setName("海参");
                                    break;
                                case "2":
                                    biologyBeanPo.setTitle("猫").setName("猫");
                                    break;
                                case "3":
                                    biologyBeanPo.setTitle("鹰").setName("鹰");
                                    break;
                                case "4":
                                    biologyBeanPo.setTitle("小鲵").setName("小鲵");
                                    break;
                            }
                            biologyBeanPoList.add(biologyBeanPo);
                        }
                        if (i==8){
                            biologyBeanPo = new BiologyBeanPo();
                            biologyBeanPo.set_parentId(Integer.parseInt(a)).setPid(Integer.parseInt(a)).setId(n).setDate(new Date()).setOpen(false).setArea(Zhou_StdRandom.uniform(1.0,150.0));
                            switch (a){
                                case "1":
                                    biologyBeanPo.setTitle("海豚").setName("海豚");
                                    break;
                                case "2":
                                    biologyBeanPo.setTitle("狗").setName("狗");
                                    break;
                                case "3":
                                    biologyBeanPo.setTitle("海鸥").setName("海鸥");
                                    break;
                                case "4":
                                    biologyBeanPo.setTitle("鳄鱼").setName("鳄鱼");
                                    break;
                            }
                            biologyBeanPoList.add(biologyBeanPo);
                        }

                    }
                }
            }

        }
        return biologyBeanPoList;
    }

    public static BiologyBeanPoUtils getBiologyBeanPoUtils() {
        if (biologyBeanPoUtils == null){
            biologyBeanPoUtils = new BiologyBeanPoUtils();
        }
        return biologyBeanPoUtils;
    }

    private BiologyBeanPoUtils(){
        init();
    }

    /**
     *
     * 功能描述:
     *
     * @param:page(多少页)
     * @param:pageSize(每页数量)
     * @return:BookExample列表
     * @auther: zch
     * @date: 2018/9/16 11:59
     */
    public List<BookExample> bookExampleList(int pageSize, int page){
        List<BookExample> vos = Lists.newArrayList();
        for (int i = 0; i < examples.size(); i++) {
            if (i >= (page-1)*pageSize && i <  ((page-1)*pageSize+pageSize)   ){
                vos.add(examples.get(i));
            }
        }
        return vos;
    }

    private void init(){
        int id = 0;
        examples.add(new BookExample().setAuthor("杨显惠").setPrice(34.00).setTitle("夹边沟记事").setLabel("当代文学").setContent(
                "这是一段尘封四十年的历史，当年的幸存者散落在各个角落，没有人问过他们到底发生了什么，" +
                        "当年的死难者早已化为白骨，连他们的后代也不知道埋在何处。幸亏杨显惠这位有...").setId(String.valueOf(++id)).setCreateTime(new Date()));

        examples.add(new BookExample().setAuthor("刘震云").setId(String.valueOf(++id)).setPrice(29.80).setTitle("一句顶一万句").setLabel("当代文学").setCreateTime(new Date()).setContent(
                "《一句顶一万句》的故事很简单，小说的前半部写的是过去：孤独无助的吴摩西失去唯一能够“说得上话”的养女，为了寻找，走出延津；小说的后半部写的是现在：吴摩西养女..."));

        examples.add(new BookExample().setAuthor("阿城").setPrice(13.00).setTitle("棋王").setLabel("当代文学").setContent(
                "在处女作《棋王》中，阿城表现出自己的哲学：“普遍认为很苦的知青生活，在生活水准低下的贫民阶层看来，也许是物质上升了一级呢！另外就是普通人的‘英雄’行为常常是...").setId(String.valueOf(++id)).setCreateTime(new Date()));

        examples.add(new BookExample().setAuthor("白先勇 ").setPrice( 21.80).setTitle("孽子 : 孽子").setLabel("当代文学").setContent(
                "放逐 在我们的王国里 安乐乡 那些青春鸟的行旅 研悲情为金粉的歌剧 白先勇写作年表"
        ).setId(String.valueOf(++id)).setCreateTime(new Date()));


        examples.add(new BookExample().setAuthor("阿城 ").setPrice( 25.00).setTitle("阿城精选集").setLabel("当代文学").setContent(
                "20世纪的社会生活风云激荡，沧桑巨变。20世纪的华文文学也波澜壮阔，气象万千。上承19世纪。下启21世纪的20世纪华文文学，在与社会生活的密切连接和与时代情..."
        ).setId(String.valueOf(++id)).setCreateTime(new Date()));

        examples.add(new BookExample().setAuthor("迟子建 ").setPrice( 29.00).setTitle("额尔古纳河右岸").setLabel("当代文学").setContent(
                "这是第一部描述我国东北少数民族鄂温克人生存现状及百年沧桑的长篇小说。似一壁饱得天地之灵气，令人惊叹却难得其解的神奇岩画；又似一卷时而安恬、时而激越，向世人诉..."
        ).setId(String.valueOf(++id)).setCreateTime(new Date()));

        examples.add(new BookExample().setAuthor("阿来 ").setPrice( 22.0).setTitle("尘埃落定").setLabel("当代文学").setContent(
                "一个声势显赫的康巴藏族土司，在酒后和汉族太太生了一个傻瓜儿子。这个人人都认定的傻子与现实生活格格不入，却有着超时代的预感和举止，成为土司制度兴衰的见证人。小..."
        ).setId(String.valueOf(++id)).setCreateTime(new Date()));

        examples.add(new BookExample().setAuthor("王小波").setPrice( 28.00).setTitle("红拂夜奔").setLabel("当代文学").setContent(
                "《红拂夜奔》这书题就会让读者认为是写隋末杨素家妓红拂敬慕大军事家李靖，私奔相从的风流逸事。然而，这本书它以现代人的眼光去观照历史，又以历史文化原型来建构现代..."
        ).setId(String.valueOf(++id)).setCreateTime(new Date()));

        examples.add(new BookExample().setAuthor("王朔").setPrice( 15.5).setTitle("动物凶猛").setLabel("当代文学").setContent(
                "他们逃课、泡妞、打群架，他们由于“不必学习那些后来注定要忘掉的无用的知识”而使自身的动物本能获得了空前的解放； 他们深知自己的未来已被框定于固定的范畴之内，..."
        ).setId(String.valueOf(++id)).setCreateTime(new Date()));

        examples.add(new BookExample().setAuthor("阿城 ").setPrice( 200).setTitle("棋王.樹王.孩子王").setLabel("当代文学").setContent(
                "20世紀中文小說100強，阿城的代表作。李家同最愛的40本書之一 阿城的三個中篇：棋王，樹王，孩子王，取材截然不同，可是，他所要表達的主題是相同的：人，是最..."
        ).setId(String.valueOf(++id)).setCreateTime(new Date()));

        examples.add(new BookExample().setAuthor("陈忠实 ").setPrice(28.00).setTitle("白鹿原").setLabel("当代文学").setContent(
                "这是一部渭河平原五十年变迁的雄奇史诗，一轴中国农村班斓多彩、触目惊心的长幅画卷。主人公六娶六丧，神秘的序曲预示着不祥。一个家族两代子孙，为争夺白鹿原的统治代..."
        ).setId(String.valueOf(++id)).setCreateTime(new Date()));

        examples.add(new BookExample().setAuthor(" 北岛  ").setPrice(35.00).setTitle("城门开").setLabel("当代文学").setContent(
                "生于北京，长于北京的作者，阔别家乡十三年后重回北京，发现他在自己的故乡成了异乡人，仿佛到了一座陌生的城市。于是他便要“重建我的北京”——用他文字召回北京消失的气味儿、声音和光影，恢复被拆除的四合院、胡同和庙宇的原貌，让瓦顶排浪般涌向低低天际线的景象重现，让鸽哨响彻蓝天的清脆回到人们耳边……同时，作者也把我们带回到他儿时的游戏中，带回到他的读书生活中，带回到他的母校，带回的“大串联”的旅途……他重建了这座“孩子们熟知四季变化，" +
                        "居民们胸有方向感”的城池，打开城门，欢迎四海漂泊的游子，欢迎无家可归的孤魂，欢迎所有好奇的客人们。"
        ).setId(String.valueOf(++id)).setCreateTime(new Date()));

        examples.add(new BookExample().setAuthor("苏童 ").setPrice(23.00).setTitle("妻妾成群").setLabel("当代文学").setContent(
                "《妻妾成群》借旧中国特有的封建家庭模式作小说的框架，一个男人娶了四个女人做太太。但作者关心的不是一个男人如何在四个女人中周旋，如何控制她们，而是关心四个女人..."
        ).setId(String.valueOf(++id)).setCreateTime(new Date()));

        examples.add(new BookExample().setAuthor("路遥  ").setPrice(20.00).setTitle("人生").setLabel("当代文学").setContent(
                "《人生》是路遥的一部中篇小说，发表于1982年，它以改革时期陕北高原的城乡生活为时空背景，叙述了高中毕业生高加林回到土地又离开土地，再回到土地这样人生的变化..."
        ).setId(String.valueOf(++id)).setCreateTime(new Date()));

        examples.add(new BookExample().setAuthor("余华").setPrice(18.00).setTitle("在细雨中呼喊").setLabel("当代文学").setContent(
                "作者以第一人称讲述了一个家庭的欢乐和苦痛，细腻地刻画了柔弱的母亲如何完成了自己忍受的一生，她唯一爆发出来的愤怒是在弥留之际；名叫孙广财的父亲又是如何骄傲地将..."
        ).setId(String.valueOf(++id)).setCreateTime(new Date()));

        examples.add(new BookExample().setAuthor("莫言").setPrice( 27.00).setTitle("檀香刑").setLabel("当代文学").setContent(
                "《檀香刑》是莫言潜心五年完成的一部长篇新作。在这部神品妙构的小说中，莫言以1900年德国人在山东修建胶济铁路、袁世凯镇压山东义和团运动、八国联军攻陷北京、慈..."
        ).setId(String.valueOf(++id)).setCreateTime(new Date()));
    }

}
