package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Automobile;
import model.OptionSet;

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

    public Automobile parsePropertiesFile(Properties props) {
        String carName = props.getProperty("CarName");
        String carMake = props.getProperty("CarMake");
        String carModel = props.getProperty("CarModel");
        int basePrice = Integer.parseInt(props.getProperty("BasePrice"));

        Automobile auto = new Automobile(carName, carMake, carModel, basePrice);

        for (String key : props.stringPropertyNames()) {
            if (key.startsWith("OptionSet")) {
                String optionSetName = props.getProperty(key);
                auto.addOptions(optionSetName);

                // Add options to OptionSet
                int optionIndex = 1;
                while (true) {
                    String optionValueKey = "OptionValue" + key.substring(9) + optionIndex;
                    String optionPriceKey = "OptionPrice" + key.substring(9) + optionIndex;
                    String optionName = props.getProperty(optionValueKey);
                    int optionPrice = Integer.parseInt(props.getProperty(optionPriceKey));

                    auto.addOption(optionSetName, optionName, optionPrice);
                    optionIndex++;
                }
            }
        }

        return auto;
    }
}
