package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Automobile implements Serializable {

    private String name;
    private String make;
    private String model;
    private int basePrice;
    private ArrayList<OptionSet> optset;
    private ArrayList<OptionSet.Option> choices;

    public Automobile(String name, String make, String model, int basePrice) {
        this.name = name;
        this.make = make;
        this.model = model;
        this.basePrice = basePrice;
        this.optset = new ArrayList<OptionSet>();
        this. choices = new ArrayList<OptionSet.Option>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }

    public ArrayList<OptionSet> getOptset() {
        return optset;
    }

    public void setOptset(ArrayList<OptionSet> optset) {
        this.optset = optset;
    }

    public void setOptions(int i, String optionsName) {
        optset.set(i, new OptionSet(optionsName));
    }

    public void addOptions(String optionsName) {
        optset.add(new OptionSet(optionsName));
    }

    public int findOptions(String optionSetName) {
        for (int i = 0; i < optset.size(); i++) {
            if (optset.get(i).getName().equals(optionSetName)) {
                return i;
            }
        }

        return -1;
    }

    public void delOptions(String optionSetName) {
        for (int i = 0; i < optset.size(); i++) {
            if (optset.get(i).getName().equals(optionSetName)) {
                optset.remove(i);
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

    public void setOption(String optionsName, int index, String name, int price) {
        for (OptionSet options : optset) {
            if (options.getName().equals(optionsName)) {
                options.setOption(index, name, price);
                ;
                break;
            }
        }
    }

    public void addOption(String optionsName, String name, int price) {
        for (OptionSet options : optset) {
            if (options.getName().equals(optionsName)) {
                options.addOption(name, price);
                ;
                break;
            }
        }
    }

    public int findOption(String optionsName, String optionName) {
        for (OptionSet options : optset) {
            if (options.getName().equals(optionsName)) {
                return options.findOption(optionName);
            }
        }
        return -1;
    }

    public void delOption(String optionsName, String optionName) {
        for (OptionSet options : optset) {
            if (options.getName().equals(optionsName)) {
                options.delOption(optionName);
                break;
            }
        }
    }

    public void updateOptionName(String optionsName, String optionName, String newName) {
        for (OptionSet options : optset) {
            if (options.getName().equals(optionsName)) {
                options.updateOptionName(optionName, newName);
                break;
            }
        }
    }

    public void updateOptionPrice(String optionsName, String optionName, int price) {
        for (OptionSet options : optset) {
            if (options.getName().equals(optionsName)) {
                options.updateOptionPrice(optionName, price);
                break;
            }
        }
    }

    public String getOptionChoiceName(String optionsName) {
        for (OptionSet options : optset) {
            if (options.getName().equals(optionsName)) {
                return options.getChoiceName();
            }
        }
        return "";
    }

    public int getOptionChoicePrice(String optionsName) {
        for (OptionSet options : optset) {
            if (options.getName().equals(optionsName)) {
                return options.getChoicePrice();
            }
        }
        return 0;
    }

    public void setOptionChoice(String optionsName, String optionName) {
        for (OptionSet options : optset) {
            if (options.getName().equals(optionsName)) {
                OptionSet.Option oldChoice = options.getChoice();
                choices.remove(oldChoice);
                OptionSet.Option choice = options.setChoice(optionName);
                choices.add(choice);
            }
        }
    }

    public int getTotalPrice(){
        int total = basePrice;
        for (OptionSet.Option choice: choices) {
            total += choice.getPrice();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Automobile{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", basePrice=" + basePrice +
                ", optset=" + optset +
                ", choices=" + choices +
                ", totalPrice=" + getTotalPrice() +
                '}';
    }
}
