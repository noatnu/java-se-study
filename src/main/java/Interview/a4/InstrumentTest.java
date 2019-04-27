package Interview.a4;

interface Instrument {
    public static final String sgs = "";
    //    int VALUE ;// 因为接口中的成员变量或者说域是隐式的 static 与 final 而且还是public
    int VALUE = 5;

    void play(Note n);

    void adjust();
}

public class InstrumentTest {
    public static void main(String[] args) {
        System.out.println(" " + Wind.VALUE);
    }
}

class Wind implements Instrument {
    @Override
    public void play(Note n) {

    }

    @Override
    public void adjust() {

    }
}

abstract class Note {

}