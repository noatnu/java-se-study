package Interview.a2;

import java.util.Random;

public class SwitchTest {
    public static void main(String[] args) {
        new SwitchTest().isB();
    }

    public void isB() {
        int num = new Random(System.currentTimeMillis()).nextInt(10);
        switch (num) {
            case 1:
                System.out.println("num:" + num + " time:" + System.currentTimeMillis());
                break;
            case 2:
                System.out.println("num:" + num + " time:" + System.currentTimeMillis());
                break;
            case 3:
                System.out.println("num:" + num + " time:" + System.currentTimeMillis());
                break;
            case 4:
                System.out.println("num:" + num + " time:" + System.currentTimeMillis());
                break;
            case 5:
                System.out.println("num:" + num + " time:" + System.currentTimeMillis());
                break;
            default:
                System.out.println("num:" + num + " time:" + System.currentTimeMillis());
        }
    }
}
