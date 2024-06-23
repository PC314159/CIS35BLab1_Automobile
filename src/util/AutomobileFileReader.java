package util;

import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import exception.AutoException;
import model.Automobile;
import model.OptionSet;

public class AutomobileFileReader {
    public Automobile createAutomobileFromFile(String filePath) {
        try {
            FileReader file = new FileReader(filePath);
            BufferedReader buff = new BufferedReader(file);
            String autoName = buff.readLine();
            try {
                if (autoName.isEmpty()) {
                    throw new AutoException(1, "Empty auto name");
                }
            } catch (AutoException ex) {
                Scanner sc = new Scanner(System.in);
                System.out.println("Missing auto name, please enter one");
                String inputName = sc.nextLine();
                sc.close();
                autoName = inputName;
            }
            String autoMake = buff.readLine();
            String autoModel = buff.readLine();
            int basePrice = Integer.parseInt(buff.readLine());
            buff.readLine();
            buff.readLine();
            int configAmount = Integer.parseInt(buff.readLine());
            Automobile auto = new Automobile(autoName, autoMake, autoModel, basePrice);

            for (int i = 0; i < configAmount; ++i) {
                buff.readLine();
                buff.readLine();
                String optionsName = buff.readLine();
                auto.addOptions(optionsName);
                int optionsAmount = Integer.parseInt(buff.readLine());
                buff.readLine();

                for (int j = 0; j < optionsAmount; ++j) {
                    String optName = buff.readLine();
                    int optPrice = Integer.parseInt(buff.readLine());
                    auto.addOption(optionsName, optName, optPrice);
                }
            }

            return auto;
        } catch (NumberFormatException | NullPointerException | IOException ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, ex.toString());
            System.exit(1);
            return null;
        }
    }

    public Automobile createAutomobileStringBuffer(StringBuffer sb) {
        try {
            StringReader stringReader = new StringReader(sb.toString());
            BufferedReader buff = new BufferedReader(stringReader);
            String autoName = buff.readLine();
            try {
                if (autoName.isEmpty()) {
                    throw new AutoException(1, "Empty auto name");
                }
            } catch (AutoException ex) {
                Scanner sc = new Scanner(System.in);
                System.out.println("Missing auto name, please enter one");
                String inputName = sc.nextLine();
                sc.close();
                autoName = inputName;
            }
            String autoMake = buff.readLine();
            String autoModel = buff.readLine();
            int basePrice = Integer.parseInt(buff.readLine());
            buff.readLine();
            buff.readLine();
            int configAmount = Integer.parseInt(buff.readLine());
            Automobile auto = new Automobile(autoName, autoMake, autoModel, basePrice);

            for (int i = 0; i < configAmount; ++i) {
                buff.readLine();
                buff.readLine();
                String optionsName = buff.readLine();
                auto.addOptions(optionsName);
                int optionsAmount = Integer.parseInt(buff.readLine());
                buff.readLine();

                for (int j = 0; j < optionsAmount; ++j) {
                    String optName = buff.readLine();
                    int optPrice = Integer.parseInt(buff.readLine());
                    auto.addOption(optionsName, optName, optPrice);
                }
            }

            return auto;
        } catch (NumberFormatException | NullPointerException | IOException ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, ex.toString());
            System.exit(1);
            return null;
        }
    }
}
