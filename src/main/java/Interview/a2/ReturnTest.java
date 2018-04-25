package Interview.a2;

public class ReturnTest {
    public static void main(String[] args) {

    }

    protected int getInt(){
        long ol = Math.round(Math.random())*10;
        if (StrictMath.random()>Math.random()){
            ol++;
            return (int)ol;
        }else {
            ol--;
            return (int)ol;
        }
    }

    private void isM(){
        //----------从某种意义上说每一个method 都有一个return 但现实是不一定非得写出来 因为这个 return是可以省略的
        return;//这个return是隐式的
    }
}
