package scale;

import adapter.EditThread;
import adapter.UpdateAuto;
import model.Automobile;
import java.util.LinkedHashMap;

public class EditOptions extends Thread {
    private EditThread et;
    private String modelName;
    private String optionsName;
    private String optionName;
    private int newOptionPrice;

    public EditOptions(EditThread et, String modelName, String optionsName, String optionName, int newOptionPrice) {
        this.et = et;
        this.modelName = modelName;
        this.optionsName = optionsName;
        this.optionName = optionName;
        this.newOptionPrice = newOptionPrice;
    }

    // The main run method that will be executed by the thread
    @Override
    public void run() {
        editOptionPrice();
    }

    private synchronized void editOptionPrice() {
        et.updateOptionPrice(modelName, optionsName, optionName, newOptionPrice);
    }
}