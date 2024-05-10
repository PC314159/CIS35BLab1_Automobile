package driver;

import adapter.BuildAuto;

public class Driver {
    public static void main(String[] args) {
//        AutomobileFileReader afr = new AutomobileFileReader();
//        Automobile FordZTW = afr.createAutomobileFromFile("FordZTW.txt");
//        System.out.println(FordZTW.toString());
//        FileIO.serializeAuto("auto.ser", FordZTW);
//        Automobile newFordZTW = FileIO.deserializeAuto("auto.ser");
//        System.out.println(FordZTW.toString());
        BuildAuto ba = new BuildAuto();
        System.out.println("Enter FordZTW when missing name");
        ba.BuildAuto("FordZTW.txt");

        System.out.println("Print ford before update");
        ba.printAuto("FordZTW");

        ba.updateOptionSetName("FordZTW", "Color", "Colors");
        ba.updateOptionPrice("FordZTW", "Colors", "Metallic", -10000);

        System.out.println("Print ford after update");
        ba.printAuto("FordZTW");

        ba.BuildAuto("LexusRx400.txt");

        System.out.println("Print lexus");
        ba.printAuto("LexusRx400");

        System.out.println("Prices of option selection default to 0 until a choice is made");
        System.out.println("Sets Lexus Transmission to Standard");
        ba.updateChoice("LexusRx400", "Transmission", "Standard");

        System.out.println("Print all auto");
        ba.printAllAuto();

    }
}
