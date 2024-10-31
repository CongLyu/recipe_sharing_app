package main.java.utility;

import java.io.*;

public class FileProcessor {

    public boolean notExist(String path) {
        try {
            FileInputStream fileIn = new FileInputStream(path);
            fileIn.close();
            return false;
        } catch (FileNotFoundException e) {
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean createNew(String path) {
        try {
            File file = new File(path);
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
