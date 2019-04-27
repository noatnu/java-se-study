package jdbc.encryption_and_decryption;


import tool.help.Zhou_StdRandom;

import java.util.Arrays;

/**
 * 凯撒密码
 * 主要是通过移位来加密
 */
public class Caesar {
    public static void main(String[] args) throws Exception {
        //加密
        String[] strings = new String[]{"hello world", Zhou_StdRandom.uniform(100000, 200000) + ""};
        System.out.println("array:" + Arrays.toString(strings));
        isit(strings);

        //解密
        strings[0] = "czggj rjmgy";
        strings[1] = "-" + 198245;
        isit(strings);
    }

    public static void isit(String... str) {
        String s = str[0];//加密或者解密的字符串
        int key = Integer.parseInt(str[1]);//密匙
        String es = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 'a' && c <= 'z') {//小写字母
                c += key % 26;
                if (c < 'a') c += 26;
                if (c > 'z') c -= 26;
            } else if (c >= 'A' && c <= 'Z') {//大写字母
                c += key % 26;
                if (c < 'A') c += 26;
                if (c > 'Z') c -= 26;
            }
            es += c;
        }
        if (key > 0)
            System.out.println("加密后的密文 = [" + es + "]");
        else
            System.out.println("解密后的密文 = [" + es + "]");

    }
}
