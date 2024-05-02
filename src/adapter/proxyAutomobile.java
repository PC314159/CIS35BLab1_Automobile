package adapter;

import model.Automobile;
import util.AutomobileFileReader;

public abstract class proxyAutomobile {
    private static Automobile a1;

    public void BuildAuto(String filename) {
        AutomobileFileReader afr = new AutomobileFileReader();
        a1 = afr.createAutomobileFromFile(filename);
    }

    public void printAuto(String modelName) {
        if (modelName.equals(a1.getName())) {
            System.out.println(a1.toString());
        }
    }

    public void updateOptionSetName(String modelName, String optionSetName, String newName) {
        if (modelName.equals(a1.getName())) {
            a1.updateOptionsName(optionSetName, newName);
        }
    }

    public void updateOptionPrice(String modelName, String optionSetName, String OptionName, int newPrice) {
        a1.updateOptionPrice(optionSetName, OptionName, newPrice);
    }
}
