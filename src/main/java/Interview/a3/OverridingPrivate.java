package Interview.a3;

public class OverridingPrivate extends WithFInals {
    public static void main(String[] args) {

    }

    @Override
    void g() {//只能覆写g()
        super.g();
    }
}

class WithFInals {
    private final void f() {
    }

    void g() {
        System.out.println("WithFInals.g");
    }

    final void k() {
    }
}
