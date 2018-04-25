package com.regex;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by zhou on 18-1-27.
 */
public class InputDemo {
    private final static String CHAR_UTF = "UTF-8";

    public static String getText(String path) {
        StringBuilder builder = new StringBuilder(1024);
        File file = new File(path);
        try {
            InputStream in = new FileInputStream(file);
            BufferedInputStream reader = new BufferedInputStream(in);
            byte[] bs = new byte[(int) file.length()];
            while ((reader.read(bs, 0, bs.length)) != -1) ;
            builder.append(new String(bs, "" + CHAR_UTF));
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}
