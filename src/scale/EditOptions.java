package scale;

import model.Automobile;
import java.util.LinkedHashMap;

public class EditOptions extends Thread {
    private LinkedHashMap<String, Automobile> automobileLinkedHashMap;
    private String modelName;
    private String optionsName;
    private String optionName;
    private int newOptionPrice;

    public EditOptions(LinkedHashMap<String, Automobile> automobileLinkedHashMap, String modelName, String optionsName, String optionName, int newOptionPrice) {
        this.automobileLinkedHashMap = automobileLinkedHashMap;
        this.modelName = modelName;
        this.optionsName = optionsName;
        this.optionName = optionName;
        this.newOptionPrice = newOptionPrice;
    }

    // The main run method that will be executed by the thread
    @Override
    public void run() {
        editOption();
    }

    private void editOption() {
        synchronized (automobileLinkedHashMap) {
            Automobile auto = automobileLinkedHashMap.get(modelName);
            if (auto != null) {
                auto.updateOptionPrice(optionsName, optionName, newOptionPrice);
            }
        }
    }
}