package server;

import model.Automobile;
import util.FileIO;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Properties;

public class BuildCarModelOptions implements AutoServer {
    private LinkedHashMap<String, Automobile> autos = new LinkedHashMap<>();

    public void addAutomobile(String fileName) {
        Automobile auto = FileIO.parsePropertiesFile(fileName);
        autos.put(auto.getModel(), auto);
    }

    public ArrayList<String> getAvailableModels() {
        return new ArrayList<>(autos.keySet());
    }

    public Automobile getAutomobile(String modelName) {
        return autos.get(modelName);
    }
}

