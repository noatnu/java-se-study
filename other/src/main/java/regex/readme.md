#Java正则表达式
> 正则表达式是一种可以用于模式匹配和替换的强有力的工具

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
if (matcher.find()) {
    System.out.println(matcher.group());
}
```