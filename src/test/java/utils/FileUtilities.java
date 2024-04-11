package utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileUtilities {
    public static void cleanDir(String filepath) throws IOException {
        File dir = new File(filepath);
        if (dir.exists()) {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    FileUtils.forceDelete(file);
                }
            }
        } else {
            dir.mkdirs();
        }
    }
}
