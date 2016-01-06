package com.myjava.util;

import java.io.File;

public class PathUtil {
    private static String classPath;
    private static String confPath;
    private static String supplementalFilesPath;

    public static String getClassPath() {
        if (classPath == null) {
            classPath = PathUtil.class.getResource("/").getPath();
            classPath = new File("").getAbsolutePath() + "/resources/";
            if (classPath.contains("test-classes")) {
                classPath = classPath.replace("test-classes", "classes");
            }
        }
        return classPath;
    }

    public static String getConfPath() {
        if (confPath == null) {
            confPath = getClassPath() + "conf/";
        }
        return confPath;
    }

    public static String getSupplementalFilesPath() {
        if (supplementalFilesPath == null) {
            supplementalFilesPath = getClassPath() + "supplementals/";
        }
        return supplementalFilesPath;
    }

    public static String getConfGridPath() {
        return getConfPath() + "grid/";
    }

    public static void deleteFolder(String folderName) {
        File folder = new File(folderName);
        if (!folder.exists()) {
            return;
        }

        deleteAll(folder);
    }

    private static void deleteAll(File file) {
        if (file.isFile() || file.list().length == 0) {
            file.delete();
        } else {
            File[] files = file.listFiles();
            for (File file2 : files) {
                deleteAll(file2);
            }

            if (file.exists()) {
                file.delete();
            }
        }
    }
}
