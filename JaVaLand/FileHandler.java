package io.github.JaVaLand;

import java.io.*;
import java.util.*;

public class FileHandler<T>{
    private final String filename;

    public FileHandler(String filename) {
        this.filename = filename;
    }

    public void add_content(ArrayList<T> content) throws Exception{
        ObjectOutputStream stream = null;

        try {
            stream = new ObjectOutputStream(new FileOutputStream(filename));
            stream.writeObject(content);
        }

        catch (Exception e) {
            System.out.println("Error writing to file: " + e.getMessage());
            throw e;
        }

        finally {
            if (stream != null) {
                try {
                    stream.close();
                }

                catch (Exception e) {
                    System.out.println("Error closing file: " + e.getMessage());
                }
            }
        }
    }

    public ArrayList<T> get_content() throws Exception{
        ArrayList<T> content = null;
        ObjectInputStream stream = null;

        try {
            stream = new ObjectInputStream(new FileInputStream(filename));
            content = (ArrayList<T>) stream.readObject();
        }

        catch (Exception e) {
            System.out.println("Error reading from file: " + e.getMessage());
            throw e;
        }

        finally {
            if (stream != null) {
                try {
                    stream.close();
                }

                catch (Exception e) {
                    System.out.println("Error closing file: " + e.getMessage());
                }
            }
        }

        return content != null ? content : new ArrayList<>();
    }
}

