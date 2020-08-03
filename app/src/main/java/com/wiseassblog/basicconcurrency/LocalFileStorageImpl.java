package com.wiseassblog.basicconcurrency;

import com.wiseassblog.basicconcurrency.domain.Day;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class LocalFileStorageImpl {

    private static File GAME_DATA = new File(
            System.getProperty("user.home"),
            "day.txt"
    );

    public void update() throws IOException {
        try {
            FileOutputStream fileOutputStream =
                    new FileOutputStream(GAME_DATA);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(new Day());
            objectOutputStream.close();
        } catch (IOException e) {
            throw new IOException("Unable to access Game Data");
        }
    }

    public String get() throws IOException {
        FileInputStream fileInputStream =
                new FileInputStream(GAME_DATA);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        try {
            Day day = (Day) objectInputStream.readObject();
            objectInputStream.close();
            return "derp";
        } catch (ClassNotFoundException e) {
            throw new IOException("File Not Found");
        }
    }
}
