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
while (matcher.find()) {
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

+ "\"的作用 (转义符)
```
在java中的String使用中，我们知道"\"表示转义符。当我们需要表示
String s="He is a "Monster"";
String s="He is a \"Monster\"";
但是在java中的正则表达式中，有时候需要用到"\"来表示一些特定的符号，比如 \d 在正则表达式中表示匹配一位数字，但用到正则表达式中则必须使用 \\ 这样的双反斜杠来表示一个 \ 。也就是说我们在代码中必须写成 \\d 来匹配一个数组，相当于正则表达式中的 \d 。

假如你想在正则表达式中插入一个正常的 \ ，则需要写入 \\\\ 。
private static String s = "\\12";
public static void main(String[] args) {
  testOne(s);
}
private static void testOne(String s){
  System.out.println(s);
  System.out.println(s.contains("\\"));
  System.out.println(s.matches("\\\\?\\d+"));
}
```
### 量词

| 贪婪型       |     勉强型    |     占有型     |         说明         |
|    :-----:  |     :----:   |     :----:   |        :----:       |
|      X?     |      X??     |      X?+     |     一个或0个X        |
|      X*     |      X*?     |      X*+     |     0个或多个X        |
|      X+     |      X+?     |      X++     |     一个或多个X       |
|      X{n}   |      X{n}?   |      X{n}+   |     恰好n次X         |
|      X{n,}  |      X{n,}?  |      X{n,}+  |     至少n次X         |
|      X{n,m} |      X{n,m}? |      X{n,m}+ |     至少n次，至多m次X  |

### 字符类
|     示例       |                  说明            |
|      :-----:  |               :----:            |
|      .        |          匹配任意单个字符          |
|     [abc]     |   包含abc中任意的字符,等价于a        |
|     [^abc]    |   除了abc外的任意字符              |
|   [a-zA-Z]    |   从a到z或者从A到Z中的任意字符       |
|   [abd[1-9]]  |abd中任意字符或者1-9中任意字符，取并集 |
| [a-z&&[hij]]  |      任意h、i、j字符，取交集        |
|       \s      |空白符(空格、tab、换行、换页和回车)    |
|       \S      |            非空白符(^\s)          |
|       \d      |            数字(0-9)             |
|       \D      |            非数字(^0-9)          |
|       \w      |            词字符[a-zA-Z0-9]     |
|       \W      |            非词字符[^\w]         |

### 边界匹配符

|     示例       |                  说明            |
|      :-----:  |               :----:            |
|      ^        |          一行的起始               |
|     $         |          一行的结束               |
|     \b        |          词的边界                 |
|     \B        |          非词的边界               |
|     \G        |          前一个匹配的结束          |


### 逻辑操作符

|     示例       |                  说明            |
|      :-----:  |               :----:            |
|      XY       |          Y跟在X后边              |
|     X         |          Y                     |
|    (X)        |          捕获组                 |