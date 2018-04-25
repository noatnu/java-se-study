package Interview.a2;

public class BreakTest {
    public static void main(String[] args) {
        tOOg();
    }

    public static void tOOg() {
        int i = 0;
        while (i<100){
            i++;
            System.out.println("i:"+i);
            if (i==50)break;
        }
        return ;
    }
}
