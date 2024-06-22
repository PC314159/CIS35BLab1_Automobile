package server;

import adapter.ProxyAutomobile;
import model.Automobile;
import util.FileIO;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Properties;

public class BuildCarModelOptions extends ProxyAutomobile implements AutoServer {

    private static final int WAITING = 0;
    private static final int REQUEST_BUILD_AUTO = 1;
    private static final int REQUEST_CONFIG_AUTO = 2;
    private int state = WAITING;
    private boolean fromServlet;


    public BuildCarModelOptions() {
        fromServlet = false;
    }
    public BuildCarModelOptions(boolean fromServlet) {
        this.fromServlet = fromServlet;
    }


    public Object processRequest(Object obj) {
        Object toClient = null;

        if (state == REQUEST_BUILD_AUTO) {
            //add code to buildauto
            boolean added = false;
            System.out.println(obj);
            if (obj instanceof StringBuffer) {
                super.BuildAuto((StringBuffer) obj);
                added = true;
            }
            if (added) {
                toClient = "Automobile object successfully added to database\n"
                        + "Press any key to return to main menu";
            }
            else {
                toClient = "Add Failed. Press any key to return to main menu";
            }
        }
        else if (state == REQUEST_CONFIG_AUTO) {
            //add code for configureauto
//            System.out.println("choosingmodel");
            String modelName = (String) obj;
            toClient = super.getAutomobile(modelName);
        }
        else {

        }

        this.state = WAITING;

        return toClient;
    }

    public Object setRequest(int i) {
        Object output = null;

        if (i == 1) {
            this.state = REQUEST_BUILD_AUTO;
            output = "Upload a file to create an Automobile";
        }
        else if (i == 2) {
            this.state = REQUEST_CONFIG_AUTO;
            if (fromServlet) {
                ArrayList<String> models = super.getAvailableModels();
                String[] modelsArray = new String[models.size()];
                models.toArray(modelsArray);
                output = modelsArray;

            } else {
                output = "Select an Automobile from the following list to configure: \n" +
                        super.getAvailableModels();

            }
        }
        else {
            output = "Invalid request";
        }

        return output;
    }

}

