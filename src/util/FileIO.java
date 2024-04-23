package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Automobile;

public class FileIO {
    public FileIO() {
    }

    public static void serializeAuto(String fileName, Automobile auto) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
            oos.writeObject(auto);
            oos.close();
        } catch (IOException ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, ex.toString());
            System.exit(1);
        }

    }

    public static Automobile deserializeAuto(String fileName) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
            Automobile auto = (Automobile)ois.readObject();
            ois.close();
        } catch (ClassNotFoundException | IOException ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, ex.toString());
            System.exit(1);
        }

        return null;
    }
}
