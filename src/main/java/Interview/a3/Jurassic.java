package Interview.a3;

//public class Jurassic extends Dinosaur {  //不能继承final修饰过的class
//final 可以修饰 参数 方法 类 实例变量,不能修饰interface 以及abstract
public class Jurassic{
}

class SmallBrain{}

final class Dinosaur{
    int i = 7;
    int j = 11;
    SmallBrain smallBrain = new SmallBrain();
    void f(){}
}
//final interface DDD{}
//final abstract class LLL{}
