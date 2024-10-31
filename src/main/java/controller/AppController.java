package main.java.controller;

import main.java.utility.FileProcessor;

import java.io.*;

/**
 * Super class of all the other three controllers, contains load and read as gateways to read and write files.
 */
public abstract class AppController implements Serializable {

    protected AppController load(String path) {
        FileProcessor fileProcessor = new FileProcessor();
        if (fileProcessor.notExist(path)) {
            if (fileProcessor.createNew(path)) {
                return null;
            }
        } else {
            return read(path);
        }
        return null;
    }

    /**
     * @param path the location that we read from
     * @return the controller that is stored in path.
     */
    private AppController read(String path) {
        try {
            FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream objIn = new ObjectInputStream(fileIn);
            AppController readObject = (AppController) objIn.readObject();
            objIn.close();
            fileIn.close();
            return readObject;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found.");
            e.printStackTrace();
        }
        return null;
    }

    protected void write(String path) {
        try {
            FileOutputStream fileOut = new FileOutputStream(path);
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(this);
            objOut.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * use write to help save the new controller
     */
    public abstract void save();
}
