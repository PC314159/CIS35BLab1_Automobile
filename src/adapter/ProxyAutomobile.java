package adapter;

import model.Automobile;
import util.AutomobileFileReader;

import java.util.LinkedHashMap;

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

    public void fix(int errNo) {

    }
}
