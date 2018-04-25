package Interview.a2;

public class ForEachTest {
    public static void main(String[] args) {
        Integer[] integers = new Integer[100];
        int i=0;
        while (i<integers.length-1){
            i++;
            System.out.println(i);
            integers[i] = Integer.valueOf(""+Math.round(Math.random())*10);
        }

        for (Integer in:integers){
            System.out.println("==>"+in);
        }
    }
}
