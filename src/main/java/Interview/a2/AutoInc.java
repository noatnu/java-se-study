package Interview.a2;

public class AutoInc {
    private static int n = 0;

    public static void main(String[] args) {
        int y = 0;
        System.out.println(++y);
        System.out.println(AutoInc.n++);
    }
}
