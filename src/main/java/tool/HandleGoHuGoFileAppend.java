package tool;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class HandleGoHuGoFileAppend {
    private static final Logger logger = LoggerFactory.getLogger(HandleGoHuGoFileAppend.class);

    public static void main(String[] args) {
        String head = null;
        String oldPath = null;
        String newPath = null;

        head = "123";

        oldPath = "D:\\IdeaProjects\\studyDoc";
        newPath = "D:\\temp\\two";

        List<String> extensions = new ArrayList<>(1);
        extensions.add("md");
        if (StringUtils.isBlank(head) || StringUtils.isBlank(oldPath) || StringUtils.isBlank(newPath)) {
            return;
        }
        try {
            append(oldPath, newPath, head, extensions);
            System.out.println("end!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void append(String oldPath, String newPath, String head, List<String> extensions) throws Exception {
        File file = new File(newPath);
        FileFilter filter = new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isFile()) {
                    return extensions.contains(FilenameUtils.getExtension(f.getName()));
                } else {
                    if (".git".equals(f.getName())) {
                        return false;
                    }
                    if (".github".equals(f.getName())) {
                        return false;
                    }
                    if (".idea".equals(f.getName())) {
                        return false;
                    }
                    if (StringUtils.contains(f.getName(), ".")) {
                        return false;
                    }
                }
                return true;
            }
        };
        FileUtils.copyDirectory(new File(oldPath), file, filter, false);
        replace(file, head, extensions);
    }

    private static void replace(File file, String head, List<String> extensions) {
        if (file.isFile()) {
            String extension = FilenameUtils.getExtension(file.getName());
            if (!extensions.contains(extension)) {
                return;
            }
            try {
                String string = FileUtils.readFileToString(file, "UTF-8");
                String newString = new StringJoiner("\n\r").add(head).add(string).toString();
                FileUtils.write(file, newString, false);
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        } else {
            File[] files = file.listFiles();
            if (files == null || files.length == 0) {
                return;
            }
            for (File f : files) {
                replace(f, head, extensions);
            }
        }
    }

}
