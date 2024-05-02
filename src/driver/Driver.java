package driver;

import adapter.BuildAuto;
import exception.AutoException;
import model.Automobile;
import util.AutomobileFileReader;
import util.FileIO;

import java.awt.*;

public class Driver {
    public static void main(String[] args) {
//        AutomobileFileReader afr = new AutomobileFileReader();
//        Automobile FordZTW = afr.createAutomobileFromFile("FordZTW.txt");
//        System.out.println(FordZTW.toString());
//        FileIO.serializeAuto("auto.ser", FordZTW);
//        Automobile newFordZTW = FileIO.deserializeAuto("auto.ser");
//        System.out.println(FordZTW.toString());
        BuildAuto ba = new BuildAuto();
        ba.BuildAuto("FordZTW.txt");
        ba.printAuto("FordZTW");
        ba.updateOptionSetName("FordZTW", "Color", "Colors");
        ba.updateOptionPrice("FordZTW", "Colors", "Metallic", -10000);
        ba.printAuto("FordZTW");
    }
}
