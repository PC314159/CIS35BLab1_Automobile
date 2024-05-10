package adapter;

import model.Automobile;
import util.AutomobileFileReader;

import java.util.LinkedHashMap;

public abstract class proxyAutomobile {
    private static LinkedHashMap<String, Automobile> automobileLinkedHashMap = new LinkedHashMap<String, Automobile>();

    public void BuildAuto(String filename) {
        AutomobileFileReader afr = new AutomobileFileReader();
        Automobile a1 = afr.createAutomobileFromFile(filename);
        automobileLinkedHashMap.put(a1.getName(), a1);
    }

    public void printAuto(String modelName) {
        if (automobileLinkedHashMap.get(modelName) != null) {
            System.out.println(automobileLinkedHashMap.get(modelName).toString());
        }
    }
    public void printAllAuto() {
        for (String k: automobileLinkedHashMap.keySet()) {
            System.out.println(automobileLinkedHashMap.get(k).toString());
        }
    }

    public void updateOptionSetName(String modelName, String optionSetName, String newName) {
        if (automobileLinkedHashMap.get(modelName) != null) {
            automobileLinkedHashMap.get(modelName).updateOptionsName(optionSetName, newName);
        }
    }

    public void updateOptionPrice(String modelName, String optionSetName, String optionName, int newPrice) {
        if (automobileLinkedHashMap.get(modelName) != null) {
           automobileLinkedHashMap.get(modelName).updateOptionPrice(optionSetName, optionName, newPrice);
        }
    }

    public void updateChoice(String modelName, String optionsName, String optionName) {
        if (automobileLinkedHashMap.get(modelName) != null) {
            automobileLinkedHashMap.get(modelName).setOptionChoice(optionsName, optionName);
        }
    }

    public void fix(int errNo) {

    }
}
