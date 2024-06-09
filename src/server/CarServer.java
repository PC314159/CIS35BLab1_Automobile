package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class CarServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(4444)) {
            System.out.println("Server is listening on port 4444");
            while (true) {
                Socket socket = serverSocket.accept();
                new DefaultSocketClient(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
