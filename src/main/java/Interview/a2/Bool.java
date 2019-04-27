package Interview.a2;

import java.util.Random;

public class Bool {

    public static void isM() {
        PPP ppp1 = new Bool().new PPP("blake");
        PPP ppp2 = new Bool().new PPP("alice");
//        System.out.println(ppp1&&ppp2);
//        ppp1 = ppp2+ppp1;
        System.out.println(ppp1 + " " + ppp2);
        ppp2 = ppp1;
        System.out.println(ppp1 + " " + ppp2);
    }

    public static void main(String[] args) {
        isM();
    }

    public static void isN() {
        Random random = new Random(System.currentTimeMillis());
        int i = random.nextInt(100);
        int j = random.nextInt(100);
        System.out.println("i:" + i + " j:" + j);
        System.out.println("i > j :" + (i > j));
        System.out.println("i < j :" + (i < j));
        System.out.println("i != j :" + (i != j));
        System.out.println("i = j :" + (i == j));

//        System.out.println("i && j" + (i && j));//Java中 && || ! 只能应用于boolean value

        System.out.println("(i < j) && (i > j)" + ((i < j) && (i > j)));
    }

    class PPP {
        private String name;

        public PPP(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public int hashCode() {
            return super.hashCode() + name.hashCode();
        }

        @Override
        public String toString() {
            return getName() + " " + hashCode();
        }
    }
}
