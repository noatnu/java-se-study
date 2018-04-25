package Interview.a2;

public class Equivalence {
    public static void main(String[] args) {
        Integer a1 = new Integer(47);
        Integer a2 = new Integer(47);
        System.out.println(a1==a2);
        System.out.println(a1!=a2);

        Integer b = new Integer(1);
        Integer p = new Integer(9);
        System.out.println(b==p);
    }
}
