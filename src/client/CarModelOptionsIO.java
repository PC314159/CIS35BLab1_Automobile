package client;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Properties;

public class CarModelOptionsIO {
    private String serverAddress = "localhost";
    private int serverPort = 4444;

    public void uploadPropertiesFile(String filePath) {
        try (Socket socket = new Socket(serverAddress, serverPort);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            Properties props = new Properties();
            props.load(new FileInputStream(filePath));
            out.writeObject(props);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void configureCar() {
        try (Socket socket = new Socket(serverAddress, serverPort);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            out.writeObject("GET_MODELS");
            ArrayList<String> models = (ArrayList<String>) in.readObject();


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
