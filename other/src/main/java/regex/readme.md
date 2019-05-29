#Java正则表达式
> 正则表达式是一种可以用于模式匹配和替换的强有力的工具

+ Pattern 类
> pattern 对象是一个正则表达式的编译表示。Pattern 类没有公共构造方法。要创建一个 Pattern 对象，你必须首先调用其公共静态编译方法，它返回一个 Pattern 对象。该方法接受一个正则表达式作为它的第一个参数。

+ Matcher 类
> Matcher 对象是对输入字符串进行解释和匹配操作的引擎。与Pattern 类一样，Matcher 也没有公共构造方法。你需要调用 Pattern 对象的 matcher 方法来获得一个 Matcher 对象。

+ 基础匹配
```
Pattern pattern = Pattern.compile("to");
Matcher matcher = pattern.matcher("Customers like to grab a chance to pick up a doll");
if (matcher.find()) {
    System.out.println(matcher.group());
}
```
+ .可以匹配任何字符
```
//在正则表达式里，特殊字符（或字符集和）用来给出要搜索的东西。 .字符可以匹配任何一个单个的字符
Pattern pattern = Pattern.compile("Cannes.");
Matcher matcher = pattern.matcher("Celebrities shine at the opening of Cannes。");
while (matcher.find()) {//一定记得用while循环
    System.out.println(matcher.group());
}
```