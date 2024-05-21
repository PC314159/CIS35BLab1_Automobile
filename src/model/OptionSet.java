package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class OptionSet implements Serializable {
    private String name;
    private ArrayList<Option> options;
    private int choice;

    protected OptionSet(String name) {
        this.name = name;
        this.options = new ArrayList<Option>();
        this.choice = -1;
    }

    protected String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected int getOptionPrice(String name) {
        for (int i = 0; i < options.size(); ++i) {
            if (options.get(i).getName().equals(name)) {
                return options.get(i).getPrice();
            }
        }
        return 0;
    }

    protected void setOption(int i, String name, int price) {
        options.set(i, new Option(name, price));
    }

    protected void addOption(String name, int price) {
        options.add(new Option(name, price));
    }

    protected int findOption(String name) {
        for (int i = 0; i < options.size(); ++i) {
            if (options.get(i).getName().equals(name)) {
                return i;
            }
        }

        return -1;
    }

    protected void delOption(String name) {
        for (int i = 0; i < options.size(); ++i) {
            if (options.get(i).getName().equals(name)) {
                options.remove(i);
                break;
            }
        }

    }

    protected void updateOptionName(String name, String newName) {
        for (int i = 0; i < this.options.size(); ++i) {
            if (options.get(i).getName().equals(name)) {
                options.get(i).setName(newName);
                break;
            }
        }
    }

    protected void updateOptionPrice(String name, int price) {
        for (int i = 0; i < this.options.size(); ++i) {
            if (options.get(i).getName().equals(name)) {
                options.get(i).setPrice(price);
                break;
            }
        }
    }

    protected Option getChoice() {
        if (choice >= 0 && choice < options.size()) {
            return options.get(choice);
        }
        return null;
    }
    protected String getChoiceName() {
        if (choice >= 0 && choice < options.size()) {
            return options.get(choice).getName();
        }
        return "";
    }

    protected int getChoicePrice() {
        if (choice >= 0 && choice < options.size()) {
            return options.get(choice).getPrice();
        }
        return 0;
    }

    protected Option setChoice(String name) {
        for (int i = 0; i < this.options.size(); ++i) {
            if (options.get(i).getName().equals(name)) {
                choice = i;
                return options.get(i);
            }
        }
        return null;
    }

    protected void print() {
        StringBuffer sb1 = new StringBuffer();
        sb1.append("--------------------");
        sb1.append("OptionSet:\n");
        sb1.append("Name:\n");
        sb1.append(this.name+"\n\n");
        sb1.append("Choice:");
        System.out.println(sb1.toString());
        if (getChoice() != null) {
            getChoice().print();
        }
        else {
            System.out.println("Empty\n");
        }
        System.out.println("Options:");
        for (Option option : this.options) {
            option.print();
        }
    }

    class Option implements Serializable {
        private String name;
        private int price;

        protected Option(String name, int price) {
            this.name = name;
            this.price = price;
        }

        protected String getName() {
            return name;
        }

        protected int getPrice() {
            return price;
        }

        protected void setName(String name) {
            this.name = name;
        }

        protected void setPrice(int price) {
            this.price = price;
        }

        protected void print() {
            StringBuffer sb = new StringBuffer();
            sb.append("----------");
            sb.append("Option:\n");
            sb.append("Name:\n");
            sb.append(this.name+"\n\n");
            sb.append("Price:\n");
            sb.append(this.price+"\n\n");
            System.out.print(sb.toString());
        }
    }
}
