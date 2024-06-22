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

    public synchronized String getName() {
        return name;
    }

    public synchronized void setName(String name) {
        this.name = name;
    }

    public synchronized String getMake() {
        return make;
    }

    public synchronized void setMake(String make) {
        this.make = make;
    }

    public synchronized String getModel() {
        return model;
    }

    public synchronized void setModel(String model) {
        this.model = model;
    }

    public synchronized int getBasePrice() {
        return basePrice;
    }

    public synchronized void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }

    public synchronized ArrayList<OptionSet> getOptset() {
        return optset;
    }

    public synchronized void setOptset(ArrayList<OptionSet> optset) {
        this.optset = optset;
    }

    public synchronized void setOptions(int i, String optionsName) {
        optset.set(i, new OptionSet(optionsName));
    }

    public synchronized void addOptions(String optionsName) {
        optset.add(new OptionSet(optionsName));
    }

    public synchronized int findOptions(String optionSetName) {
        for (int i = 0; i < optset.size(); i++) {
            if (optset.get(i).getName().equals(optionSetName)) {
                return i;
            }
        }

        return -1;
    }

    public synchronized void delOptions(String optionSetName) {
        for (int i = 0; i < optset.size(); i++) {
            if (optset.get(i).getName().equals(optionSetName)) {
                optset.remove(i);
                break;
            }
        }
    }

    public synchronized void updateOptionsName(String optionsName, String newName) {
        for (OptionSet options : optset) {
            if (options.getName().equals(optionsName)) {
                options.setName(newName);
                break;
            }
        }
    }

    public synchronized void setOption(String optionsName, int index, String name, int price) {
        for (OptionSet options : optset) {
            if (options.getName().equals(optionsName)) {
                options.setOption(index, name, price);
                ;
                break;
            }
        }
    }

    public synchronized void addOption(String optionsName, String name, int price) {
        for (OptionSet options : optset) {
            if (options.getName().equals(optionsName)) {
                options.addOption(name, price);
                ;
                break;
            }
        }
    }

    public synchronized int findOption(String optionsName, String optionName) {
        for (OptionSet options : optset) {
            if (options.getName().equals(optionsName)) {
                return options.findOption(optionName);
            }
        }
        return -1;
    }

    public synchronized void delOption(String optionsName, String optionName) {
        for (OptionSet options : optset) {
            if (options.getName().equals(optionsName)) {
                options.delOption(optionName);
                break;
            }
        }
    }

    public synchronized void updateOptionName(String optionsName, String optionName, String newName) {
        for (OptionSet options : optset) {
            if (options.getName().equals(optionsName)) {
                options.updateOptionName(optionName, newName);
                break;
            }
        }
    }

    public synchronized void updateOptionPrice(String optionsName, String optionName, int price) {
        for (OptionSet options : optset) {
            if (options.getName().equals(optionsName)) {
                options.updateOptionPrice(optionName, price);
                break;
            }
        }
    }

    public synchronized ArrayList<String> getOptionsNames() {
        ArrayList<String> names = new ArrayList<String>();
        for (OptionSet options: optset) {
            names.add(options.getName());
        }
        return names;
    }

    public synchronized ArrayList<String> getOptionNames(String optionsName) {
        for (OptionSet options : optset) {
            if (options.getName().equals(optionsName)) {
                return options.getOptionNames();
            }
        }
        return null;
    }
    public synchronized String getOptionChoiceName(String optionsName) {
        for (OptionSet options : optset) {
            if (options.getName().equals(optionsName)) {
                return options.getChoiceName();
            }
        }
        return "";
    }

    public synchronized int getOptionChoicePrice(String optionsName) {
        for (OptionSet options : optset) {
            if (options.getName().equals(optionsName)) {
                return options.getChoicePrice();
            }
        }
        return 0;
    }

    public synchronized void setOptionChoice(String optionsName, String optionName) {
        for (OptionSet options : optset) {
            if (options.getName().equals(optionsName)) {
                OptionSet.Option oldChoice = options.getChoice();
                choices.remove(oldChoice);
                OptionSet.Option choice = options.setChoice(optionName);
                choices.add(choice);
            }
        }
    }

    public synchronized int getTotalPrice(){
        int total = basePrice;
        for (OptionSet.Option choice: choices) {
            total += choice.getPrice();
        }
        return total;
    }

    public void print() {
        StringBuffer sb1 = new StringBuffer();
        sb1.append("------------------------------");
        sb1.append("Automobile:\n");
        sb1.append("Name:\n");
        sb1.append(this.name+"\n\n");
        sb1.append("Make:\n");
        sb1.append(this.make+"\n\n");
        sb1.append("Model:\n");
        sb1.append(this.model+"\n\n");
        sb1.append("TotalPrice:\n");
        sb1.append(getTotalPrice()+"\n\n");
        sb1.append("BasePrice:\n");
        sb1.append(this.basePrice+"\n\n");
        sb1.append("Choices:");
        System.out.println(sb1.toString());
        if (choices.isEmpty()) {
            System.out.print("Empty");
        }
        else {
            for (OptionSet.Option op : choices) {
                op.print();
            }
        }
        System.out.println("\nOptionSet:");
        for (OptionSet options: optset) {
            options.print();
        }
    }
}
