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

    protected String getName() {
        return name;
    }

    protected String getOptionName(int i) {
        return options[i].getName();
    }

    protected int getOptionPrice(int i) {
        return options[i].getPrice();
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected void setOption(int i, String name, int price) {
        options[i] = new Option(name, price);
    }

    protected int findOption(String name) {
        for(int i = 0; i < options.length; ++i) {
            if (options[i].getName().equals(name)) {
                return i;
            }
        }

        return -1;
    }

    protected void delOption(String name) {
        for(int i = 0; i < options.length; ++i) {
            if (options[i].getName().equals(name)) {
                options[i] = null;
                break;
            }
        }

    }

    protected void updateOption(String name, int price) {
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

        public String toString() {
            return "Option{name='" + name + "', price=" + price + "}";
        }
    }
}
