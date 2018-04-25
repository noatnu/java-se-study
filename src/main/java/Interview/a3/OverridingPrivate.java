package Interview.a3;

public class OverridingPrivate extends WithFInals {
    @Override
    void g() {//只能覆写g()
        super.g();
    }

    public static void main(String[] args) {

    }
}

class WithFInals{
    private final void f(){}
    void g(){
        System.out.println("WithFInals.g");
    }
    final void k(){}
}
