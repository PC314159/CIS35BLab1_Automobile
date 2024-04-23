package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Automobile;
import model.OptionSet;

public class AutomobileFileReader {
    public Automobile createAutomobileFromFile(String filePath) {
        try {
            FileReader file = new FileReader(filePath);
            BufferedReader buff = new BufferedReader(file);
            String autoName = buff.readLine();
            int basePrice = Integer.parseInt(buff.readLine());
            buff.readLine();
            buff.readLine();
            int configAmount = Integer.parseInt(buff.readLine());
            Automobile auto = new Automobile(autoName, basePrice, new OptionSet[configAmount]);

            for(int i = 0; i < configAmount; ++i) {
                buff.readLine();
                buff.readLine();
                String optionsName = buff.readLine();
                int optionsAmount = Integer.parseInt(buff.readLine());
                OptionSet options = new OptionSet(optionsName, optionsAmount);
                buff.readLine();

                for(int j = 0; j < optionsAmount; ++j) {
                    String optName = buff.readLine();
                    int optPrice = Integer.parseInt(buff.readLine());
                    options.setOption(j, optName, optPrice);
                }

                auto.setOptionSet(i, options);
            }

            return auto;
        } catch (NumberFormatException | NullPointerException | IOException var15) {
            Logger.getAnonymousLogger().log(Level.WARNING, var15.toString());
            System.exit(1);
            return null;
        }
    }
}
