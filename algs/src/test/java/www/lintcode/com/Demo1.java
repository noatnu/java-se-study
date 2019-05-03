package www.lintcode.com;


/**
 * @Author noatn
 * @Description
 * @createDate 2019/5/3
 **/
public class Demo1 {

    /*反转一个3位整数*/
    public int reverseInteger(int number) {
        int a = number % 10;
        int b = number / 10;
        b = b % 10;
        int c = number / 100;
        c = c % 10;
        int[] arr = new int[]{a, b, c};
        int num = Integer.parseInt(String.format("%d%d%d", a, b, c));
        return num;
    }

    /**
     * 将一个字符由小写字母转换为大写字母 185ms
     */
    public Character lowercaseToUppercase(char character) {
        int num = (int) character;
        int flag = 0;
        final int A1 = 65;
        final int A2 = 90;
        final int a1 = 97;
        final int a2 = 122;
        //大写字母
        if (num >= A1 && num <= A2) {
            flag++;
        }
        //小写字母
        if (num >= a1 && a2 >= num) {
            flag--;
        }
        if (flag > 0) {
            return character;
        }
        if (flag < 0) {
            int sub = num - a1;
            sub += A1;
            return (char) sub;
        }
        return null;
    }

    /**
     * a+b不用加号
     *
     * @param a: An integer
     * @param b: An integer
     * @return: The sum of a and b
     */
    public int aplusb(int a, int b) {
        int res = a;
        int xor = a ^ b;//得到原位和
        int forward = (a & b) << 1;//得到进位和
        if (forward != 0) {//若进位和不为0，则递归求原位和+进位和
            res = aplusb(xor, forward);
        } else {
            res = xor;//若进位和为0，则此时原位和为所求和
        }
        return res;
    }

    @org.testng.annotations.Test
    public void test() {
        System.out.println(aplusb(2, 4));
    }

}
