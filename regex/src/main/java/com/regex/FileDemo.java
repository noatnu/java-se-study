package com.regex;

import java.io.File;

/**
 * Created by zhou on 18-1-27.
 */
public class FileDemo {
    public static StringBuilder getStringBuilder(String path){
        class DemoT {
            String s = path;//传入的是一个路径path
            File file = new File(s);
            private StringBuilder builder = new StringBuilder(1024 * 2);

            /**
             * 用了一个递归
             * @param file1
             */
            private void isit(File file1) {
                if (file1.isFile()) {
                    builder.append(file1.getName()).append(" ");
                } else {
                    File[] files = file1.listFiles();
                    for (File f : files) {
                        isit(f);
                    }
                }
            }

            public DemoT() {
                isit(file);
            }

            public StringBuilder getBuilder() {
                return builder;
            }
        }
        DemoT demo = new DemoT();
        return demo.getBuilder();
    }
}
