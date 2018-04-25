package Interview.a2;

public class FinalClass {
    private String password = "24352345";
    private final String name = "miss you";
    final class XXXDFG{
        public void isit(){
            System.out.println(name);
            System.out.println(password);
        }
    }

//    class XXXDFGIIII extends XXXDFG{}//final修饰的类不能被继承
    public static void main(String[] args) {
        FinalClass finalClass = new FinalClass();
        FinalClass.XXXDFG xxxdfg = new FinalClass().new XXXDFG();
        xxxdfg.isit();
//        finalClass.name = "ssdgd";//final修饰的变量只能赋予 value 一次
    }
}
