package driver;

import model.Automobile;
import util.AutomobileFileReader;
import util.FileIO;

public class Driver {
    public static void main(String[] args) {
        AutomobileFileReader afr = new AutomobileFileReader();
        Automobile FordZTW = afr.createAutomobileFromFile("FordZTW.txt");
        System.out.println(FordZTW.toString());
        FileIO.serializeAuto("auto.ser", FordZTW);
        Automobile newFordZTW = FileIO.deserializeAuto("auto.ser");
        System.out.println(FordZTW.toString());
    }
}
