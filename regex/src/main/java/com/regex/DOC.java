package com.regex;

/**
 * Created by zhou on 18-1-6.
 */
public enum DOC {
    SRC("/home/zhou/ssh_java/work_project"),INPUT("/home/zhou/ssh_java/work_project/INTELLIJ-Community/studyWhy/src/main/java/com/regex/help/file/");
    private String name;

    DOC(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
