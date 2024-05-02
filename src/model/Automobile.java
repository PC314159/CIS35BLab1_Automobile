package model;

import java.io.Serializable;
import java.util.Arrays;

public class Automobile implements Serializable {
    private String name;
    private int basePrice;
    private OptionSet[] optset;

    public Automobile(String name, int basePrice, OptionSet[] optset) {
        this.name = name;
        this.basePrice = basePrice;
        this.optset = optset;
    }

    public String getName() {
        return name;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public OptionSet[] getOptset() {
        return optset;
    }

    public OptionSet getOptions(int i) {
        return optset[i];
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }

    public void setOptset(OptionSet[] optset) {
        this.optset = optset;
    }

    public void setOptionSet(int i, OptionSet options) {
        this.optset[i] = options;
    }

    public void setOption(OptionSet options, int index, String name, int price) {
        options.setOption(index, name, price);
    }
    public int findOptionSet(String optionSetName) {
        for(int i = 0; i < optset.length; i++) {
            if (optset[i].getName().equals(optionSetName)) {
                return i;
            }
        }

        return -1;
    }

    public void delOptionSet(String optionSetName) {
        for(int i = 0; i < optset.length; i++) {
            if (optset[i].getName().equals(optionSetName)) {
                optset[i] = null;
                break;
            }
        }

    }

    public void delOption(String optionName) {
        for (OptionSet options: optset) {

            options.delOption(optionName);
        }

    }

    public void updateOptionSet(OptionSet newOptions) {
        for (int i = 0; i < optset.length; i++) {
            if (optset[i].getName().equals(newOptions.getName())) {
                optset[i] = newOptions;
                break;
            }
        }

    }

    public void updateOptionsName(String optionsName, String newName) {
        for (OptionSet options : optset) {
            if (options.getName().equals(optionsName)) {
                options.setName(newName);
                break;
            }
        }
    }

    public void updateOptionPrice(String optionsName, String optionName, int price) {
        for (OptionSet options : optset) {
            if (options.getName().equals(optionsName)) {
                options.updateOption(optionName, price);
                break;
            }
        }
    }

    public String toString() {
        return "Automobile{name='" + name + "', basePrice=" + basePrice + ", optset=" + Arrays.toString(optset) + "}";
    }
}
