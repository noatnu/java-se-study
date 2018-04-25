package Interview.a2;

public class LableTest {
    public static void main(String[] args) {
        new LableTest().lable();
    }

    public void lable(){
        int i = 0;
        outer:
        for (;3>1;){
            inner:for (;i<10;i++){
                System.out.println("i:"+i);
                if (i==2){
                    System.out.println("continue");
                }
                if (i==4){
                    System.out.println("break");
                    break ;
                }
                if (i==7){
                    System.out.println("break inner");
                    break inner;
                }

            }
        }
    }
}
