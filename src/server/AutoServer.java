package server;

import model.Automobile;

import java.util.ArrayList;
import java.util.Properties;

public interface AutoServer {
    void addAutomobile(String fileName);
    ArrayList<String> getAvailableModels();
    Automobile getAutomobile(String modelName);
}
