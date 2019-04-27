package Interview.a3;

import java.util.Random;

public class FinalData {
    final static Object v1 = "";
    private final static int VALUE_TWO = 99;
    private static Random random = new Random(System.currentTimeMillis());
    private final int valueOne = 9;
    private String id;
    public FinalData(String id) {
        this.id = id;
    }

    public static void main(String[] args) {
        FinalData finalData = new FinalData(3 + "");
//        finalData.valueOne++;
//        v1 = new Object();//对象的引用不可以改变
    }
}
