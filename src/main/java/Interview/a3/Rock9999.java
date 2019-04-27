package Interview.a3;

public class Rock9999 {
    protected static int num = 10;

    public Rock9999() {
        return;
    }

    public static void main(String[] args) {
        Rock9999 rock = new Rock9999();
        int i = rock.Rock9999(4);
        System.out.println("i=" + i);

        rock.Rock9999();

    }

    public void Rock9999() {
        System.out.println("Rock9999.Rock9999");
        return;
    }

    public int Rock9999(int i) {
        return i++;
    }
}
