package model;

import java.io.Serializable;
import java.util.Arrays;

public class OptionSet implements Serializable {
    private String name;
    private Option[] options;

    public OptionSet(String name, int size) {
        this.name = name;
        this.options = new Option[size];
    }

    public String getName() {
        return name;
    }

    public String getOptionName(int i) {
        return options[i].getName();
    }

    public int getOptionPrice(int i) {
        return options[i].getPrice();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOption(int i, String name, int price) {
        options[i] = new Option(name, price);
    }

    public int findOption(String name) {
        for(int i = 0; i < options.length; ++i) {
            if (options[i].getName().equals(name)) {
                return i;
            }
        }

        return -1;
    }

    public void delOption(String name) {
        for(int i = 0; i < options.length; ++i) {
            if (options[i].getName().equals(name)) {
                options[i] = null;
                break;
            }
        }

    }

    public void updateOption(String name, int price) {
        for(int i = 0; i < this.options.length; ++i) {
            if (options[i].getName().equals(name)) {
                options[i] = new Option(name, price);
                break;
            }
        }

    }

    public String toString() {
        return "OptionSet{name='" + name + "', options=" + Arrays.toString(options) + "}";
    }

    class Option implements Serializable {
        private String name;
        private int price;

        private Option(String name, int price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public int getPrice() {
            return price;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String toString() {
            return "Option{name='" + name + "', price=" + price + "}";
        }
    }
}
