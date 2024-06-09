package client;

import java.util.Scanner;

public class ClientApp {
    public static void main(String[] args) {
        CarModelOptionsIO clientIO = new CarModelOptionsIO();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Upload Properties File");
            System.out.println("2. Configure a Car");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter the path to the Properties file:");
                    String filePath = scanner.next();
                    clientIO.uploadPropertiesFile(filePath);
                    break;
                case 2:
                    clientIO.configureCar();
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
