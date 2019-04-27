package Interview.a2;

public class LetterA {
    public static void f(Letter y) {
        y.c = 'z';
    }

    public static void main(String[] args) {
        Letter x = new Letter();
        x.c = 'a';
        System.out.println("1:" + x.c);
        f(x);// f method 实际改变的是f()之外的对象
        System.out.println("2:" + x.c);
    }
}

class Letter {
    char c;
}
