package driver;

import adapter.*;
import model.Automobile;
import scale.EditOptions;
import util.AutomobileFileReader;
import util.FileIO;

public class Driver {
    public static void main(String[] args) {

        //part serialization testing
        AutomobileFileReader afr = new AutomobileFileReader();
        Automobile lex= afr.createAutomobileFromFile("LexusRx400.txt");
        System.out.println("before serialized");
        lex.print();
        System.out.println();
        System.out.println("after serialized");
        FileIO.serializeAuto("auto.ser", lex);
        Automobile newlex = FileIO.deserializeAuto("auto.ser");
        newlex.print();


        //part fix auto testing and update option testing
//        BuildAuto ba = new BuildAuto();
//        System.out.println("Enter FordZTW when missing name");
//        BuildAuto(ba,"FordZTWBroken.txt");
//
//        System.out.println("Print ford before update");
//        printAuto(ba,"FordZTW");
//
//        updateOptionSetName(ba,"FordZTW", "Color", "Colors");
//        updateOptionPrice(ba,"FordZTW", "Colors", "Metallic", -10000);
//
//        System.out.println("Print ford after update");
//        printAuto(ba,"FordZTW");


        //part update choice testing
//        BuildAuto ba = new BuildAuto();
//        BuildAuto(ba,"LexusRx400.txt");
//
//        System.out.println("Print lexus");
//        printAuto(ba,"LexusRx400");
//
//        System.out.println("Prices of option selection default to 0 until a choice is made");
//        System.out.println("Sets Lexus Transmission to Standard");
//        updateChoice(ba,"LexusRx400", "Transmission", "Standard");
//
//        System.out.println("Print all auto");
//        printAllAuto(ba);


        //part multithreading edit options testing
//        BuildAuto ba = new BuildAuto();
//        BuildAuto(ba,"LexusRx400.txt");
//
//        Thread t1 = new EditOptions(ba,"LexusRx400", "Color", "Fort Knox Gold Clearcoat Metallic", 1000);
//        Thread t2 = new EditOptions(ba,"LexusRx400", "Color", "Fort Knox Gold Clearcoat Metallic", 2000);;
//        t1.start();
//        t2.start();
//        try {
//            t1.join();
//            t2.join();
//        } catch (InterruptedException e) {
//
//        }
//        ba.printAuto("LexusRx400");
    }

    public static void BuildAuto(CreateAuto ca, String fileName) {
        ca.BuildAuto(fileName);
    }

    public  static void printAuto(CreateAuto ca, String modelName) {
        ca.printAuto(modelName);
    }

    public static void printAllAuto(CreateAuto ca) {
        ca.printAllAuto();
    }

    public static void updateOptionSetName(UpdateAuto ua, String modelName, String optionSetName, String newName) {
        ua.updateOptionSetName(modelName, optionSetName, newName);
    }
    public static void updateOptionPrice(UpdateAuto ua, String modelName, String optionsName, String optionName, int newPrice) {
        ua.updateOptionPrice(modelName, optionsName, optionName, newPrice);
    }

    public static void updateChoice(UpdateAuto ua, String modelName, String optionsName, String optionName) {
        ua.updateChoice(modelName, optionsName, optionName);
    }

    public static void fix(FixAuto fa, int errNo) {
        fa.fix(errNo);
    }
}
