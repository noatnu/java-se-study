package Interview.a3;

public class InitParams {
    private String s;
    private int n;
    private char c;
    private float f;
    private double d;

    public void init(){
        System.out.println("s="+s);
        System.out.println("n="+n);
        System.out.println("f="+f);
        System.out.println("d="+d);
        System.out.println("c="+c);

        int o;//局部变量必须初始化
//        System.out.println("o="+o); //直接会编译错误
        return;
    }

    public static void main(String[] args) {
        new InitParams().init();
    }

    public InitParams(){
        return;
    }
}
