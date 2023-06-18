package urise.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainFile {
    public static void main(String[] args) {
        String path = "C:\\Users\\Sergei\\Desktop\\java\\basejava\\.gitignore";

        File file = new File(path);
        try {
            System.out.println(file.getCanonicalFile());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        File dir = new File("C:\\Users\\Sergei\\Desktop\\java\\basejava\\basejava");
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }

        try (FileInputStream fis = new FileInputStream(path)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Start recursive path via directory");
        outputFileName(dir);
    }

    public static void outputFileName(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                System.out.println(file.isFile() ? "File: " + file.getName() : "Directory: " + file.getName());
            outputFileName(file);
            }
        }
    }
}