package Interview.a3;

class Peppet{
    private int i;

    Peppet(int i) {
        this.i = i;
    }
}

public class BlankFinal {
    private final int i = 0;
    private final int j;
    private final Peppet p;//可以暂时不初始化
    public BlankFinal(){
        j = 1;
        p = new Peppet(22);
    }

    public BlankFinal(int jo){
        j = 1;
        p = new Peppet(jo);
    }

    public static void main(String[] args) {
        new BlankFinal();
        new BlankFinal(22);
    }
}
