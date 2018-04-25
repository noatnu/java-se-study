package Interview.a2;

public class ContinueTest {

    public static void main(String[] args) {
        isContinue();
    }

    public static void isContinue(){
        int j = 0;
        do {
            j++;
            if (j==30)continue;
            System.out.println(System.nanoTime()+" "+j);
        }while (j<100);
        return;
    }
}
