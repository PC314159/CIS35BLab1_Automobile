package adapter;

import model.Automobile;
import util.AutomobileFileReader;
import util.FileIO;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Properties;

public abstract class ProxyAutomobile {
    private static LinkedHashMap<String, Automobile> automobileLinkedHashMap = new LinkedHashMap<String, Automobile>();

    public static synchronized LinkedHashMap<String, Automobile> getHashMap() {
        return automobileLinkedHashMap;
    }

    public synchronized void BuildAuto(String filename) {
        AutomobileFileReader afr = new AutomobileFileReader();
        Automobile a1 = afr.createAutomobileFromFile(filename);
        automobileLinkedHashMap.put(a1.getName(), a1);
    }

    public synchronized void BuildAuto(StringBuffer sb) {
        AutomobileFileReader afr = new AutomobileFileReader();
        Automobile a1 = afr.createAutomobileStringBuffer(sb);
        automobileLinkedHashMap.put(a1.getName(), a1);
    }

    public synchronized void BuildAuto(Properties prop) {
        Automobile a1 = new FileIO().parsePropertiesFile(prop);
        automobileLinkedHashMap.put(a1.getName(), a1);
    }

    public synchronized void printAuto(String modelName) {
        if (automobileLinkedHashMap.get(modelName) != null) {
            automobileLinkedHashMap.get(modelName).print();
        }
    }
    public synchronized void printAllAuto() {
        for (String k: automobileLinkedHashMap.keySet()) {
            automobileLinkedHashMap.get(k).print();
        }
    }

    public synchronized ArrayList<String> getAvailableModels() {
        ArrayList<String> names = new ArrayList<String>();
        for (String k: automobileLinkedHashMap.keySet()) {
            names.add(automobileLinkedHashMap.get(k).getName());
        }
        return names;
    }

    public synchronized Automobile getAutomobile(String modelName) {
        if (automobileLinkedHashMap.get(modelName) != null) {
          return automobileLinkedHashMap.get(modelName);
        }
        return null;
    }

    public synchronized void updateOptionSetName(String modelName, String optionSetName, String newName) {
        if (automobileLinkedHashMap.get(modelName) != null) {
            automobileLinkedHashMap.get(modelName).updateOptionsName(optionSetName, newName);
        }
    }

    public synchronized void updateOptionPrice(String modelName, String optionSetName, String optionName, int newPrice) {
        if (automobileLinkedHashMap.get(modelName) != null) {
           automobileLinkedHashMap.get(modelName).updateOptionPrice(optionSetName, optionName, newPrice);
        }
    }

    public synchronized void updateChoice(String modelName, String optionsName, String optionName) {
        if (automobileLinkedHashMap.get(modelName) != null) {
            automobileLinkedHashMap.get(modelName).setOptionChoice(optionsName, optionName);
        }
    }

    public synchronized ArrayList<String> getOptionsNames(String modelName) {
        if (automobileLinkedHashMap.get(modelName) != null) {
            return automobileLinkedHashMap.get(modelName).getOptionsNames();
        }
        return null;
    }

    public synchronized ArrayList<String> getOptionNames(String modelName, String optionsName) {
        if (automobileLinkedHashMap.get(modelName) != null) {
            return automobileLinkedHashMap.get(modelName).getOptionNames(optionsName);
        }
        return null;
    }

    public void fix(int errNo) {

    }
}
